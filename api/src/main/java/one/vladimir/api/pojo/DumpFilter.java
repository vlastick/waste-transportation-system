package one.vladimir.api.pojo;

import one.vladimir.api.enums.DumpType;

import java.util.List;

public class DumpFilter extends PointFilter {

    private List<String> dumpTypeList;

    public List<String> getDumpTypeList() {
        return dumpTypeList;
    }

    public void setDumpTypeList(List<String> dumpTypeList) {
        this.dumpTypeList = dumpTypeList;
    }
}
