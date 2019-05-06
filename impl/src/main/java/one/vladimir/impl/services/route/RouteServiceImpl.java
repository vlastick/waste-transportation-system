package one.vladimir.impl.services.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import one.vladimir.api.*;

import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.enums.RouteStatus;
import one.vladimir.api.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.logging.Filter;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Autowired
    @Qualifier("database")
    private Database db;

    @Autowired
    @Qualifier("pointService")
    private PointService pointService;

    @Autowired
    @Qualifier("transportService")
    private TransportService transportService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @PostConstruct
    public void postConstructLog() {
        /*ObjectMapper mapper = new ObjectMapper();
        String testJSON = null;
        try {
            testJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildroute(1));
            System.out.println(testJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.buildroute(1);*/
        //System.out.println(this.updateRoutePointStatus(1, 1, RoutePointStatus.CANCELED));
    }

    private Integer numberOfRoutePoints = 2;

    @Override
    public String addRoute(Route route) {
        return "Route added";
    }

    @Override
    public String updateRoute(Route route) {
        return "Route updated";
    }

    @Override
    public Route getRoute(Integer id) {
        return new Route();
    }


    @Override
    public Route createRoute(Integer vesselId) {
        Route route = new Route();
        Vessel vessel = new Vessel();
        route.setStatus(RouteStatus.IN_PROGRESS);
        vessel.setId(vesselId);
        route.setId(db.addRoute(route, vessel));
        List<Dump> dumps = db.getAllDumps();
        Set<RoutePoint> routePoints = new HashSet<RoutePoint>();
        Integer counter = 1;
        RoutePoint currRoutePoint;

        for (Dump dump : dumps) {
            currRoutePoint = new RoutePoint();
            currRoutePoint.setStatus(RoutePointStatus.AWAITING);
            currRoutePoint.setNumber(counter);
            currRoutePoint.setContainedPoint(dump);
            currRoutePoint.setId(db.addRoutePoint(currRoutePoint, route));
            routePoints.add(currRoutePoint);
            counter++;
        }
        route.setRoutePoints(routePoints);
        return route;
    }

    public List<Route> getRoutesByFilter(RouteFilter routeFilter) {
        List<Route> routes;
        routes = db.getRoutesByFilter(routeFilter);
        return routes;
    }


    @Override
    public Route buildroute(Integer vesselId) {

        RouteFilter filter = new RouteFilter();

        List<Integer> vesselIdList = new Vector<>();
        vesselIdList.add(vesselId);

        List<String> statusList = new Vector<>();
        statusList.add(RouteStatus.IN_PROGRESS.toString());

        filter.setVesselIdList(vesselIdList);
        filter.setRouteStatusList(statusList);

        List<Route> routes;
        routes = this.getRoutesByFilter(filter);

        Route currentRoute;
        if (routes.size() > 1) {
            throw new IllegalArgumentException("There are more than one current route. Something was wrong");
        }
        if (routes.size() == 0) {
            currentRoute = new Route();
            currentRoute.setStatus(RouteStatus.IN_PROGRESS);
            currentRoute.setRoutePoints(new HashSet<>());
            Vessel vessel = new Vessel();
            vessel.setId(vesselId);
            currentRoute.setId(db.addRoute(currentRoute, vessel));
        } else {
            currentRoute = routes.get(0);
        }

        Integer plannedLoad = 0;
        Integer lastPointNumber = currentRoute.getRoutePoints().size();

        //RoutePoints of currents vessel with statuses AWAITING and IN_PROGRESS
        List<RoutePoint> activeRoutePoints = new Vector<>();
        try {
            for (RoutePoint routePoint : currentRoute.getRoutePoints()) {
                if (routePoint.getStatus() == RoutePointStatus.IN_PROGRESS
                        || routePoint.getStatus() == RoutePointStatus.AWAITING) {
                    activeRoutePoints.add(routePoint);
                    DumpFilter dumpFilter = new DumpFilter();
                    List<Integer> dumpIds = new Vector<>();
                    dumpIds.add(routePoint.getContainedPoint().getPointId());
                    dumpFilter.setPointIdList(dumpIds);
                    plannedLoad += db.getDumpsByFilter(dumpFilter).get(0).getSize();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return currentRoute;
        }

        if (activeRoutePoints.size() < numberOfRoutePoints) {
            // Get all avaliable dumps
            DumpFilter dumpFilter = new DumpFilter();
            dumpFilter.setActive(true);
            Vessel vessel = transportService.getVessel(vesselId);

            Double latitude = vessel.getLatitude();
            Double longitude = vessel.getLongitude();

            dumpFilter.setMaxSize(vessel.getCapacity() - vessel.getCurrentLoad() - plannedLoad);
            List<Integer> groupIdList = new Vector<>();
            groupIdList.add(db.getGroupByCoordinates(latitude, longitude).getId());

            dumpFilter.setGroupidList(groupIdList); // TODO - write group correctly

            List<Dump> avaliableDumps = pointService.getDumpsByFilter(dumpFilter);
            List<Dump> usedDumps = new Vector<>();
            for (Dump dump : avaliableDumps) {
                if (this.routePointWithPointIdAlreadyExists(dump.getPointId())) {
                    usedDumps.add(dump);
                }
            }
            for (Dump dump : usedDumps) {
                avaliableDumps.remove(dump);
            }
            if (avaliableDumps.size() == 0) {
                throw new NoSuchElementException("No dumps available");
            }

            if (activeRoutePoints.size() != 0) {
                for (RoutePoint currentPoint : activeRoutePoints) {
                    if (currentPoint.getNumber() == lastPointNumber) {
                        latitude = currentPoint.getContainedPoint().getLatitude();
                        longitude = currentPoint.getContainedPoint().getLongitude();
                    }
                }
            }

            List<RoutePoint> newRoutePoints = new Vector<>();
            Boolean noMoreAvailableDumps = false;
            while (activeRoutePoints.size() + newRoutePoints.size() < numberOfRoutePoints
                    && avaliableDumps.size() != 0
                    && noMoreAvailableDumps == false) {

                noMoreAvailableDumps = true;
                if (newRoutePoints.size() != 0) {
                    for (RoutePoint currentPoint : newRoutePoints) {
                        if (lastPointNumber < currentPoint.getNumber()) {
                            latitude = currentPoint.getContainedPoint().getLatitude();
                            longitude = currentPoint.getContainedPoint().getLongitude();
                            lastPointNumber = currentPoint.getNumber();
                        }
                    }
                }
                RoutePoint routePoint = new RoutePoint();
                routePoint.setStatus(RoutePointStatus.AWAITING);
                routePoint.setNumber(lastPointNumber + 1);

                Dump nearestDump = avaliableDumps.get(0);

                for (Dump candidateDump : avaliableDumps) {
                    Double currentDistance =
                            Math.sqrt(
                                    Math.pow(nearestDump.getLatitude() - latitude, 2) +
                                            Math.pow(nearestDump.getLongitude() - longitude, 2)
                            );
                    Double candidateDistance =
                            Math.sqrt(
                                    Math.pow(candidateDump.getLatitude() - latitude, 2) +
                                            Math.pow(candidateDump.getLongitude() - longitude, 2)
                            );
                    if (candidateDistance <= currentDistance && candidateDump.getSize() <= (vessel.getCapacity() - vessel.getCurrentLoad() - plannedLoad)) {
                        nearestDump = candidateDump;
                        noMoreAvailableDumps = false;
                    }
                }
                if (noMoreAvailableDumps) {
                    break;
                }
                routePoint.setContainedPoint(nearestDump);
                plannedLoad += nearestDump.getSize();
                newRoutePoints.add(routePoint);
                avaliableDumps.remove(nearestDump);
            }

            for (RoutePoint routePoint : newRoutePoints) {
                currentRoute.addRoutePoint(routePoint);
                db.addRoutePoint(routePoint, currentRoute);
            }

        }


        return currentRoute;
    }

    public String updateRoutePointStatus(Integer routePointId, Integer vesselId, RoutePointStatus status) {
        RoutePoint routePoint = db.getRoutePointById(routePointId);
        Vessel vessel = transportService.getVessel(vesselId);

        if (vessel.getCurrRoute() == null) {
            return "no current route";
        }
        if (vessel.getCurrRoute().getRoutePoints() == null) {
            return "current route is empty";
        }
        Boolean routePointCorrect = false;
        for (RoutePoint currRoutePoint : vessel.getCurrRoute().getRoutePoints()) {
            if (currRoutePoint.getId() == routePoint.getId()) {
                routePointCorrect = true;
            }
        }
        if (!routePointCorrect) {
            return "RoutePoint is not included in current Route";
        }
        if (routePoint.getStatus() == RoutePointStatus.CANCELED
                || routePoint.getStatus() == RoutePointStatus.COMPLETED) {
            return "This RoutePoint can't be updated";
        }
        if (status == RoutePointStatus.COMPLETED) {
            transportService.updateCoordinates(vesselId, routePoint.getContainedPoint().getLatitude(), routePoint.getContainedPoint().getLongitude());
            routePoint.setStatus(status);
        } else if (status == RoutePointStatus.CANCELED) {
            routePoint.setStatus(status);
        } else {
            return "RoutePoint can't be updated";
        }
        db.updateRoutePoint(routePoint);

        BaseFilter baseFilter = new BaseFilter();
        List<Integer> pointIds = new Vector<>();
        pointIds.add(routePoint.getContainedPoint().getPointId());
        baseFilter.setPointIdList(pointIds);

        //In case Route is closing
        if ((routePoint.getStatus() == RoutePointStatus.AWAITING
                || routePoint.getStatus() == RoutePointStatus.IN_PROGRESS)
                && pointService.getBasesByFilter(baseFilter).size() == 1
                && status == RoutePointStatus.COMPLETED) {
            Route currRoute = vessel.getCurrRoute();
            currRoute.setStatus(RouteStatus.COMPLETED);
            db.updateRoute(currRoute, vessel);
        }
        if ((routePoint.getStatus() == RoutePointStatus.AWAITING
                || routePoint.getStatus() == RoutePointStatus.IN_PROGRESS)
                && pointService.getBasesByFilter(baseFilter).size() == 0
                && status == RoutePointStatus.COMPLETED) {
            Point point = routePoint.getContainedPoint();
            point.setActive(false);
            db.updatePoint(point, userService.getAuthenticatedUser());
        }
        return "RoutePoint updated";
    }

    public Route finishRoute(Integer vesselId) {
        RouteFilter filter = new RouteFilter();

        List<Integer> vesselIdList = new Vector<>();
        vesselIdList.add(vesselId);

        List<String> statusList = new Vector<>();
        statusList.add(RouteStatus.IN_PROGRESS.toString());

        filter.setVesselIdList(vesselIdList);
        filter.setRouteStatusList(statusList);

        List<Route> routes;
        routes = this.getRoutesByFilter(filter);

        Route currentRoute;
        if (routes.size() != 1) {
            throw new IllegalArgumentException("Current route not found");
        } else {
            currentRoute = routes.get(0);
        }

        Vessel vessel = transportService.getVessel(vesselId);

        Double longitude = vessel.getLongitude();
        Double latitude = vessel.getLatitude();
        Integer lastNumber = currentRoute.getRoutePoints().size();

        Integer currNumber = 0;
        for (RoutePoint routePoint : currentRoute.getRoutePoints()) {
            if (routePoint.getStatus() == RoutePointStatus.IN_PROGRESS
                    || routePoint.getStatus() == RoutePointStatus.AWAITING) {
                throw new IllegalArgumentException("Not all routePoints are completed or canceled");
            }
        }

        BaseFilter baseFilter = new BaseFilter();
        baseFilter.setActive(true);
        List<Integer> groupIdList = new Vector<>();
        groupIdList.add(db.getGroupByCoordinates(latitude, longitude).getId());

        baseFilter.setGroupidList(groupIdList);
        List<Base> availableBases = db.getBasesByFilter(baseFilter);
        if (availableBases.size() == 0) {
            throw new IllegalArgumentException("No bases available");
        }

        Base nearestBase = availableBases.get(0);

        for (Base candidateBase : availableBases) {
            Double currentDistance =
                    Math.sqrt(
                            Math.pow(nearestBase.getLatitude() - latitude, 2) +
                                    Math.pow(nearestBase.getLongitude() - longitude, 2)
                    );
            Double candidateDistance =
                    Math.sqrt(
                            Math.pow(candidateBase.getLatitude() - latitude, 2) +
                                    Math.pow(candidateBase.getLongitude() - longitude, 2)
                    );
            if (candidateDistance < currentDistance) {
                nearestBase = candidateBase;
            }
        }

        RoutePoint routePoint = new RoutePoint();
        routePoint.setStatus(RoutePointStatus.AWAITING);
        routePoint.setNumber(lastNumber + 1);
        routePoint.setContainedPoint(nearestBase);
        currentRoute.addRoutePoint(routePoint);
        db.addRoutePoint(routePoint, currentRoute);
        return currentRoute;
    }

    private boolean routePointWithPointIdAlreadyExists(Integer pointId) {
        List<RoutePoint> routePoints = db.getRoutePointsByPointId(pointId);
        for (RoutePoint routePoint : routePoints) {
            if (routePoint.getStatus() == RoutePointStatus.AWAITING ||
                    routePoint.getStatus() == RoutePointStatus.IN_PROGRESS) {
                return true;
            }
        }
        return false;
    }


}
