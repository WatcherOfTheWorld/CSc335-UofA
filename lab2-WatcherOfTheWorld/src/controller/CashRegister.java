package controller;

import java.util.Scanner;

import model.Menu;
import model.PunchCard;

public class CashRegister {
	
	public static void main(String [] args) {
		Menu menu = new Menu();
		LoyaltyProgram loyalty = new LoyaltyProgram();
		
		Scanner input = new Scanner(System.in);
		
		do {
		
			System.out.println("Welcome to Clarkbucks, what's your name? ");
			
			String id = input.next();
			
			System.out.print(menu);
			
			PunchCard userCard = loyalty.getOrCreatePunchCard(id);
			// TODO implement addVisit(); 
			userCard.addVisit();
			
			System.out.print("What would you like to drink? ");
			String userInput = input.next().toLowerCase();
			double currentTab = 0;
			
			while (!userInput.equals("no")) {
	
				System.out.println(menu.getQuip(userInput));
				
				currentTab += menu.getPrice(userInput);
				
				System.out.print("Anything else? (enter drink name or \"no\" to ring out)");
				userInput = input.next().toLowerCase();
			}
			
			// TODO implement isNthVisit
			if(userCard.isNthVisit(LoyaltyProgram.VISITS_FOR_DISCOUNT)) {
				System.out.println("Congratulations! You get a discount for being a loyal customer.");
				currentTab *= (1 - LoyaltyProgram.DISCOUNT);
			}
			
			System.out.printf("Cool, that will be $%.2f, take care!\n", currentTab);
			
			System.out.println("Enter \"exit\" to close down the store or anything else for the next customer.");
		} while(!input.next().toLowerCase().equals("exit"));
		input.close();
	}
}
