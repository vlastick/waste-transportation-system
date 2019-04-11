package com.netcracker.api;

import org.springframework.http.ResponseEntity;

public interface Rest {

    ResponseEntity <String> addPoint(String type, String configJSON);

    ResponseEntity <String> getPoint(String strId, String type);

    ResponseEntity <String> updatePoint(String id, String type, String configJSON);

    ResponseEntity <String> addRoute(String type, String configJSON);

    ResponseEntity <String> getRoute(String strId, String type);

    ResponseEntity <String> updateRoute(String id, String type, String configJSON);

    // Just test methods
    ResponseEntity <String> addUser();

    ResponseEntity <String> getUser();

    ResponseEntity <String> testGeo(String data);
}