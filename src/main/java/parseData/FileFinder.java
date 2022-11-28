package parseData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private static final List<String> pathCSV = new ArrayList<>();
    private static final List<String> pathJSON = new ArrayList<>();

    public static void searchFiles(File folder) {
        File[] directoryFiles = null;
        if (folder.isDirectory()) {
            directoryFiles = folder.listFiles();
        }
        if (directoryFiles != null) {
            for (File file : directoryFiles) {
                if (file.getName().endsWith(".csv") && !file.getAbsolutePath().contains("MACOSX")) {
                    pathCSV.add(file.toString());
                }
                if (file.getName().endsWith(".json") && !file.getAbsolutePath().contains("MACOSX")) {
                    pathJSON.add(file.toString());
                }
                searchFiles(file);
            }
        }
    }

    public static List<String> getPathCSV() {
        return pathCSV;
    }

    public static List<String> getPathJSON() {
        return pathJSON;
    }
}