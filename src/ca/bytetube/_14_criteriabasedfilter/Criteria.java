package ca.bytetube._14_criteriabasedfilter;

import java.util.Map;

public abstract class Criteria {
    protected String operator;

    public Criteria(String operator) {
        this.operator = operator;
    }

    public abstract boolean isMatch(Map<String,Object> input);
}
