package com.netcracker.impl.database.entities;

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