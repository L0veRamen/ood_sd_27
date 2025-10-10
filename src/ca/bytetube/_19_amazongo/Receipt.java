package ca.bytetube._19_amazongo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Receipt {
    String orderId;
    Map<Product, Integer> items = new LinkedHashMap<>();
    Price subTotal = new Price(0.0, "USD");
    Price total = new Price(0.0, "USD");

    public Receipt(String orderId) {
        this.orderId = orderId;
    }

    public void add(Product product, int qty) {
        items.put(product, qty);
        subTotal = subTotal.add(product.getPrice().multiply(qty));
        total = subTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public Price getSubTotal() {
        return subTotal;
    }

    public Price getTotal() {
        return total;
    }

}
