package one.vladimir.api;

import one.vladimir.api.pojo.BaseFilter;
import one.vladimir.api.pojo.DumpFilter;
import one.vladimir.api.pojo.RouteFilter;

public interface FilterService {

    DumpFilter createDumpFilterFromJson(String jsonString);

    RouteFilter createRouteFilterFromJson(String jsonString);

    BaseFilter createBaseFilterFromJson(String jsonString);

}
