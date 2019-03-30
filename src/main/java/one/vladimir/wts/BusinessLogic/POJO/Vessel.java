package one.vladimir.wts.BusinessLogic.POJO;

import java.util.Set;

public class Vessel {
    private String name;

    private Set<Crewman> crew;

    private Route currRoute;

    private Integer capacity;

    private Integer length, width;


    //    getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Crewman> getCrew() {
        return crew;
    }

    public void setCrew(Set<Crewman> crew) {
        this.crew = crew;
    }

    public Route getCurrRoute() {
        return currRoute;
    }

    public void setCurrRoute(Route currRoute) {
        this.currRoute = currRoute;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
