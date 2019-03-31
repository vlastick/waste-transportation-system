package one.vladimir.wts.BusinessLogic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import one.vladimir.wts.BusinessLogic.POJO.*;
import one.vladimir.wts.DBService.DBServiceImplementation;
import one.vladimir.wts.DBService.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
import org.springframework.beans.factory.annotation.Autowired;
import one.vladimir.wts.DBService.DBServiceImplementation;
import one.vladimir.wts.DBService.Entities.GroupEntity;
import one.vladimir.wts.DBService.Entities.PointEntity;
import one.vladimir.wts.DBService.Entities.UserEntity;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;*/

@Service
public class BusinessLogic implements BusinessLogicInterface{

    @Autowired
    private DBServiceImplementation db = new DBServiceImplementation();

    public String postPoint(Point point) {
        return "PointEntity was posted";
    }

    public String postDump(Dump dump) {
        return "DumpEntity was posted";
    }

    public String postBase(Base base) {
        return "BaseEntity was posted";
    }

    public String postPoints(List<Point> point) {
        return "Points list was posted";
    }

    public String postDumps(List<Dump> dump) {
        return "Dumps list  was posted";
    }

    public String postBases(List<Base> base) {
        return "Bases list  was posted";
    }

    // TODO - write more functions to get all types of entities.
    // There is and example below. How to parse JsonNode you can see in RestImplementation
    public String getDumps(JsonNode filter) {

        List<Dump> dumpList = new ArrayList<>();
        Dump testDump = new Dump();
        testDump.setPriority(99);
        testDump.setStatus(DumpStatus.REMOVED);
        testDump.setType(DumpType.LIQUID);

        for (int i = 0; i < 10; i++) {
            dumpList.add(testDump);
        }

        String answerJson = "Error";
        ObjectMapper mapper = new ObjectMapper();
        try {
            answerJson = mapper.writeValueAsString(dumpList);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return answerJson;
    }


    public String addUser(String strLogin, String strRole, String strPassword){

        User user = new User();
        user.setLogin(strLogin);
        user.setRole(strRole);
        user.setPassword(strPassword);
        //db.addUser(user);
        return "User added";
    }


    public String getUser(String strId){

        Integer id = Integer.valueOf(strId);
        User user;
        try{
            user = db.getUserById(id);
        } catch (NoSuchElementException e){
            return e.getMessage();
        }
        return user.getLogin();
    }

    /*
    public static PointEntity getPoint(String strId){

        Integer id = Integer.valueOf(strId);
        PointEntity point = db.getPointById(id);
        return point;
    }

    public static String addPoint(String strCreatorId, String strGroupId){

        Integer creatorId = Integer.valueOf(strCreatorId);
        Integer groupId = Integer.valueOf(strGroupId);
        UserEntity creator = db.getUserById(creatorId);
        GroupEntity group = db.getGroupById(groupId);
        PointEntity point = new PointEntity();
        point.setGroup(group);
        point.setCreator(creator);
        db.addPoint(point);
        return "True";
    }
    */
}