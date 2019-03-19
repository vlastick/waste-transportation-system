package one.vladimir.wts;

import one.vladimir.wts.entity.UserRequest;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepo extends  CrudRepository<UserRequest, Integer>{
}
