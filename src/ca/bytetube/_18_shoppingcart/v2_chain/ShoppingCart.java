package ca.bytetube._18_shoppingcart.v2_chain;

import ca.bytetube._18_shoppingcart.model.CartItem;
import ca.bytetube._18_shoppingcart.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    private DiscountHandler head;
    private DiscountHandler tail;

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(String productId) {
        Iterator<CartItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProduct().getId().equals(productId)) {
                it.remove();
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addDiscountHandler(DiscountHandler handler) {
        if (head == null) {
            head = handler;
            tail = handler;
        } else {
            tail.setNext(handler);
            tail = handler;
        }
    }

    public double getSubtotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double getTotal() {
        double subtotal = getSubtotal();
        if (head == null) return subtotal;
        PricingContext ctx = new PricingContext(items, subtotal);
        head.handle(ctx);
        return ctx.getTotal();
    }
}


