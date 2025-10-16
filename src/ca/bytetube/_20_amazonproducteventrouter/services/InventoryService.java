package ca.bytetube._20_amazonproducteventrouter.services;

import ca.bytetube._20_amazonproducteventrouter.DownstreamService;
import ca.bytetube._20_amazonproducteventrouter.ProductEvent;

public class InventoryService implements DownstreamService {
    @Override
    public String name() {
        return "InventoryService";
    }

    @Override
    public void handle(ProductEvent event) {
        System.out.println("InventoryService repository syc : product = " + event.getProductId() + ", qty=" + event.getInventory());
    }
}
