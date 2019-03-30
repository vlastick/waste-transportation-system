package one.vladimir.wts.RestModule;

public interface RestInterface {

    String getPoint(String configJson);
    String postPoint(String configJson);
    String deletePoint(String configJson);
    String getRoute(String configJson);

    String getPoints(String configJson);
    String postPoints(String configJson);
    String deletePoints(String configJson);
    String getRoutes(String configJson);

    /*
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

    Point getPoint(String strId);*/
}
