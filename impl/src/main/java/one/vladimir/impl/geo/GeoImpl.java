package one.vladimir.impl.geo;

import one.vladimir.api.Geo;
import one.vladimir.api.pojo.Point;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class GeoImpl implements Geo {

    // overpass-api.de is blocked by roskomnadzor - check other services or solutions.
    private final String OVERPASS_API = "http://www.overpass-api.de/api/interpreter";
    private final String OPENSTREETMAP_API_06 = "http://www.openstreetmap.org/api/0.6/";


    public String getResult(String query) {

        System.out.println(query);

        String hostname = OVERPASS_API;
        String queryString = query;//"is_in(47.4959374,19.1174585)";
        String resultString = "";

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

            resultString = connection.getInputStream().readAllBytes().toString();
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
