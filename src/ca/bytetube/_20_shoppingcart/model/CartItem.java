package ca.bytetube._20_shoppingcart.model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getUnitPrice() * quantity;
    }
}



