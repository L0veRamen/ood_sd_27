package ca.bytetube._18_shoppingcart.v1_strategy;

import ca.bytetube.ood._20_shoppingcart.model.CartItem;

import java.util.List;

public class PercentageOffRule implements PricingRule {
    private final double percentOff;
    private final double minimumSubtotal;

    public PercentageOffRule(double percentOff, double minimumSubtotal) {
        if (percentOff < 0 || percentOff > 1) throw new IllegalArgumentException("percentOff must be between 0 and 1");
        this.percentOff = percentOff;
        this.minimumSubtotal = minimumSubtotal;
    }

    @Override
    public double apply(List<CartItem> items, double currentTotal) {
        if (currentTotal >= minimumSubtotal) {
            return currentTotal * (1 - percentOff);
        }
        return currentTotal;
    }
}


