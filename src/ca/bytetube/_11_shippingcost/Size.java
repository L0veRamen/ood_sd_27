package ca.bytetube._11_shippingcost;

public enum Size {
    SMALL(1.0), MEDIUM(2.0), LARGE(3.0);
    private final double multiplier;

    Size(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
