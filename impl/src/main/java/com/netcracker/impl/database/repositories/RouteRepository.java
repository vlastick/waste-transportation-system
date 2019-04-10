package com.netcracker.impl.database.repositories;

import com.netcracker.impl.database.entities.RouteEntity;
import com.netcracker.impl.database.entities.VesselEntity;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends CrudRepository<RouteEntity, Integer> {

    RouteEntity findRouteByVesselVesselId(Integer vesselId);

    RouteEntity findRouteByVessel(VesselEntity vessel);

}
