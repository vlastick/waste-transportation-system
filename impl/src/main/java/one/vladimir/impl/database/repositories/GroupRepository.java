package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.GroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {

    @Query("SELECT g.groupId FROM GroupEntity g")
    List<Integer> findAllIds();

}
