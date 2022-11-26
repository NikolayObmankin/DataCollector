package parseData;

import metroData.Line;
import metroData.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParserHTML {
    private static StationIndex stationIndex;

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
        parseStations(lineNumber, stationName);
        return doc;
    }
    //todo Подумать над типом возвращаемых данных и доступом
    public static void parseLine (Elements lineNumber, Elements lineName) {
        stationIndex = new StationIndex();
        int i = 0;
        Map<String, String> lineWithNumbers = new HashMap<>();
        for (Element element : lineNumber) {
            Line line = new Line(element.attr("data-line"), lineName.get(i++).text());
            stationIndex.addLine(line);
            System.out.println(line);
        }
        //todo Для проверки значений, удалить поссле проверок.
//        for (Map.Entry<String, String> entry : lineWithNumbers.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
    }
    //todo Подумать над типом возвращаемых данных и доступом
    public static void parseStations (Elements lineName, Elements stationName) {
        //Map<String, ArrayList<String>> line2stations = new HashMap<>();
        for (int i = 0; i < lineName.size(); i++) {
//            ArrayList<String> stations = new ArrayList<>();
            String number = lineName.get(i).attr("data-line");
            Line line = stationIndex.getLine(number);

            for (int j = 0; j < stationName.get(i).select("p.single-station").size(); j++) {
                String nameStation = stationName.get(i).select("[class=\"name\"]").get(j).text();
                Station station = new Station(nameStation, line);
                System.out.println(station);
                stationIndex.addStation(station);
            }
//            line2stations.put(lineName.get(i).text(), stations);
        }
        //todo Для проверки значений, удалить поссле проверок.
//        int count = 1;
//        for (Map.Entry<String, ArrayList<String>> entry : line2stations.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//            for (String s : entry.getValue()) {
//                System.out.println(s);
//                count++;
//            }
//        }
//        System.out.println(count);
    }
}
