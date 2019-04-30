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

    @PostConstruct
    public void postConstructLog() {

        ObjectMapper mapper = new ObjectMapper();
        String testJSON = null;
        try {
            testJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.buildroute(4));
            System.out.println(testJSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
    public Route buildroute(Integer vesselID) {

        RouteFilter filter = new RouteFilter();

        List<Integer> vesselIdList = new Vector<>();
        vesselIdList.add(vesselID);

        List<String> statusList = new Vector<>();
        statusList.add(RouteStatus.IN_PROGRESS.toString());

        filter.setVesselIdList(vesselIdList);
        filter.setRouteStatusList(statusList);

        List<Route> routes;
        routes = this.getRoutesByFilter(filter);

        Route currentRoute;
        if (routes.size() > 1) {
            throw new IllegalArgumentException("There more than one route. Something was wrong");
        }
        if (routes.size() == 0) {
            currentRoute = new Route();
            currentRoute.setStatus(RouteStatus.IN_PROGRESS);
        } else {
            currentRoute = routes.get(0);
        }

        if (currentRoute.getRoutePoints().size() < numberOfRoutePoints) {
            // Get all avaliable dumps
            DumpFilter dumpFilter = new DumpFilter();
            dumpFilter.setActive(true);

            List<Integer> groupIdList = new Vector<>();
            vesselIdList.add(2);

            dumpFilter.setGroupidList(groupIdList); // TODO - write group correctly

            List<Dump> avaliableDumps = pointService.getDumpsByFilter(dumpFilter);

            // get current coordinate
            Vessel vessel = transportService.getVessel(vesselID);

            Double latitude = vessel.getLatitude();
            Double longitude = vessel.getLongitude();

            while (currentRoute.getRoutePoints().size() < numberOfRoutePoints && avaliableDumps.size() != 0) {
                Integer lastPointNumber = 0;
                if(currentRoute.getRoutePoints().size() != 0){
                    for(RoutePoint currentPoint:currentRoute.getRoutePoints()){
                        if(lastPointNumber < currentPoint.getNumber()){
                            latitude = currentPoint.getContainedPoint().getLatitude();
                            longitude = currentPoint.getContainedPoint().getLongitude();
                        }
                    }
                }
                RoutePoint routePoint = new RoutePoint();
                routePoint.setStatus(RoutePointStatus.AWAITING);
                routePoint.setNumber(lastPointNumber + 1);

                Dump nearestDump = avaliableDumps.get(0);

                for(Dump candidateDump:avaliableDumps){
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
                    if(candidateDistance < currentDistance){
                        nearestDump = candidateDump;
                    }
                }

                routePoint.setContainedPoint(nearestDump);
                currentRoute.getRoutePoints().add(routePoint);
            }

        }


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
