import parseData.FileFinder;
import parseData.ParserHTML;

import java.io.File;

public class Main {
    private static final String URL = "https://skillbox-java.github.io/";
    private static final String PATH = "temp/stations-data";

    public static void main(String[] args) {
        File folder = new File(PATH);
        FileFinder.searchFiles(folder);
        ParserHTML.parseHTML(URL);
    }
}