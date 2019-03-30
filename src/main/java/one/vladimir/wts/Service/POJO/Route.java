package one.vladimir.wts.Service.POJO;

import java.util.Set;

public class Route {

    private Set<RoutePoint> routePoints;

    private RouteStatus status;


    //    getters and setters
    public Set<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(Set<RoutePoint> routePoints) {
        this.routePoints = routePoints;
    }

    public RouteStatus getStatus() {
        return status;
    }

    public void setStatus(RouteStatus status) {
        this.status = status;
    }
}
