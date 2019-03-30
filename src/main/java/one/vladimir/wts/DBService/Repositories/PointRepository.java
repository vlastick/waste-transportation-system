package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Integer> {
}
