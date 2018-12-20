package model;

import java.io.IOException;

public class Bagel extends FoodItem {
	public Bagel() {
		super(2.0);
	}
	
	@Override
	public String toString(){
		return "Bagel: $" + this.getPrice();
	}

	public int inte() throws Exception{
		return 0;
	}
}