package project;

import java.util.ArrayList;
import java.util.InputMismatchException;

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
				System.out.println("\033[32m" + "Great! Your team name is: " + game.getTeamName() + "\033[0m");
				break;
			} catch (NameException e){
				System.out.println("\033[31m" + e.getMessage() + "\033[0m");
			} 
		}
		
		// input season length
		System.out.println("Please input how many weeks you would like the season to last (between 5 and 15)");
		
		while (true) {
			try {
				String weeksInput = scanner.nextLine();
				int weeksValue = Integer.parseInt(weeksInput);
				game.setSeasonLength(weeksValue);
				System.out.println("\033[32m" + "Great! The season will last " + game.getSeasonLength() + " weeks" + "\033[0m");
				break;
			} catch (NumberFormatException e) {
				System.out.println("\033[31m" + "Please enter a numerical value between 5 - 15" + "\033[0m");
			} catch (IllegalArgumentException e){
				System.out.println("\033[31m" + e.getMessage() + "\033[0m");
			} 
		}
		
		// Purchase the starting athletes for your team
		
		for (int i = 0; i < 4; i ++) {
			if (i == 0) {
				System.out.println("Now you need a starting team...");
				System.out.println("You have " + game.getMoneyFormatted());
				System.out.println("Input any key to view the list of purchasable athletes");
			} else {
				System.out.println("You have " + game.getMoneyFormatted());
				System.out.println("Team filled: " + i + "/4 players");
				System.out.println("Input any key to view the next list of purchasable athletes");
			}
			
			scanner.nextLine();
			ArrayList<Athlete> startingList = game.getStartingAthletes();
			
			int value = 1;
			for (Athlete athlete: startingList) {
				System.out.println("\nAthlete number " + value + ":");
				System.out.println(athlete);
				value += 1;
		}
			System.out.println("\n\nInput athlete number to purchase an athlete");
			
			int athleteValue = scanNumericalValue(1, 4);
			Athlete athlete = startingList.get(athleteValue - 1);
			
			System.out.println("\nYou have purchased the following athlete: ");
			System.out.println(athlete);
			System.out.println("\nSelect one of the following options");
        	System.out.println("1. Add to team as attacker");
        	System.out.println("2. Add to team as defender");
			
        	int positionValue = scanNumericalValue(1, 2);
        	String athletePosition = null;
        	if (positionValue == 1) {
        		athletePosition = "Attacker";
        	} else if (positionValue == 2) {
        		athletePosition = "Defender";
        	}
        	
        	game.purchaseAthlete(athlete, athletePosition);
        	System.out.println("\033[32m" + "\nGreat! Purchased athlete " + athlete.getName() + " as " + athletePosition + "\n" + "\033[0m");
		    
		}
		
		System.out.println("\nGreat! Choose a difficulty");
		System.out.println("\nSelect one of the following options");
    	System.out.println("1. Normal");
    	System.out.println("2. Hard");
    	
    	int difficultyValue = scanNumericalValue(1, 2);
    	String difficultyInput = null;
    	if (difficultyValue == 1) {
    		difficultyInput = "Normal";
    	} else if (difficultyValue == 2) {
    		difficultyInput = "Hard";
    	}
    	
    	game.setGameDifficulty(difficultyInput);
		
		System.out.println("\033[32m" + "\nGreat! Ready to start?" + "\033[0m");
		System.out.println(game);
    	System.out.println("\nPress any key to start game");
    	scanner.nextLine();
    	scanner.close();

		
	}
	
	public int scanNumericalValue(int lowerLimit, int upperLimit) {
		
		Scanner scanner = new Scanner(System.in);
	    while (true) {
	        try {
	        	String input = scanner.nextLine();
				int inputValue = Integer.parseInt(input);
	        	if (inputValue < lowerLimit || inputValue > upperLimit) {
		            System.out.println("\033[31mPlease input a value between " + lowerLimit + " - " + upperLimit + "\033[0m");
		        } else {
		        	return inputValue;
		        }
	        } catch (NumberFormatException e) {
				System.out.println("\033[31mPlease enter a numerical value between " + lowerLimit + " - " + upperLimit + "\033[0m");
	        	
	        }
	    }
	}
	
	public static void main(String[] args) {
		CommandLineUI commandLine = new CommandLineUI();
		commandLine.startGame();
	}

}
