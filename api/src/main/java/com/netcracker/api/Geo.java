package com.netcracker.api;

import com.netcracker.api.pojo.Point;

import java.util.List;

public interface Geo {

    String  getIslandName(Point point);
    Integer getIslandID (Point point);
    Point getIslandCoordinates(String name);
    Point getIslandCoordinates(Integer ID);
    List<Point> getWay(Point A, Point B);

    // Test
    String getResult(String query);
}
