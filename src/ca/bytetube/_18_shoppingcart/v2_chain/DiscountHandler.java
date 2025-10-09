package ca.bytetube._18_shoppingcart.v2_chain;

public interface DiscountHandler {
    void setNext(DiscountHandler next);
    void handle(PricingContext context);
}



