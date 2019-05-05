package one.vladimir.api;

import one.vladimir.api.pojo.*;

import java.util.List;
import java.util.Set;

public interface Database {

    //add requests

    public Integer addUser(User user);

    public Integer addPoint(Point point, User creator, Group group);

    public Integer addGroup(Group group);

    public Integer addDump(Dump dump);

    public Integer addBase(Base base);

    public Integer addCrewman(Crewman crewman);

    public Integer addRoute(Route route, Vessel vessel);

    public Integer addRoutePoint(RoutePoint routePoint, Route route);

    public Integer addVessel(Vessel vessel);

    //get requests

    public User getUserById(Integer id);

    public Point getPointById(Integer id);

    public Group getGroupById(Integer id);

    public Dump getDumpById(Integer id);

    public Base getBaseById(Integer id);

    public Crewman getCrewmanById(Integer id);

    public Route getRouteById(Integer id);

    public RoutePoint getRoutePointById(Integer id);

    public Vessel getVesselById(Integer id);

    public User getUserByLogin(String login);

    public Vessel getVesselByCrewmanId(Integer id);


    /**
     * get group that contains point with coordinates
     *
     * @return group pojo
     */
    public Group getGroupByCoordinates(Double latitude, Double longitude);

    public List<Dump> getAllDumps();

    public List<Base> getAllBases();

    public List<Dump> getDumpsByIds(List<Integer> ids);

    public List<Base> getBasesByIds(List<Integer> ids);

    public Set<RoutePoint> getRoutePointsByRouteId(Integer id);

    public List<Dump> getDumpsByFilter(DumpFilter dumpFilter);

    public List<Route> getRoutesByFilter(RouteFilter routeFilter);

    public List<RoutePoint> getRoutePointsByPointId(Integer id);

    //update requests

    public void updateUser(User user);

    public void updatePoint(Point point, User creator, Group group);

    public void updatePoint(Point point, User updater);

    public void updateGroup(Group group);

    public void updateDump(Dump dump);

    public void updateBase(Base base);

    public void updateCrewman(Crewman crewman);

    public void updateRoute(Route route, Vessel vessel);

    public void updateRoutePoint(RoutePoint routePoint);

    public void updateVessel(Vessel vessel);

}
