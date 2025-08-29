package ca.bytetube._11_shippingcost;

public class SignatureRequiredDecorator extends ItemOptionDecorator {
    public SignatureRequiredDecorator(Package pack) {
        super(pack);
    }

    @Override
    public String getDescription() {
        return pack.getDescription() + ", Signature Required";
    }

    @Override
    public double getCost() {
        return pack.getCost() + 8.00 * pack.getSize().getMultiplier();
    }
}
