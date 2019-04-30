package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.RouteEntity;
import one.vladimir.impl.database.entities.RoutePointEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoutePointRepository extends CrudRepository<RoutePointEntity, Integer> {

    List<RoutePointEntity> findRoutePointsByRoute(RouteEntity routeEnt);

    List<RoutePointEntity> findRoutePointsByRouteRouteIdOrderByNumber(Integer routeId);

    List<RoutePointEntity> findRoutePointsByPointPointId(Integer routeId);

}
