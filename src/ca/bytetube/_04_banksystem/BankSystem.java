package ca.bytetube._04_banksystem;

import java.util.List;

public class BankSystem {
    private List<BankAccount> accounts;
    private List<Transaction> transactions;

    public BankSystem(List<BankAccount> accounts, List<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public int openAccount(String name, int tellerId) {
        int customerId = accounts.size();
        BankAccount account = new BankAccount(customerId, name, 0);
        accounts.add(account);

        //log transaction
        Transaction transaction = new OpenAccount(customerId, tellerId);
        transactions.add(transaction);

        return customerId;
    }

    public int deposit(int customerId, int tellerId, int amount) {
        BankAccount account = accounts.get(customerId);
        account.deposit(amount);
        //log transaction
        Transaction transaction = new Deposit(customerId, tellerId, amount);
        transactions.add(transaction);

        return account.getBalance();
    }

    public int withdraw(int customerId, int tellerId, int amount) {
        BankAccount account = accounts.get(customerId);
        if (amount > account.getBalance()) throw new Error("Insufficient amount ! ");
        account.withdraw(amount);

        //log transaction
        Transaction transaction = new Withdraw(customerId, tellerId, amount);
        transactions.add(transaction);
        return account.getBalance();
    }


    public List<BankAccount> getAccounts() {
        return accounts;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

}
