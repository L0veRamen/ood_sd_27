package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSFileScanStrategy implements FileScanStrategy {

    @Override
    public List<File> scan(File directory, Filter filter) {
        List<File> result = new ArrayList<>();
        if (!directory.isDirectory()) return result;

        Queue<File> queue = new LinkedList<>();
        queue.offer(directory);
        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            if (files == null) continue;
            for (File file : files) {
                if (file.isFile() && filter.matches(file)) {
                    result.add(file);
                }
            }
        }

        return result;
    }
}
