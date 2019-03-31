package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.DumpEntity;
import org.springframework.data.repository.CrudRepository;

public interface DumpRepository extends CrudRepository<DumpEntity, Integer> {
}
