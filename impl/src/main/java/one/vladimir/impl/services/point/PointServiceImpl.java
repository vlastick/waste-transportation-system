package one.vladimir.impl.services.point;

import one.vladimir.api.DatabaseService;
import one.vladimir.api.GeoService;
import one.vladimir.api.PointService;
import one.vladimir.api.pojo.*;

import one.vladimir.impl.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.List;

@Service("pointService")
public class PointServiceImpl implements PointService {

    @Autowired
    @Qualifier("database")
    private DatabaseService db;

    @Autowired
    @Qualifier("geo")
    private GeoService geo;

    @Autowired
    @Qualifier("userService")
    private UserServiceImpl userService;

    @PostConstruct
    public void postConstructLog(){

    }

    @Override
    public String addDump(Dump dump, User creator) {
        Group group;
        group = db.getGroupByCoordinates(dump.getLatitude(), dump.getLongitude());
        dump.setCreatedBy(creator);
        dump.setActive(true);
        dump.setCreatedWhen(new Timestamp(System.currentTimeMillis()));
        dump.setUpdatedWhen(new Timestamp(System.currentTimeMillis()));
        dump.setPointId(db.addPoint(dump, creator, group));
        db.addDump(dump);
        return "New dump was added";
    }

    @Override
    public String addBase(Base base, User creator) {
        Group group;
        group = db.getGroupByCoordinates(base.getLatitude(), base.getLongitude());
        base.setCreatedBy(creator);
        base.setActive(true);
        base.setCreatedWhen(new Timestamp(System.currentTimeMillis()));
        base.setUpdatedWhen(new Timestamp(System.currentTimeMillis()));
        base.setPointId(db.addPoint(base, creator, group));
        db.addBase(base);
        return "Base was added";
    }

    @Override
    public Dump getDump(Integer id) {

        Dump dump = db.getDumpById(id);
        if(dump == null){
           dump = new Dump();
        }
        return dump;
    }

    @Override
    public Base getBase(Integer id) {
        Base base = db.getBaseById(id);
        if(base == null){
            base = new Base();
        }
        return base;
    }

    @Override
    public List<Dump> getDumpsByFilter(DumpFilter dumpFilter) {
        List<Dump> dumps;
        dumps = db.getDumpsByFilter(dumpFilter);
        return dumps;
    }

    @Override
    public List<Base> getBasesByFilter(BaseFilter baseFilter) {
        List<Base> bases;
        bases = db.getBasesByFilter(baseFilter);
        return  bases;
    }


    @Override
    public String updateDump(Dump dump, User updater) {
        dump.setUpdatedBy(updater);
        dump.setUpdatedWhen(new Timestamp(System.currentTimeMillis()));
        db.updatePoint(dump, updater);
        db.updateDump(dump);
        return "Dump was updated";
    }

    @Override
    public String updateBase(Base base, User updater) {
        base.setUpdatedBy(updater);
        base.setUpdatedWhen(new Timestamp(System.currentTimeMillis()));
        db.updatePoint(base, updater);
        db.updateBase(base);
        return "Base was updated";
    }

    @Override
    public String testGeo(String request) {
        return geo.getResult(request);
    }

    @Override
    public String addGroup(Group group){
        db.addGroup(group);
        return "Group added";
    }

    @Override
    public String updateGroup(Group group){
        db.updateGroup(group);
        return "Group updated";
    }

    @Override
    public Group getGroup(Integer id){
        Group group;
        group = db.getGroupById(id);
        return group;
    }

}