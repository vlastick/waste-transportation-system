package one.vladimir.wts.entity;

public class Point {
    private final Integer id;
    private Double latitude;
    private Double longitude;

    public Point(Integer id, Double latitude, Double longitude){
        if(id >= 0) {
            this.id = id;
        }else{
            this.id = 0;
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
}
