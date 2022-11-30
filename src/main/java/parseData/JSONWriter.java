package parseData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import metroData.Line;
import metroData.Station;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public class JSONWriter {
    public static void test() {
        TreeSet<Station> stations = StationIndex.stationIndex.getStationsSet();
        Map<String, Line> line = StationIndex.stationIndex.getNumber2line();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        for (Station s : stations) {
//            String json = gson.toJson(s);
//            System.out.println(json);
//        }
        String path = "F:\\Java2\\FilesAndNetwork\\DataCollector\\src\\main\\resources\\stations.json";

        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            Gson gson = new Gson();
            for (Map.Entry<String, Line> s : line.entrySet()) {
                String jsonString = gson.toJson(s.getKey());
                out.write(jsonString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
