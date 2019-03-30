package one.vladimir.wts.DBService.Entities;

import javax.persistence.*;

@Entity
public class Dump {
    @Id
    @GeneratedValue
    private Integer dumpId;

    @OneToOne
    @JoinColumn(name = "PointId")
    private Point point;


    //    getters and setters
    public Integer getDumpId() {
        return dumpId;
    }

    public void setDumpId(Integer dumpId) {
        this.dumpId = dumpId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
