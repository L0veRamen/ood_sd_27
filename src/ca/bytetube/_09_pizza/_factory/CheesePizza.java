package ca.bytetube._09_pizza._factory;

public class CheesePizza extends Pizza {

    public CheesePizza(Size size) {
        super(size);
        this.name = "Cheese Pizza";
    }

    @Override
    public double getBasePrice() {
        switch (size) {
            case SMALL -> {
                return 8.99;
            }
            case MEDIUM -> {
                return 10.99;
            }

            case LARGE -> {
                return 12.99;
            }

            default -> {
                return 0;
            }


        }

    }


}
