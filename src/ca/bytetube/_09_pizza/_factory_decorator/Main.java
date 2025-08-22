package ca.bytetube._09_pizza._factory_decorator;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Member member = new Member("M001", "Dal", MembershipLevel.GOLD);
        Order order = new Order(member);
        // 使用工厂创建第一个披萨并添加配料
        Pizza pizza1 = PizzaFactory.createPizza("cheese", Size.LARGE);
        pizza1 = new ExtraCheeseDecorator(pizza1);
        pizza1 = new MushroomDecorator(pizza1);
        order.addPizza(pizza1);

        // 使用工厂创建第二个披萨并添加配料
        Pizza pizza2 = PizzaFactory.createPizza("pepperoni", Size.MEDIUM);
        order.addPizza(pizza2);

        // 创建优惠券
        Coupon coupon = new Coupon("SAVE10", 10, true, LocalDate.now().plusDays(30));
        CouponManager couponManager = new CouponManager();
        couponManager.addCoupon(coupon);

        // 验证并使用优惠券
        couponManager.validateCoupon("SAVE10").ifPresent(validCoupon -> order.addDiscount(new CouponDiscount(validCoupon)));


        order.printOrderDetails();
    }
}
