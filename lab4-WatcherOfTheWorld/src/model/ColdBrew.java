package model;

public class ColdBrew extends Beverage {
	public ColdBrew() {
		super(3.5);
	}
	
	@Override
	public String toString() {
		return "Cold Brew: $" + this.getPrice();
	}
}