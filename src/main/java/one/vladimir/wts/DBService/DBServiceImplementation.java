package one.vladimir.wts.DBService;

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
            throw new NoSuchElementException("User not found");
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

    }

    public PointEntity getPointById(Integer id) {
        PointEntity point;
        try {
            point = pointRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("point not found");
        }
        return point;
    }

    public void addGroup(GroupEntity group) {
        groupRepo.save(group);
    }

    public GroupEntity getGroupById(Integer id) {
        GroupEntity group;
        try {
            group = groupRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("group not found");
        }
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
