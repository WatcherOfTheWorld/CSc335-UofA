package controller;

import java.util.Scanner;

import model.*;

public class CashRegister {
	
	public static void main(String [] args) {
		Menu menu = new Menu();
		LoyaltyProgram loyalty = new LoyaltyProgram();
		SalesLog salesLog = new SalesLog();
		Scanner input = new Scanner(System.in);
		
		LOOP: while(true) {
		
			System.out.println("Welcome to Clarkbucks, what's your name? ");
			
			String id = input.next();
			
			System.out.print(menu);
			
			PunchCard userCard = loyalty.getOrCreatePunchCard(id);
			userCard.addVisit();
			
			System.out.print("What would you like to order? ");
			String userInput = input.next().toLowerCase();
			double currentTab = 0;
			
			while (!userInput.equals("no")) {
				MenuItem itemPurchased;
				try {
					itemPurchased = menu.getItem(userInput);
				}catch (InvalidMenuItemException e){
					itemPurchased = null;
					System.out.println("item not exist");
					break;
				}
				currentTab += itemPurchased.getPrice();
				salesLog.addItem(itemPurchased);
				
				System.out.print("Anything else? (enter drink name or \"no\" to ring out)");
				userInput = input.next().toLowerCase();
			}
			
			if(userCard.isNthVisit(LoyaltyProgram.VISITS_FOR_DISCOUNT)) {
				System.out.println("Congratulations! You get a discount for being a loyal customer.");
				currentTab *= (1 - LoyaltyProgram.DISCOUNT); 
			}
			
			System.out.printf("Cool, that will be $%.2f, take care!\n", currentTab);
			
			System.out.println("Enter:\n\t\"exit\" to close down the store");
			System.out.println("\t\"next\" for the next customer");
			System.out.println("\t\"stats\" for the day's sales");
			
			String command = input.next().toLowerCase();
			
			switch(command) {
			case "exit":
				break LOOP;
			case "stats":
				printStatistics(salesLog);
				break;
			}
		}
		
		input.close();
	}
	
	public static void printStatistics(SalesLog log) {
		System.out.print("Number of items sold today: ");
		
		//runQuery will call the passed lambda function once per
		//item in the sales log. It will take the returned value
		//of the lambda, add it up and return the sum.
		//
		//If we pass a function that for each item simply returns 1,
		//the sum of 1 added N times is N, the total number of items 
		SalesQuery countQuery = item -> 1;
		System.out.println(log.runQuery(countQuery));
		
		
		System.out.print("Total income today: ");
		
		//We can also query the item passed and get a property of the
		//item like it's price.
		SalesQuery totalQuery = item -> item.getPrice();
		System.out.println(log.runQuery( totalQuery ));
		
		// TODO Display how many items sold today were Beverages
		// Do not add any properties to Beverages, simply determine
		// if the item in the SalesLog IsA Beverage
		SalesQuery Beverage = item -> {
			int count = 0;
			if(item instanceof model.Beverage){
				count++;
			}
			return count;
		};
		System.out.print("beverage sells: "+log.runQuery(Beverage)+"\n");

		
		// TODO Display the total money spent on Food items.
		// This is similar to each of the three queries above.
		SalesQuery FoodQuery = item -> {
			double count=0;
			if(item instanceof FoodItem){
				count+=item.getPrice();
			}
			return count;
		};
		System.out.print("food sells: $"+log.runQuery(FoodQuery)+"\n");

	}

}
