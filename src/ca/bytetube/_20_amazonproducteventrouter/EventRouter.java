package ca.bytetube._20_amazonproducteventrouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventRouter {
    List<RouteRule> rules = new ArrayList<>();

    public EventRouter addRule(RouteRule routeRule) {
        rules.add(Objects.requireNonNull(routeRule));
        return this;
    }

    public void route(ProductEvent event) {
        for (RouteRule rule : rules) {
            if (rule.getCondition().matches(event)) {
                for (DownstreamService target : rule.getTargets()) {
                    target.handle(event);
                }
            }
        }
    }

}
