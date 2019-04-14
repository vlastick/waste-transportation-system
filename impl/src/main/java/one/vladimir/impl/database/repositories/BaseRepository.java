package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepository extends CrudRepository<BaseEntity, Integer> {
}
