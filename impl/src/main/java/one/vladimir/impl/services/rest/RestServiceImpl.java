package one.vladimir.impl.services.rest;

import one.vladimir.api.*;
import one.vladimir.api.enums.RoutePointStatus;
import one.vladimir.api.enums.RouteStatus;
import one.vladimir.api.enums.UserRole;
import one.vladimir.api.pojo.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import one.vladimir.impl.database.DatabaseServiceImpl;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RestServiceImpl {

    @Autowired
    @Qualifier("pointService")
    private PointService pointService;

    @Autowired
    @Qualifier("filterService")
    private FilterService filterService;

    @Autowired
    @Qualifier("routeService")
    private RouteService routeService;

    @Autowired
    @Qualifier("transportService")
    private TransportService transportService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    private static final Logger log = Logger.getLogger(DatabaseServiceImpl.class);

    @PostConstruct
    public void postConstructLog() {
        log.info("restService initialized");
    }


    @RequestMapping(method = GET, value = "/test_geo")
    public ResponseEntity<String> testGeo(
            @RequestParam(name = "command", defaultValue = "") String command) {

        return new ResponseEntity(pointService.testGeo(command), OK);
    }

    //final methods

    @RequestMapping(method = POST, value = "/point/")
    @ResponseBody
    public ResponseEntity<String> addPoint(

            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        User user = userService.getAuthenticatedUser();
        log.info("Received POST /point/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode array = mapper.readValue(configJSON, JsonNode.class);
            if (array.getNodeType() == OBJECT) {

                switch (type) {

                    case "dump":

                        Dump dump = mapper.readValue(configJSON, Dump.class);
                        answerJSON = pointService.addDump(dump, userService.getAuthenticatedUser());
                        status = CREATED;
                        break;

                    case "base":

                        Base base = mapper.readValue(configJSON, Base.class);
                        answerJSON = pointService.addBase(base, userService.getAuthenticatedUser());
                        status = CREATED;
                        break;

                    default:
                        answerJSON = "Error. Unsupported type: type =" + type;
                        break;
                }

            }

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            answerJSON = "JSON init exception." +
                    "JSON body contains values which don't exist in type=" + type + " class.";
        } catch (java.io.IOException e) {
            answerJSON = "JSON parsing caused IOException";
        } catch (NullPointerException e) {
            answerJSON = "Coordinates are not in reserve";
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(answerJSON, status);
        }

        return new ResponseEntity<>(answerJSON, status);
    }


    @RequestMapping(method = POST, value = "/points/")
    @ResponseBody
    public ResponseEntity<String> getPoints(
            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String configJSON) {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        PointFilter pointFilter;

//        System.out.println(configJSON);

        if (type.equals("dump") || type.equals("not given")) {
            pointFilter = filterService.createDumpFilterFromJson(configJSON);
        } else {
            pointFilter = filterService.createBaseFilterFromJson(configJSON);
        }

        ObjectMapper mapper = new ObjectMapper();

        User user = userService.getAuthenticatedUser();
        log.info("Received POST /points/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);
        List<Integer> creatorsIdList;
        switch (user.getRole()) {
            case TOURIST:
                /*if (type.equals("dump") || type.equals("not given")) {
                    pointFilter = new DumpFilter();
                } else {
                    pointFilter = new BaseFilter();
                }
                creatorsIdList = new ArrayList<>();
                creatorsIdList.add(user.getUserId());
                pointFilter.setCreatorsIdList(creatorsIdList);
                pointFilter.setActive(true);
                break;*/
                if (pointFilter.getPointIdList() == null || pointFilter.getPointIdList().size() != 1) {
                    if (type.equals("dump") || type.equals("not given")) {
                        pointFilter = new DumpFilter();
                    } else {
                        answerJSON = "access denied";
                        status = HttpStatus.FORBIDDEN;
                        return new ResponseEntity<>(answerJSON, status);
                    }
                    creatorsIdList = new ArrayList<>();
                    creatorsIdList.add(user.getUserId());
                    pointFilter.setCreatorsIdList(creatorsIdList);
                    pointFilter.setActive(true);
                    break;
                }
                pointFilter.setGroupidList(null);
                pointFilter.setActive(null);
                pointFilter.setCreatorsIdList(null);
                if (type.equals("dump") || type.equals("not given")) {
                    ((DumpFilter) pointFilter).setMaxSize(null);
                    ((DumpFilter) pointFilter).setDumpTypeList(null);
                }
            case CREWMAN:
                if (pointFilter.getPointIdList() == null || pointFilter.getPointIdList().size() != 1) {
                    if (type.equals("dump") || type.equals("not given")) {
                        pointFilter = new DumpFilter();
                    } else {
                        answerJSON = "access denied";
                        status = HttpStatus.FORBIDDEN;
                        return new ResponseEntity<>(answerJSON, status);
                    }
                    creatorsIdList = new ArrayList<>();
                    creatorsIdList.add(user.getUserId());
                    pointFilter.setCreatorsIdList(creatorsIdList);
                    pointFilter.setActive(true);
                    break;
                }
                pointFilter.setGroupidList(null);
                pointFilter.setActive(null);
                pointFilter.setCreatorsIdList(null);
                if (type.equals("dump") || type.equals("not given")) {
                    ((DumpFilter) pointFilter).setMaxSize(null);
                    ((DumpFilter) pointFilter).setDumpTypeList(null);
                }

                break;
        }

//        try {
//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pointFilter));
//        } catch (JsonProcessingException e) {
//        }


        try {

            if (type.equals("dump") || type.equals("not given")) {
                List<Dump> points = pointService.getDumpsByFilter((DumpFilter) pointFilter);
                answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(points);
            } else {
                List<Base> points = pointService.getBasesByFilter((BaseFilter) pointFilter);
                answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(points);
            }
            status = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            answerJSON = "Can't parse class to JSON";
        }

//        try {
//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(answerJSON));
//        } catch (JsonProcessingException e) {
//        }

        return new ResponseEntity<>(answerJSON, status);
    }


    @RequestMapping(method = POST, value = "/routes/")
    @ResponseBody
    public ResponseEntity<String> getRoutes(
            @RequestBody String configJSON) {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RouteFilter routeFilter = filterService.createRouteFilterFromJson(configJSON);
        User user = userService.getAuthenticatedUser();
        log.info("Received POST /routes/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);

        switch (user.getRole()) {
            case CREWMAN:
                routeFilter = new RouteFilter();
                List<Integer> vesselIdList = new ArrayList<>();
                vesselIdList.add(transportService.getVesselByCrewmanId(user.getUserId()).getId());
                routeFilter.setVesselIdList(vesselIdList);
                List<String> routeStatusList = new ArrayList<>();
                routeStatusList.add(RouteStatus.IN_PROGRESS.toString());
                routeFilter.setRouteStatusList(routeStatusList);
                break;
            case ADMIN:
                break;
            default:
                answerJSON = "Access denied";
                status = HttpStatus.FORBIDDEN;
                return new ResponseEntity<>(answerJSON, status);
        }
        List<Route> routes = routeService.getRoutesByFilter(routeFilter);
        try {
            ObjectMapper mapper = new ObjectMapper();
            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(routes);
            status = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            answerJSON = "Can't parse class to JSON";
        }
        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = PUT, value = "/routepoint/")
    @ResponseBody
    public ResponseEntity<String> updateRoutePointStatus(
            @RequestBody String configJSON) {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;

        User user = userService.getAuthenticatedUser();
        log.info("Received PUT /routepoint/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);

        if (user.getRole() != UserRole.CREWMAN) {
            answerJSON = "access denied";
            status = HttpStatus.FORBIDDEN;
            log.warn("Access denied for " + user.getRole().toString() + " with id " + user.getUserId());
            return new ResponseEntity<>(answerJSON, status);
        }

        JSONParser jsonParser = new JSONParser();
        RoutePointStatus routePointStatus;
        Integer routePointId;
        try {
            Object object = jsonParser.parse(configJSON);
            JSONObject jsonObject = (JSONObject) object;
            routePointStatus = RoutePointStatus.valueOf((String) jsonObject.get("status"));
            routePointId = ((Long) jsonObject.get("id")).intValue();
        } catch (ParseException e) {
            answerJSON = "Invalid body";
            return new ResponseEntity<>(answerJSON, status);
        }
        answerJSON = routeService.updateRoutePointStatus(routePointId, transportService.getVesselByCrewmanId(user.getUserId()).getId(), routePointStatus);
        status = OK;
        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/route/")
    public ResponseEntity<String> buildRoute() {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        User user = userService.getAuthenticatedUser();
        log.info("Received GET /route/ request from " + user.getRole().toString() +
                " with id " + user.getUserId());

        if (user.getRole() != UserRole.CREWMAN) {
            answerJSON = "access denied";
            log.warn("Access denied for " + user.getRole().toString() + " with id " + user.getUserId());
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(answerJSON, status);
        }
        Route route;
        try {
            route = routeService.buildRoute(transportService.getVesselByCrewmanId(user.getUserId()).getId());
        } catch (NoSuchElementException e) {
            status = HttpStatus.OK;
            answerJSON = "No dumps available";
            return new ResponseEntity<>(answerJSON, status);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(route);
            status = OK;
        } catch (JsonProcessingException e) {
            answerJSON = "Can't parse class to JSON";
        }


        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = PUT, value = "/route/")
    @ResponseBody
    public ResponseEntity<String> updateRouteStatus(
            @RequestBody String configJSON) {

        String answerJSON;
        HttpStatus status;
        User user = userService.getAuthenticatedUser();
        log.info("Received PUT /route/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);

        if (user.getRole() != UserRole.CREWMAN) {
            answerJSON = "access denied";
            log.warn("Access denied for " + user.getRole().toString() + " with id " + user.getUserId());
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(answerJSON, status);
        }

        Vessel vessel = transportService.getVesselByCrewmanId(user.getUserId());

        ObjectMapper mapper = new ObjectMapper();
        try {
            Route route = routeService.finishRoute(vessel.getId());
            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(route);
            status = OK;
        } catch (JsonProcessingException e) {
            answerJSON = "Can't parse class to JSON";
            status = HttpStatus.BAD_REQUEST;
        } catch (IllegalArgumentException e) {
            answerJSON = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = PUT, value = "/vessel/")
    @ResponseBody
    public ResponseEntity<String> updateVesselCoordinates(
            @RequestBody String configJSON) {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        User user = userService.getAuthenticatedUser();
        log.info("Received PUT /vessel/ request from " + user.getRole().toString() +
                " with id " + user.getUserId() +
                " with body " + configJSON);

        if (user.getRole() != UserRole.CREWMAN) {
            answerJSON = "Access denied";
            status = HttpStatus.FORBIDDEN;
            log.warn("Access denied for " + user.getRole().toString() + " with id " + user.getUserId());
            return new ResponseEntity<>(answerJSON, status);
        }

        Vessel vessel = transportService.getVesselByCrewmanId(user.getUserId());
        JSONParser jsonParser = new JSONParser();
        Double longitude;
        Double latitude;
        try {
            Object object = jsonParser.parse(configJSON);
            JSONObject jsonObject = (JSONObject) object;
            String latStr, lonStr;
            latStr = (String) jsonObject.get("latitude");
            lonStr = (String) jsonObject.get("longitude");
            latitude = Double.parseDouble(latStr);
            longitude = Double.parseDouble(lonStr);
            transportService.updateCoordinates(vessel.getId(), latitude, longitude);
        } catch (ParseException e) {
            answerJSON = "Invalid body";
            return new ResponseEntity<>(answerJSON, status);
        } catch (NumberFormatException e) {
            answerJSON = "Invalid body";
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(answerJSON, status);
        } catch (NullPointerException e) {
            answerJSON = "Coordinates are not in reserve";
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(answerJSON, status);
        }
//        vessel.setLongitude(longitude);
//        vessel.setLatitude(latitude);
//        transportService.updateVessel(vessel);
        answerJSON = "Coordinates updated";
        status = HttpStatus.OK;

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/vessel/")
    @ResponseBody
    public ResponseEntity<String> getCurrentVessel() {

        String answerJSON;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        User user = userService.getAuthenticatedUser();
        log.info("Received GET /vessel/ request from " + user.getRole().toString() +
                " with id " + user.getUserId());

        if (user.getRole() != UserRole.CREWMAN) {
            log.warn("Access denied for " + user.getRole().toString() + " with id " + user.getUserId());
            answerJSON = "Access denied";
            status = HttpStatus.FORBIDDEN;
            return new ResponseEntity<>(answerJSON, status);
        }
        Vessel vessel = transportService.getVesselByCrewmanId(user.getUserId());
        ObjectMapper mapper = new ObjectMapper();
        try {
            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vessel);
            status = OK;
        } catch (JsonProcessingException e) {
            answerJSON = "Can't parse class to JSON";
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/user_role/")
    @ResponseBody
    public ResponseEntity<String> getCurrentUser() {

        String answerJSON;
        HttpStatus status = OK;
        User user = userService.getAuthenticatedUser();
        log.info("Received GET /user_role/ request from " + user.getRole().toString() +
                " with id " + user.getUserId());
        answerJSON = user.getRole().toString();
        return new ResponseEntity<>(answerJSON, status);
    }

    /*  @RequestMapping(method = GET, value = "/point/{strId}")
    @ResponseBody
    public ResponseEntity<String> getPoint(
            @PathVariable String strId,
            @RequestParam(name = "type", defaultValue = "") String type) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if ((strId.isEmpty()) || (type.equals(""))) {
            answerJSON = "Error. ID or type point aren't given";
        } else {

            try {
                Integer id = Integer.parseInt(strId);

                switch (type) {

                    case "dump":
                        Dump dump = pointService.getDump(id);

                        if (dump != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dump);
                            status = OK;
                        } else {
                            answerJSON = "Dump with id=" + strId + "  not found";
                            status = NOT_FOUND;
                        }

                        break;

                    case "base":

                        Base base = pointService.getBase(id);

                        if (base != null) {
                            ObjectMapper mapper = new ObjectMapper();
                            answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(base);
                            status = OK;
                        } else {
                            answerJSON = "Base with id=" + strId + "  not found";
                            status = NOT_FOUND;
                        }

                        break;

                    default:

                        answerJSON = "Error. Unsupported type: type=" + type;
                        break;
                }

            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a dumb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = POST, value = "/route/")
    @ResponseBody
    public ResponseEntity<String> addRoute(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Route route = mapper.readValue(configJSON, Route.class);
            answerJSON = routeService.addRoute(route);
            status = CREATED;

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
            e.printStackTrace();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

            answerJSON = "JSON init exception." +
                    "JSON body contains values which don't exist in route class.";
            e.printStackTrace();

        } catch (java.io.IOException e) {

            System.out.println("Java IO Exception");
            e.printStackTrace();
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/route/{strId}")
    @ResponseBody
    public ResponseEntity<String> getRoute(@PathVariable String strId) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (strId.isEmpty()) {
            answerJSON = "Error. ID isn't given";
        } else {

            try {

                Integer id = Integer.parseInt(strId);
                Route route = routeService.getRoute(id);

                if (route != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(route);
                    status = OK;
                } else {
                    answerJSON = "Route with id=" + strId + "  not found";
                    status = NOT_FOUND;
                }
            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }


    @RequestMapping(method = POST, value = "/vessel/")
    @ResponseBody
    public ResponseEntity<String> addVessel(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Vessel vessel = mapper.readValue(configJSON, Vessel.class);
            answerJSON = transportService.addVessel(vessel);
            status = CREATED;

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
            e.printStackTrace();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

            answerJSON = "JSON init exception." +
                    "JSON body contains values which don't exist in vessel class.";
            e.printStackTrace();

        } catch (java.io.IOException e) {

            System.out.println("Java IO Exception");
            e.printStackTrace();
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/vessel/{strId}")
    @ResponseBody
    public ResponseEntity<String> getVessel(@PathVariable String strId) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (strId.isEmpty()) {
            answerJSON = "Error. ID isn't given";
        } else {

            try {

                Integer id = Integer.parseInt(strId);
                Vessel vessel = transportService.getVessel(id);

                if (vessel != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vessel);
                    status = OK;
                } else {
                    answerJSON = "Vessel with id=" + strId + "  not found";
                    status = NOT_FOUND;
                }
            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }
*/



    /* Uncomment and fix in the future when Update modules comes and birds sings with a beautiful voices.
    @RequestMapping(method = PUT, value = "/point/{strId}")
    @ResponseBody

    public ResponseEntity<String> updatePoint(
            @PathVariable String strId,
            @RequestParam(name = "type", defaultValue = "") String type,
            @RequestBody String configJSON){

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if((strId.isEmpty()) || (type.equals(""))) {
            answerJSON = "Error. ID or type point aren't given";
        }

        else {

            try {
                Integer id = Integer.parseInt(strId);

                try {

                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode array = mapper.readValue(configJSON, JsonNode.class);

                    if (array.getNodeType() == OBJECT) {

                        switch (type) {

                            case "dump":

                                Dump dump = mapper.readValue(configJSON, Dump.class);
                                answerJSON = "UPD dump";//pointService.addDump(dump);
                                status = CREATED;
                                break;

                            case "base":

                                Base base = mapper.readValue(configJSON, Base.class);
                                answerJSON = "UPD base";//pointService.addBase(base);
                                status = CREATED;
                                break;

                            default:
                                answerJSON = "Error. Unsupported type: type =" + type;
                                break;
                        }

                    }

                } catch (com.fasterxml.jackson.core.JsonParseException e) {

                    answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
                    e.printStackTrace();

                } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

                    answerJSON = "JSON init exception." +
                            "JSON body contains values which don't exist in type=" + type +" class.";
                    e.printStackTrace();

                } catch (java.io.IOException e) {

                    System.out.println("Java IO Exception");
                    e.printStackTrace();
                }


            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }
    */


    // Test methods

   /* @RequestMapping(method = POST, value = "/user/")
    @ResponseBody
    public ResponseEntity<String> addUser(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(configJSON, User.class);
            answerJSON = userService.addUser(user);
            status = CREATED;

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
            e.printStackTrace();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

            answerJSON = "JSON init exception." +
                    "JSON body contains values which don't exist in vessel class.";
            e.printStackTrace();

        } catch (java.io.IOException e) {

            System.out.println("Java IO Exception");
            e.printStackTrace();
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/user/{strId}")
    @ResponseBody
    public ResponseEntity<String> getUser(@PathVariable String strId) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (strId.isEmpty()) {
            answerJSON = "Error. ID isn't given";
        } else {

            try {

                Integer id = Integer.parseInt(strId);
                User user = userService.getUser(id);

                if (user != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
                    status = OK;
                } else {
                    answerJSON = "User with id=" + strId + "  not found";
                    status = NOT_FOUND;
                }
            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = POST, value = "/group/")
    @ResponseBody
    public ResponseEntity<String> addGroup(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Group group = mapper.readValue(configJSON, Group.class);
            answerJSON = pointService.addGroup(group);
            status = CREATED;

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            answerJSON = "JSON parsing exception: can't read JSON structure because there are some mistakes!";
            e.printStackTrace();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

            answerJSON = "JSON init exception." +
                    "JSON body contains values which don't exist in vessel class.";
            e.printStackTrace();

        } catch (java.io.IOException e) {

            System.out.println("Java IO Exception");
            e.printStackTrace();
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/group/{strId}")
    @ResponseBody
    public ResponseEntity<String> getGroup(@PathVariable String strId) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (strId.isEmpty()) {
            answerJSON = "Error. ID isn't given";
        } else {

            try {

                Integer id = Integer.parseInt(strId);
                Group group = pointService.getGroup(id);

                if (group != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(group);
                    status = OK;
                } else {
                    answerJSON = "Group with id=" + strId + "  not found";
                    status = NOT_FOUND;
                }
            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @RequestMapping(method = GET, value = "/create_route/{strId}")
    @ResponseBody
    public ResponseEntity<String> testRoute(@PathVariable String strId) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (strId.isEmpty()) {
            answerJSON = "Error. ID isn't given";
        } else {

            try {

                Integer id = Integer.parseInt(strId);
                Route route = routeService.createRoute(id);

                if (route != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    answerJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(route);
                    status = OK;
                } else {
                    answerJSON = "Group with id=" + strId + "  not found";
                    status = NOT_FOUND;
                }
            } catch (NumberFormatException e) {

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }*/

    /*@RequestMapping(method = GET, value = "/add_user/")
    public ResponseEntity<String> addUser() {

        String result = UserService.addUser();
        return new ResponseEntity<>(result, OK);
    }

    @RequestMapping(method = GET, value = "/get_user/")
    public ResponseEntity<String> getUser() {


        // It works but you should chose the correct ID
        User user = userService.getUser(1);
        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(method = GET, value = "/test_geo")
    public ResponseEntity<String> testGeo(
            @RequestBody String data) {


        return new ResponseEntity(pointService.testGeo(data), OK);
    }*/


}
