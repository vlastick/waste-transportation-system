package one.vladimir.wts.Service.POJO;

public class Dump extends Point {

    private DumpStatus status;

    private Integer priority;

    private DumpType type;


    //    getters and setters
    public DumpStatus getStatus() {
        return status;
    }

    public void setStatus(DumpStatus status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public DumpType getType() {
        return type;
    }

    public void setType(DumpType type) {
        this.type = type;
    }
}
