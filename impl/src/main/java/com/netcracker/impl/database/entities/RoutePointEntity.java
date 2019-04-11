package com.netcracker.impl.database.entities;

import com.netcracker.api.pojo.RoutePoint;
import com.netcracker.api.pojo.RoutePointStatus;

import javax.persistence.*;

@Entity
public class RoutePointEntity {
    @Id
    @GeneratedValue
    private Integer routePointId;

    private Integer number;

    private String status;

    @ManyToOne
    @JoinColumn(name = "PointId")
    private PointEntity point;

    @ManyToOne
    @JoinColumn(name = "RouteId")
    private RouteEntity route;


    //    getters and setters

    //POJO setter
    public void setRoutePoint(RoutePoint routePoint) {
        this.setRoutePointId(routePoint.getId());
        this.setNumber(routePoint.getNumber());
        this.setStatus(routePoint.getStatus().toString());
    }


    //POJO getter
    public RoutePoint getRoutePoint() {
        RoutePoint routePoint = new RoutePoint();
        routePoint.setId(this.getRoutePointId());
        routePoint.setStatus(RoutePointStatus.valueOf(this.getStatus()));
        routePoint.setNumber(this.getNumber());
        return routePoint;
    }

    public Integer getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Integer routePointId) {
        this.routePointId = routePointId;
    }

    public PointEntity getPoint() {
        return point;
    }

    public void setPoint(PointEntity point) {
        this.point = point;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
