package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Vessel {
    @Id
    @GeneratedValue
    private Integer vesselId;

    private String name;

    @OneToMany(mappedBy = "vessel", cascade = CascadeType.ALL)
    private Collection<Crewman> crewmans;

    @OneToOne(mappedBy = "vessel", cascade = CascadeType.ALL)
    private Route route;

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
