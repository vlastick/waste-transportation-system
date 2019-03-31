package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {
}
