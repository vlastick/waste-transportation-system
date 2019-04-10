package com.netcracker.impl.rest;

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
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RestImpl implements Rest {

    private PointService pointService;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    void init(){
        pointService = context.getBean("pointService", PointService.class);
        System.out.println("rest postconstruct");
    }

    @Override
    @RequestMapping(method = POST, value = "/point/")
    @ResponseBody
    public ResponseEntity<String> addPoint(
            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String configJSON) {

        String answerJSON = "Error";
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
                        answerJSON = "Unsupported type: " + type;
                        break;
                }

            }

        } catch (com.fasterxml.jackson.core.JsonParseException e) {

            System.out.println("JSON parsing exception: can't read JSON structure - there are some errors!");
            e.printStackTrace();

        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {

            System.out.println("JSON incorrect value exception: class has not value in JSON!");
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
            @RequestParam(name = "type", defaultValue = "not given") String type) {

        String answerJSON = "Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        System.out.println("I AM HERE");

        if((strId.isEmpty()) || (type.equals("not given"))) {
            answerJSON = "ID or type are not given";
        }
        else{
            try {
                Integer id = Integer.parseInt(strId);

                switch (type) {

                    case "dump":
                        Dump dump = pointService.getDump(id);

                        if(dump != null) {
                            answerJSON = dump.toString();
                        }
                        else {
                            answerJSON = "Dump not found";
                        }
                        status = OK;
                        break;

                    case "base":
                        //TODO call new function
                        Base base = pointService.getBase(id);
                        if(base != null) {
                            answerJSON = base.toString();
                        }
                        else {
                            answerJSON = "Base not found";
                        }
                        status = OK;

                        break;

                    default:
                        answerJSON = "Unsupported type: " + type;
                        break;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                answerJSON = "Incorrect type of id (not number)";
            }
        }

        return new ResponseEntity<>(answerJSON, status);
    }

    // Test DB methods

    @Override
    @RequestMapping(method = GET, value = "/add_user/")
    public ResponseEntity<String> addUser(){

        String result = pointService.addUser("MyleneFarmer", "User", "1123");
        return new ResponseEntity<>(result, OK);
    }

    @Override
    @RequestMapping(method = GET, value = "/get_user/")
    public ResponseEntity<String> getUser(){


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
