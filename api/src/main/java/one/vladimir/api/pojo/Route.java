package one.vladimir.api.pojo;

import one.vladimir.api.enums.RouteStatus;

import java.util.Set;

public class Route {

    private Integer id;

    private Set<RoutePoint> routePoints;

    private RouteStatus status;


    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void addRoutePoint(RoutePoint routePoint) {
        this.routePoints.add(routePoint);
    }
}
