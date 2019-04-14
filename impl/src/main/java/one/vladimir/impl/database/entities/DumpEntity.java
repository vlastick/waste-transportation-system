package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Dump;
import one.vladimir.api.pojo.DumpStatus;
import one.vladimir.api.pojo.DumpType;


import javax.persistence.*;

@Entity
public class DumpEntity {
    @Id
    @GeneratedValue
    private Integer dumpId;

    private String status;

    private Integer priority;

    private String type;

    @OneToOne
    @JoinColumn(name = "PointId")
    private PointEntity point;


    //    getters and setters

    //POJO setter
    public void setDump(Dump dump) {
        this.setDumpId(dump.getId());
        this.setPriority(dump.getPriority());
        this.setStatus(dump.getStatus().toString());
        this.setType(dump.getType().toString());
    }

    //POJO getter
    public Dump getDump() {
        Dump dump = new Dump();
        dump.setId(this.getDumpId());
        dump.setPriority(this.getPriority());
        dump.setStatus(DumpStatus.valueOf(this.getStatus()));
        dump.setType(DumpType.valueOf(this.getType()));
        return dump;
    }

    public Integer getDumpId() {
        return dumpId;
    }

    public void setDumpId(Integer dumpId) {
        this.dumpId = dumpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PointEntity getPoint() {
        return point;
    }

    public void setPoint(PointEntity point) {
        this.point = point;
    }
}
