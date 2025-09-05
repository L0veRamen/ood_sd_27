package ca.bytetube._12_linuxfilefinder;

import java.io.File;

public class NotFilter implements Filter {
    private Filter filter;

    public NotFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public boolean matches(File file) {

        return !filter.matches(file);
    }
}
