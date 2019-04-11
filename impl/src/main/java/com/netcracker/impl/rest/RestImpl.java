package com.netcracker.impl.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netcracker.api.Rest;
import com.netcracker.api.pojo.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.netcracker.api.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import static com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RestImpl implements Rest {

    private PointService pointService;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    void init() {

        pointService = context.getBean("pointService", PointService.class);
        System.out.println("rest postconstruct");
    }

    @Override
    @RequestMapping(method = POST, value = "/point/")
    @ResponseBody
    public ResponseEntity<String> addPoint(

            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode array = mapper.readValue(configJSON, JsonNode.class);

            if (array.getNodeType() == OBJECT) {

                switch (type) {

                    case "dump":

                        Dump dump = mapper.readValue(configJSON, Dump.class);
                        answerJSON = pointService.addDump(dump);
                        status = CREATED;
                        break;

                    case "base":

                        Base base = mapper.readValue(configJSON, Base.class);
                        answerJSON = pointService.addBase(base);
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
                    "JSON body contains values which don't exist in type=" + type + " class.";
            e.printStackTrace();

        } catch (java.io.IOException e) {

            System.out.println("Java IO Exception");
            e.printStackTrace();
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @Override
    @RequestMapping(method = GET, value = "/point/{strId}")
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

                answerJSON = "Error. Given ID doesn't a numb";
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                answerJSON = "Can't parse class to JSON";
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    @Override
    @RequestMapping(method = POST, value = "/route/")
    @ResponseBody
    public ResponseEntity<String> addRoute(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Route route = mapper.readValue(configJSON, Route.class);
            answerJSON = pointService.addRoute(route);
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

    @Override
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
                Route route = pointService.getRoute(id);

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


    @Override
    @RequestMapping(method = POST, value = "/vessel/")
    @ResponseBody
    public ResponseEntity<String> addVessel(@RequestBody String configJSON) {

        String answerJSON = "";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            Vessel vessel = mapper.readValue(configJSON, Vessel.class);
            answerJSON = pointService.addVessel(vessel);
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

    @Override
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
                Vessel vessel = pointService.getVessel(id);

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




    /* Uncomment and fix in the future when Update modules comes and birds sings with a beautiful voices.
    @Override
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


    // Test DB methods

    @Override
    @RequestMapping(method = GET, value = "/add_user/")
    public ResponseEntity<String> addUser() {

        String result = pointService.addUser("MyleneFarmer", "User", "1123");
        return new ResponseEntity<>(result, OK);
    }

    @Override
    @RequestMapping(method = GET, value = "/get_user/")
    public ResponseEntity<String> getUser() {


        // It works but you should chose the correct ID
        String result = pointService.getUser("1");
        return new ResponseEntity<>(result, OK);
    }

    @Override
    @RequestMapping(method = GET, value = "/test_geo")
    public ResponseEntity<String> testGeo(
            @RequestBody String data) {


        return new ResponseEntity(pointService.testGeo(data), OK);
    }
}
