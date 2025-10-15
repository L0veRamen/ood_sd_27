package ca.bytetube._20_amazonproducteventrouter;

import ca.bytetube._20_amazonproducteventrouter.services.CatalogService;
import ca.bytetube._20_amazonproducteventrouter.services.InventoryService;
import ca.bytetube._20_amazonproducteventrouter.services.PricingService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RouteRuleFactory {

    public static List<RouteRule> defaultRules() {
        DownstreamService catalog = new CatalogService();
        DownstreamService inventory = new InventoryService();
        DownstreamService pricing = new PricingService();

        RouteRule createCatalogAndInventory = new RouteRule(
                "CREATE->Catalog & Inventory",
                e -> e.getEvenType() == ProductEvent.EventType.CREATE,
                Arrays.asList(catalog, inventory)
        );
        RouteRule pricingChange = new RouteRule(
                "UPDATE price -> Pricing",
                e -> e.getEvenType() == ProductEvent.EventType.UPDATE,
                Collections.singletonList(pricing)

        );

        RouteRule electronicsPriority = new RouteRule(
                "Electronics from GOLD/PLATINUM->fast handle",
                e -> (e.getProductCategory() == ProductEvent.ProductCategory.ELECTRONICS) &&
                        (e.getSellerTier() == ProductEvent.SellerTier.GOLD || e.getSellerTier() == ProductEvent.SellerTier.PLATINUM),
                Collections.singletonList(inventory)
        );

        return Arrays.asList(createCatalogAndInventory, pricingChange, electronicsPriority);

    }
}
