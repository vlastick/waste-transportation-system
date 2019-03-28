package one.vladimir.wts;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

//    @Autowired
//    private RequestRepo requestRepo;

    private final AtomicInteger counter = new AtomicInteger();

    @RequestMapping("/request/save")
    public String saveRequest(
            @RequestParam(name = "name", defaultValue = "empty") String name,
            @RequestParam(name = "description", defaultValue = "empty") String description,
            @RequestParam(name = "weight", defaultValue = "0.0") String strWeight,
            @RequestParam(name = "volume", defaultValue = "0.0") String strVolume) {
        /*try {
            UserRequest userRequest = new UserRequest();
            userRequest.setWeight(Double.valueOf(strWeight));
            userRequest.setVolume(Double.valueOf(strVolume));
            userRequest.setPointId(1);
            userRequest.setName(name);
            userRequest.setDescription(description);
            requestRepo.save(userRequest);
            return "True";

        } catch (NumberFormatException e) {
            return "False";
        }*/
        return "True";
    }

  /*  @RequestMapping("/request/all")
    public Iterable<UserRequest> getRequest() {
        return requestRepo.findAll();
    }

    @RequestMapping("/request/")
    public UserRequest getRequest(@RequestParam(name = "id", defaultValue = "1") String strId) {
        try {
            return requestRepo.findById(Integer.valueOf(strId)).get();
        } catch (NumberFormatException e) {
            return new UserRequest();
        }
    }*/

}
