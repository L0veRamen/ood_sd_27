package ca.bytetube._18_shoppingcart.v1_strategy;

import ca.bytetube.ood._20_shoppingcart.model.CartItem;

import java.util.List;

public class BuyXGetYFreeRule implements PricingRule {
    private final String productId;
    private final int buyX;
    private final int getY;

    public BuyXGetYFreeRule(String productId, int buyX, int getY) {
        if (buyX <= 0 || getY < 0) throw new IllegalArgumentException("invalid x/y");
        this.productId = productId;
        this.buyX = buyX;
        this.getY = getY;
    }

    @Override
    public double apply(List<CartItem> items, double currentTotal) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(productId)) {
                int group = buyX + getY;
                int quantity = item.getQuantity();
                int freeUnits = (quantity / group) * getY;
                double discount = freeUnits * item.getProduct().getUnitPrice();
                return Math.max(0, currentTotal - discount);
            }
        }
        return currentTotal;
    }
}


