package com.netcracker.api;

import com.netcracker.api.pojo.*;

public interface Database {

    public void addUser(User user);

    public void addPoint(Point point, User creator, Group group);

    public void addGroup(Group group);

    public void addDump(Dump dump);

    public void addBase(Base base);

    public void addCrewman(Crewman crewman);

    public void addRoute(Route route, Vessel vessel);

    public void addRoutePoint(RoutePoint routePoint, Route route);

    public void addVessel(Vessel vessel);

    public User getUserById(Integer id);

    public Point getPointById(Integer id);

    public Group getGroupById(Integer id);

    public Dump getDumpById(Integer id);

    public Base getBaseById(Integer id);

    public Crewman getCrewmanById(Integer id);

    public Route getRouteById(Integer id);

    public RoutePoint getRoutePointById(Integer id);

    public Vessel getVesselById(Integer id);

    public void updateUser(User user);

    public void updatePoint(Point point, User creator, Group group);

    public void updateGroup(Group group);

    public void updateDump(Dump dump);

    public void updateBase(Base base);

    public void updateCrewman(Crewman crewman);

    public void updateRoute(Route route, Vessel vessel);

    public void updateRoutePoint(RoutePoint routePoint);

    public void updateVessel(Vessel vessel);

}
