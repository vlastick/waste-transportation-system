package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.PointEntity;
import one.vladimir.wts.DBService.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<PointEntity, Integer> {

    @Query("SELECT p FROM PointEntity p where p.creator.userId = ?1")
    List<PointEntity> findPointsByCreatorId(Integer creatorId);

    List<PointEntity> findPointsByCreator(UserEntity creator);

    @Query("SELECT p.pointId FROM PointEntity p")
    List<Integer> findAllIds();
}
