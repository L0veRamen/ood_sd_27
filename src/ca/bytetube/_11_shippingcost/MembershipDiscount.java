package ca.bytetube._11_shippingcost;

public class MembershipDiscount {
    private Member member;

    public MembershipDiscount(Member member) {
        this.member = member;
    }

    public double applyDiscount(double originalPrice) {
        return originalPrice * member.getLevel().getMultiplier();

    }

    public String getDesc() {
        return "MemberShip " + member.getId() + "Discount";
    }
}
