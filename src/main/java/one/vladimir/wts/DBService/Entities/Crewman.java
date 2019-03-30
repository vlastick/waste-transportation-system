package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class Crewman {
    @Id
    @GeneratedValue
    private Integer crewmanId;

    @OneToOne
    @JoinColumn(name = "UserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "VesselId")
    private Vessel vessel;

    public Integer getCrewmanId() {
        return crewmanId;
    }

    public void setCrewmanId(Integer crewmanId) {
        this.crewmanId = crewmanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}
