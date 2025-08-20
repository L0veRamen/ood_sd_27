package ca.bytetube._08_moviebooking;

import java.util.List;

public class Booking {
    private String id;
    private User user;
    private Show show;
    private List<Seat> seats;
    private BookingStatus status;
    private PaymentManager payment;

    public Booking(String id, User user, Show show, List<Seat> seats) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.PENDING;
    }

    public double getTotalAmount() {
        return show.getPrice() * seats.size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setPayment(PaymentManager payment) {
        this.payment = payment;
    }


}
