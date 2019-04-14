package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.CrewmanEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrewmanRepository extends CrudRepository<CrewmanEntity, Integer> {
}
