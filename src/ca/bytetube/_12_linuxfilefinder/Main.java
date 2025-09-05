package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "/Users/dall./Desktop/apache-tomcat-8.5.55";
        FileFinder finder = new FileFinder();
        // Find all files larger than 5MB in a directory
        List<File> largeFiles = finder.findLargeFiles(path, 1 * 1024 * 1024);
       // System.out.println("\nfiles larger than 1 MB:");
        //largeFiles.forEach(file -> System.out.println(file.getAbsolutePath()));
        //Find all XML files in a directory
        List<File> xmlFiles = finder.findFilesByExtension(path, "xml");
//        System.out.println("\n xml files:");
//        xmlFiles.forEach(file -> System.out.println(file.getAbsolutePath()));

        // Find all XML files and larger than 1MB in a directory
        Filter complexFilter = new AndFilter(new SizeFilter(1 * 1024 * 1024), new ExtensionFilter("xml"));
        List<File> files = finder.find(path, complexFilter);
        System.out.println("\n xml files larger than 1MB:");
        xmlFiles.forEach(file -> System.out.println(file.getAbsolutePath()));

        // Find all XML files or larger than 5MB in a directory
        Filter complexFilter2 = new OrFilter(new SizeFilter(1 * 1024 * 1024), new ExtensionFilter("xml"));
        List<File> files2 = finder.find(path, complexFilter2);
        System.out.println("\n xml files or larger than 1MB:");
        files2.forEach(file -> System.out.println(file.getAbsolutePath()));
    }
}
