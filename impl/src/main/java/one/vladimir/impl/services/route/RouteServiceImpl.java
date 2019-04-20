package one.vladimir.impl.services.route;

import one.vladimir.api.Database;
import one.vladimir.api.RouteService;

import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.enums.RouteStatus;
import one.vladimir.api.pojo.Dump;
import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.RoutePoint;
import one.vladimir.api.pojo.Vessel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @Autowired
    @Qualifier("database")
    private Database db;

    @PostConstruct
    public void postConstructLog(){
        System.out.println("routeService initialized");
    }

    @Override
    public String addRoute(Route route){
        return "Route added";
    }

    @Override
    public String updateRoute(Route route){
        return "Route updated";
    }

    @Override
    public Route getRoute(Integer id){
        return new Route();
    }

    @Override
    public Route createRoute(Integer vesselId){
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

}
