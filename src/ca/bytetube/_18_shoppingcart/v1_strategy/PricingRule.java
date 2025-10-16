package ca.bytetube._18_shoppingcart.v1_strategy;

import ca.bytetube._18_shoppingcart.model.CartItem;

import java.util.List;

public interface PricingRule {
    double apply(List<CartItem> items, double currentTotal);
}


