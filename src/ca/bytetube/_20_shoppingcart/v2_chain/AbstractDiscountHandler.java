package ca.bytetube._20_shoppingcart.v2_chain;

public abstract class AbstractDiscountHandler implements DiscountHandler {
    private DiscountHandler next;

    @Override
    public void setNext(DiscountHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PricingContext context) {
        apply(context);
        if (next != null) {
            next.handle(context);
        }
    }

    protected abstract void apply(PricingContext context);
}



