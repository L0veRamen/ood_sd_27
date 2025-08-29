package ca.bytetube._11_shippingcost;

public class BasePackage implements Package {
    private Size size;
    private DeliverySpeed deliverySpeed;

    public BasePackage(Size size, DeliverySpeed deliverySpeed) {
        this.size = size;
        this.deliverySpeed = deliverySpeed;
    }

    @Override
    public String getDescription() {
        return "Package:(" + size + "," + deliverySpeed + ").";
    }

    @Override
    public double getCost() {
        return deliverySpeed.getCost() * size.getMultiplier();
    }

    @Override
    public Size getSize() {
        return size;
    }
}
