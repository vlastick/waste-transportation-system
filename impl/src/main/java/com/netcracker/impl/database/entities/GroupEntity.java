package com.netcracker.impl.database.entities;

import com.netcracker.api.pojo.Group;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pointGroupEntity")
public class GroupEntity {
    @Id
    @GeneratedValue
    private Integer groupId;

    private Integer Koef;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<PointEntity> points;


    //    getters and setters

    //POJO setter
    public void setGroup(Group group) {
        this.setGroupId(group.getId());
        this.setKoef(group.getKoef());
    }

    //POJO getter
    public Group getGroup() {
        Group group = new Group();
        group.setId(this.getGroupId());
        group.setKoef(this.getKoef());
        return group;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getKoef() {
        return Koef;
    }

    public void setKoef(Integer koef) {
        Koef = koef;
    }
}
