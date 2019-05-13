package one.vladimir.api;

import one.vladimir.api.pojo.BaseFilter;
import one.vladimir.api.pojo.DumpFilter;
import one.vladimir.api.pojo.RouteFilter;

public interface FilterService {

    /**
     * Creates DumpFilter Object filled with values from json string.
     * If any field in json string is empty or string doesn't contain it,
     * corresponding DumpFilter object field will be initialized with null.
     * @param jsonString Could include following fields:
     *                   "pointIdList" : [Integers],
     *                   "groupIdList" : [Integers],
     *                   "dumpTypeList" : [DumpTypes],
     *                   "creatorsIdList" : [Integers],
     *                   "isActive" : Boolean,
     *                   "maxSize" : Integer
     *
     * @return initialized DumpFilter object
     */
    DumpFilter createDumpFilterFromJson(String jsonString);


    /**
     * Creates RouteFilter Object filled with values from json string.
     * If any field in json string is empty or string doesn't contain it,
     * corresponding RouteFilter object field will be initialized with null.
     * @param jsonString Could include following fields:
     *                   "routeIdList" : [Integers],
     *                   "vesselIdList" : [Integers],
     *                   "routeStatusList" : [RouteStatuses]
     * @return initialized RouteFilter object
     */
    RouteFilter createRouteFilterFromJson(String jsonString);


    /**
     * Creates BaseFilter Object filled with values from json string.
     * If any field in json string is empty or string doesn't contain it,
     * corresponding BaseFilter object field will be initialized with null.
     * @param jsonString Could include following fields:
     *                   "pointIdList" : [Integers],
     *                   "groupIdList" : [Integers],
     *                   "creatorsIdList" : [Integers],
     *                   "isActive" : Boolean
     *
     * @return initialized BaseFilter object
     */
    BaseFilter createBaseFilterFromJson(String jsonString);

}
