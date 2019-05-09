package one.vladimir.api;

import one.vladimir.api.pojo.Vessel;

public interface TransportService {

    Vessel getVessel(Integer id);

    String addVessel(Vessel vessel);

    String updateVessel(Vessel vessel);

    String updateCoordinates(Integer vesselId, Double latitude, Double longitude);

    Vessel getVesselByCrewmanId(Integer id);

}
