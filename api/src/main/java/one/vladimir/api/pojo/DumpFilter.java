package one.vladimir.api.pojo;

import java.util.List;

public class DumpFilter extends PointFilter {

    private List<String> dumpTypeList;

    private Integer maxSize;

    public List<String> getDumpTypeList() {
        return dumpTypeList;
    }

    public void setDumpTypeList(List<String> dumpTypeList) {
        this.dumpTypeList = dumpTypeList;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }
}
