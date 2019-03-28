package one.vladimir.wts.RestModule;
import one.vladimir.wts.DBModule.Entities.Point;

public interface RestInterface {

    // Orders interface

    String getOrdersList();

    String getOrders(String ownerId, String statusId, String typeId);

    String getOrdersCurrent();

    String postOrders(String latitude, String longtitude,
                      String typeId, String size, String weight,
                      String description);

    String putOrders(String orderID,
                     String latitude, String longtitude,
                     String typeId, String size, String weight,
                     String description);

    String deleteOrders(String orderID);

    String putOrdersStatus(String orderID, String status);

    // Test queries to database
    String addUser(String strLogin, String strRole);

    String getUser(String strId);

    String addPoint(String strCreatorId, String strGroupId);

    Point getPoint(String strId);
}
