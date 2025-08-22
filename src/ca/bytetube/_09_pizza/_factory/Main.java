package ca.bytetube._09_pizza._factory;

public class Main {
    public static void main(String[] args) {
        Pizza cheesePizza = PizzaFactory.createPizza("cheese", Size.LARGE);//12.99
        cheesePizza.addTopping(new Topping("extra cheese", 1.50));//1.50
        cheesePizza.addTopping(new Topping("Mushroom", 1.00));//1.00
        Order order = new Order();
        order.addPizza(cheesePizza);

        Pizza pepperoniPizza = PizzaFactory.createPizza("Pepperoni", Size.MEDIUM);//11.99
        pepperoniPizza.addTopping(new Topping("Olivers", 0.75));//0.75
        order.addPizza(pepperoniPizza);

        System.out.println(order.getTotalPrice());//28.23

    }
}
