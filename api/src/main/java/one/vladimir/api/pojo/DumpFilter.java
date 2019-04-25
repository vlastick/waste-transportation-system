package one.vladimir.api.pojo;

import one.vladimir.api.enums.DumpType;

import java.util.List;

public class DumpFilter extends PointFilter {

    private List<DumpType> dumpTypeList;

    public List<DumpType> getDumpTypeList() {
        return dumpTypeList;
    }

    public void setDumpTypeList(List<DumpType> dumpTypeList) {
        this.dumpTypeList = dumpTypeList;
    }
}
