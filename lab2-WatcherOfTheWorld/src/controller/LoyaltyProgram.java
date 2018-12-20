package controller;
import java.util.HashMap;

import model.PunchCard;

public class LoyaltyProgram {
	public static final int VISITS_FOR_DISCOUNT = 5;
	public static final float DISCOUNT = 0.1f;
	
	private HashMap<String, PunchCard> customerPunchCards = new HashMap<>();
	
	public PunchCard getOrCreatePunchCard(String userID) {
		PunchCard card = customerPunchCards.get(userID);
		if(card == null) {
			PunchCard newUser = new PunchCard();
			customerPunchCards.put(userID, newUser);
			return newUser;
			
		}
		return card;
	}
}
