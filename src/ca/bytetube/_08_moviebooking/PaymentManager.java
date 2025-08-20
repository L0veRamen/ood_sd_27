package ca.bytetube._08_moviebooking;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentManager {
    private String id;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private LocalDateTime paymentTime;

    public PaymentManager(double amount, PaymentMethod method) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    public boolean process() {

        try {

            Thread.sleep(1000);
            this.status = PaymentStatus.SUCCESS;
            this.paymentTime = LocalDateTime.now();
            return true;
        } catch (Exception e) {
            this.status = PaymentStatus.FAILED;
            return false;
        }
    }

    // getters and setters
}
