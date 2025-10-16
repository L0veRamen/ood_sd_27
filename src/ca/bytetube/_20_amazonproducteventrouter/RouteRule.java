package ca.bytetube._20_amazonproducteventrouter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RouteRule {
    String name;
    Condition condition;
    List<DownstreamService> targets;

    public RouteRule(String name, Condition condition, List<DownstreamService> targets) {
        this.name = name;
        this.condition = condition;
        this.targets = new ArrayList<>(Objects.requireNonNull(targets, "targets"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<DownstreamService> getTargets() {
        return Collections.unmodifiableList(targets);
    }

    public void setTargets(List<DownstreamService> targets) {
        this.targets = targets;
    }
}
