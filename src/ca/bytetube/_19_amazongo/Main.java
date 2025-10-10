package ca.bytetube._19_amazongo;

public class Main {
    public static void main(String[] args) {
        Product water = new Product("W", "Water", new Price(1.50, "USD"));
        Product chips = new Product("C", "Chips", new Price(2.00, "USD"));

        TrackingRegister tracking = new TrackingRegister();
        Session s = tracking.start(new Customer("U-1"));
        tracking.pickup(s.getId(), water, 2);
        tracking.pickup(s.getId(), chips, 1);
        tracking.putback(s.getId(), water, 1);
        CheckoutRegister checkout = new CheckoutRegister();

        Order order = checkout.makeOrder(s);

        Receipt receipt = checkout.makeReceipt(order);

        System.out.println("OrderID: " + order.getId());
        System.out.println("Items:");
        for (var e : receipt.getItems().entrySet()) {
            System.out.println(" - " + e.getKey().getName() + " x" + e.getValue() + " = " + e.getKey().getPrice().multiply(e.getValue()));
        }
        System.out.println("Subtotal: " + receipt.getSubTotal());
        System.out.println("Total: " + receipt.getTotal());
        PaymentMethod method = new CardOnFile("**** **** **** 1234");

        PaymentResult result = checkout.pay(order, method);
        System.out.println("Payment Status: " + result.getStatus());
        System.out.println("Auth ID: " + result.getAuthorizationId());
        System.out.println("Capture ID: " + result.getCaptureId());

    }
}
