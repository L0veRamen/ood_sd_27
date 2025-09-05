package ca.bytetube._12_linuxfilefinder;

import java.io.File;

public interface Filter {
    boolean matches(File file);
}
