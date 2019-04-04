package one.vladimir.wts.BusinessLogic;

import com.fasterxml.jackson.databind.JsonNode;
import one.vladimir.wts.BusinessLogic.POJO.Base;
import one.vladimir.wts.BusinessLogic.POJO.Dump;
import one.vladimir.wts.BusinessLogic.POJO.Point;

import java.util.List;

public interface BusinessLogicInterface {

    String postPoint(Point point);

    String postDump(Dump dump);

    String postBase(Base base);

    String postPoints(List<Point> point);

    String postDumps(List<Dump> dump);

    String postBases(List<Base> base);

    String getDumps(JsonNode filter);

    String testGeo(String request);
}
