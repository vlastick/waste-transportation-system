package one.vladimir.wts.DBModule.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Point {
    @Id
    @GeneratedValue
    private Long PointId;

    private Long CreatorId;

    private Long GroupId;

    public Long getPointId() {
        return PointId;
    }

    public void setPointId(Long pointId) {
        PointId = pointId;
    }

    public Long getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(Long creatorId) {
        CreatorId = creatorId;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }
}
