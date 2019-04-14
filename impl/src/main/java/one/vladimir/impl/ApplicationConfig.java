package one.vladimir.impl;

import one.vladimir.api.Database;
import one.vladimir.api.Geo;
import one.vladimir.api.PointService;
import one.vladimir.impl.database.DatabaseImpl;
import one.vladimir.impl.geo.GeoImpl;
import one.vladimir.impl.services.point.PointServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class ApplicationConfig {

    @PostConstruct
    public void testMethod(){
        System.out.println("ApplicationConfig postconstruct");
    }

    @Bean(name = "geo")
    Geo getGeo(){

        return new GeoImpl();
    }

    @Bean(name = "pointService")
    PointService getPointService(){
        return new PointServiceImpl();
    }

    @Bean(name = "database")
    Database getDatabase(){
        return new DatabaseImpl();
    }
}
