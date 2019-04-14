package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Vessel;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class VesselEntity {
    @Id
    @GeneratedValue
    private Integer vesselId;

    private String name;

    @OneToMany(mappedBy = "vessel", cascade = CascadeType.ALL)
    private Collection<CrewmanEntity> crewmans;

    @OneToOne(mappedBy = "vessel", cascade = CascadeType.ALL)
    private RouteEntity route;


    //    getters and setters

    //POJO setter
    public void setVessel(Vessel vessel) {
        this.vesselId = vessel.getId();
        this.name = vessel.getName();
    }

    //POJO getter
    public Vessel getVessel() {
        Vessel vessel = new Vessel();
        vessel.setId(this.getVesselId());
        vessel.setName(this.getName());
        return vessel;
    }

    public Integer getVesselId() {
        return vesselId;
    }

    public void setVesselId(Integer vesselId) {
        this.vesselId = vesselId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
