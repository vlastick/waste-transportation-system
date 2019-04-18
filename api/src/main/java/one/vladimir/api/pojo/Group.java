package one.vladimir.api.pojo;

public class Group {

    private Integer id;

    public Integer koef;

    private Double leftLongitude;

    private Double topLatitude;

    private Double rightLongitude;

    private Double bottomLatitude;


    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKoef() {
        return koef;
    }

    public void setKoef(Integer koef) {
        this.koef = koef;
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
