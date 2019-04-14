package one.vladimir.api;

import com.fasterxml.jackson.databind.JsonNode;
import one.vladimir.api.pojo.Base;
import one.vladimir.api.pojo.Dump;
import one.vladimir.api.pojo.Route;
import one.vladimir.api.pojo.Vessel;

import java.util.List;

public interface PointService {

    // Post point
    String addDump(Dump dump);

    // Post point
    String addBase(Base base);

    // Get point
    Dump getDump(Integer id);

    // Get point
    Base getBase(Integer id);

    // Get points
    List <Dump> getDumps();

    // Get points
    List <Base> getBases();

    // Get route
    Route getRoute(Integer id);

    // Get routes
    List<Route> getRoutes();

    // Post route
    String addRoute(Route route);

    // Put point
    String updateDump(Dump dump);

    // Put point
    String updateBase(Base base);

    // Put route
    String updateRoute(Route route);

    // Post vessel
    String addVessel(Vessel vessel);

    // Get vessel
    Vessel getVessel(Integer id);

    // Put vessel
    String updateVessel(Vessel vessel);

    String getDumps(JsonNode filter);

    String testGeo(String request);

    String addUser(String strLogin, String strRole, String strPassword);

    String getUser(String strId);

}
