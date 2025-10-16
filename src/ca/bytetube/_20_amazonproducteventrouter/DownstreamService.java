package ca.bytetube._20_amazonproducteventrouter;

public interface DownstreamService {
    String name();

    void handle(ProductEvent event);

}
