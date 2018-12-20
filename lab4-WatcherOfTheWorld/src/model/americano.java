package model;

public class americano extends Beverage {
    public americano() {
        super(3.0);
    }

    @Override
    public String toString() {
        return "americano: $" + this.getPrice();
    }
}

