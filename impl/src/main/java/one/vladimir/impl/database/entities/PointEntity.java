package one.vladimir.impl.database.entities;

import one.vladimir.api.pojo.Point;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;


//TODO: add create and modified dates
@Entity
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pointId;

    private Double longitude;

    private Double latitude;

    private Timestamp updatedWhen;

    private Timestamp createdWhen;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "CreatorId")
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "UpdaterId")
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
        this.updatedWhen = point.getUpdatedWhen();
        this.createdWhen = point.getCreatedWhen();
        this.isActive = point.getActive();
    }

    //POJO getter
    public Point getPoint() {
        Point point = new Point();
        point.setPointId(this.pointId);
        point.setLatitude(this.latitude);
        point.setLongitude(this.longitude);
        point.setCreatedWhen(this.createdWhen);
        point.setUpdatedWhen(this.updatedWhen);
        point.setActive(this.isActive);
        return point;
    }

    //POJO getter for exact Point
    public void getPoint(Point point) {
        point.setPointId(this.pointId);
        point.setLatitude(this.latitude);
        point.setLongitude(this.longitude);
        point.setCreatedWhen(this.createdWhen);
        point.setUpdatedWhen(this.updatedWhen);
        point.setActive(this.isActive);
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
