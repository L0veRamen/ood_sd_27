package ca.bytetube._07_bookreservation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class Resource {
    private String id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    private Queue<String> waitingList;
    private Map<String, Integer> userRentalDays; // track rental days for each user


    public Resource(String id, int quantity, String name) {
        this.id = id;
        this.totalQuantity = quantity;
        this.name = name;
        availableQuantity = quantity;
        waitingList = new LinkedList<>();
        this.userRentalDays = new HashMap<>();
    }

    // Abstract method for daily pricing
    public abstract double getDailyRate();

    // Start rental
    public void startRental(String userName, int days) {
        userRentalDays.put(userName, days);
    }

    public boolean hasActiveRental(String userId) {
        return userRentalDays.containsKey(userId);
    }

    // Calculate fee without removing rental (for payment check)
    public double calculateFee(String userName) {
        Integer days = userRentalDays.get(userName);
        if (days == null) return 0.0;
        return days * getDailyRate();
    }

    // Calculate fee when releasing and remove tracking
    public double endRentalAndCalculateFee(String userName) {
        Integer days = userRentalDays.remove(userName);
        if (days == null) return 0.0;

        return days * getDailyRate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Queue<String> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Queue<String> waitingList) {
        this.waitingList = waitingList;
    }
}
