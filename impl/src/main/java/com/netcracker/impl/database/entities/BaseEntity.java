package com.netcracker.impl.database.entities;

import com.netcracker.api.pojo.Base;

import javax.persistence.*;

@Entity
public class BaseEntity {
    @Id
    @GeneratedValue
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