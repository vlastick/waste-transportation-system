package one.vladimir.wts.RestModule;


import one.vladimir.wts.DBModule.Entities.Point;
import one.vladimir.wts.Service.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class RestImplementation implements RestInterface {

    @Override
    @RequestMapping(method=GET, value = "/orders/list/")
    public String getOrdersList() {
        return Service.getOrdersList();
    }

    @Override
    @RequestMapping(method=GET, value = "/orders/")
    public String getOrders(
            @RequestParam (name = "owner_id",   defaultValue = "") String ownerId,
            @RequestParam (name = "status_id",  defaultValue = "") String statusId,
            @RequestParam (name = "type_id",    defaultValue = "") String typeId) {
        return Service.getOrders(ownerId, statusId, typeId);
    }

    @Override
    @RequestMapping(method=GET, value = "/orders/current/")
    public String getOrdersCurrent() {
        return Service.getOrdersCurrent();
    }

    @Override
    @RequestMapping(method=POST, value = "/orders/")
    public String postOrders(String latitude, String longtitude, String typeId, String size, String weight, String description) {
        return null;
    }

    @Override
    @RequestMapping(method=PUT, value = "/orders/")
    public String putOrders(String orderID, String latitude, String longtitude, String typeId, String size, String weight, String description) {
        return null;
    }

    @Override
    @RequestMapping(method=DELETE, value = "/orders/")
    public String deleteOrders(String orderID) {
        return null;
    }

    @Override
    @RequestMapping(method=PUT, value = "/orders/status/")
    public String putOrdersStatus(String orderID, String status) {
        return null;
    }


    // Test methods
    @Override
    @RequestMapping(method=POST, value = "/add/user/")
    public String addUser(
            @RequestParam(name = "login", defaultValue = "DeafaultUser") String strLogin,
            @RequestParam(name = "role", defaultValue = "User") String strRole){

        return Service.addUser(strLogin, strRole);
    }

    @Override
    @RequestMapping(method=GET, value = "/get/user/")
    public String getUser(
            @RequestParam(name = "id", defaultValue = "1") String strId){

        return Service.getUser(strId);
    }

    @Override
    @RequestMapping(method=GET, value = "/get/point/")
    public Point getPoint(
            @RequestParam(name = "id", defaultValue = "1") String strId){

        return Service.getPoint(strId);
    }

    @Override
    @RequestMapping(method=POST, value = "/add/point/")
    public String addPoint(
            @RequestParam(name = "creatorId", defaultValue = "1") String strCreatorId,
            @RequestParam(name = "groupId", defaultValue = "1") String strGroupId){
        return Service.addPoint(strCreatorId, strGroupId);
    }
}
