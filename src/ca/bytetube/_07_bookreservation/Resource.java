package ca.bytetube._07_bookreservation;

import java.util.LinkedList;
import java.util.Queue;

public class Resource {
    private String id;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    private Queue<String> waitingList;

    public Resource(String id, int quantity, String name) {
        this.id = id;
        this.totalQuantity = quantity;
        this.name = name;
        availableQuantity = quantity;
        waitingList = new LinkedList<>();
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
