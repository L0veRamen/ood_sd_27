package ca.bytetube._08_moviebooking;

import java.time.LocalDateTime;
import java.util.*;

public class MovieBookingSystem {
    private List<Movie> movies;
    // Key: showId (eg. "S1", "S2")
    // Value: Show
    private Map<String, Show> shows;
    // Key: bookingId (eg. "B1", "B2")
    // Value: Booking
    private Map<String, Booking> bookings;
    private UserManager userManager;

    public MovieBookingSystem() {
        this.movies = new ArrayList<>();
        this.shows = new HashMap<>();
        this.bookings = new HashMap<>();
        this.userManager = new UserManager();
    }

    public User registerUser(String username, String email, String phoneNumber, String password) {
        return userManager.registerUser(username, email, phoneNumber, password);
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    public Booking createBooking(String userId, String showId, List<Seat> seats) {
        // 1. parameter calibration
        if (userId == null || showId == null || seats == null || seats.isEmpty()) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        // 2. Obtain user information and verify the user status
        User user = userManager.getUserById(userId);
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("User is not active");
        }

        // 3. Obtain the Show information
        Show show = shows.get(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found");
        }

        // 4. Verification show session time
        if (show.getShowTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Show has already started");
        }

        // 5. Seat verification and reservation (using a synchronization block to ensure thread safety)
        synchronized (show) {
            // 5.1 Check whether all the seats in the "seats" parameter are available.
            for (Seat seat : seats) {
                if (!show.isSeatAvailable(seat)) {
                    throw new IllegalStateException(String.format("Seat %d-%d is not available", seat.getRow(), seat.getColumn())
                    );
                }
            }
            // 5.2 reserve all the seats in parameter "seats"
            try {
                for (Seat seat : seats) {
                    if (!show.bookSeat(seat)) {
                        // If the reservation fails, the previously reserved seats need to be rolled back.
                        rollbackBookedSeats(show, seats, seat);
                        throw new IllegalStateException("Failed to book seat");
                    }
                }
            } catch (Exception e) {
                rollbackBookedSeats(show, seats, null);
                throw e;
            }
        }

        // 6. generate Booking
        String bookingId = generateBookingId();
        Booking booking = new Booking(bookingId, user, show, seats);

        // 7. save booking info
        bookings.put(bookingId, booking);

        user.addBooking(booking);

        return booking;
    }


    private void rollbackBookedSeats(Show show, List<Seat> allSeats, Seat failedSeat) {
        for (Seat seat : allSeats) {
            if (seat.equals(failedSeat)) {
                break;
            }
            show.releaseSeat(seat);
        }
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addShow(Movie movie, Show show) {
        movie.addShow(show);
        shows.put(show.getId(), show);
    }

    public boolean makePayment(String bookingId, PaymentMethod method) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        PaymentManager payment = new PaymentManager(booking.getTotalAmount(), method);
        boolean success = payment.process();

        if (success) {
            booking.setStatus(BookingStatus.CONFIRMED);
            booking.setPayment(payment);
        }

        return success;
    }

    private String generateBookingId() {
        return UUID.randomUUID().toString();
    }


}
