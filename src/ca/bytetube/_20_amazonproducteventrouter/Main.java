package ca.bytetube._20_amazonproducteventrouter;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        EventRouter router = new EventRouter();
        RouteRuleFactory.defaultRules().forEach(router::addRule);

        ProductEvent create = new ProductEvent(
                "evt-1", ProductEvent.EventType.CREATE, Instant.now(),
                "p-1001", "s-001", ProductEvent.SellerTier.STANDARD,
                ProductEvent.ProductCategory.GROCERY, "Organic Honey", 19.99, "CAD", 200
        );

        ProductEvent update = new ProductEvent(
                "evt-2", ProductEvent.EventType.UPDATE, Instant.now(),
                "p-1001", "s-001", ProductEvent.SellerTier.GOLD,
                ProductEvent.ProductCategory.ELECTRONICS, "Bluetooth Headset", 39.99, "CAD", 50
        );
        System.out.println("route create");
        router.route(create);
        System.out.println("route update");
        router.route(update);
    }
}
