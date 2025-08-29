package ca.bytetube._11_shippingcost;

public enum DeliverySpeed {
    SAME_DAY(10.0), NEXT_DAY(5.0), TWO_DAY(3.0);
    private final double cost;

    DeliverySpeed(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
