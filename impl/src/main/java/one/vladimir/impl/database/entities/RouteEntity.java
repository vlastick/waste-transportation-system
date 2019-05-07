package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Route;
import one.vladimir.api.enums.RouteStatus;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "VesselId")
    VesselEntity vessel;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<RoutePointEntity> routePoints;


    //    getters and setters

    //POJO setter
    public void setRoute(Route route) {
        this.setRouteId(route.getId());
        this.setStatus(route.getStatus().toString());
    }

    //POJO getter
    public Route getRoute() {
        Route route = new Route();
        route.setId(this.getRouteId());
        route.setStatus(RouteStatus.valueOf(this.getStatus()));
        return route;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public VesselEntity getVessel() {
        return vessel;
    }

    public void setVessel(VesselEntity vessel) {
        this.vessel = vessel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
