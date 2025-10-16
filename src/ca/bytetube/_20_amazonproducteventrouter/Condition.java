package ca.bytetube._20_amazonproducteventrouter;

public interface Condition {
    boolean matches(ProductEvent event);
}
