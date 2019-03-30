package one.vladimir.wts.RestModule;

import one.vladimir.wts.Service.POJO.Base;
import one.vladimir.wts.Service.POJO.Dump;
import one.vladimir.wts.Service.POJO.Point;
import one.vladimir.wts.Service.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY;
import static com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RestImplementation implements RestInterface {

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
                        answerJSON = Service.postPoint(point);
                        status = CREATED;
                        break;

                    case "dump":
                        Dump dump = mapper.readValue(configJSON, Dump.class);
                        answerJSON = Service.postDump(dump);
                        status = CREATED;
                        break;

                    case "base":
                        Base base = mapper.readValue(configJSON, Base.class);
                        answerJSON = Service.postBase(base);
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
                        answerJSON = Service.postPoints(pointList);
                        status = CREATED;
                        break;

                    case "dump":
                        List<Dump> dumpList = mapper.readValue(configJSON, new TypeReference<List<Dump>>() {
                        });
                        answerJSON = Service.postDumps(dumpList);
                        status = CREATED;
                        break;

                    case "base":
                        List<Base> baseList = mapper.readValue(configJSON, new TypeReference<List<Base>>() {
                        });
                        answerJSON = Service.postBases(baseList);
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
                    answerJSON = Service.getDumps(filterArray);
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
}
