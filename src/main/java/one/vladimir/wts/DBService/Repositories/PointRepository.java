package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.PointEntity;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<PointEntity, Integer> {
}
