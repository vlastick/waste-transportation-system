package one.vladimir.wts.DBService;

import one.vladimir.wts.DBService.Entities.*;
import one.vladimir.wts.DBService.Repositories.DumpRepository;
import one.vladimir.wts.DBService.Repositories.GroupRepository;
import one.vladimir.wts.DBService.Repositories.PointRepository;
import one.vladimir.wts.DBService.Repositories.UserRepository;
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

    public void addUser(UserEntity user) {
        userRepo.save(user);
    }

    public UserEntity getUserById(Integer id) {
        UserEntity user;
        try {
            user = userRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("user not found");
        }

        return user;
    }

    public void addPoint(PointEntity point) {
        UserEntity user = point.getCreator();
        pointRepo.save(point);
    }

    public PointEntity getPointById(Integer id) {
        PointEntity point;
        try {
            point = pointRepo.findById(id).get();
        } catch (NoSuchElementException e){
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
        } catch (NoSuchElementException e){
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
