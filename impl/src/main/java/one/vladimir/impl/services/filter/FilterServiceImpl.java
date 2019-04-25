package one.vladimir.impl.services.filter;

import one.vladimir.api.FilterService;
import one.vladimir.api.pojo.DumpFilter;
import one.vladimir.api.pojo.RouteFilter;
import org.springframework.stereotype.Service;

@Service("filterService")
public class FilterServiceImpl implements FilterService {

    public DumpFilter createDumpFilterFromJson(String jsonString) {
        return new DumpFilter();
    }

    public RouteFilter createRouteFilterFromJson(String jsonString) {
        return new RouteFilter();
    }


}
