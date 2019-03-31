package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class RoutePointEntity {
    @Id
    @GeneratedValue
    private Integer routePointId;

    private Integer Koef;

    @ManyToOne
    @JoinColumn(name = "PointId")
    private PointEntity point;

    @ManyToOne
    @JoinColumn(name = "RouteId")
    private RouteEntity route;


    //    getters and setters
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

    public PointEntity getPoint() {
        return point;
    }

    public void setPoint(PointEntity point) {
        this.point = point;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }
}
