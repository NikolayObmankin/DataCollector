package parseData;

import metroData.Line;
import metroData.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static parseData.StationIndex.stationIndex;

public class ParserHTML {

    public static void parseHTML(String url) {
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
    }

    public static void parseLine(Elements lineNumber, Elements lineName) {
        stationIndex = new StationIndex();
        for (int i = 0; i < lineNumber.size(); i++) {
            Line line = new Line(lineNumber.get(i).attr("data-line"), lineName.get(i).text());
            stationIndex.addLine(line);
        }
    }

    public static void parseStations(Elements lineName, Elements stationName) {
        for (int i = 0; i < lineName.size(); i++) {
            String number = lineName.get(i).attr("data-line");
            Line line = stationIndex.getLine(number);
            for (int j = 0; j < stationName.get(i).select("p.single-station").size(); j++) {
                boolean isConnected = !stationName.get(i).select("p.single-station").get(j).select("span.t-icon-metroln").isEmpty();
                String name = stationName.get(i).select("[class=\"name\"]").get(j).text();
                Station station = new Station(name, line);
                station.setConnected(isConnected);
                stationIndex.addStation(station);
                line.addStation(station);
                ParserCSV.getOpeningDate();
                ParserJSON.getDepthsStation();
            }
        }
    }
}
