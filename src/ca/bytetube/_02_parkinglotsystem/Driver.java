package ca.bytetube._02_parkinglotsystem;

public class Driver {
    private int id;
    private Vehicle vehicle;
    private int paymentDue;

    public Driver(int id, Vehicle vehicle) {
        this.id = id;
        this.vehicle = vehicle;
    }

    public void charge(int amount) {
        paymentDue += amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(int paymentDue) {
        this.paymentDue = paymentDue;
    }
}
