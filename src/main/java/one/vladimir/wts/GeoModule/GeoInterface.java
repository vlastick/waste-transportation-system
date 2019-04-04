package one.vladimir.wts.GeoModule;

import one.vladimir.wts.BusinessLogic.POJO.Point;

import java.util.List;

public interface GeoInterface {
    String  getIslandName(Point point);
    Integer getIslandID (Point point);
    Point getIslandCoordinates(String name);
    Point getIslandCoordinates(Integer ID);
    List<Point> getWay(Point A, Point B);

    // Test
    String getResult(String query);
}
