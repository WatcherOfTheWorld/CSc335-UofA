package model;

public abstract class Coffee {
	private double price;
	
	public Coffee(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
}
