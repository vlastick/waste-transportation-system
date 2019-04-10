package com.netcracker.impl.database;

import com.netcracker.api.Database;
import com.netcracker.api.pojo.*;
import com.netcracker.impl.database.entities.*;

import com.netcracker.impl.database.repositories.DumpRepository;
import com.netcracker.impl.database.repositories.GroupRepository;
import com.netcracker.impl.database.repositories.PointRepository;
import com.netcracker.impl.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

@Service
public class DatabaseImpl implements Database {

    @Autowired
    private PointRepository pointRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DumpRepository dumpRepo;

    @Autowired
    private GroupRepository groupRepo;

    //For DB testing
    @PostConstruct
    public void testDBService() {

//        pointRepo.findPointsByCreator(userEnt).forEach(point -> System.out.println(point.getPointId()));
//        pointRepo.findPointsByCreatorId(1).forEach(point -> System.out.println(point.getPointId()));
//        this.getUserById(1).getCreatedPoints().forEach(x->System.out.println(x.getLatitude()));
//        Point p = new Point();
//        Group g = new Group();
//        User u  = new User();
//        u.setUserId(1);
//        g.setId(7);
//        g.setKoef(111);
//        this.addGroup(g);
//        p.setPointId(8);
//        p.setLongitude(1111.1);
//        this.addPoint(p, u, g);
//        System.out.println(this.getPointById(14).getLatitude().toString() + this.getPointById(19).getLongitude().toString());

//        DumpStatus s = null;
//        System.out.println(s.toString());
//        DumpStatus d = DumpStatus.valueOf("removed");
//        System.out.println(s);
//        System.out.println(d);
//        System.out.println(this.getGroupById(23).getKoef());
//        PointEntity p = pointRepo.findById(8).get();
//        DumpEntity d = dumpRepo.findById(11).get();
//        Dump p1 = d.getDump();
//        d.getPoint().getPoint(p1);
//        Dump p1 = this.getDumpById(11);
//        System.out.println(p1.getStatus() + p1.getType().toString() + p1.getPointId().toString() + p1.getLatitude().toString());

//        Dump d = new Dump();
//        d.setType(DumpType.MIXED);
//        d.setStatus(DumpStatus.UNCONFIRMED);
//        d.setPointId(8);
//        d.setLatitude(133.0);
//        d.setPriority(1);
//        this.addDump(d);
//        GroupEntity groupEnt = new GroupEntity();
//        groupEnt.setGroupId(7);
//        pointRepo.findPointsByGroup(groupEnt).forEach(point -> System.out.println(point.getPointId()));
//        System.out.println(this.getGroupById(7).getContainedPoints());
        System.out.println("db postconstruct");

    }


//    Basic Queries

    public void addUser(User user) {
        if (userRepo.findAllEmails().contains(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        UserEntity userEnt = new UserEntity();
        userEnt.setUser(user);
        userEnt.setUserId(null);
        userRepo.save(userEnt);
    }

    public User getUserById(Integer id) {
        UserEntity userEnt;
        try {
            userEnt = userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        User user = userEnt.getUser();
//        List<PointEntity> createdPointEntities = pointRepo.findPointsByCreatedBy(userEnt);
//        List<Point> createdPoints = new Vector<Point>();
//        for (PointEntity pointEnt : createdPointEntities) {
//            createdPoints.add(pointEnt.getPoint());
//        }
//        user.setCreatedPoints(createdPoints);
        return user;
    }

    public void addPoint(Point point, User creator, Group group) {
        if (!userRepo.findAllIds().contains(creator.getUserId())) {
            throw new IllegalArgumentException("User with id " + creator.getUserId() + " not found");
        }
        if (!groupRepo.findAllIds().contains(group.getId())) {
            throw new IllegalArgumentException("Group with id " + group.getId() + " not found");
        }
        PointEntity pointEnt = new PointEntity();
        GroupEntity groupEnt = new GroupEntity();
        UserEntity creatorEnt = new UserEntity();
        groupEnt.setGroupId(group.getId());
        creatorEnt.setUserId(creator.getUserId());
        pointEnt.setPoint(point);
        pointEnt.setCreatedBy(creatorEnt);
        pointEnt.setGroup(groupEnt);
        pointEnt.setPointId(null);
        pointRepo.save(pointEnt);
    }

    public Point getPointById(Integer id) {
        PointEntity pointEnt;
        try {
            pointEnt = pointRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Point with id " + id + " not found");
        }
        Point point = pointEnt.getPoint();
        return point;
    }

    public void addGroup(Group group) {
        GroupEntity groupEnt = new GroupEntity();
        groupEnt.setGroup(group);
        groupEnt.setGroupId(null);
        groupRepo.save(groupEnt);
    }

    public Group getGroupById(Integer id) {
        GroupEntity groupEnt;
        try {
            groupEnt = groupRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Group with id " + id + " not found");
        }
        Group group = groupEnt.getGroup();
        List<PointEntity> pointEntities = pointRepo.findPointsByGroup(groupEnt);
        List<Point> points = new Vector<Point>();
        for (PointEntity pointEnt : pointEntities) {
            points.add(pointEnt.getPoint());
        }
        group.setContainedPoints(points);
        return group;
    }

    //TODO: add object uniqueness check for exact point
    public void addDump(Dump dump) {
        if (!pointRepo.findAllIds().contains(dump.getPointId())) {
            throw new IllegalArgumentException("Point with id " + dump.getPointId() + " not found");
        }
        DumpEntity dumpEnt = new DumpEntity();
        PointEntity pointEnt = new PointEntity();
        pointEnt.setPointId(dump.getPointId());
        try {
            dumpEnt.setDump(dump);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Type and Status of dump couldn't be null");
        }

        dumpEnt.setPoint(pointEnt);
        dumpEnt.setDumpId(null);
        dumpRepo.save(dumpEnt);
    }

    public Dump getDumpById(Integer id) {
        DumpEntity dumpEnt;
        try {
            dumpEnt = dumpRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Dump with id " + id + " not found");
        }
        Dump dump = dumpEnt.getDump();
        dumpEnt.getPoint().getPoint(dump);
        return dump;
    }

    //TODO: vvv implement following queries vvv

    public void addBase(Base base) {
    }


    public Base getBaseById(Integer id) {
        return new Base();
    }

    public void addCrewman(Crewman crewman) {
    }

    public Crewman getCrewmanById(Integer id) {
        return new Crewman();
    }

    public void addRoute(Route route) {
    }

    public Route getRouteById(Integer id) {
        return new Route();
    }

    public void addRoutePoint(RoutePoint routePoint) {
    }

    public RoutePoint getRoutePointById(Integer id) {
        return new RoutePoint();
    }

    public void addVessel(Vessel vessel) {
    }

    public Vessel getVesselById(Integer id) {
        return new Vessel();
    }

}
