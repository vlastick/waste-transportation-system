package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.DumpEntity;
import org.springframework.data.repository.CrudRepository;

public interface DumpRepository extends CrudRepository<DumpEntity, Integer> {
}
