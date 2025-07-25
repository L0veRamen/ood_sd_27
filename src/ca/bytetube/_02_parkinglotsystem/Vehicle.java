package ca.bytetube._02_parkinglotsystem;

public class Vehicle {
    private int spotSize;

    public Vehicle(int spotSize) {
        this.spotSize = spotSize;
    }

    public int getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(int spotSize) {
        this.spotSize = spotSize;
    }
}
