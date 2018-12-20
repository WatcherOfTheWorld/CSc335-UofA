package model;

public class Mocha extends Beverage {
	public Mocha() {
		super(5.0);
	}
	
	@Override
	public String toString() {
		return "Mocha: $" + this.getPrice();
	}
}
