package ca.bytetube._04_banksystem;

public class BankAccount {
    private int customerId;
    private String name;
    private int balance;

    public BankAccount(int customerId, String name, int balance) {
        this.customerId = customerId;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }


    public String getName() {
        return name;
    }


    public int getBalance() {
        return balance;
    }

}
