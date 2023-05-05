package project;

import java.util.ArrayList;

/**
 * This class drives a simple command line application that works in a simple runtime loop
 *
 * @author Jordan Redfern
 * @version 1.1, April 2023.
 */

import java.util.Scanner;  // Import the Scanner class


public class CommandLineUI {
	
	boolean running = true;
	GameEnvironment game;
	
	public void startGame() {
		game = new GameEnvironment();
		
		// input team name
		Scanner scanner = new Scanner(System.in); // creates scanner object for team name input
		System.out.println("Welcome to the game! Please input a team name");
		
		while (true) {
			try {
				String nameInput = scanner.nextLine();
				game.setTeamName(nameInput);
				System.out.println("Great! Your team name is: " + game.getTeamName());
				break;
			} catch (NameException e){
				System.out.println(e.getMessage());
			} 
		}
		
		// input season length
		System.out.println("Please input how many weeks you would like the season to last (between 5 and 15)");
		
		while (true) {
			try {
				String weeksInput = scanner.nextLine();
				int weeksValue = Integer.parseInt(weeksInput);
				game.setSeasonLength(weeksValue);
				System.out.println("Great! The season will last " + game.getSeasonLength() + " weeks");
				break;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a numerical value between 5 - 15");
			} catch (IllegalArgumentException e){
				System.out.println(e.getMessage());
			} 
		}
		
		// Purchase the starting athletes for your team

		System.out.println("Now you need a starting team...");
		System.out.println("You have " + game.getMoneyFormatted());
		System.out.println("Input any key to view the list of purchaseable athletes");
		scanner.nextLine();
		ArrayList<Athlete> startingList = game.generateStartingAthletes();
		
		int i = 1;
		for (Athlete athlete: startingList) {
			System.out.println("\nAthlete number " + i + ":");
			System.out.println(athlete);
			i += 1;
		}

		

		
	}
	
	public static void main(String[] args) {
		CommandLineUI commandLine = new CommandLineUI();
		commandLine.startGame();
	}

}
