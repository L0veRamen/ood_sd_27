package ca.bytetube._04_banksystem;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private List<BankTeller> tellers;
    private BankSystem system;
    private int cashOnHold;
    private String address;

    public Branch(BankSystem system, int cashOnHold, String address) {
        this.system = system;
        this.cashOnHold = cashOnHold;
        this.address = address;
        tellers = new ArrayList<>();
    }

    public void addTeller(BankTeller teller) {
        tellers.add(teller);
    }

    private BankTeller getAvailableTeller() {
        int index = (int) Math.round(Math.random() * (tellers.size() - 1));
        return tellers.get(index);
    }

    public int openAccount(String name) {
        if (tellers.isEmpty()) throw new RuntimeException("there is not available teller, wait a sec");
        BankTeller teller = getAvailableTeller();
        return system.openAccount(name, teller.getId());
    }

    public int deposit(int customerId, int amount) {
        if (tellers.isEmpty()) throw new RuntimeException("there is not available teller, wait a sec");

        BankTeller teller = getAvailableTeller();
        cashOnHold += amount;
        return system.deposit(customerId, teller.getId(), amount);
    }

    public int withdraw(int customerId, int amount) {
        if (amount > cashOnHold) throw new RuntimeException("branch does not have enough cash ");
        if (tellers.isEmpty()) throw new RuntimeException("there is not available teller, wait a sec");

        BankTeller teller = getAvailableTeller();
        cashOnHold -= amount;
        return system.withdraw(customerId, teller.getId(), amount);
    }

    public int collectCash(double ratio) {
        int cashToCollect = (int) Math.round(cashOnHold * ratio);
        cashOnHold -= cashToCollect;
        return cashToCollect;
    }

}


