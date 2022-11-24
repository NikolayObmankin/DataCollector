import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private static List<File> pathCSV = new ArrayList<>();
    private static List<File> pathJSON = new ArrayList<>();

    public static void searchFiles(File folder) {
        File[] directoryFiles = null;
        if (folder.isDirectory()) {
            directoryFiles = folder.listFiles();
        }
        if (directoryFiles != null) {
            for (File file : directoryFiles) {
                if (file.getName().endsWith(".csv") && !file.getAbsolutePath().contains("MACOSX")) {
                    pathCSV.add(file);
                    System.out.println(file);
                }
                if (file.getName().endsWith(".json") && !file.getAbsolutePath().contains("MACOSX")) {
                    pathJSON.add(file);
                    System.out.println(file);
                }
                searchFiles(file);
            }
        }
    }

    public static List<File> getPathCSV() {
        return pathCSV;
    }

    public static List<File> getPathJSON() {
        return pathJSON;
    }

    //todo временный метод для проверки данных
    public static void reader (String s) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(s));
            lines.forEach(line -> sb.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}