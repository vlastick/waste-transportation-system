package one.vladimir.impl.database.repositories;

import one.vladimir.impl.database.entities.UserEntity;
import one.vladimir.impl.database.entities.VesselEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT u.email FROM UserEntity u ")
    List<String> findAllEmails();

    @Query("SELECT u.userId FROM UserEntity u ")
    List<Integer> findAllIds();

    @Query("SELECT u FROM UserEntity u where u.login = ?1")
    UserEntity findUserByLogin(String login);

}
