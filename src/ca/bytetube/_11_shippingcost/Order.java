package ca.bytetube._11_shippingcost;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int orderCount;
    private List<Package> packages;
    private Member member;

    public Order(Member member) {
        orderId = ++orderCount;
        this.member = member;
        packages = new ArrayList<>();

    }

    public void addPackages(Package p) {
        packages.add(p);
    }

    public double subTotalPrice() {
        return packages.stream().mapToDouble(Package::getCost).sum();
    }

    public double getTotalPrice() {
        double price = subTotalPrice();
        if (member != null && member.getLevel() != MembershipLevel.NONE) {
            MembershipDiscount discount = new MembershipDiscount(member);
            price = discount.applyDiscount(price);
        }

        return price;
    }

    public void printOrderDetails() {
        System.out.println("Order #" + orderId);

        if (member != null) {
            System.out.println("Member: " + member.getName() + " (" + member.getLevel() + ")");
        }

        System.out.println("\nItems:");
        packages.forEach(p ->
                System.out.println("- " + p.getDescription() +
                        " : $" + String.format("%.2f", p.getCost())));

        System.out.println("\nSubtotal: $" + String.format("%.2f", subTotalPrice()));

        System.out.println("Final Total: $" + String.format("%.2f", getTotalPrice()));
    }

}
