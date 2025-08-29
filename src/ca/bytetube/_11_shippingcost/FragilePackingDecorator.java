package ca.bytetube._11_shippingcost;

public class FragilePackingDecorator extends ItemOptionDecorator {
    public FragilePackingDecorator(Package pack) {
        super(pack);
    }

    @Override
    public String getDescription() {
        return pack.getDescription() + ", Fragile Packing";
    }

    @Override
    public double getCost() {
        return pack.getCost() + 5.00 * pack.getSize().getMultiplier();
    }
}
