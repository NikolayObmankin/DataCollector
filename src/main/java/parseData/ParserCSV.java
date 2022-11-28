package parseData;

import metroData.Station;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParserCSV {
    private static List<String> openingDateList = new ArrayList<>();

    public static void getOpeningDate() {
        List<String> pathCSV = FileFinder.getPathCSV();
        for (String path : pathCSV) {
            try {
                openingDateList = Files.readAllLines(Paths.get(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        TreeSet<Station> stations = StationIndex.stationIndex.getStationsSet();

        for (String station : openingDateList) {
            String[] st = station.split(",");
            if (st.length == 2 && !st[0].equals("name") && !st[1].equals("date")) {
                for (Station station1 : stations) {
                    if (station1.getName().equalsIgnoreCase(st[0]) && station1.getOpeningDate() == null) {
                        station1.setOpeningDate(st[1]);
                    }
                }
            }
        }
    }
}
