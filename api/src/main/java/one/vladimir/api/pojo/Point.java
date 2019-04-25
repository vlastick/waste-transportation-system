package one.vladimir.api.pojo;

import java.sql.Timestamp;

public class Point {

    private Integer id;

    private Double longitude;

    private Double latitude;

    private User createdBy;

    private User updatedBy;

    private Group group;

    private Timestamp createdWhen;

    private Timestamp updatedWhen;

    private Boolean isActive;

    //    getters and setters
    public Integer getPointId() {
        return id;
    }

    public void setPointId(Integer id) {
        this.id = id;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Timestamp createdWhen) {
        this.createdWhen = createdWhen;
    }

    public Timestamp getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Timestamp updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
