package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class CrewmanEntity {
    @Id
    @GeneratedValue
    private Integer crewmanId;

    @OneToOne
    @JoinColumn(name = "UserId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "VesselId")
    private VesselEntity vessel;


    //    getters and setters
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
