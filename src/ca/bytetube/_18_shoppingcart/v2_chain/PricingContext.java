package ca.bytetube._18_shoppingcart.v2_chain;

import ca.bytetube._18_shoppingcart.model.CartItem;

import java.util.List;

public class PricingContext {
    private final List<CartItem> items;
    private double total;

    public PricingContext(List<CartItem> items, double total) {
        this.items = items;
        this.total = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}


