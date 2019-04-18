package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.BaseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BaseRepository extends CrudRepository<BaseEntity, Integer> {

    @Query("Select b FROM BaseEntity b")
    List<BaseEntity> findAllBases();

    List<BaseEntity> findBasesByBaseIdIn(List<Integer> ids);

}
