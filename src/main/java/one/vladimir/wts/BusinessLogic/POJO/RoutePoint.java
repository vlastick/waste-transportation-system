package one.vladimir.wts.BusinessLogic.POJO;

public class RoutePoint {

    private Point containedPoint;

    private RoutePointStatus status;

    private Integer number;


    //    getters and setters
    public Point getContainedPoint() {
        return containedPoint;
    }

    public void setContainedPoint(Point containedPoint) {
        this.containedPoint = containedPoint;
    }

    public RoutePointStatus getStatus() {
        return status;
    }

    public void setStatus(RoutePointStatus status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
