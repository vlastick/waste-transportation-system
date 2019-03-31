package one.vladimir.wts.BusinessLogic.POJO;

import java.util.List;
import java.util.Set;

public class User {

    private Integer id;

    private String login;

    private String role;

    private String password;

    private String email;

    private List<Point> createdPoints;


    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Point> getCreatedPoints() {
        return createdPoints;
    }

    public void setCreatedPoints(List<Point> createdPoints) {
        this.createdPoints = createdPoints;
    }
}
