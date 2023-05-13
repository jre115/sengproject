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
	
	public void gameSetup() {
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
		
    	int weeksValue = scanNumericalValue(5, 15);
		
		game.setSeasonLength(weeksValue);
		System.out.println("\033[32m" + "Great! The season will last " + game.getSeasonLength() + " weeks" + "\033[0m");

		
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
			ArrayList<Athlete> startingList = game.getInitialAthletes();
			
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
        	
        	game.purchaseInitialAthlete(athlete, athletePosition);
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
    	//scanner.close();
    	game.startGame();
    	mainMenu();


		
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
	
	
	
	
	public void mainMenu() {
		System.out.println("\nMENU");
		System.out.println("Money: " + game.getMoneyFormatted() + " week: " + game.getCurrentWeek());
		
		System.out.println("\nSelect one of the following options:");
		System.out.println("1. Club\n2. Stadium\n3. Market\n4. Bye");
		
		int menuValue = scanNumericalValue(1, 4);
		
		switch (menuValue) {
	    case 1:
	        goToClub();
	        break;
	    case 2:
	    	
	        // 
	        break;
	    case 3:
	    	goToMarket();
	        // 
	        break;
	    case 4:
	        // 
	        break;
		}
		
		
			
		
	}
	
	public void goToMarket() {
		System.out.print("\nMARKET");
		System.out.print("\n1. Sell\n2. Buy player\n3. Buy item\n4. Back\n5");
		
		int marketValue = scanNumericalValue(1, 3);
		switch(marketValue) {
		
		case 1:
			Scanner scanner = new Scanner(System.in);
	        boolean selling = true;

	        while (selling) {
	            System.out.println("1. Sell Player");
	            System.out.println("2. Sell Reserve Player");
	            System.out.println("3. Sell Item");
	            System.out.println("4. Go back");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();
// need to fix index of selling
	            switch (choice) {
	                case 1:
	                	System.out.println("Your Team List:");
	                    game.displayPlayerList(game.getTeamList());
	                    System.out.print("Enter the index of the player to sell: ");
	                    int index = scanner.nextInt();
	                    
	                    index--;
	                    
	                    if (index >= 0 && index < game.getTeamList().size()) {
	                        Athlete player = game.getTeamList().get(index);
	                        game.sellPlayer(player);
	                        System.out.println(player.getName() + " has been sold.");
	                    } else {
	                        System.out.println("Invalid player index or you have no players.");
	                    }
	                
	                	
	                    
	                case 2:
	                	System.out.println("Your Reserves List:");
	                    game.displayPlayerList(game.getReservesList());
	                    System.out.print("Enter the index of the player to sell: ");
	                    int index2 = scanner.nextInt();
	                    index2--;

	                    if (index2 >= 0 && index2 < game.getReservesList().size()) {
	                        Athlete player = game.getReservesList().get(index2);
	                        game.sellReservePlayer(player);
	                        System.out.println(player.getName() + " has been sold.");
	                    } else {
	                        System.out.println("Invalid player index or you have no player, push any key to return\n.");
	                    }
	                
	                    
	                    break;
	                case 3:
	                	
	                    System.out.println("Your Inventory:");
	                    game.displayItemList(game.getInventory());
	                    System.out.print("Enter the index of the item to sell: ");
	                    int index3 = scanner.nextInt();
	                    index3--;

	                    if (index3 >= 0 && index3 < game.getInventory().size()) {
	                        Item item = game.getInventory().get(index3);
	                        game.sellItem(item);
	                        System.out.println(item.getName() + " has been sold.");
	                    } else {
	                        System.out.println("Invalid item index.");
	                    }
	                	
	                    
	                    break;
	                case 4:
	                    mainMenu();
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        }
	        scanner.close();
	    
			
		
		case 2:
			   ArrayList<Athlete> marketPlayers = game.getShopAthletes();

			    System.out.println("\nAvailable Players in the Market:");
			    int value = 1;
			    for (Athlete player : marketPlayers) {
			        System.out.println("\nPlayer number " + value + ":");
			        System.out.println(player);
			        value++;
			    }

			    System.out.println("\nInput the player number to purchase:");
			    int playerValue = scanNumericalValue(1, marketPlayers.size());
			    Athlete player = marketPlayers.get(playerValue - 1);

			    System.out.println("\nYou have purchased the following player:");
			    System.out.println(player);

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
			    

			    game.purchaseAthlete(player, athletePosition, athletePosition); 
			    System.out.println("\033[32m" + "Great! Player " + player.getName() + " has been added to your team as a " + athletePosition + "\033[0m");
			    break;
		case 3:
			ArrayList<Item> marketItems = game.getShopItems();
			System.out.println("\nAvailable Items in the Market:");
		    int val = 1;
		    for (Item equipment : marketItems) {
		        System.out.println("\nItem number " + val + ":");
		        System.out.println(equipment.getName() +": $" + equipment.getContractPrice() + " , "+ equipment.getDefence() +" Def "+ equipment.getOffence() +" Off");
		        val++;
		    }
		        System.out.println("\nInput the item number to purchase:");
		        int itemValue = scanNumericalValue(1, marketItems.size());
			    Item equipment = marketItems.get(itemValue - 1);
			    
			    System.out.println(equipment.getName());
			    game.purchaseItem(equipment);
			    System.out.println("Great! "+ equipment.getName()+ " has been added to your inventory");
			    System.out.println(game.getMoneyFormatted()+" left");
		case 4:
			mainMenu();
			    
		    
			
			}
		
			
			 
			
            
			
			
			
		}
	
			
			
	
	
	
	public void goToClub() {
		
		System.out.println("\nCLUB");
		System.out.println("\n1. View team properties\n2. View player inventory\n\n3. Back");
		
		int optionValue = scanNumericalValue(1, 3);
		
		switch (optionValue) {
	    case 1:
	    	while (true) {
		        System.out.println("\nTEAM: " + game.getTeamName() + "\n");
		        
		        System.out.println("Players:");
		        int i = 0;
				while (i < game.getTeamList().size()) {
					Athlete athlete = game.getTeamList().get(i);
					if (athlete.getPosition() == "Attacker") {
						System.out.println("\u001B[34m" + (i + 1) + ". " + athlete.getName() + "\u001B[0m");
					} else if (athlete.getPosition() == "Defender") {
						System.out.println("\033[0;31m" + (i + 1) + ". " + athlete.getName() + "\033[0m");
					}
					i += 1;
					}
				
				System.out.println("\nReserves:");
				i += 1;
				if (game.getReservesList().size() == 0) {
					System.out.println("No reserves");
				} else {
					for (Athlete athlete : game.getReservesList()) {
						System.out.println(i + ". " + athlete.getName());
						i += 1;
						}
					}
				
				System.out.println("\n" + i + ". Back");
				
				int athleteOptions = scanNumericalValue(1, i);
				
				boolean athleteView = true;
				while (athleteView) {
					if (athleteOptions <= game.getTeamList().size()) {
						Athlete athlete = game.getTeamList().get(athleteOptions - 1);
						if (athlete.getPosition() == "Attacker") {
							System.out.println("\n\u001B[34m" + athlete + "\u001B[0m");
						} else if (athlete.getPosition() == "Defender") {
							System.out.println("\n\033[0;31m" + athlete + "\033[0m");
						}
						System.out.println("\n1. Edit nickname\n2. Edit position\n3. Swap player with reserve\n\n4. Back");
						int playerOption = scanNumericalValue(1, 4);
						switch (playerOption) {
						case 1:
							System.out.println("Please input what you would like to change " + athlete.getName() + "'s name to");
							Scanner scanner = new Scanner(System.in);
							while (true) {
								try {
									String nameInput = scanner.nextLine();
									athlete.setName(nameInput);
									System.out.println("\n\033[32m" + "Great! Athlete name changed to: " + athlete.getName() + "\033[0m");
									break;
								} catch (NameException e){
									System.out.println("\033[31m" + e.getMessage() + "\033[0m");
								} 
							}
							break;
						case 2:
							System.out.println("Change " + athlete.getName() + "'s position to " + athlete.getAlternatePosition() + "?");
							System.out.println("\n1. Yes\n2. Back");
							int positionOption = scanNumericalValue(1, 2);
							if (positionOption == 1) {
								athlete.changeAthletePosition();
								System.out.println("\n\033[32m" + "Great! Athlete position changed to: " + athlete.getPosition() + "\033[0m");
							}
							break;
						case 3:
							System.out.println("\nWhich reserve would you like to swap with " + athlete.getName() + "?");
							try {
								int reservePosition = 1;
								for (Athlete reserve : game.getReservesList()) {
									System.out.println("\n" + reservePosition + ". " + reserve);
									reservePosition += 1;
								}
								System.out.println("\n" + reservePosition + ". Back");
								int swapOption = scanNumericalValue(1, i);
								if (swapOption != reservePosition) {
									Athlete reserve = game.getReservesList().get(swapOption - 1);
									game.swapAthletes(athlete, reserve);
									System.out.println("\n\033[32m" + "Great! " + athlete.getName() + "swapped with " + reserve.getName() + "\033[0m");
								}
							} catch (IllegalStateException e) {
								System.out.println("\033[31m" + e.getMessage() + "\033[0m");
								athleteView = false;
							}
							break;
						case 4:
							athleteView = false;
						}
						
					} else if (athleteOptions == i){
						goToClub();
					} else {
						Athlete athlete = game.getReservesList().get(athleteOptions - game.getTeamList().size() - 1);
						System.out.println(athlete);
						System.out.println("\n1. Edit nickname\n2. Swap reserve with player\n\n3. Back");
						int reserveOption = scanNumericalValue(1, 3);
						switch (reserveOption) {
						case 1:
							System.out.println("Please input what you would like to change " + athlete.getName() + "'s name to");
							Scanner scanner = new Scanner(System.in);
							while (true) {
								try {
									String nameInput = scanner.nextLine();
									athlete.setName(nameInput);
									System.out.println("\n\033[32m" + "Great! Athlete name changed to: " + athlete.getName() + "\033[0m");
									break;
								} catch (NameException e){
									System.out.println("\033[31m" + e.getMessage() + "\033[0m");
								} 
							}
							break;
						case 2:
							System.out.println("\nWhich player would you like to swap with " + athlete.getName() + "?");
							int counter = 1;
							for (Athlete player : game.getTeamList()) {
								System.out.println("\n" + counter + ". " + player);
								counter += 1;
							}
							System.out.println("\n" + counter + ". Back");
							int swapOption = scanNumericalValue(1, i);
							if (swapOption != counter) {
								Athlete player = game.getTeamList().get(swapOption - 1);
								game.swapAthletes(player, athlete);
								System.out.println("\n\033[32m" + "Great! " + athlete.getName() + "swapped with " + player.getName() + "\033[0m");
							}
							break;
							
						case 3:
							athleteView = false;
					}
				}
				}

				
			} 
			
	       case 2:
	    	System.out.println("TEST");
	    	break;
	    case 3:
	    	mainMenu();
	    	break;
		}
		
		
	}
	
	public static void main(String[] args) {
		CommandLineUI commandLine = new CommandLineUI();
		commandLine.gameSetup();
	}

}
