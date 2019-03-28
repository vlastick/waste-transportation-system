package one.vladimir.wts.DBModule.Entities;

import javax.persistence.*;

@Entity
public class Base {
    @Id
    @GeneratedValue
    private Integer baseId;

    @OneToOne
    @JoinColumn(name = "PointId")
    private Point point;

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}