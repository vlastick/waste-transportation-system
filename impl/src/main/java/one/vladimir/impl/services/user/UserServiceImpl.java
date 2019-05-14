package one.vladimir.impl.services.user;

import one.vladimir.api.DatabaseService;
import one.vladimir.api.UserService;
import one.vladimir.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("database")
    private DatabaseService db;

    @PostConstruct
    public void postConstructLog(){
        System.out.println("userService initialized");
    }

    @Override
    public User getAuthenticatedUser(){
        User user;
        String login;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            login = ((UserDetails)principal).getUsername();
        } else {
            login = principal.toString();
        }
        user = db.getUserByLogin(login);
        return user;
    }

    @Override
    public User getUser(Integer id){
        User user = db.getUserById(id);
        return user;
    }

    @Override
    public String addUser(User user){
        db.addUser(user);
        return "User added";
    }

    @Override
    public String updateUser(User user){
        db.updateUser(user);
        return "User updated";
    }


}
