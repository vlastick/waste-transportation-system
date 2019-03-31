package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class DumpEntity {
    @Id
    @GeneratedValue
    private Integer dumpId;

    @OneToOne
    @JoinColumn(name = "PointId")
    private PointEntity point;


    //    getters and setters
    public Integer getDumpId() {
        return dumpId;
    }

    public void setDumpId(Integer dumpId) {
        this.dumpId = dumpId;
    }

    public PointEntity getPoint() {
        return point;
    }

    public void setPoint(PointEntity point) {
        this.point = point;
    }
}
