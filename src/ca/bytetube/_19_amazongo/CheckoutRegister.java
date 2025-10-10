package ca.bytetube._19_amazongo;

public class CheckoutRegister {

    public Order makeOrder(Session s) {
        Order order = new Order("O-" + System.nanoTime(), s.getId());
        for (var e : s.getBasket().view().entrySet()) {
            order.add(e.getKey(), e.getValue());
        }

        return order;

    }

    public Receipt makeReceipt(Order order) {
        Receipt receipt = new Receipt(order.getId());
        for (var e : order.getItems().entrySet()) {
            receipt.add(e.getKey(), e.getValue());
        }

        return receipt;
    }

    public PaymentResult pay(Order order, PaymentMethod method) {
        Receipt receipt = makeReceipt(order);
        Price total = receipt.getTotal();
        PaymentResult auth = method.authorize(total);
        if (auth.getStatus() != PaymentStatus.AUTHORIZED) return auth;

        return method.capture(auth.getAuthorizationId(), total);
    }
}
