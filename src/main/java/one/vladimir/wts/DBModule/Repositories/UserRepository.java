package one.vladimir.wts.DBModule.Repositories;

import one.vladimir.wts.DBModule.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
