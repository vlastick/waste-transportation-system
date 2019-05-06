package one.vladimir.api;

import one.vladimir.api.pojo.Vessel;

public interface TransportService {

    public Vessel getVessel(Integer id);

    public String addVessel(Vessel vessel);

    public String updateVessel(Vessel vessel);

    public String updateCoordinates(Integer vesselId, Double latitude, Double longitude);

    public Vessel getVesselByCrewmanId(Integer id);

}
