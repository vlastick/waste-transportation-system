package one.vladimir.wts.RestModule;

import org.springframework.http.ResponseEntity;

public interface RestInterface {

    ResponseEntity<String> postPoint(String type, String configJSON);

    ResponseEntity<String> getPoint(String type, String filterJSON);

}
