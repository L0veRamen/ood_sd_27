package ca.bytetube._09_pizza._factory_decorator;

public interface DiscountStrategy {
    double applyDiscount(double originalPrice);
    String getDescription();
}
