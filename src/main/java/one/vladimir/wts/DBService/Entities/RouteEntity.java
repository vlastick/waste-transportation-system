package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RouteEntity {
    @Id
    @GeneratedValue
    private Integer routeId;

    @OneToOne
    @JoinColumn(name = "VesselId")
    VesselEntity vessel;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Collection<RoutePointEntity> routePoints;


    //    getters and setters
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
}
