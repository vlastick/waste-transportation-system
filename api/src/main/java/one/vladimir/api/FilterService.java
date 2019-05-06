package one.vladimir.api;

import one.vladimir.api.pojo.BaseFilter;
import one.vladimir.api.pojo.DumpFilter;
import one.vladimir.api.pojo.RouteFilter;

public interface FilterService {

    public DumpFilter createDumpFilterFromJson(String jsonString);

    public RouteFilter createRouteFilterFromJson(String jsonString);

    public BaseFilter createBaseFilterFromJson(String jsonString);

}
