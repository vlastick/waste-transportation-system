package one.vladimir.impl.services.transport;

import one.vladimir.api.TransportService;
import one.vladimir.api.pojo.Vessel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("transportService")
public class TransportServiceImpl implements TransportService {

    @PostConstruct
    public void postConstructLog(){
        System.out.println("transportService initialized");
    }

    @Override
    public Vessel getVessel(String strId){
        return new Vessel();
    }

    @Override
    public String addVessel(Vessel vessel){
        return "Vessel added";
    }

    @Override
    public String updateVessel(Vessel vessel){
        return "Vessel updated";
    }

}
