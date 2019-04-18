package one.vladimir.api;

import one.vladimir.api.pojo.Vessel;

public interface TransportService {

    public Vessel getVessel(String strId);

    public String addVessel(Vessel vessel);

    public String updateVessel(Vessel vessel);


}
