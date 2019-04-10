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

    @Override
    public String addDump(Dump dump) {
        return null;
    }

    @Override
    public String addBase(Base base) {
        return null;
    }

    @Override
    public Dump getDump(Integer id) {
        return null;
    }

    @Override
    public Base getBase(Integer id) {
        return null;
    }

    @Override
    public List<Dump> getDumps() {
        return null;
    }

    @Override
    public List<Base> getBases() {
        return null;
    }

    @Override
    public Route getRoute(Integer id) {
        return null;
    }

    @Override
    public List<Route> getRoutes() {
        return null;
    }

    @Override
    public String addRoute(Route route) {
        return null;
    }

    @Override
    public String updateDump(Dump dump) {
        return null;
    }

    @Override
    public String updateBase(Base base) {
        return null;
    }

    @Override
    public String updateRoute(Route route) {
        return null;
    }

    @Override
    public String addVessel(Vessel vessel) {
        return null;
    }

    @Override
    public Vessel getVessel(Vessel vessel) {
        return null;
    }

    @Override
    public String updateVessel(Vessel vessel) {
        return null;
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