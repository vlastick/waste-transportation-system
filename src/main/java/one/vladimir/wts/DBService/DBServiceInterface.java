package one.vladimir.wts.DBService;

import one.vladimir.wts.BusinessLogic.POJO.User;

public interface DBServiceInterface {

    public void addUser(User user);

    public User getUserById(Integer id);
    
}
