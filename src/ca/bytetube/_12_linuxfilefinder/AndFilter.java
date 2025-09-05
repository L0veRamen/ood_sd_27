package ca.bytetube._12_linuxfilefinder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AndFilter implements Filter {
    private List<Filter> filters;

    public AndFilter(Filter... filters) {
        this.filters = Arrays.asList(filters);
    }

    @Override
    public boolean matches(File file) {
        for (Filter filter : filters) {
            if (!filter.matches(file)) {
                return false;
            }
        }
        return true;
    }
}
