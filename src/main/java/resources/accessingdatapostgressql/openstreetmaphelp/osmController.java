package resources.accessingdatapostgressql.openstreetmaphelp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import resources.accessingdatapostgressql.database.driverlocation;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;


public class osmController {
    public osmController() throws IOException {}
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    public ArrayList<Integer> getDistance(float lat1, float long1, ArrayList<driverlocation> table) throws IOException, JSONException {
        StringBuilder location = new StringBuilder();
        for (driverlocation i : table){
            location.append(";").append(i.getLatLongString());
        }
//        String request = "http://router.project-osrm.org/table/v1/driving/"+long1 +","
//                + lat1 + location + "?sources=0";
//        long start = System.currentTimeMillis();
                String request = "http://20.205.161.180:5000/table/v1/driving/"+long1 +","
                + lat1 + location + "?sources=0";
        JSONObject json = readJsonFromUrl(request);

        JSONArray arr = json.getJSONArray("durations").getJSONArray(0);
        ArrayList<Integer> resultID = new ArrayList<>();
        ArrayList<Double> result = new ArrayList<>();
        double temp = 0;
        for (int i = 1; i < arr.length(); i++) {
            result.add(arr.getDouble(i));
        }

//        long finish = System.currentTimeMillis();
//        long timeElapsed = finish - start;
//        System.out.print("Time in ms: " + timeElapsed + "\n");

        ArrayList<Double> resultcopy = new ArrayList<>(result);
        Collections.sort(result);
        for (int i = 0; i < 5; i++) {
            temp = result.get(i);
            for (int j = 0; j < result.size(); j++) {
                if (temp == resultcopy.get(j)){
                    resultID.add(j);
                    resultcopy.set(j,-1.0);
                }
            }
        }
        return resultID;
    }
}
