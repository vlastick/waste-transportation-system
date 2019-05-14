package one.vladimir.api;

import one.vladimir.api.pojo.Vessel;

public interface TransportService {


    /**
     * Finds Vessel with given id.
     * @param id id of Vessel
     * @return initialized Vessel Object
     */
    Vessel getVessel(Integer id);


    /**
     * Updates Vessel with vessel.id in DataBase
     * Updated are only following fields: name, currentLoad, capacity, latitude, longitude.
     * @param vessel Vessel object, that contains information for update. id field is mandatory.
     * @return information message.
     */
    String updateVessel(Vessel vessel);


    /**
     * Updates vessel coordinates.
     * @param vesselId id of Vessel, that will be updated.
     * @param latitude current latitude.
     * @param longitude current longitude.
     * @return information message.
     */
    String updateCoordinates(Integer vesselId, Double latitude, Double longitude);


    /**
     * Finds Vessel with given Crewman
     * @param id id of Crewman
     * @return initialized Vessel object.
     */
    Vessel getVesselByCrewmanId(Integer id);


    String addVessel(Vessel vessel);

}
