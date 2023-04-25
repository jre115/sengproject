package project;

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
				int weeksValue = new Integer(weeksInput);
				game.setSeasonLength(weeksValue);
				System.out.println("Great! The season will last " + game.getSeasonLength() + " weeks");
			} catch (IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}

		
		

		
	}
	
	public static void main(String[] args) {
		CommandLineUI commandLine = new CommandLineUI();
		commandLine.startGame();
	}

}
