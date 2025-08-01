package ca.bytetube._04_banksystem;

import java.util.List;

public class Bank {
    private List<Branch> branches;
    private BankSystem system;
    private int totalCash;

    public Bank(List<Branch> branches, BankSystem system, int totalCash) {
        this.branches = branches;
        this.system = system;
        this.totalCash = totalCash;
    }

    public void collectCash(double ratio) {
        for (Branch branch : branches) {
            int cashCollected = branch.collectCash(ratio);
            totalCash += cashCollected;
        }
    }

    public Branch addBranch(String address, int initFunds) {
        Branch branch = new Branch(system, initFunds, address);
        branches.add(branch);
        return branch;
    }

    public void printTransactions() {
        for (Transaction transaction : system.getTransactions()){
            System.out.println(transaction.getTransactionDesc());
        }
    }

}
