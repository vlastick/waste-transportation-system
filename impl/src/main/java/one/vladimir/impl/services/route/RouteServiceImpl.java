package one.vladimir.impl.services.route;

import one.vladimir.api.RouteService;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    @PostConstruct
    public void postConstructLog(){
        System.out.println("routeService initialized");
    }
}
