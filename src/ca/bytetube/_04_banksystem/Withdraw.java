package ca.bytetube._04_banksystem;

public class Withdraw extends Transaction {
    private int amount;

    public Withdraw(int customerId, int tellerId, int amount) {

        super(customerId, tellerId);
        this.amount = amount;
    }

    @Override
    public String getTransactionDesc() {
        return "teller:" + getTellerId() + " withdraw " + amount + " to account " + getCustomerId();
    }
}
