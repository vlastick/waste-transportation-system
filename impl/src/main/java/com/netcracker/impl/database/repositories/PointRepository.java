package com.netcracker.impl.database.repositories;

import com.netcracker.impl.database.entities.GroupEntity;
import com.netcracker.impl.database.entities.PointEntity;
import com.netcracker.impl.database.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<PointEntity, Integer> {

    @Query("SELECT p FROM PointEntity p where p.createdBy.userId = ?1")
    List<PointEntity> findPointsByCreatedById(Integer creatorId);

    List<PointEntity> findPointsByCreatedBy(UserEntity createdBy);

    @Query("SELECT p FROM PointEntity p where p.group.groupId = ?1")
    List<PointEntity> findPointsByGroupId(Integer groupId);

    List<PointEntity> findPointsByGroup(GroupEntity groupEnt);

    @Query("SELECT p.pointId FROM PointEntity p")
    List<Integer> findAllIds();
}
