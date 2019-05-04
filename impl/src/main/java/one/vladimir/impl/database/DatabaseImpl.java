package one.vladimir.impl.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.expr.Instanceof;
import one.vladimir.api.Database;
import one.vladimir.api.enums.DumpType;
import one.vladimir.api.pojo.*;
import one.vladimir.impl.database.entities.*;
import one.vladimir.impl.database.repositories.*;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import java.util.*;
import java.util.List;

@Service("database")
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

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger log = Logger.getLogger(DatabaseImpl.class);

    //For DB testing
    @PostConstruct
    public void testDBService() {
        String message = "DB initialized";
        log.info(message);

        /*DumpFilter df = new DumpFilter();
        List<Integer> ids = new Vector<>();
        ids.add(2);
        df.setCreatorsIdList(ids);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.getDumpsByFilter(df)));
        } catch (JsonProcessingException e) {
        }*/


        //Example of multicriterial query
        /*CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<PointEntity> c = b.createQuery(PointEntity.class);
        Root<PointEntity> root = c.from(PointEntity.class);
        c.select(root);
        List<Integer> i = new Vector<>();
        i.add(3);
        i.add(5);
        Expression<Integer> idExpr = root.get("pointId");
        Predicate idPred = idExpr.in(i);
        c.where(idPred);
        c.where(b.equal(root.get("pointId"), 5));
        List<PointEntity> pl = entityManager.createQuery(c).getResultList();
        System.out.println(pl.size());*/

    }


