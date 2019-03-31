package one.vladimir.wts.BusinessLogic.POJO;

public class Dump extends Point {

    private Integer id;

    private DumpStatus status;

    private Integer priority;

    private DumpType type;

    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(DumpStatus status) {
        this.status = status;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setType(DumpType type) {
        this.type = type;
    }

    public DumpStatus getStatus() {
        return status;
    }

    public Integer getPriority() {
        return priority;
    }

    public DumpType getType() {
        return type;
    }
}
