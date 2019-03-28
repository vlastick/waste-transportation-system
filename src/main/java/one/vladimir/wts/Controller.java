package one.vladimir.wts;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import one.vladimir.wts.DBModule.DBModule;
import one.vladimir.wts.DBModule.Entities.Group;
import one.vladimir.wts.DBModule.Entities.Point;
import one.vladimir.wts.DBModule.Entities.User;
import one.vladimir.wts.DBModule.Repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

//    @Autowired
//    private RequestRepo requestRepo;
    @Autowired
    DBModule db = new DBModule();

    private final AtomicInteger counter = new AtomicInteger();

    @RequestMapping("/request/save")
    public String saveRequest(
            @RequestParam(name = "name", defaultValue = "empty") String name,
            @RequestParam(name = "description", defaultValue = "empty") String description,
            @RequestParam(name = "weight", defaultValue = "0.0") String strWeight,
            @RequestParam(name = "volume", defaultValue = "0.0") String strVolume) {
        /*try {
            UserRequest userRequest = new UserRequest();
            userRequest.setWeight(Double.valueOf(strWeight));
            userRequest.setVolume(Double.valueOf(strVolume));
            userRequest.setPointId(1);
            userRequest.setName(name);
            userRequest.setDescription(description);
            requestRepo.save(userRequest);
            return "True";

        } catch (NumberFormatException e) {
            return "False";
        }*/
        return "True";
    }


    @RequestMapping("/add/user/")
    public String addUser(
            @RequestParam(name = "login", defaultValue = "DeafaultUser") String strLogin,
            @RequestParam(name = "role", defaultValue = "User") String strRole){

        User user = new User();
        user.setLogin(strLogin);
        user.setRole(strRole);
        db.addUser(user);
        return "User added";
    }

    @RequestMapping("/get/user/")
    public String getUser(
            @RequestParam(name = "id", defaultValue = "1") String strId){
        Integer id = Integer.valueOf(strId);
        User user;
        try{
            user = db.getUserById(id);
        } catch (NoSuchElementException e){
            return e.getMessage();
        }
        return user.getLogin();
    }

    @RequestMapping("/get/point/")
    public Point getPoint(
            @RequestParam(name = "id", defaultValue = "1") String strId){
        Integer id = Integer.valueOf(strId);
        Point point = db.getPointById(id);
        return point;
    }

    @RequestMapping("/add/point/")
    public String addPoint(
            @RequestParam(name = "creatorId", defaultValue = "1") String strCreatorId,
            @RequestParam(name = "groupId", defaultValue = "1") String strGroupId){
        Integer creatorId = Integer.valueOf(strCreatorId);
        Integer groupId = Integer.valueOf(strGroupId);
        User creator = db.getUserById(creatorId);
        Group group = db.getGroupById(groupId);
        Point point = new Point();
        point.setGroup(group);
        point.setCreator(creator);
        db.addPoint(point);
        return "True";
    }

  /*  @RequestMapping("/request/all")
    public Iterable<UserRequest> getRequest() {
        return requestRepo.findAll();
    }

    @RequestMapping("/request/")
    public UserRequest getRequest(@RequestParam(name = "id", defaultValue = "1") String strId) {
        try {
            return requestRepo.findById(Integer.valueOf(strId)).get();
        } catch (NumberFormatException e) {
            return new UserRequest();
        }
    }*/

}
