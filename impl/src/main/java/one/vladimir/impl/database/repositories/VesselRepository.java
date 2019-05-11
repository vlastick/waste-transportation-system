package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.VesselEntity;
import org.springframework.data.repository.CrudRepository;

public interface VesselRepository extends CrudRepository<VesselEntity, Integer> {

    VesselEntity findVesselByCrewmansUserUserId(Integer id);

}
