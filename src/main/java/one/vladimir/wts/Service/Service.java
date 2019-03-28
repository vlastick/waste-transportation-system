package one.vladimir.wts.Service;

import one.vladimir.wts.DBModule.DBModule;
import one.vladimir.wts.DBModule.Entities.Group;
import one.vladimir.wts.DBModule.Entities.Point;
import one.vladimir.wts.DBModule.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class Service {

    @Autowired
    static DBModule db = new DBModule();
    private final AtomicInteger counter = new AtomicInteger();


    static public String getOrdersList() {
        return "ordersList";
    }

    static public String getOrders(String ownerId, String statusId, String typeId) {
        return "getOrders";
    }

    static public String getOrdersCurrent() {
        return "currentOrdersList";
    }

    //Test

    public static String addUser(String strLogin, String strRole){

        User user = new User();
        user.setLogin(strLogin);
        user.setRole(strRole);
        db.addUser(user);
        return "User added";
    }


    public static String getUser(String strId){

        Integer id = Integer.valueOf(strId);
        User user;
        try{
            user = db.getUserById(id);
        } catch (NoSuchElementException e){
            return e.getMessage();
        }
        return user.getLogin();
    }


    public static Point getPoint(String strId){

        Integer id = Integer.valueOf(strId);
        Point point = db.getPointById(id);
        return point;
    }

    public static String addPoint(String strCreatorId, String strGroupId){

        Integer creatorId = Integer.valueOf(strCreatorId);
        Integer groupId = Integer.valueOf(strGroupId);
        User creator = db.getUserById(creatorId);
        Group group = db.getGroupById(groupId);
        Point point = new Point();
        point.setGroup(group);
        point.setCreator(creator);
        db.addPoint(point);
        return "True";
    }
}