package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.RouteEntity;
import one.vladimir.impl.database.entities.VesselEntity;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<RouteEntity, Integer> {

    RouteEntity findRouteByVesselVesselId(Integer vesselId);

    RouteEntity findRouteByVessel(VesselEntity vessel);

}
