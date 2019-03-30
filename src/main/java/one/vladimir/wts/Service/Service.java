package one.vladimir.wts.Service;

import one.vladimir.wts.Service.POJO.Base;
import one.vladimir.wts.Service.POJO.Dump;
import one.vladimir.wts.Service.POJO.Point;

import java.util.List;

/*
import org.springframework.beans.factory.annotation.Autowired;
import one.vladimir.wts.DBService.DBServiceImplementation;
import one.vladimir.wts.DBService.Entities.Group;
import one.vladimir.wts.DBService.Entities.Point;
import one.vladimir.wts.DBService.Entities.User;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;*/

public class Service {



    public static String postPoint(Point point){
        return  "Point was posted";
    }

    public static String postDump(Dump dump){
        return  "Dump was posted";
    }

    public static String postBase(Base base){
        return "Base was posted";
    }

    public static String postPoints(List <Point> point){
        return  "Points list was posted";
    }

    public static String postDumps(List <Dump> dump){
        return  "Dumps list  was posted";
    }

    public static String postBases(List <Base> base){
        return "Bases list  was posted";
    }



   // @Autowired
    //static DBServiceImplementation db = new DBServiceImplementation();

    /*
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
    */
}