package parseData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParserHTML {

    public static Document parseHTML(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Elements lineNumber = doc.select("div.js-metro-stations");
        Elements lineName = doc.select("div.js-toggle-depend");
        Elements stationName = doc.select("div.js-metro-stations");
        parseLine(lineNumber, lineName);
        parseStations(lineName, stationName);
        return doc;
    }
    //todo Подумать над типом возвращаемых данных и доступом
    public static void parseLine (Elements lineNumber, Elements lineName) {
        int i = 0;
        Map<String, String> lineWithNumbers = new HashMap<>();
        for (Element line : lineNumber) {
            lineWithNumbers.put(line.attr("data-line"), lineName.get(i++).text());
        }
        //todo Для проверки значений, удалить поссле проверок.
//        for (Map.Entry<String, String> entry : lineWithNumbers.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
    }
    //todo Подумать над типом возвращаемых данных и доступом
    public static void parseStations (Elements lineName, Elements stationName) {
        Map<String, ArrayList<String>> line2stations = new HashMap<>();
        for (int i = 0; i < lineName.size(); i++) {
            ArrayList<String> stations = new ArrayList<>();
            for (int j = 0; j < stationName.get(i).select("p.single-station").size(); j++) {
                String station = stationName.get(i).select("[class=\"name\"]").get(j).text();
                stations.add(station);
            }
            line2stations.put(lineName.get(i).text(), stations);
        }
        //todo Для проверки значений, удалить поссле проверок.
        int count = 1;
        for (Map.Entry<String, ArrayList<String>> entry : line2stations.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
            for (String s : entry.getValue()) {
                System.out.println(s);
                count++;
            }
        }
        System.out.println(count);
    }
}
