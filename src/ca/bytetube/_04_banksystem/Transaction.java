package ca.bytetube._04_banksystem;

public abstract class Transaction {
    private int customerId;
    private int tellerId;

    public Transaction(int customerId, int tellerId) {
        this.customerId = customerId;
        this.tellerId = tellerId;
    }

    public abstract String getTransactionDesc();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTellerId() {
        return tellerId;
    }

    public void setTellerId(int tellerId) {
        this.tellerId = tellerId;
    }
}
