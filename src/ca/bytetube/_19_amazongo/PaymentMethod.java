package ca.bytetube._19_amazongo;

public interface PaymentMethod {

    PaymentResult authorize(Price total);

    PaymentResult capture(String authId, Price total);
}
