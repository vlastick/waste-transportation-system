package one.vladimir.impl.services.route;

import one.vladimir.api.RouteService;

import one.vladimir.api.pojo.Route;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @PostConstruct
    public void postConstructLog(){
        System.out.println("routeService initialized");
    }

    @Override
    public String addRoute(Route route){
        return "Route added";
    }

    @Override
    public String updateRoute(Route route){
        return "Route updated";
    }

    @Override
    public Route getRoute(String strId){
        return new Route();
    }

}
