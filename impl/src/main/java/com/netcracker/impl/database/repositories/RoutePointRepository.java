package com.netcracker.impl.database.repositories;

import com.netcracker.impl.database.entities.RouteEntity;
import com.netcracker.impl.database.entities.RoutePointEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoutePointRepository extends CrudRepository<RoutePointEntity, Integer> {

    List<RoutePointEntity> findRoutePointsByRoute(RouteEntity routeEnt);

    List<RoutePointEntity> findRoutePointsByRouteRouteId(Integer routeId);

}
