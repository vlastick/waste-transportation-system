package one.vladimir.api;

import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.Vessel;

public interface RouteService {

    public String addRoute(Route route);

    public String updateRoute(Route route);

    public Route getRoute(Integer id);

    public Route createRoute(Integer vesselId);

}
