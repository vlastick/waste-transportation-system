package com.netcracker.impl.services.point;


import com.netcracker.api.Database;
import com.netcracker.api.Geo;
import com.netcracker.api.PointService;
import com.netcracker.api.pojo.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private ApplicationContext context;

    private Database db;

    private Geo geo;

    @PostConstruct
    void init(){
        db = context.getBean("database", Database.class);
        geo = context.getBean("geo", Geo.class);
        System.out.println("PointServiceImpl postconstruct");
    }


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

    @Override
    public String testGeo(String request) {

        return geo.getResult(request);
    }

    @Override
    public String addUser(String strLogin, String strRole, String strPassword){

        User user = new User();
        user.setLogin(strLogin);
        user.setRole(strRole);
        user.setPassword(strPassword);
        db.addUser(user);
        return "User added";
    }

    @Override
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
        point.setCreatedBy(creator);
        db.addPoint(point);
        return "True";
    }
    */
}