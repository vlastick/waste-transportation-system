package com.netcracker.api;

import com.netcracker.api.pojo.*;

public interface Database {

    public void addUser(User user);

    public User getUserById(Integer id);

    public void addPoint(Point point, User creator, Group group);

    public Point getPointById(Integer id);

    public void addGroup(Group group);

    public Group getGroupById(Integer id);

    public void addDump(Dump dump);

    public Dump getDumpById(Integer id);

    public void addBase(Base base);

    public Base getBaseById(Integer id);

    public void addCrewman(Crewman crewman);

    public Crewman getCrewmanById(Integer id);

    public void addRoute(Route route, Vessel vessel);

    public Route getRouteById(Integer id);

    public void addRoutePoint(RoutePoint routePoint, Route route);

    public RoutePoint getRoutePointById(Integer id);

    public void addVessel(Vessel vessel);

    public Vessel getVesselById(Integer id);

}
