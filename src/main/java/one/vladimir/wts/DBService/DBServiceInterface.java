package one.vladimir.wts.DBService;

import one.vladimir.wts.BusinessLogic.POJO.Dump;
import one.vladimir.wts.BusinessLogic.POJO.Group;
import one.vladimir.wts.BusinessLogic.POJO.Point;
import one.vladimir.wts.BusinessLogic.POJO.User;

public interface DBServiceInterface {

    public void addUser(User user);

    public User getUserById(Integer id);

    public void addPoint(Point point, User creator, Group group);

    public Point getPointById(Integer id);

    public void addGroup(Group group);

    public Group getGroupById(Integer id);

    public void addDump(Dump dump);

    public Dump getDumpById(Integer id);

}
