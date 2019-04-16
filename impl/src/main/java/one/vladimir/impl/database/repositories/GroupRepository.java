package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.GroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {



    @Query("SELECT g.groupId FROM GroupEntity g")
    List<Integer> findAllIds();

    @Query("Select g FROM GroupEntity g" +
            " WHERE " +
            "((?1 < g.topLatitude AND ?1 > g.bottomLatitude) " +
            "OR" +
            "(?1 > g.topLatitude AND ?1 < g.bottomLatitude))" +
            "AND" +
            "((?2 > g.leftLongitude AND ?2 < g.rightLongitude)" +
            "OR" +
            "(?2 < g.leftLongitude AND ?2 > g.rightLongitude))")
    GroupEntity findGroupByCoordinates(Double latitude, Double longitude);

}
