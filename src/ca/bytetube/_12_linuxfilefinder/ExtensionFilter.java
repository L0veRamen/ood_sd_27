package ca.bytetube._12_linuxfilefinder;

import java.io.File;

public class ExtensionFilter implements Filter {
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension.toLowerCase();
    }

    @Override
    public boolean matches(File file) {
        if (!file.isFile()) {
            return false;
        }

        String name = file.getName().toLowerCase();
        return name.endsWith("." + extension);
    }
}
