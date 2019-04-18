package one.vladimir.api;

import one.vladimir.api.pojo.Route;

public interface RouteService {

    public String addRoute(Route route);

    public String updateRoute(Route route);

    public Route getRoute(String strId);

}
