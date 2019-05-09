package one.vladimir.api;

import one.vladimir.api.pojo.*;

import java.util.List;
import java.util.Set;

public interface Database {

    //add requests

    Integer addUser(User user);

    Integer addPoint(Point point, User creator, Group group);

    Integer addGroup(Group group);

    Integer addDump(Dump dump);

    Integer addBase(Base base);

    Integer addCrewman(Crewman crewman);

    Integer addRoute(Route route, Vessel vessel);

    Integer addRoutePoint(RoutePoint routePoint, Route route);

    Integer addVessel(Vessel vessel);

    //get requests

    User getUserById(Integer id);

    Point getPointById(Integer id);

    Group getGroupById(Integer id);

    Dump getDumpById(Integer id);

    Base getBaseById(Integer id);

    Crewman getCrewmanById(Integer id);

    Route getRouteById(Integer id);

    RoutePoint getRoutePointById(Integer id);

    Vessel getVesselById(Integer id);

    User getUserByLogin(String login);

    Vessel getVesselByCrewmanId(Integer id);


    /**
     * get group that contains point with coordinates
     *
     * @return group pojo
     */
    Group getGroupByCoordinates(Double latitude, Double longitude);

    List<Dump> getAllDumps();

    List<Base> getAllBases();

    List<Dump> getDumpsByIds(List<Integer> ids);

    List<Base> getBasesByIds(List<Integer> ids);

    Set<RoutePoint> getRoutePointsByRouteId(Integer id);

    List<Dump> getDumpsByFilter(DumpFilter dumpFilter);

    List<Base> getBasesByFilter(BaseFilter baseFilter);

    List<Route> getRoutesByFilter(RouteFilter routeFilter);

    List<RoutePoint> getRoutePointsByPointId(Integer id);

    //update requests

    void updateUser(User user);

    void updatePoint(Point point, User creator, Group group);

    void updatePoint(Point point, User updater);

    void updateGroup(Group group);

    void updateDump(Dump dump);

    void updateBase(Base base);

    void updateCrewman(Crewman crewman);

    void updateRoute(Route route, Vessel vessel);

    void updateRoutePoint(RoutePoint routePoint);

    void updateVessel(Vessel vessel);

}
