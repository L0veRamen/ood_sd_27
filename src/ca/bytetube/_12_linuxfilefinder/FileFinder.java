package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.List;

public class FileFinder {
    private FileScanStrategy scanStrategy;

    public FileFinder() {
        this(new DFSFileScanStrategy());
    }

    public FileFinder(FileScanStrategy scanStrategy) {
        this.scanStrategy = scanStrategy;
    }

    public List<File> find(String directoryPath, Filter filter) {
        File directory = new File(directoryPath);
        return scanStrategy.scan(directory, filter);

    }

    public List<File> findLargeFiles(String directoryPath, long minSize) {
        return find(directoryPath, new SizeFilter(minSize));

    }

    public List<File> findFilesByExtension(String directoryPath, String extension) {
        return find(directoryPath, new ExtensionFilter(extension));

    }


}
