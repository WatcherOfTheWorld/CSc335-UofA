package model;

import java.util.HashMap;

public class Menu {
	
	private HashMap<String, MenuItem> items;
	
	public Menu() {
		items = new HashMap<>();
		
		items.put("mocha", new Mocha());
		items.put("americano", new americano());
		items.put("iced", new ColdBrew());
		items.put("bagel", new Bagel());
		items.put("croissant", new Croissant());
	}
	
	public MenuItem getItem(String in) {
		if(items.containsKey(in)){
			return items.get(in);
		}else{
			throw new InvalidMenuItemException("item no found");
		}
	}
	
	public String toString() {
		String ret = "We have:\n";

		for(String item: items.keySet()) {
			ret +=  item + "\n";
		}
		return ret;
	}
}
