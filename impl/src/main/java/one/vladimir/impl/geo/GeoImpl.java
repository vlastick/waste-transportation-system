package one.vladimir.impl.geo;

import one.vladimir.api.Geo;
import one.vladimir.api.pojo.Point;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.DataOutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service("geo")
public class GeoImpl implements Geo {

    // overpass-api.de is blocked by roskomnadzor - check other services or solutions.
    private final String OVERPASS_API = "http://www.overpass-api.de/api/interpreter";
    private final String OPENSTREETMAP_API_06 = "http://www.openstreetmap.org/api/0.6/";


    public static String toString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }


    public String getResult(String query) {

        System.out.println(query);

        String hostname = OVERPASS_API;
        String queryString = query = "is_in(61.2246,30.05194);out;";
        String resultString = "";

        System.out.println("There is an input for geo: " + queryString);

        try {
            URL osm = new URL(hostname);
            HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
            printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
            printout.flush();
            printout.close();

            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document d = docBuilder.parse(connection.getInputStream());

            resultString = toString(d);

        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        catch (SAXException e){
            e.printStackTrace();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
        return resultString;
    }

    @Override
    public String getIslandName(Point point) {

        return null;
    }

    @Override
    public Integer getIslandID(Point point) {
        return null;
    }

    @Override
    public Point getIslandCoordinates(String name) {
        return null;
    }

    @Override
    public Point getIslandCoordinates(Integer ID) {
        return null;
    }

    @Override
    public List<Point> getWay(Point A, Point B) {
        return null;
    }
}