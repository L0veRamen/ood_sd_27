package ca.bytetube._12_linuxfilefinder;

import java.io.File;

public class SizeFilter implements Filter {
    private long minSize;
    private long maxSize;

    public SizeFilter(long minSize) {
        this(minSize, Long.MAX_VALUE);
    }

    public SizeFilter(long minSize, long maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean matches(File file) {
        return file.isFile() && file.length() >= minSize && file.length() <= maxSize;
    }
}
