package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFSFileScanStrategy implements FileScanStrategy {

    @Override
    public List<File> scan(File directory, Filter filter) {
        List<File> result = new ArrayList<>();
        if (!directory.isDirectory()) return result;

        scanDirectory(directory, filter, result);


        return result;
    }

    private void scanDirectory(File directory, Filter filter, List<File> result) {
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile() && filter.matches(file)) {
                result.add(file);
            } else if (file.isDirectory()) {
                scanDirectory(file, filter, result);
            }
        }
    }
}
