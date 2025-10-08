package ca.bytetube._20_shoppingcart.v2_chain;

public class PercentageOffHandler extends AbstractDiscountHandler {
    private final double percentOff;
    private final double minimumSubtotal;

    public PercentageOffHandler(double percentOff, double minimumSubtotal) {
        if (percentOff < 0 || percentOff > 1) throw new IllegalArgumentException("percentOff must be between 0 and 1");
        this.percentOff = percentOff;
        this.minimumSubtotal = minimumSubtotal;
    }

    @Override
    protected void apply(PricingContext context) {
        double total = context.getTotal();
        if (total >= minimumSubtotal) {
            context.setTotal(total * (1 - percentOff));
        }
    }
}


