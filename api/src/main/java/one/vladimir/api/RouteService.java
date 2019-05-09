package one.vladimir.api;

import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.RouteFilter;
import java.util.List;

public interface RouteService {

    String addRoute(Route route);

    String updateRoute(Route route);

    Route getRoute(Integer id);

    Route createRoute(Integer vesselId);

    List<Route> getRoutesByFilter(RouteFilter routeFilter);

    Route buildroute(Integer vesselID);

    String updateRoutePointStatus(Integer routePointId, Integer vesselId, RoutePointStatus status);

    Route finishRoute(Integer vesselId);

}
