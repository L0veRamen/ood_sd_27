package ca.bytetube._08_moviebooking;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;  // 实际应用中需要加密存储
    private UserStatus status;
    private List<Booking> bookingHistory;

    public User(String id, String username, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = UserStatus.ACTIVE;
        this.bookingHistory = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookingHistory.add(booking);
    }

    public List<Booking> getBookingHistory() {
        return new ArrayList<>(bookingHistory);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setBookingHistory(List<Booking> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

}

