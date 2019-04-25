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
                    JSONObject dumptypeJson = (JSONObject) iterator.next();
                    String dumpType = (String) dumptypeJson.get("dumpType");
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
        return new RouteFilter();
    }


}
