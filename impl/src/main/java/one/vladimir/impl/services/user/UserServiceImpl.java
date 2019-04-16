package one.vladimir.impl.services.user;

import one.vladimir.api.Database;
import one.vladimir.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("userService")
public class UserServiceImpl {
    @Autowired
    @Qualifier("database")
    private Database db;

    @PostConstruct
    public void postConstructLog(){
        System.out.println("userService initialized");
    }

    public User getUser(){
        User user = new User();
        user.setUserId(1);
        return user;
    }


}
