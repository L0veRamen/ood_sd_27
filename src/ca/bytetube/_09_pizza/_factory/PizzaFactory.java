package ca.bytetube._09_pizza._factory;

public class PizzaFactory {
    public static Pizza createPizza(String name, Size size) {
        switch (name.toLowerCase()) {
            case "cheese":
                return new CheesePizza(size);
            case "pepperoni":
                return new PepperoniPizza(size);

            default:
                throw new IllegalArgumentException("unknown pizza type !");
        }


    }

}
