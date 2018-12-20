package model;

import java.util.HashMap;

public class Menu {
	
	private HashMap<String, Coffee> items;
	
	public Menu() {
		items = new HashMap<>();
		items.put("mocha", new Mocha());
		items.put("americano", new americano());
		items.put("iced", new ColdBrew());
	}
	
	public double getPrice(String in) {
		return items.get(in).getPrice();
	}
	
	public String getQuip(String in) {
		return items.get(in).toString();
	}
	
	public String toString() {
		String ret = "We have:\n";

		for(String item: items.keySet()) {
			ret +=  item + "\n";
		}
		return ret;
	}
}
