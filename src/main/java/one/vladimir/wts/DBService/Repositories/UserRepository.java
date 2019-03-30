package one.vladimir.wts.DBService.Repositories;

import one.vladimir.wts.DBService.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
