package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.List;

public interface FileScanStrategy {
    List<File> scan(File directory, Filter filter);
}
