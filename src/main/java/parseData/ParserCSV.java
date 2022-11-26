package parseData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserCSV {
    private static Map<String, String> openingDate = new HashMap<>();
    private static List<String> openingDateList = new ArrayList<>();
    public static Map<String, String> getOpeningDate () {
        List<String> pathCSV = FileFinder.getPathCSV();
        for (String path : pathCSV) {
            try {
                openingDateList = Files.readAllLines(Paths.get(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        for (String statoion : openingDateList) {
            String[] st = statoion.split(",");
            if (st.length == 2 && !st[0].equals("name") && !st[1].equals("date")) {
                openingDate.put(st[0], st[1]);
            }
        }
        //todo Для проверки значений, удалить поссле проверок.
        int count = 1;
        for (Map.Entry<String, String> entry : openingDate.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
            count++;
        }
        System.out.println(count);
        return openingDate;
    }
}
