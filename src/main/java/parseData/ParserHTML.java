package parseData;

import metroData.Line;
import metroData.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

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
                if (isConnected) {
                    TreeSet<Station> connect = new TreeSet<>();
                    for (int k = 0; k < lineName.get(i).select("p.single-station")
                            .get(j).select("span.t-icon-metroln").size(); k++) {
                        String numberLineConnection = lineName.get(i).select("p.single-station").get(j)
                                .select("span.t-icon-metroln").get(k).attr("class");
                        String nameStationConnection = lineName.get(i).select("p.single-station")
                                .get(j).select("span.t-icon-metroln").get(k).attr("title");
                        numberLineConnection = getNumberLineConnection(numberLineConnection);
                        nameStationConnection = getNameStationConnection(nameStationConnection);
//                        System.out.println("Переход на станцию: " + nameStationConnection + " , линия: " + numberLineConnection);
                    }
                }
                stationIndex.addStation(station);
                line.addStation(station);
                ParserCSV.getOpeningDate();
                ParserJSON.getDepthsStation();
            }
        }
    }
    public static void parseConnections(Elements lineNumber){
        for (int i = 0; i < lineNumber.size(); i++) {
            System.out.println();
            System.out.println(lineNumber.get(i).attr("data-depend-set"));
            System.out.println();
            for (int j = 0; j < lineNumber.get(i).select("p.single-station").size(); j++) {
                System.out.println(lineNumber.get(i).select("p.single-station").get(j).text());
                boolean isConnected = !lineNumber.get(i).select("p.single-station")
                        .get(j).select("span.t-icon-metroln").isEmpty();
                if (isConnected) {
                    for (int k = 0; k < lineNumber.get(i).select("p.single-station")
                            .get(j).select("span.t-icon-metroln").size(); k++) {
                        String numberLineConnection = lineNumber.get(i).select("p.single-station").get(j)
                                .select("span.t-icon-metroln").get(k).attr("class");
                        String nameStationConnection = lineNumber.get(i).select("p.single-station")
                                .get(j).select("span.t-icon-metroln").get(k).attr("title");
//                        System.out.println(nameStationConnection);
                        numberLineConnection = getNumberLineConnection(numberLineConnection);
                        nameStationConnection = getNameStationConnection(nameStationConnection);
//                        System.out.println(numberLineConnection);
                        System.out.println("Переход на станцию: " + nameStationConnection + " , линия: " + numberLineConnection);
                    }
                }
            }
        }
    }
    public static String getNumberLineConnection(String numberLineConnection) {
        String[] temp = numberLineConnection.split("-");
        String lineNumber = temp[temp.length-1];
        return lineNumber;
    }
    public static String getNameStationConnection(String nameStationConnection) {
        String[] temp = nameStationConnection.split("«");
        temp = temp[1].split("»");
        String nameConnection = temp[0];
        return nameConnection;
    }
}
