package ca.bytetube._09_pizza._factory_decorator;

public abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public Size getSize() {
        return pizza.getSize();
    }
}
