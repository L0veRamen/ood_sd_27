package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class OrFilter implements Filter {
    private List<Filter> filters;

    public OrFilter(Filter... filters) {
        this.filters = Arrays.asList(filters);
    }

    @Override
    public boolean matches(File file) {
        for (Filter filter : filters) {
            if (filter.matches(file)) {
                return true;
            }
        }
        return false;
    }
}
