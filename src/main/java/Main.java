import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import parseData.FileFinder;
import parseData.ParserCSV;
import parseData.ParserHTML;
import parseData.ParserJSON;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String url = "https://skillbox-java.github.io/";
    public static final String PATH = "temp/stations-data";
    //    static String path1 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\0\\5\\dates-2.csv";
//    static String path2 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\2\\4\\depths-1.json";
//    static String path3 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\4\\6\\dates-1.csv";
//    static String path4 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\4\\6\\depths-3.json";
//    static String path5 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\7\\1\\depths-2.json";
//    static String path6 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\9\\6\\dates-3.csv";
    public static void main(String[] args) throws IOException {


//        System.out.println("Введите путь к папке или файлу: ");
//        String path = new Scanner(System.in).nextLine();
//        ParserHTML.parseHTML(url);
        File folder = new File(PATH);
        FileFinder.searchFiles(folder);
        ParserCSV.getOpeningDate();
        ParserJSON.getDepthsStation();
//        System.exit(0);
    }
}