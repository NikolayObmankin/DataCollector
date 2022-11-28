package parseData;

import metroData.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserJSON {
    private static final Map<String, Double> depthsStation = new HashMap<>();

    public static void getDepthsStation() {
        String regex = "-?\\d+.?\\d{0,2}";
        Pattern pattern = Pattern.compile(regex);
        TreeSet<Station> stations = StationIndex.stationIndex.getStationsSet();
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
                    for (Station station1 : stations) {
                        if (station1.getName().equalsIgnoreCase(name) && (station1.getDepth() == null || station1.getDepth() < dep)) {
                            station1.setDepth(dep);
                        }
                    }
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
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
