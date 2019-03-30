package one.vladimir.wts.BusinessLogic.POJO;

import java.util.Set;

public class User {
    private String login;

    private String role;

    private String password;

    private Set<Point> createdPoints;


    //    getters and setters
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

    public Set<Point> getCreatedPoints() {
        return createdPoints;
    }

    public void setCreatedPoints(Set<Point> createdPoints) {
        this.createdPoints = createdPoints;
    }
}
