package one.vladimir.impl.services.point;

import one.vladimir.api.Database;
import one.vladimir.api.Geo;
import one.vladimir.api.PointService;
import one.vladimir.api.pojo.*;

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
        db.addDump(dump);
        return "New dump was added";
    }

    @Override
    public String addBase(Base base) {
        db.addBase(base);
        return "Base was added";
    }

    @Override
    public Dump getDump(Integer id) {

        Dump dump = db.getDumpById(id);
        if(dump == null){
           dump = new Dump();
           System.out.println("Dump was empty");
        }
        return dump;
    }

    @Override
    public Base getBase(Integer id) {
        Base base = db.getBaseById(id);
        if(base == null){
            base = new Base();
            System.out.println("Base was empty");
        }
        return base;
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
        Route route = db.getRouteById(id);
        if(route == null){
            route = new Route();
            System.out.println("Route was empty");
        }
        return route;
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

        db.addVessel(vessel);
        return "Vessel was added";
    }

    @Override
    public Vessel getVessel(Integer id) {
        Vessel vessel = db.getVesselById(id);
        if(vessel == null){
            vessel = new Vessel();
            System.out.println("Vessel was empty");
        }
        return vessel;
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