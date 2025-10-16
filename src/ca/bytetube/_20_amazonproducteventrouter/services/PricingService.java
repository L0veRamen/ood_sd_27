package ca.bytetube._20_amazonproducteventrouter.services;

import ca.bytetube._20_amazonproducteventrouter.DownstreamService;
import ca.bytetube._20_amazonproducteventrouter.ProductEvent;

public class PricingService implements DownstreamService {
    @Override
    public String name() {
        return "PricingService";
    }

    @Override
    public void handle(ProductEvent event) {
        System.out.println("PricingService price update " + event.getProductId() + " new Price= " + event.getPrice());
    }
}
