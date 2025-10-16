package ca.bytetube._19_amazongo;

public class Session {
    String id;
    Customer customer;
    Basket basket = new Basket();

    public Session(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Basket getBasket() {
        return basket;
    }

}
