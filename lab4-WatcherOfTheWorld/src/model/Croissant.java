package model;

public class Croissant extends FoodItem {
	public Croissant() {
		super(3.0);
	}
	
	@Override
	public String toString() {
		return "Croissant: $" + this.getPrice();
	}
}