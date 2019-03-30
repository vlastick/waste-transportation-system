package one.vladimir.wts.Service;

import one.vladimir.wts.DBService.DBServiceImplementation;
import one.vladimir.wts.DBService.Entities.Group;
import one.vladimir.wts.DBService.Entities.Point;
import one.vladimir.wts.DBService.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class Service {

    @Autowired
    static DBServiceImplementation db = new DBServiceImplementation();
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