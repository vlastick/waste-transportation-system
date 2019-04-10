package com.netcracker.api;

import com.netcracker.api.pojo.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface PointService {

    String postPoint(Point point);

    String postDump(Dump dump);

    String postBase(Base base);

    String postPoints(List<Point> point);

    String postDumps(List<Dump> dump);

    String postBases(List<Base> base);

    String getDumps(JsonNode filter);

    String testGeo(String request);

    String addUser(String strLogin, String strRole, String strPassword);

    String getUser(String strId);

}
