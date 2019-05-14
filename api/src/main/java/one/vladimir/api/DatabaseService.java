package one.vladimir.api;

import one.vladimir.api.pojo.*;

import java.util.List;
import java.util.Set;

public interface DatabaseService {


    //add requests

    /**
     * adds new User to Database.
     * @param user object that contains information about user.
     * @return id of added User.
     */
    Integer addUser(User user);


    /**
     * adds new Point associated with group and user to Database.
     * @param point object that contains information about point.
     * @param creator object that contains information about creator. Only id field is mandatory.
     * @param group object that contains information about group. Only id field is mandatory.
     * @return id of added Point.
     */
    Integer addPoint(Point point, User creator, Group group);


    /**
     * adds new Group to Database.
     * @param group object that contains information about group.
     * @return id of added Group.
     */
    Integer addGroup(Group group);


    /**
     * adds new Dump to Database.
     * @param dump object that contains information about dump.
     * @return id of added Dump.
     */
    Integer addDump(Dump dump);


    /**
     * adds new Base to Database.
     * @param base object that contains information about base.
     * @return id of added Base.
     */
    Integer addBase(Base base);


    /**
     * adds new Crewman to Database.
     * @param crewman object that contains information about crewman.
     * @return id of added Crewman.
     */
    Integer addCrewman(Crewman crewman);


    /**
     * adds new Route associated with Vessel to Database.
     * @param route object that contains information about route.
     * @param vessel object that contains information about vessel. Only id field is mandatory.
     * @return id of added Route.
     */
    Integer addRoute(Route route, Vessel vessel);


    /**
     * adds new RoutePoint associated with Route to Database.
     * @param routePoint object that contains information about routePoint.
     * @param route object that contains information about Route. Only id field is mandatory.
     * @return id of added RoutePoint.
     */
    Integer addRoutePoint(RoutePoint routePoint, Route route);


    /**
     * adds new Vessel to Database.
     * @param vessel  object that contains information about vessel.
     * @return id of added Vessel.
     */
    Integer addVessel(Vessel vessel);


    //get requests


    /**
     * finds User based on id.
     * @param id id of User.
     * @return initialized User object.
     */
    User getUserById(Integer id);


    /**
     * finds Point based on id.
     * @param id id of Point.
     * @return initialized Point object.
     */
    Point getPointById(Integer id);


    /**
     * finds Group based on id.
     * @param id id of Group.
     * @return initialized Group object.
     */
    Group getGroupById(Integer id);


    /**
     * finds Dump based on id.
     * @param id id of Dump.
     * @return initialized Dump object.
     */
    Dump getDumpById(Integer id);


    /**
     * finds Base based on id.
     * @param id id of Base.
     * @return initialized Base object.
     */
    Base getBaseById(Integer id);


    /**
     * finds Crewman based on id.
     * @param id id of Crewman.
     * @return initialized Crewman object.
     */
    Crewman getCrewmanById(Integer id);


    /**
     * finds Route based on id.
     * @param id id of Route.
     * @return initialized Route object.
     */
    Route getRouteById(Integer id);


    /**
     * finds RoutePoint based on id.
     * @param id id of RoutePoint.
     * @return initialized RoutePoint object.
     */
    RoutePoint getRoutePointById(Integer id);


    /**
     * finds Vessel based on id.
     * @param id id of Vessel.
     * @return initialized Vessel object.
     */
    Vessel getVesselById(Integer id);


    /**
     * finds User with given login.
     * @param login login of User.
     * @return initialized User object.
     */
    User getUserByLogin(String login);


    /**
     * finds Vessel that has Crewman with given id.
     * @param id id of Crewman.
     * @return initialized Vessel object.
     */
    Vessel getVesselByCrewmanId(Integer id);


    /**
     * finds Group, that contains given coordinates.
     * @param latitude latitude coordinate.
     * @param longitude longitude coordinate.
     * @return initialized Group object.
     */
    Group getGroupByCoordinates(Double latitude, Double longitude);


    /**
     * finds RoutePoints of given Route.
     * @param id id of Route.
     * @return Set of initialized RoutePoint objects.
     */
    Set<RoutePoint> getRoutePointsByRouteId(Integer id);


    /**
     * finds Dumps based on DumpFilter.
     * @param dumpFilter object that contains information about needed conditions.
     * @return List of Dumps that fit the DumpFilter.
     */
    List<Dump> getDumpsByFilter(DumpFilter dumpFilter);


    /**
     * finds Bases based on BaseFilter.
     * @param baseFilter object that contains information about needed conditions.
     * @return List of Bases that fit the BaseFilter.
     */
    List<Base> getBasesByFilter(BaseFilter baseFilter);


    /**
     * finds Routes based on RouteFilter.
     * @param routeFilter object that contains information about needed conditions.
     * @return List of Routes that fit the RouteFilter.
     */
    List<Route> getRoutesByFilter(RouteFilter routeFilter);


    /**
     * finds all RoutePoints that contain Point with given id.
     * @param id id of Point.
     * @return List of initialized RoutePoint objects.
     */
    List<RoutePoint> getRoutePointsByPointId(Integer id);


    //update requests


    /**
     * updates User in Database.
     * @param user object, that contains information about user.
     */
    void updateUser(User user);


    /**
     * updates Point in Database and associates it with User and Group.
     * @param point object, that contains information about point.
     * @param creator object that contains information about user. Only id field is mandatory.
     * @param group object that contains information about group. Only id field is mandatory.
     */
    void updatePoint(Point point, User creator, Group group);


    /**
     * updates Point in Database and associates it with User.
     * @param point object, that contains information about point.
     * @param updater object that contains information about user. Only id field is mandatory.
     */
    void updatePoint(Point point, User updater);


    /**
     * updates Group in Database.
     * @param group object, that contains information about group.
     */
    void updateGroup(Group group);


    /**
     * updates Dump in Database.
     * @param dump object, that contains information about dump.
     */
    void updateDump(Dump dump);


    /**
     * updates Base in Database.
     * @param base object, that contains information about base.
     */
    void updateBase(Base base);


    /**
     * updates Crewman in Database.
     * @param crewman object, that contains information about crewman.
     */
    void updateCrewman(Crewman crewman);


    /**
     * updates Route in Database and associates it with Vessel.
     * @param route object, that contains information about route.
     * @param vessel object that contains information about vessel. Only id field is mandatory.
     */
    void updateRoute(Route route, Vessel vessel);


    /**
     * updates RoutePoint in Database.
     * @param routePoint object, that contains information about routePoint.
     */
    void updateRoutePoint(RoutePoint routePoint);


    /**
     * updates Vessel in Database.
     * @param vessel object, that contains information about vessel.
     */
    void updateVessel(Vessel vessel);

}
