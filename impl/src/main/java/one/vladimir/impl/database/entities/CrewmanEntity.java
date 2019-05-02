package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Crewman;

import javax.persistence.*;

@Entity
public class CrewmanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewmanId;

    @OneToOne
    @JoinColumn(name = "UserId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "VesselId")
    private VesselEntity vessel;


    //    getters and setters

    //POJO setter
    public void setCrewman(Crewman crewman) {
        this.setCrewmanId(crewman.getId());
    }

    //POJO getter
    public Crewman getCrewman() {
        Crewman crewman = new Crewman();
        crewman.setId(this.getCrewmanId());
        return crewman;
    }

    public Integer getCrewmanId() {
        return crewmanId;
    }

    public void setCrewmanId(Integer crewmanId) {
        this.crewmanId = crewmanId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public VesselEntity getVessel() {
        return vessel;
    }

    public void setVessel(VesselEntity vessel) {
        this.vessel = vessel;
    }
}
