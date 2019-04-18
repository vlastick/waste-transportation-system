package one.vladimir.api;

import one.vladimir.api.pojo.User;

public interface UserService {


    /**
     * get current user, that works with system
     *
     * @return user pojo
     */
    public User getUser();

    public User getUser(String strId);

    public String addUser(User user);

    public String updateUser(User user);

}
