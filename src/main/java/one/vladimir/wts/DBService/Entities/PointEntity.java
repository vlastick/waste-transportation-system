package one.vladimir.wts.DBService.Entities;

import one.vladimir.wts.BusinessLogic.POJO.Point;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class PointEntity {
    @Id
    @GeneratedValue
    private Integer pointId;

    private Double longitude;

    private Double latitude;

    @ManyToOne
    @JoinColumn(name = "CreatorId")
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "GroupId")
    private GroupEntity group;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private DumpEntity dump;

    @OneToOne(mappedBy = "point", cascade = CascadeType.ALL)
    private BaseEntity base;

    @OneToMany(mappedBy = "point", cascade = CascadeType.ALL)
    private Collection<RoutePointEntity> routePoints;




    //    getters and setters

    //POJO setter
    public void setPoint(Point point) {
        this.pointId = point.getId();
        this.longitude = point.getLongitude();
        this.latitude = point.getLatitude();
    }

    //POJO getter
    public Point getPoint() {
        Point point = new Point();
        point.setId(this.pointId);
        point.setLatitude(this.latitude);
        point.setLongitude(this.longitude);
        return point;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
