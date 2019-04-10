package com.netcracker.impl.rest;

import com.netcracker.api.Rest;
import com.netcracker.api.pojo.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.netcracker.api.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY;
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
    public ResponseEntity<String> postPoint(
            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String configJSON) {

        String answerJSON = "Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode array = mapper.readValue(configJSON, JsonNode.class);

            if (array.getNodeType() == OBJECT) {

                switch (type) {

                    case "point":
                        Point point = mapper.readValue(configJSON, Point.class);
                        answerJSON = pointService.postPoint(point);
                        status = CREATED;
                        break;

                    case "dump":
                        Dump dump = mapper.readValue(configJSON, Dump.class);
                        answerJSON = pointService.postDump(dump);
                        status = CREATED;
                        break;

                    case "base":
                        Base base = mapper.readValue(configJSON, Base.class);
                        answerJSON = pointService.postBase(base);
                        status = CREATED;
                        break;

                    default:
                        answerJSON = "Unsupported type: " + type;
                        break;
                }

            } else if (array.getNodeType() == ARRAY) {

                switch (type) {

                    case "point":
                        List<Point> pointList = mapper.readValue(configJSON, new TypeReference<List<Point>>() {
                        });
                        answerJSON = pointService.postPoints(pointList);
                        status = CREATED;
                        break;

                    case "dump":
                        List<Dump> dumpList = mapper.readValue(configJSON, new TypeReference<List<Dump>>() {
                        });
                        answerJSON = pointService.postDumps(dumpList);
                        status = CREATED;
                        break;

                    case "base":
                        List<Base> baseList = mapper.readValue(configJSON, new TypeReference<List<Base>>() {
                        });
                        answerJSON = pointService.postBases(baseList);
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
    @RequestMapping(method = GET, value = "/point/")
    @ResponseBody
    public ResponseEntity<String> getPoint(
            @RequestParam(name = "type", defaultValue = "not given") String type,
            @RequestBody String filterJSON) {


        String answerJSON = "Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode filterArray = mapper.readValue(filterJSON, JsonNode.class);

            switch (type) {

                case "point":
                    //TODO call new function
                    status = OK;
                    break;

                case "dump":
                    answerJSON = pointService.getDumps(filterArray);
                    status = OK;
                    break;

                case "base":
                    //TODO call new function
                    status = OK;
                    break;

                default:
                    answerJSON = "Unsupported type: " + type;
                    break;
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
