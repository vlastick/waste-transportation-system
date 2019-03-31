package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer userId;

    private String role;

    private String login;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Collection<PointEntity> points;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CrewmanEntity crewmans;


    //    getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
