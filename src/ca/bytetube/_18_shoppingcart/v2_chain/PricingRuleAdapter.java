package ca.bytetube._18_shoppingcart.v2_chain;

import ca.bytetube._18_shoppingcart.model.CartItem;
import ca.bytetube._18_shoppingcart.v1_strategy.PricingRule;

import java.util.List;

public class PricingRuleAdapter extends AbstractDiscountHandler {
    private final PricingRule rule;

    public PricingRuleAdapter(PricingRule rule) {
        this.rule = rule;
    }

    @Override
    protected void apply(PricingContext context) {
        List<CartItem> items = context.getItems();
        double newTotal = rule.apply(items, context.getTotal());
        context.setTotal(newTotal);
    }
}


