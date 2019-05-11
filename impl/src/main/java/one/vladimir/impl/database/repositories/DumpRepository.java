package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.DumpEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DumpRepository extends CrudRepository<DumpEntity, Integer> {

    @Query("Select d FROM DumpEntity d")
    List<DumpEntity> findAllDumps();

    List<DumpEntity> findDumpsByDumpIdIn(List<Integer> ids);

}
