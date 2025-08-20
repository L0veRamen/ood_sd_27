package ca.bytetube._07_bookreservation;

public class User {
    private String id;
    private String name;
    private double balance;

    public User(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("%s paid $%.2f. Remaining balance: $%.2f%n",
                    name, amount, balance);
            return true;
        } else {
            System.out.printf("%s insufficient funds! Need $%.2f but only has $%.2f%n",
                    name, amount, balance);
            return false;
        }
    }

    public void addMoney(double amount) {
        balance += amount;
        System.out.printf("%s added $%.2f. New balance: $%.2f%n", name, amount, balance);
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
}
