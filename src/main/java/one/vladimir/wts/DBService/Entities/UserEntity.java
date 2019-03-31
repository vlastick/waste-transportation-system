package one.vladimir.wts.DBService.Entities;

import one.vladimir.wts.BusinessLogic.POJO.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Integer userId;

    private String role;

    private String login;

    private String password;

    private String email;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private Collection<PointEntity> points;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CrewmanEntity crewmans;

    //constructors

    //default constructor
    public UserEntity(){

    }

    //POJO constructor
    public UserEntity(User user){
        this.role = user.getRole();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }


    //    getters and setters

    //POJO getter
    public User getUser(){
        User user = new User();
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setRole(this.role);
        user.setEmail(this.email);
        return user;
    }

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
}
