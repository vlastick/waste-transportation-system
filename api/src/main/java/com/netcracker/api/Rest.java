package com.netcracker.api;

import org.springframework.http.ResponseEntity;

public interface Rest {

    ResponseEntity <String> addPoint(String type, String configJSON);

    ResponseEntity <String> getPoint(String strId, String type);

    // Just test methods
    ResponseEntity <String> addUser();

    ResponseEntity <String> getUser();

    ResponseEntity <String> testGeo(String data);
}