package one.vladimir.api;

import one.vladimir.api.pojo.User;

public interface UserService {


    /**
     * get current user, that works with system
     *
     * @return user pojo
     */
    User getAuthenticatedUser();

    User getUser(Integer id);

    String addUser(User user);

    String updateUser(User user);

}
