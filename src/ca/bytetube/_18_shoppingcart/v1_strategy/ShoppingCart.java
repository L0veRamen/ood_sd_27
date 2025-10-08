package ca.bytetube._18_shoppingcart.v1_strategy;

import ca.bytetube.ood._20_shoppingcart.model.CartItem;
import ca.bytetube.ood._20_shoppingcart.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    private final List<PricingRule> pricingRules = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addPricingRule(PricingRule rule) {
        pricingRules.add(rule);
    }

    public double getSubtotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double getTotal() {
        double total = getSubtotal();
        for (PricingRule rule : pricingRules) {
            total = rule.apply(items, total);
        }
        return total;
    }
}


