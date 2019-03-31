package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