//    Basic Queries

    public Integer addUser(User user) {
//        if (userRepo.findAllEmails().contains(user.getEmail())) {
//            throw new IllegalArgumentException("User with this email already exists");
//        }
        UserEntity userEnt = new UserEntity();
        userEnt.setUser(user);
//        System.out.println("before" + userEnt.getUserId());
        userEnt.setUserId(null);
        userRepo.save(userEnt);
        return userEnt.getUserId();
//        System.out.println("after" + userEnt.getUserId());

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

    public User getUserByLogin(String login) {
        UserEntity userEnt;
        userEnt = userRepo.findUserByLogin(login);
        User user = userEnt.getUser();
        return user;
    }


    public Integer addPoint(Point point, User creator, Group group) {
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
        return pointEnt.getPointId();
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

    public Integer addGroup(Group group) {
        GroupEntity groupEnt = new GroupEntity();
        groupEnt.setGroup(group);
        groupEnt.setGroupId(null);
        groupRepo.save(groupEnt);
        return groupEnt.getGroupId();
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

    public Integer addDump(Dump dump) {
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
        return dumpEnt.getDumpId();
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
        dump.setUpdatedBy(dumpEnt.getPoint().getUpdatedBy().getUser());
        dump.setCreatedBy(dumpEnt.getPoint().getCreatedBy().getUser());
        dump.setGroup(dumpEnt.getPoint().getGroup().getGroup());
        return dump;
    }

    public Integer addBase(Base base) {
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
        return baseEnt.getBaseId();
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
        base.setUpdatedBy(baseEnt.getPoint().getUpdatedBy().getUser());
        base.setCreatedBy(baseEnt.getPoint().getCreatedBy().getUser());
        base.setGroup(baseEnt.getPoint().getGroup().getGroup());
        return base;
    }

    public Integer addCrewman(Crewman crewman) {
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
        return crewmanEnt.getCrewmanId();
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

    public Integer addRoute(Route route, Vessel vessel) {
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
        return routeEnt.getRouteId();
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

    public Integer addRoutePoint(RoutePoint routePoint, Route route) {
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
        return routePointEnt.getRoutePointId();
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

    public Integer addVessel(Vessel vessel) {
        VesselEntity vesselEnt = new VesselEntity();
        vesselEnt.setVessel(vessel);
        vesselEnt.setVesselId(null);
        vesselRepo.save(vesselEnt);
        return vesselEnt.getVesselId();
    }


    public Vessel getVesselById(Integer id) {
        VesselEntity vesselEnt;
        try {
            vesselEnt = vesselRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Vessel with id " + id + " not found");
        }
        Vessel vessel = vesselEnt.getVessel();
        try {
            vessel.setCurrRoute(routeRepo.findRouteByVessel(vesselEnt).getRoute());
        } catch (NullPointerException e) {
            vessel.setCurrRoute(null);
        }
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

    public void updatePoint(Point point, User updater) {
        PointEntity pointEnt;
        UserEntity userEnt;
        pointEnt = pointRepo.findById(point.getPointId()).get();
        userEnt = userRepo.findById(updater.getUserId()).get();
        pointEnt.setPoint(point);
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

    public Group getGroupByCoordinates(Double latitude, Double longitude) {
        Group group;
        group = groupRepo.findGroupByCoordinates(latitude, longitude).getGroup();
        return group;
    }

    public List<Dump> getAllDumps() {
        List<DumpEntity> dumpEnts;
        List<Dump> dumps = new Vector<Dump>();
        dumpEnts = dumpRepo.findAllDumps();
        for (DumpEntity dumpEnt : dumpEnts) {
            Dump dump = dumpEnt.getDump();
            dumpEnt.getPoint().getPoint(dump);
            dumps.add(dump);
        }
        return dumps;
    }

    public List<Base> getAllBases() {
        List<BaseEntity> baseEnts;
        List<Base> bases = new Vector<Base>();
        baseEnts = baseRepo.findAllBases();
        for (BaseEntity baseEnt : baseEnts) {
            Base base = baseEnt.getBase();
            baseEnt.getPoint().getPoint(base);
            bases.add(base);
        }
        return bases;
    }

    public List<Dump> getDumpsByIds(List<Integer> ids) {
        List<DumpEntity> dumpEnts;
        List<Dump> dumps = new Vector<Dump>();
        dumpEnts = dumpRepo.findDumpsByDumpIdIn(ids);
        for (DumpEntity dumpEnt : dumpEnts) {
            Dump dump = dumpEnt.getDump();
            dumpEnt.getPoint().getPoint(dump);
            dumps.add(dump);
        }
        return dumps;
    }

    public List<Base> getBasesByIds(List<Integer> ids) {
        List<BaseEntity> baseEnts;
        List<Base> bases = new Vector<Base>();
        baseEnts = baseRepo.findBasesByBaseIdIn(ids);
        for (BaseEntity baseEnt : baseEnts) {
            Base base = baseEnt.getBase();
            baseEnt.getPoint().getPoint(base);
            bases.add(base);
        }
        return bases;
    }

    public List<Dump> getDumpsByFilter(DumpFilter dumpFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DumpEntity> query = builder.createQuery(DumpEntity.class);
        Root<DumpEntity> dumpEntity = query.from(DumpEntity.class);
        Join<DumpEntity, PointEntity> pointEntity = dumpEntity.join("point");
        Join<PointEntity, GroupEntity> groupEntity = pointEntity.join("group");
        Join<PointEntity, UserEntity> userEntity = pointEntity.join("createdBy");

        Predicate pointIdPred;
        Predicate groupIdPred;
        Predicate dumpTypePred;
        Predicate creatorIdPred;
        Predicate isActivePred;
        Predicate maxSizePred;

        Expression<Integer> pointIdExpr = pointEntity.get("pointId");
        pointIdPred = pointIdExpr.isNotNull();
        if (dumpFilter.getPointIdList() != null) {
            pointIdPred = pointIdExpr.in(dumpFilter.getPointIdList());
        }

        Expression<Integer> groupIdExpr = groupEntity.get("groupId");
        groupIdPred = groupIdExpr.isNotNull();
        if (dumpFilter.getGroupidList() != null) {
            groupIdPred = groupIdExpr.in(dumpFilter.getGroupidList());
        }

        Expression<String> dumpTypeExpr = dumpEntity.get("type");
        dumpTypePred = dumpTypeExpr.isNotNull();
        if (dumpFilter.getDumpTypeList() != null) {
            dumpTypePred = dumpTypeExpr.in(dumpFilter.getDumpTypeList());
        }

        Expression<String> creatorIdExpr = userEntity.get("userId");
        creatorIdPred = creatorIdExpr.isNotNull();
        if (dumpFilter.getCreatorsIdList() != null) {
            creatorIdPred = creatorIdExpr.in(dumpFilter.getCreatorsIdList());
        }

        isActivePred = pointEntity.get("isActive").isNotNull();
        if (dumpFilter.getActive() != null) {
            isActivePred = builder.equal(pointEntity.get("isActive"), dumpFilter.getActive());
        }

        maxSizePred = dumpEntity.get("size").isNotNull();
        if (dumpFilter.getMaxSize() != null) {
            maxSizePred = builder.le(dumpEntity.get("size"), dumpFilter.getMaxSize());
        }

        query.where(pointIdPred, groupIdPred, dumpTypePred, creatorIdPred, isActivePred, maxSizePred);

        List<DumpEntity> dumpEntities = entityManager.createQuery(query).getResultList();
        List<Dump> dumps = new Vector<Dump>();
        for (DumpEntity dumpEnt : dumpEntities) {
            Dump dump = dumpEnt.getDump();
            dumpEnt.getPoint().getPoint(dump);
            Point currPoint = this.getPointById(dump.getPointId());
            dump.setGroup(currPoint.getGroup());
            dump.setCreatedBy(currPoint.getCreatedBy());
            dump.setUpdatedBy(currPoint.getUpdatedBy());
            dumps.add(dump);
        }

        return dumps;
    }

    public List<Route> getRoutesByFilter(RouteFilter routeFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RouteEntity> query = builder.createQuery(RouteEntity.class);
        Root<RouteEntity> routeEntity = query.from(RouteEntity.class);
        Join<RouteEntity, VesselEntity> vesselEntity = routeEntity.join("vessel");

        Predicate routeIdPred;
        Predicate vesselIdPred;
        Predicate routeStatusPred;


        Expression<Integer> routeIdExpr = routeEntity.get("routeId");
        routeIdPred = routeIdExpr.isNotNull();
        if (routeFilter.getRouteIdList() != null) {
            routeIdPred = routeIdExpr.in(routeFilter.getRouteIdList());
        }

        Expression<Integer> vesselIdExpr = vesselEntity.get("vesselId");
        vesselIdPred = vesselIdExpr.isNotNull();
        if (routeFilter.getVesselIdList() != null) {
            vesselIdPred = vesselIdExpr.in(routeFilter.getVesselIdList());
        }

        Expression<String> routeStatusExpr = routeEntity.get("status");
        routeStatusPred = routeStatusExpr.isNotNull();
        if (routeFilter.getRouteStatusList() != null) {
            routeStatusPred = routeStatusExpr.in(routeFilter.getRouteStatusList());
        }

        query.where(routeIdPred, vesselIdPred, routeStatusPred);

        List<RouteEntity> routeEntities = entityManager.createQuery(query).getResultList();
        List<Route> routes = new Vector<Route>();
        for (RouteEntity routeEnt : routeEntities) {
            Route route = routeEnt.getRoute();
            route.setRoutePoints(this.getRoutePointsByRouteId(route.getId()));
            routes.add(route);
        }

        return routes;
    }

    public Set<RoutePoint> getRoutePointsByRouteId(Integer id) {
        List<RoutePointEntity> routePointEntities;
        routePointEntities = routePointRepo.findRoutePointsByRouteRouteIdOrderByNumber(id);
        Set<RoutePoint> routePoints = new HashSet<>();
        for (RoutePointEntity routePointEntity : routePointEntities) {
            RoutePoint routePoint = routePointEntity.getRoutePoint();
            routePoint.setContainedPoint(routePointEntity.getPoint().getPoint());
            routePoints.add(routePoint);
        }
        return routePoints;
    }

    public List<RoutePoint> getRoutePointsByPointId(Integer id) {
        List<RoutePointEntity> routePointEntities;
        routePointEntities = routePointRepo.findRoutePointsByPointPointId(id);
        List<RoutePoint> routePoints = new Vector<>();
        for (RoutePointEntity routePointEntity : routePointEntities) {
            RoutePoint routePoint = routePointEntity.getRoutePoint();
            routePoint.setContainedPoint(routePointEntity.getPoint().getPoint());
            routePoints.add(routePoint);
        }
        return routePoints;
    }

}
