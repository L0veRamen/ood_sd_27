package ca.bytetube._19_amazongo;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    String id;
    String sessionId;
    Map<Product, Integer> items = new LinkedHashMap<>();
    Instant createdAt = Instant.now();

    public Order(String id, String sessionId) {
        this.id = id;
        this.sessionId = sessionId;
    }

    public void add(Product product, int qty) {
        items.put(product, qty);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }


}
