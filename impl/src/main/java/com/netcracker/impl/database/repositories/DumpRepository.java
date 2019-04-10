package com.netcracker.impl.database.repositories;

import com.netcracker.impl.database.entities.DumpEntity;
import org.springframework.data.repository.CrudRepository;

public interface DumpRepository extends CrudRepository<DumpEntity, Integer> {
}
