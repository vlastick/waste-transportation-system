package one.vladimir.wts.DBService;

import one.vladimir.wts.BusinessLogic.POJO.DumpStatus;
import one.vladimir.wts.BusinessLogic.POJO.Group;
import one.vladimir.wts.BusinessLogic.POJO.Point;
import one.vladimir.wts.BusinessLogic.POJO.User;
import one.vladimir.wts.DBService.Entities.*;
import one.vladimir.wts.DBService.Repositories.DumpRepository;
import one.vladimir.wts.DBService.Repositories.GroupRepository;
import one.vladimir.wts.DBService.Repositories.PointRepository;
import one.vladimir.wts.DBService.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

@Service
public class DBServiceImplementation implements DBServiceInterface {
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

//        DumpStatus s = DumpStatus.UNCONFIRMED;
//        DumpStatus d = DumpStatus.valueOf("removed");
//        System.out.println(s);
//        System.out.println(d);
//        System.out.println(this.getGroupById(23).getKoef());


        ;


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
        List<PointEntity> createdPointEntities = pointRepo.findPointsByCreator(userEnt);
        List<Point> createdPoints = new Vector<Point>();
        for (PointEntity pointEnt : createdPointEntities) {
            createdPoints.add(pointEnt.getPoint());
        }
        user.setCreatedPoints(createdPoints);
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
        pointEnt.setCreator(creatorEnt);
        pointEnt.setGroup(groupEnt);
        pointEnt.setPointId(null);
        pointRepo.save(pointEnt);
    }

    public Point getPointById(Integer id) {
        PointEntity pointEnt;
        try {
            pointEnt = pointRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("point with id " + id + " not found");
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
            throw new NoSuchElementException("group with id " + id + " not found");
        }
        Group group = groupEnt.getGroup();
        return group;
    }

    public Integer testQuery() {
        /*UserEntity u = new UserEntity();
        u.setLogin("User1");
        u.setRole("Admin");
        userRepo.save(u);

        PointEntity p = new PointEntity();
        p.setCreator(u);
        p.setGroupId(1);
        pointRepo.save(p);

        userRepo.deleteById(5);*/
        return 1;
    }
}
