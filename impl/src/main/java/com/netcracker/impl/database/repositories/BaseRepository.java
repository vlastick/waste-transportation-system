package com.netcracker.impl.database.repositories;

import com.netcracker.impl.database.entities.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseRepository extends CrudRepository<BaseEntity, Integer> {
}
