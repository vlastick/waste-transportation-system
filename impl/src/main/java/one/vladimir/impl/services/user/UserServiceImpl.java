package one.vladimir.impl.services.user;

import one.vladimir.api.Database;
import one.vladimir.api.UserService;
import one.vladimir.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.NoSuchElementException;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("database")
    private Database db;

    @PostConstruct
    public void postConstructLog(){
        System.out.println("userService initialized");
    }

    @Override
    public User getUser(){
        User user = new User();
        user.setUserId(1);
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
