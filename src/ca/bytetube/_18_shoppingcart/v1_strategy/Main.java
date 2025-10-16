package ca.bytetube._18_shoppingcart.v1_strategy;

import ca.bytetube._18_shoppingcart.model.Product;

public class Main {
    public static void main(String[] args) {
        Product apple = new Product("A1", "Apple", 1.20);
        Product banana = new Product("B1", "Banana", 0.80);
        Product milk = new Product("M1", "Milk", 3.50);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(banana, 5);
        cart.addItem(milk, 2);

        cart.addPricingRule(new BuyXGetYFreeRule("B1", 2, 1));
        cart.addPricingRule(new PercentageOffRule(0.10, 10.0));

        System.out.println("Subtotal: " + cart.getSubtotal());
        System.out.println("Total after discounts: " + cart.getTotal());
    }
}


