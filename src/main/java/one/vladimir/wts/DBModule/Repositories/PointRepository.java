package one.vladimir.wts.DBModule.Repositories;

import one.vladimir.wts.DBModule.Entities.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {
}
