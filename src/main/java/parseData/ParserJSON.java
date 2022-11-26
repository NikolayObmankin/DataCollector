package parseData;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserJSON {
    private static Map<String, String> depthsStation = new HashMap<>();
    private static List<String> depthsStationList = new ArrayList<>();
    public static Map<String, String> getDepthsStation () {
        String regex = "[\\d+]";
        Pattern pattern = Pattern.compile(regex);

            try {
                JSONArray stDepth = (JSONArray) new JSONParser().parse(getJsonFile());
                for (Object st : stDepth) {
                    JSONObject station = (JSONObject) st;
                    String name = (String) station.get("station_name");
                    String depth = (String) station.get("depth");
                    depth = depth.replaceAll(",", ".");
                    Matcher matcher = pattern.matcher(depth);
                    if (matcher.find()) {
                        Double dep = Double.parseDouble(depth);
                        System.out.println(name + " - " + depth + " dep - " + dep);
                    }
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
//        for (String statoion : depthsStationList) {
//            String[] st = statoion.split(",");
//            if (st.length == 2) {
//                depthsStation.put(st[0], st[1]);
//            }
//        }
//        //todo Для проверки значений, удалить поссле проверок.
//        for (Map.Entry<String, String> entry : depthsStation.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
        return depthsStation;
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        List<String> pathJSON = FileFinder.getPathJSON();
        for (String path : pathJSON) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(path));
                lines.forEach(builder::append);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return builder.toString();
        }
        return builder.toString();
    }
}
