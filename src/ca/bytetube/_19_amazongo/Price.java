package ca.bytetube._19_amazongo;

public class Price {
    double amount;
    String currency;

    public Price(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Price add(Price other) {
        return new Price(this.amount + other.amount, this.currency);
    }

    public Price multiply(int qty) {
        return new Price(this.amount * qty, this.currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
