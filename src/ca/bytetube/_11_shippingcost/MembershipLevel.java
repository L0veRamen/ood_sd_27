package ca.bytetube._11_shippingcost;

public enum MembershipLevel {
    NONE(0),
    BRONZE(5),
    SILVER(10),
    GOLD(15);

    private final double multiplier;

    MembershipLevel(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return (100 - multiplier) / 100.0;
    }
}
