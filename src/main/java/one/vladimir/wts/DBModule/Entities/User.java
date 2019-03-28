package one.vladimir.wts.DBModule.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer UserId;

    private String Role;

    private String Login;

    @OneToMany(mappedBy = "Creator", cascade = CascadeType.ALL)
    private Collection<Point> points;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }
}
