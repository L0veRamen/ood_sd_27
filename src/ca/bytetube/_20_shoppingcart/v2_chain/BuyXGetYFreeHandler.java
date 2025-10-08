package ca.bytetube._20_shoppingcart.v2_chain;

import ca.bytetube._20_shoppingcart.model.CartItem;

public class BuyXGetYFreeHandler extends AbstractDiscountHandler {
    private final String productId;
    private final int buyX;
    private final int getY;

    public BuyXGetYFreeHandler(String productId, int buyX, int getY) {
        if (buyX <= 0 || getY < 0) throw new IllegalArgumentException("invalid x/y");
        this.productId = productId;
        this.buyX = buyX;
        this.getY = getY;
    }

    @Override
    protected void apply(PricingContext context) {
        for (CartItem item : context.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                int group = buyX + getY;
                int quantity = item.getQuantity();
                int freeUnits = (quantity / group) * getY;
                double discount = freeUnits * item.getProduct().getUnitPrice();
                context.setTotal(Math.max(0, context.getTotal() - discount));
                return;
            }
        }
    }
}


