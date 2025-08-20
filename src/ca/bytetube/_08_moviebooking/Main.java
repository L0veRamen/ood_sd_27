package ca.bytetube._08_moviebooking;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieBookingSystem system = new MovieBookingSystem();

        try {
            System.out.println("=== system initialization ===");
            User user1 = system.registerUser("eric", "eric@example.com", "5193678876", "pwd1");
            User user2 = system.registerUser("bob", "bob@example.com", "5193657606", "pwd2");
            User user3 = system.registerUser("tony", "tony@example.com", "4509540321", "pwd3");

            Movie movie = new Movie("M1", "Captain America", 150);
            system.addMovie(movie);

            Theater theater = new Theater("T1", "Theater1");
            Show show = new Show("S1", movie, theater,
                    LocalDateTime.now().plusDays(1).withHour(14).withMinute(30), 50.0);
            system.addShow(movie, show);

            System.out.println("\n=== Concurrent booking test ===");
            Seat targetSeat = new Seat(1, 1);  // 目标座位

            Runnable bookingTask = () -> {
                try {
                    String userId = Thread.currentThread().getName().equals("User1") ? user1.getId() :
                            Thread.currentThread().getName().equals("User2") ? user2.getId() :
                                    user3.getId();

                    List<Seat> seats = Arrays.asList(targetSeat);
                    Booking booking = system.createBooking(userId, "S1", seats);

                    System.out.println(Thread.currentThread().getName() +
                            " Booking is successful! Order number: " + booking.getId());

                    boolean paymentSuccess = system.makePayment(
                            booking.getId(),
                            PaymentMethod.PAYPAL
                    );
                    System.out.println(Thread.currentThread().getName() +
                            " paid" + (paymentSuccess ? " successfully" : " unsuccessfully"));

                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() +
                            " Booking failed: " + e.getMessage());
                }
            };

            Thread t1 = new Thread(bookingTask, "User1");
            Thread t2 = new Thread(bookingTask, "User2");
            Thread t3 = new Thread(bookingTask, "User3");

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            System.out.println("\n=== Final state verification ===");
            boolean seatAvailable = show.isSeatAvailable(targetSeat);
            System.out.println("the seat is still available: " + seatAvailable);

            System.out.println("\n=== User order status ===");
            checkUserBookings(user1, "eric");
            checkUserBookings(user2, "bob");
            checkUserBookings(user3, "tony");

        } catch (Exception e) {
            System.out.println("test failure: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void checkUserBookings(User user, String userName) {
        List<Booking> bookings = user.getBookingHistory();
        System.out.println(userName + "'s Order quantity: " + bookings.size());
        for (Booking booking : bookings) {
            System.out.println("order status: " + booking.getStatus().getDescription());
        }
    }
}
