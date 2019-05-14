package one.vladimir.api;

import one.vladimir.api.pojo.User;

public interface UserService {


    /**
     * gets User who is currently working with system.
     * @return initialized User object.
     */
    User getAuthenticatedUser();

    User getUser(Integer id);

    String addUser(User user);

    String updateUser(User user);

}
