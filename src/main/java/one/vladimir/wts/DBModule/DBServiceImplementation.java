package one.vladimir.wts.DBModule;

import one.vladimir.wts.DBModule.Entities.*;
import one.vladimir.wts.DBModule.Repositories.DumpRepository;
import one.vladimir.wts.DBModule.Repositories.GroupRepository;
import one.vladimir.wts.DBModule.Repositories.PointRepository;
import one.vladimir.wts.DBModule.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DBServiceImplementation implements DBServiceInterface{
    @Autowired
    private PointRepository pointRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private DumpRepository dumpRepo;
    @Autowired
    private GroupRepository groupRepo;


//    Basic Queries

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User getUserById(Integer id) {
        User user;
        try {
            user = userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("user not found");
        }

        return user;
    }

    public void addPoint(Point point) {
        User user = point.getCreator();
        pointRepo.save(point);
    }

    public Point getPointById(Integer id) {
        Point point;
        try {
            point = pointRepo.findById(id).get();
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("point not found");
        }
        return point;
    }

    public void addGroup(Group group) {
        groupRepo.save(group);
    }

    public Group getGroupById(Integer id) {
        Group group;
        try {
            group = groupRepo.findById(id).get();
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("group not found");
        }
        return group;
    }

    public Integer testQuery() {
        /*User u = new User();
        u.setLogin("User1");
        u.setRole("Admin");
        userRepo.save(u);

        Point p = new Point();
        p.setCreator(u);
        p.setGroupId(1);
        pointRepo.save(p);

        userRepo.deleteById(5);*/
        return 1;
    }
}
