package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Group;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pointGroupEntity")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    private Integer Koef;

    private Double leftLongitude;

    private Double topLatitude;

    private Double rightLongitude;

    private Double bottomLatitude;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<PointEntity> points;


    //    getters and setters

    //POJO setter
    public void setGroup(Group group) {
        this.setGroupId(group.getId());
        this.setKoef(group.getKoef());
        this.setTopLatitude(group.getTopLatitude());
        this.setBottomLatitude(group.getBottomLatitude());
        this.setLeftLongitude(group.getLeftLongitude());
        this.setRightLongitude(group.getRightLongitude());
    }

    //POJO getter
    public Group getGroup() {
        Group group = new Group();
        group.setId(this.getGroupId());
        group.setKoef(this.getKoef());
        group.setTopLatitude(this.getTopLatitude());
        group.setBottomLatitude(this.getBottomLatitude());
        group.setLeftLongitude(this.getLeftLongitude());
        group.setRightLongitude(this.getRightLongitude());
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

    public Double getLeftLongitude() {
        return leftLongitude;
    }

    public void setLeftLongitude(Double leftLongitude) {
        this.leftLongitude = leftLongitude;
    }

    public Double getTopLatitude() {
        return topLatitude;
    }

    public void setTopLatitude(Double topLatitude) {
        this.topLatitude = topLatitude;
    }

    public Double getRightLongitude() {
        return rightLongitude;
    }

    public void setRightLongitude(Double rightLongitude) {
        this.rightLongitude = rightLongitude;
    }

    public Double getBottomLatitude() {
        return bottomLatitude;
    }

    public void setBottomLatitude(Double bottomLatitude) {
        this.bottomLatitude = bottomLatitude;
    }
}
