package one.vladimir.api.pojo;

import one.vladimir.api.enums.RoutePointStatus;

import java.util.Objects;

public class RoutePoint {

    private Integer id;

    private Point containedPoint;

    private RoutePointStatus status;

    private Integer number;


    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePoint that = (RoutePoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
