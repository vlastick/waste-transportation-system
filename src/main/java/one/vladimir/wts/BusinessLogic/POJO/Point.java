package one.vladimir.wts.BusinessLogic.POJO;

public class Point {

    private Integer id;

    private Double longitude;

    private Double latitude;

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
}
