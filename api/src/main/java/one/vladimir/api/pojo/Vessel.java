package one.vladimir.api.pojo;

public class Vessel {

    private Integer id;

    private String name;

    private Route currRoute;

    private Integer capacity;

    private Integer currentLoad;

    //    getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(Integer currentLoad) {
        this.currentLoad = currentLoad;
    }
}
