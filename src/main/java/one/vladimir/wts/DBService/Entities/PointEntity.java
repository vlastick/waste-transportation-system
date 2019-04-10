package one.vladimir.wts.DBService.Entities;

import one.vladimir.wts.BusinessLogic.POJO.Point;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;


//TODO: add create and modified dates
@Entity
public class PointEntity {
    @Id
    @GeneratedValue
    private Integer pointId;

    private Double longitude;

    private Double latitude;

    private Timestamp updatedWhen;

    private Timestamp createdWhen;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy")
    private UserEntity updatedBy;

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
        this.pointId = point.getPointId();
        this.longitude = point.getLongitude();
        this.latitude = point.getLatitude();
    }

    //POJO getter
    public Point getPoint() {
        Point point = new Point();
        point.setPointId(this.pointId);
        point.setLatitude(this.latitude);
        point.setLongitude(this.longitude);
        return point;
    }

    //POJO getter for exact Point
    public Point getPoint(Point point) {
        point.setPointId(this.pointId);
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

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public UserEntity getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserEntity updatedBy) {
        this.updatedBy = updatedBy;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Timestamp getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Timestamp updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    public Timestamp getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Timestamp createdWhen) {
        this.createdWhen = createdWhen;
    }
}
