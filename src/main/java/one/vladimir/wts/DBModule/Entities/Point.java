package one.vladimir.wts.DBModule.Entities;

import javax.persistence.*;

@Entity
public class Point {
    @Id
    @GeneratedValue
    private Integer PointId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CreatorId")
    private User Creator;

    private Integer GroupId;

    public Integer getPointId() {
        return PointId;
    }

    public void setPointId(Integer pointId) {
        PointId = pointId;
    }

    public User getCreator() {
        return Creator;
    }

    public void setCreator(User creator) {
        Creator = creator;
    }

    public Integer getGroupId() {
        return GroupId;
    }

    public void setGroupId(Integer groupId) {
        GroupId = groupId;
    }
}
