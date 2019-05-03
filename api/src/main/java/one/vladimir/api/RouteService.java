package one.vladimir.api;

import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.RouteFilter;
import one.vladimir.api.pojo.Vessel;

import java.util.List;

public interface RouteService {

    public String addRoute(Route route);

    public String updateRoute(Route route);

    public Route getRoute(Integer id);

    public Route createRoute(Integer vesselId);

    public List<Route> getRoutesByFilter(RouteFilter routeFilter);

    public Route buildroute(Integer vesselID);

    public String updateRoutePointStatus(Integer routePointId, Integer vesselId, RoutePointStatus status);

}
