import parseData.ParserHTML;

import java.io.File;

public class Main {
    static String url = "https://skillbox-java.github.io/";
    static String path = "temp/stations-data";
    //    static String path1 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\0\\5\\dates-2.csv";
//    static String path2 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\2\\4\\depths-1.json";
//    static String path3 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\4\\6\\dates-1.csv";
//    static String path4 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\4\\6\\depths-3.json";
//    static String path5 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\7\\1\\depths-2.json";
//    static String path6 = "F:\\Java\\FilesAndNetwork\\stations-data\\data\\9\\6\\dates-3.csv";
    public static void main(String[] args) {


//        System.out.println("Введите путь к папке или файлу: ");
//        String path = new Scanner(System.in).nextLine();
        File folder = new File(path);
        FileFinder.searchFiles(folder);
//        FileFinder.reader(path2);
//        System.exit(0);

        ParserHTML.parseHTML(url);
    }
}