package one.vladimir.api.pojo;

import java.util.List;

public class PointFilter {

    private List<Integer> pointIdList;

    private List<Integer> groupidList;

    private List<Integer> creatorsIdList;

    private Boolean isActive;

    public List<Integer> getPointIdList() {
        return pointIdList;
    }

    public void setPointIdList(List<Integer> pointIdList) {
        this.pointIdList = pointIdList;
    }

    public List<Integer> getGroupidList() {
        return groupidList;
    }

    public void setGroupidList(List<Integer> groupidList) {
        this.groupidList = groupidList;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Integer> getCreatorsIdList() {
        return creatorsIdList;
    }

    public void setCreatorsIdList(List<Integer> creatorsIdList) {
        this.creatorsIdList = creatorsIdList;
    }
}
