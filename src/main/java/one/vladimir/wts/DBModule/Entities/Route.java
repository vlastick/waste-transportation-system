package one.vladimir.wts.DBModule.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Route {
    @Id
    @GeneratedValue
    private Integer routeId;

    @OneToOne
    @JoinColumn(name = "VesselId")
    Vessel vessel;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private Collection<RoutePoint> routePoints;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}
