package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Base;

import javax.persistence.*;

@Entity
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baseId;

    @OneToOne
    @JoinColumn(name = "PointId")
    private PointEntity point;


    //    getters and setters

    //POJO setter
    public void setBase(Base base) {
        this.setBaseId(base.getId());
    }

    //POJO getter
    public Base getBase() {
        Base base = new Base();
        base.setId(this.getBaseId());
        return base;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    public PointEntity getPoint() {
        return point;
    }

    public void setPoint(PointEntity point) {
        this.point = point;
    }
}