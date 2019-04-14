package com.netcracker.impl.database;

import com.netcracker.api.Database;
import com.netcracker.api.pojo.*;
import com.netcracker.api.pojo.Point;
import com.netcracker.impl.database.entities.*;

import com.netcracker.impl.database.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.*;
import java.util.List;

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

    @Autowired
    private VesselRepository vesselRepo;

    @Autowired
    private RouteRepository routeRepo;

    @Autowired
    private RoutePointRepository routePointRepo;

    @Autowired
    private BaseRepository baseRepo;

    @Autowired
    private CrewmanRepository crewmanRepo;

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
//        System.out.println(this.getVesselById(13).getCurrRoute());
//        System.out.println(pointRepo.findById(8).get());


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
        pointEnt.setUpdatedBy(creatorEnt);
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
        point.setGroup(pointEnt.getGroup().getGroup());
        point.setCreatedBy(pointEnt.getCreatedBy().getUser());
        point.setUpdatedBy(pointEnt.getUpdatedBy().getUser());
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

    public void addBase(Base base) {
        PointEntity pointEnt;
        try {
            pointEnt = pointRepo.findById(base.getPointId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Point with id " + base.getPointId() + " not found");
        }
        BaseEntity baseEnt = new BaseEntity();
        baseEnt.setBase(base);
        baseEnt.setPoint(pointEnt);
        baseEnt.setBaseId(null);
        baseRepo.save(baseEnt);
    }


    public Base getBaseById(Integer id) {
        BaseEntity baseEnt;
        try {
            baseEnt = baseRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Base with id " + id + " not found");
        }
        Base base = baseEnt.getBase();
        baseEnt.getPoint().getPoint(base);
        return base;
    }

    public void addCrewman(Crewman crewman) {
        UserEntity userEnt;
        VesselEntity vesselEnt;

        try {
            userEnt = userRepo.findById(crewman.getUserId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("User with id " + crewman.getUserId() + " not found");
        }
        try {
            vesselEnt = vesselRepo.findById(crewman.getVessel().getId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Vessel with id " + crewman.getVessel().getId() + " not found");
        }
        CrewmanEntity crewmanEnt = new CrewmanEntity();
        crewmanEnt.setCrewman(crewman);
        crewmanEnt.setUser(userEnt);
        crewmanEnt.setVessel(vesselEnt);
        crewmanRepo.save(crewmanEnt);
    }

    public Crewman getCrewmanById(Integer id) {
        CrewmanEntity crewmanEnt;
        try {
            crewmanEnt = crewmanRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Crewman with id " + id + " not found");
        }
        Crewman crewman = crewmanEnt.getCrewman();
        crewmanEnt.getUser().getUser(crewman);
        crewman.setVessel(crewmanEnt.getVessel().getVessel());
        return crewman;
    }

    public void addRoute(Route route, Vessel vessel) {
        VesselEntity vesselEnt;
        try {
            vesselEnt = vesselRepo.findById(vessel.getId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Vessel with id " + vessel.getId() + " not found");
        }
        RouteEntity routeEnt = new RouteEntity();
        routeEnt.setRoute(route);
        routeEnt.setVessel(vesselEnt);
        routeEnt.setRouteId(null);
        routeRepo.save(routeEnt);
    }

    public Route getRouteById(Integer id) {
        RouteEntity routeEnt;
        try {
            routeEnt = routeRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Route with id " + id + " not found");
        }
        Route route = routeEnt.getRoute();
        List<RoutePointEntity> routePointEntities = routePointRepo.findRoutePointsByRoute(routeEnt);
        Set<RoutePoint> routePoints = new HashSet<RoutePoint>();
        for (RoutePointEntity routePointEnt : routePointEntities) {
            routePoints.add(routePointEnt.getRoutePoint());
        }
        route.setRoutePoints(routePoints);
        return new Route();
    }

    public void addRoutePoint(RoutePoint routePoint, Route route) {
        PointEntity pointEnt;
        RouteEntity routeEnt;
        try {
            pointEnt = pointRepo.findById(routePoint.getContainedPoint().getPointId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Point with id " + routePoint.getContainedPoint().getPointId() + " not found");
        }
        try {
            routeEnt = routeRepo.findById(route.getId()).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Route with id " + route.getId() + " not found");
        }
        RoutePointEntity routePointEnt = new RoutePointEntity();
        routePointEnt.setRoutePoint(routePoint);
        routePointEnt.setRoute(routeEnt);
        routePointEnt.setPoint(pointEnt);
        routePointEnt.setRoutePointId(null);
        routePointRepo.save(routePointEnt);
    }

    public RoutePoint getRoutePointById(Integer id) {
        RoutePointEntity routePointEnt;
        try {
            routePointEnt = routePointRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("RoutePoint with id " + id + " not found");
        }
        RoutePoint routePoint = routePointEnt.getRoutePoint();
        routePoint.setContainedPoint(routePointEnt.getPoint().getPoint());
        return routePoint;
    }

    public void addVessel(Vessel vessel) {
        VesselEntity vesselEnt = new VesselEntity();
        vesselEnt.setVessel(vessel);
        vesselEnt.setVesselId(null);
        vesselRepo.save(vesselEnt);
    }


    public Vessel getVesselById(Integer id) {
        VesselEntity vesselEnt;
        try {
            vesselEnt = vesselRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Vessel with id " + id + " not found");
        }
        Vessel vessel = vesselEnt.getVessel();
        vessel.setCurrRoute(routeRepo.findRouteByVessel(vesselEnt).getRoute());
        return vessel;
    }

    public void updateUser(User user) {
        UserEntity userEnt;
        userEnt = userRepo.findById(user.getUserId()).get();
        userEnt.setUser(user);
        userRepo.save(userEnt);
    }

    public void updatePoint(Point point, User updater, Group group) {
        PointEntity pointEnt;
        GroupEntity groupEnt;
        UserEntity userEnt;
        pointEnt = pointRepo.findById(point.getPointId()).get();
        userEnt = userRepo.findById(updater.getUserId()).get();
        groupEnt = groupRepo.findById(group.getId()).get();
        pointEnt.setPoint(point);
        pointEnt.setGroup(groupEnt);
        pointEnt.setUpdatedBy(userEnt);
        pointRepo.save(pointEnt);
    }

    public void updateGroup(Group group) {
        GroupEntity groupEnt;
        groupEnt = groupRepo.findById(group.getId()).get();
        groupEnt.setGroup(group);
        groupRepo.save(groupEnt);
    }

    public void updateDump(Dump dump) {
        DumpEntity dumpEnt;
        dumpEnt = dumpRepo.findById(dump.getId()).get();
        dumpEnt.setDump(dump);
        dumpRepo.save(dumpEnt);
    }

    public void updateBase(Base base) {
        BaseEntity baseEnt;
        baseEnt = baseRepo.findById(base.getId()).get();
        baseEnt.setBase(base);
        baseRepo.save(baseEnt);
    }

    public void updateCrewman(Crewman crewman) {
        CrewmanEntity crewmanEnt;
        VesselEntity vesselEnt;
        crewmanEnt = crewmanRepo.findById(crewman.getId()).get();
        vesselEnt = vesselRepo.findById(crewman.getVessel().getId()).get();
        crewmanEnt.setCrewman(crewman);
        crewmanEnt.setVessel(vesselEnt);
    }

    public void updateRoute(Route route, Vessel vessel) {
        RouteEntity routeEnt;
        VesselEntity vesselEnt;
        routeEnt = routeRepo.findById(route.getId()).get();
        vesselEnt = vesselRepo.findById(vessel.getId()).get();
        routeEnt.setRoute(route);
        routeEnt.setVessel(vesselEnt);
        routeRepo.save(routeEnt);
    }

    public void updateRoutePoint(RoutePoint routePoint) {
        RoutePointEntity routePointEnt;
        routePointEnt = routePointRepo.findById(routePoint.getId()).get();
        routePointEnt.setRoutePoint(routePoint);
        routePointRepo.save(routePointEnt);
    }

    public void updateVessel(Vessel vessel) {
        VesselEntity vesselEnt;
        vesselEnt = vesselRepo.findById(vessel.getId()).get();
        vesselEnt.setVessel(vessel);
        vesselRepo.save(vesselEnt);
    }

}
