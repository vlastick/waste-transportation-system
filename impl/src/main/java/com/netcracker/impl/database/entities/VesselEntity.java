package com.netcracker.impl.database.entities;

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
