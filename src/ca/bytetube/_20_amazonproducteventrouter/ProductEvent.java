package ca.bytetube._20_amazonproducteventrouter;

import java.time.Instant;

public class ProductEvent {
    public enum EventType {
        CREATE,
        UPDATE
    }

    public enum SellerTier {
        STANDARD,
        GOLD,
        PLATINUM

    }

    public enum ProductCategory {
        ELECTRONICS,
        GROCERY,
        FASHION,
        HOME,
        BEAUTY,
        SPORTS,
        TOYS,
        OTHER
    }

    String eventId;
    EventType evenType;
    Instant evenTime;
    String productId;
    String sellerId;
    SellerTier sellerTier;
    ProductCategory productCategory;
    String title;
    double price;
    String currency;
    int inventory;

    public ProductEvent(String eventId, EventType evenType, Instant evenTime, String productId,
                        String sellerId, SellerTier sellerTier, ProductCategory productCategory,
                        String title, double price, String currency, int inventory) {
        this.eventId = eventId;
        this.evenType = evenType;
        this.evenTime = evenTime;
        this.productId = productId;
        this.sellerId = sellerId;
        this.sellerTier = sellerTier;
        this.productCategory = productCategory;
        this.title = title;
        this.price = price;
        this.currency = currency;
        this.inventory = inventory;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public EventType getEvenType() {
        return evenType;
    }

    public void setEvenType(EventType evenType) {
        this.evenType = evenType;
    }

    public Instant getEvenTime() {
        return evenTime;
    }

    public void setEvenTime(Instant evenTime) {
        this.evenTime = evenTime;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public SellerTier getSellerTier() {
        return sellerTier;
    }

    public void setSellerTier(SellerTier sellerTier) {
        this.sellerTier = sellerTier;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "eventId='" + eventId + '\'' +
                ", evenType=" + evenType +
                ", evenTime=" + evenTime +
                ", productId='" + productId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerTier=" + sellerTier +
                ", productCategory=" + productCategory +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
