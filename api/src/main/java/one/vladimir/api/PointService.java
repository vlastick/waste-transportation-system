package one.vladimir.api;

import one.vladimir.api.pojo.*;

import java.util.List;

public interface PointService {

    // Post point
    String addDump(Dump dump, User creator);

    // Post point
    String addBase(Base base, User creator);

    // Get point
    Dump getDump(Integer id);

    // Get point
    Base getBase(Integer id);

    // Get points
    List <Dump> getDumpsByFilter(DumpFilter dumpFilter);

    // Get points
    List <Base> getBasesByFilter(BaseFilter baseFilter);


    // Get points
    List <Base> getBases();

    // Put point
    String updateDump(Dump dump, User updater);

    // Put point
    String updateBase(Base base, User updater);

    //String getDumps(JsonNode filter);

    //String getBases(JsonNode filter);

    String testGeo(String request);

    String addGroup(Group group);

    String updateGroup(Group group);

    Group getGroup(Integer id);

}
