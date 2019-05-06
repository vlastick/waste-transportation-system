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
    public void postConstructLog() {
        System.out.println("transportService initialized");
    }

    @Override
    public Vessel getVessel(Integer id) {
        Vessel vessel = db.getVesselById(id);
        return vessel;
    }

    @Override
    public String addVessel(Vessel vessel) {
        db.addVessel(vessel);
        return "Vessel added";
    }

    @Override
    public String updateVessel(Vessel vessel) {
        db.updateVessel(vessel);
        return "Vessel updated";
    }

    @Override
    public String updateCoordinates(Integer vesselId, Double latitude, Double longitude) {
        Vessel vessel = db.getVesselById(vesselId);
        vessel.setLongitude(longitude);
        vessel.setLatitude(latitude);
        db.updateVessel(vessel);
        return "Vessel coordinates updated";
    }

    @Override
    public Vessel getVesselByCrewmanId(Integer id) {
        Vessel vessel;
        vessel = db.getVesselByCrewmanId(id);
        return vessel;
    }
}
