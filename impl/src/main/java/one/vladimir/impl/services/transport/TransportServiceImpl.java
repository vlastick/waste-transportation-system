package one.vladimir.impl.services.transport;

import one.vladimir.api.Database;
import one.vladimir.api.TransportService;
import one.vladimir.api.pojo.Vessel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("transportService")
public class TransportServiceImpl implements TransportService {

    @Autowired
    @Qualifier("database")
    private Database db;

    @PostConstruct
    public void postConstructLog(){
        System.out.println("transportService initialized");
    }

    @Override
    public Vessel getVessel(Integer id){
        Vessel vessel = db.getVesselById(id);
        return new Vessel();
    }

    @Override
    public String addVessel(Vessel vessel){
        db.addVessel(vessel);
        return "Vessel added";
    }

    @Override
    public String updateVessel(Vessel vessel){
        db.updateVessel(vessel);
        return "Vessel updated";
    }

}
