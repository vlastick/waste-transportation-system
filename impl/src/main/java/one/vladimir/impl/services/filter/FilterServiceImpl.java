package one.vladimir.impl.services.filter;

import com.fasterxml.jackson.databind.util.JSONPObject;
import one.vladimir.api.FilterService;
import one.vladimir.api.enums.DumpType;
import one.vladimir.api.pojo.DumpFilter;
import one.vladimir.api.pojo.RouteFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Service("filterService")
public class FilterServiceImpl implements FilterService {

    @PostConstruct
    public void postConstructLog() {
        System.out.println("filterService initialized");
    }

    public DumpFilter createDumpFilterFromJson(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        DumpFilter dumpFilter = new DumpFilter();
        List<Integer> pointIdList = null;
        List<Integer> groupIdList = null;
        List<String> dumpTypeList = null;
        Boolean isActive = null;
        try {
            Object object = jsonParser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray pointIdsJson = (JSONArray) jsonObject.get("pointIdList");
            JSONArray groupIdsJson = (JSONArray) jsonObject.get("groupIdList");
            JSONArray dumpTypesJson = (JSONArray) jsonObject.get("dumpTypeList");

            isActive = (Boolean) jsonObject.get("isActive");
            Iterator iterator;
            if (pointIdsJson != null) {
                iterator = pointIdsJson.iterator();
                pointIdList = new Vector<Integer>();
                while (iterator.hasNext()) {
                    JSONObject idJson = (JSONObject) iterator.next();
                    Integer id = ((Long) idJson.get("id")).intValue();
                    pointIdList.add(id);
                }
                if (pointIdList.size() == 0) {
                    pointIdList = null;
                }
            }
            if (groupIdsJson != null) {
                iterator = groupIdsJson.iterator();
                groupIdList = new Vector<Integer>();
                while (iterator.hasNext()) {
                    JSONObject idJson = (JSONObject) iterator.next();
                    Integer id = ((Long) idJson.get("id")).intValue();
                    groupIdList.add(id);
                }
                if (groupIdList.size() == 0) {
                    groupIdList = null;
                }
            }
            if (dumpTypesJson != null) {
                iterator = dumpTypesJson.iterator();
                dumpTypeList = new Vector<String>();
                while (iterator.hasNext()) {
                    JSONObject dumpTypeJson = (JSONObject) iterator.next();
                    String dumpType = (String) dumpTypeJson.get("dumpType");
                    dumpTypeList.add(dumpType);
                }
                if (dumpTypeList.size() == 0) {
                    dumpTypeList = null;
                }
            }


        } catch (ParseException e) {
            System.out.println("Parsing exception");
        }

        dumpFilter.setPointIdList(pointIdList);
        dumpFilter.setGroupidList(groupIdList);
        dumpFilter.setDumpTypeList(dumpTypeList);
        dumpFilter.setActive(isActive);
        return dumpFilter;
    }

    public RouteFilter createRouteFilterFromJson(String jsonString) {
        JSONParser jsonParser = new JSONParser();
        RouteFilter routeFilter = new RouteFilter();
        List<Integer> routeIdList = null;
        List<Integer> vesselIdList = null;
        List<String> routeStatusList = null;
        try {
            Object object = jsonParser.parse(jsonString);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray routeIdsJson = (JSONArray) jsonObject.get("routeIdList");
            JSONArray vesselIdsJson = (JSONArray) jsonObject.get("vesselIdList");
            JSONArray routeStatusesJson = (JSONArray) jsonObject.get("routeStatusList");

            Iterator iterator;
            if (routeIdsJson != null) {
                iterator = routeIdsJson.iterator();
                routeIdList = new Vector<Integer>();
                while (iterator.hasNext()) {
                    JSONObject idJson = (JSONObject) iterator.next();
                    Integer id = ((Long) idJson.get("id")).intValue();
                    routeIdList.add(id);
                }
                if (routeIdList.size() == 0) {
                    routeIdList = null;
                }
            }
            if (vesselIdsJson != null) {
                iterator = vesselIdsJson.iterator();
                vesselIdList = new Vector<Integer>();
                while (iterator.hasNext()) {
                    JSONObject idJson = (JSONObject) iterator.next();
                    Integer id = ((Long) idJson.get("id")).intValue();
                    vesselIdList.add(id);
                }
                if (vesselIdList.size() == 0) {
                    vesselIdList = null;
                }
            }
            if (routeStatusesJson != null) {
                iterator = routeStatusesJson.iterator();
                routeStatusList = new Vector<String>();
                while (iterator.hasNext()) {
                    JSONObject routeStatusJson = (JSONObject) iterator.next();
                    String routeStatus = (String) routeStatusJson.get("routeStatus");
                    routeStatusList.add(routeStatus);
                }
                if (routeStatusList.size() == 0) {
                    routeStatusList = null;
                }
            }


        } catch (ParseException e) {
            System.out.println("Parsing exception");
        }

        routeFilter.setRouteIdList(routeIdList);
        routeFilter.setVesselIdList(vesselIdList);
        routeFilter.setRouteStatusList(routeStatusList);
        return routeFilter;
    }


}
