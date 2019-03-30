package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class RoutePoint {
    @Id
    @GeneratedValue
    private Integer routePointId;

    private Integer Koef;

    @ManyToOne
    @JoinColumn(name = "PointId")
    private Point point;

    @ManyToOne
    @JoinColumn(name = "RouteId")
    private Route route;

    public Integer getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Integer routePointId) {
        this.routePointId = routePointId;
    }

    public Integer getKoef() {
        return Koef;
    }

    public void setKoef(Integer koef) {
        Koef = koef;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
