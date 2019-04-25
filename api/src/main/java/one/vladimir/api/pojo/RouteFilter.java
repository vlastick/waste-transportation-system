package one.vladimir.api.pojo;

import one.vladimir.api.enums.RouteStatus;

import java.util.List;

public class RouteFilter {

    private List<Integer> routeIdList;

    private List<Integer> vesselIdList;

    private List<String> routeStatusList;

    public List<Integer> getRouteIdList() {
        return routeIdList;
    }

    public void setRouteIdList(List<Integer> routeIdList) {
        this.routeIdList = routeIdList;
    }

    public List<Integer> getVesselIdList() {
        return vesselIdList;
    }

    public void setVesselIdList(List<Integer> vesselIdList) {
        this.vesselIdList = vesselIdList;
    }

    public List<String> getRouteStatusList() {
        return routeStatusList;
    }

    public void setRouteStatusList(List<String> routeStatusList) {
        this.routeStatusList = routeStatusList;
    }
}
