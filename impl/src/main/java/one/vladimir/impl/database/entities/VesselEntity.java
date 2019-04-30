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

    private Integer capacity;

    private Integer currentLoad;

    @OneToMany(mappedBy = "vessel", cascade = CascadeType.ALL)
    private Collection<CrewmanEntity> crewmans;

    @OneToOne(mappedBy = "vessel", cascade = CascadeType.ALL)
    private RouteEntity route;


    //    getters and setters

    //POJO setter
    public void setVessel(Vessel vessel) {
        this.vesselId = vessel.getId();
        this.name = vessel.getName();
        this.currentLoad = vessel.getCurrentLoad();
        this.capacity = vessel.getCapacity();
    }

    //POJO getter
    public Vessel getVessel() {
        Vessel vessel = new Vessel();
        vessel.setId(this.getVesselId());
        vessel.setName(this.getName());
        vessel.setCapacity(this.getCapacity());
        vessel.setCurrentLoad(this.getCurrentLoad());
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(Integer currentLoad) {
        this.currentLoad = currentLoad;
    }
}
