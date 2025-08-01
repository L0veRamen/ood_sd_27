package ca.bytetube._04_banksystem;

public class OpenAccount extends Transaction {

    public OpenAccount(int customerId, int tellerId) {
        super(customerId, tellerId);
    }

    @Override
    public String getTransactionDesc() {
        return "teller:" + getTellerId() + " open account " + getCustomerId();
    }
}
