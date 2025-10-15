package ca.bytetube._20_amazonproducteventrouter.services;

import ca.bytetube._20_amazonproducteventrouter.DownstreamService;
import ca.bytetube._20_amazonproducteventrouter.ProductEvent;

public class CatalogService implements DownstreamService {
    @Override
    public String name() {
        return "CatalogService";
    }

    @Override
    public void handle(ProductEvent event) {
        System.out.println("CatalogService update catalog" + event);
    }
}
