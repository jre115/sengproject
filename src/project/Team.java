package project;

import java.util.ArrayList;

/// TEAM CLASS TO BE REMOVED DO NOT USE

import java.util.Scanner;

import projectExceptions.LimitException;
import projectExceptions.NameException;
import projectExceptions.NoReserveAthletesException;

/**
 * The team class represents the team in the Broomstick Blitz game sports competition.
 * It manages a list of athletes in the team and a list of reserve athletes.
 * It provides methods for the team such as adding and removing athletes, swapping athletes and calculating team scores. 
 *
 * @author Jordan Redfern 
 * @version 1.1, May 2023.
 */
public class Team {
	
	/**
	 * The name of the team.
	 */
	private String teamName;

	/**
	 * The list of athletes in the team.
	 */
	private ArrayList<Athlete> teamList;

	/**
	 * The list of reserve athletes.
	 */
	private ArrayList<Athlete> reservesList;

	/**
	 * The list of initial athlete options for setting up the team.
	 */
	private ArrayList<Athlete> initialAthleteOptions;
	
	/**
	 * The maximum size of the team list.
	 */
	int maxSizeTeamList = 4;
	
	/**
	 * The maximum size of the reserves list.
	 */
	int maxSizeReserveList = 5;
	
	
	/**
	 * Constructs new Team object. 
	 * Initialises the teamList and reservesList as empty ArrayLists.
	 */
	public Team() {
		teamList = new ArrayList<Athlete>();
		reservesList = new ArrayList<Athlete>();
	}
	
	// The following 4 methods are used when setting up the team.
	/**
	 * Sets the name of the specified string if the name meets requirements
	 * 
	 * @param nameInput the name for the team
	 * @throws NameException if the name does not meet the requirements (between 3 and 15 characters inclusive, no special characters)
	 */
	public void setTeamName(String nameInput) throws NameException {
		if (nameInput.length() < 3 || nameInput.length() > 15) {
			throw new NameException("Team name must be between 3 - 15 characters long");
		}
		else if (nameInput.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?~]+.*")) {
			throw new NameException("Team name must not include any special characters");
		}
		teamName = nameInput;
	}
	
	/**
	 * Adds an athlete to the initial team in a specific position when setting up the team 
	 * If the team list is less than 4 then the athlete is added to the team list
	 * @param athlete the athlete to be added to initial team list
	 * @param position the position of the athlete in the initial team
	 */
	public void addToInitialTeam(Athlete athlete, String position) {
		if (teamList.size() < maxSizeTeamList) {
			refreshInitialAthletes();
			teamList.add(athlete);
			athlete.setPosition(position);
		}
	}
	
	/**
	 * Refreshes the initial athlete options for the team for use when creating a new team
	 * Creates four new Athlete objects with a contract price of 5000 and updates the initial athlete options list
	 */
	public void refreshInitialAthletes() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			availableAthletes.add(athlete);
		}
		
		initialAthleteOptions = availableAthletes;
	}
	
	/**
	 * Returns the list of initial athlete options for the team.
	 * If the initial athlete options list is null, it is refreshed using refreshInitialAthletes();
	 * @return the ArrayList of athletes as the initial athlete options
	 */
	public ArrayList<Athlete> getInitialAthletes() {
		if (initialAthleteOptions == null) {
			refreshInitialAthletes();
		}
		return initialAthleteOptions;
	}
	
	
	/**
	 * Returns the name of the team
	 * 
	 * @return the name of the team
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Returns the ArrayList of athletes in the reserves
	 * @return reservesList an ArrayList of athletes in the reserves list
	 */
	public ArrayList<Athlete> getReservesList() {
		return reservesList;
	}
	
	/**
	 * Returns the ArrayList of athletes in the team, not including reserves
	 * @return teamList the list of athletes in the team
	 */
	public ArrayList<Athlete> getTeamList() {
		return teamList;
	}
	

	/**
	 * Calculates and returns a total score for the team by adding all offensive and defensive scores of athletes
	 * @return the total score of the team
	 */
	public int getTeamScore() {
		int teamScore = 0;
		for (Athlete athlete : teamList) {
			teamScore += athlete.getOffensive();
			teamScore += athlete.getDefensive();
		}
		
		return teamScore;
	}
	
	/**
	 * Calculates and returns the total offensive score of the team's attackers
	 * @return the total offensive score of the team's attackers
	 */
	public int getOffensiveScore() {
		int offensiveScore = 0;
		for (Athlete athlete : teamList) {
			if (athlete.getPosition() == "Attacker") {
				offensiveScore += athlete.getOffensive();
			}
		}
		
		return offensiveScore;
	}
	
	/**
	 * Calculates and returns the total defensive score of the team's defenders
	 * @return the total defensive score of the team's defenders
	 */
	public int getDefensiveScore() {
		int defensiveScore = 0;
		for (Athlete athlete : teamList) {
			if (athlete.getPosition() == "Defender") {
				defensiveScore += athlete.getDefensive();
			}
		}
		
		return defensiveScore;
	}
	
	/**
	 * Adds an athlete to the reserves list.
	 * @param athlete the athlete to be added to the reserves list
	 * @throws LimitException if the reserves list is already full (reserves list is capped at 5)
	 */
	public void addToReserves(Athlete athlete) throws LimitException {
		if (reservesList.size() >= maxSizeReserveList) {
			throw new LimitException("Your reserve team is full!");
		} else {
			reservesList.add(athlete);
			athlete.setPosition("Reserve");
		}
	}
	
	 /**
	 * Adds an athlete to the team if the team is not full - if position is not specified it automatically assigns athlete to the position there are less of in the team
	 *
	 * @param athlete the athlete that is being added to the team (and if it is a reserve it is removed from reserves)
	 * @param the position the athlete is being added as - if null then the athlete's position will be whatever there are less of in the team
	 */
	public void addToTeam(Athlete athlete, String position) throws LimitException{
		if (teamList.size() < maxSizeTeamList) {
			teamList.add(athlete);
			for (int i = 0 ; i < reservesList.size(); i ++) {
				if (athlete.equals(reservesList.get(i)))
				reservesList.remove(i);
		} 
			if (position == null) {
    		int defenderCount = 0;
    		int attackerCount = 0;
    		for (Athlete teamAthlete : getTeamList()) {
    			if (teamAthlete.getPosition() == "Defender") {
    				defenderCount += 1;
    			} else {
    				attackerCount += 1;
    			}
    		}
    		if (attackerCount < defenderCount) {
    			position = "Attacker";
    		} else {
    			position = "Defender";
    		}
    	}
			athlete.setPosition(position);
		} else {
			throw new LimitException("Your team is full");
		}
	}
	
	/**
	 * Removes athlete from the team list or reserves list
	 * @param athlete the athlete that is to be removed from one of the lists;
	 */
	public void removeAthlete(Athlete athlete) {
	    if (reservesList.contains(athlete)) {
	        reservesList.remove(athlete);
	    } else if (teamList.contains(athlete)) {
	        teamList.remove(athlete);
	    } 
	}
	
	/**
	 * Swaps the positions of the two athletes within the team and reserves lists.
	 * If both athletes are both in team list or both in the reserves list then it does nothing.
	 * 
	 * @param firstAthlete the first athlete to swap
	 * @param secondAthlete the second athlete to swap
	 */
	public void swapAthletes(Athlete firstAthlete, Athlete secondAthlete) {
		if (firstAthlete.isReserve() && secondAthlete.isReserve()) {
			return;
		} else if (!firstAthlete.isReserve() && !secondAthlete.isReserve()) {
			return;
		}
		// In the case that the first athlete is the reserve athlete
		if (firstAthlete.isReserve()) {
			int firstPlayerIndex = reservesList.indexOf(firstAthlete);
			int secondPlayerIndex = teamList.indexOf(secondAthlete);
			String positionInTeam = secondAthlete.getPosition();
			firstAthlete.setPosition(positionInTeam);
			secondAthlete.setPosition("Reserve");
			
			// Swaps athletes in team and reserves in their indexes.
			teamList.set(secondPlayerIndex, firstAthlete);
			reservesList.set(firstPlayerIndex, secondAthlete);
			
		/*
		 * the case where the second player is the reserve athlete.
		 * Methods are the opposite of the above. 
		 * This was done to ensure swapAthletes's parameters can be entered in any order to avoid errors
		 */
		} else {
			int firstPlayerIndex = teamList.indexOf(firstAthlete);
			int secondPlayerIndex = reservesList.indexOf(secondAthlete);
			String positionInTeam = firstAthlete.getPosition();
			
			firstAthlete.setPosition("Reserve");
			secondAthlete.setPosition(positionInTeam);
			
			reservesList.set(secondPlayerIndex, firstAthlete);
			teamList.set(firstPlayerIndex, secondAthlete);
				
		}
	}
	
	/**
	 * Checks if there are reserve athletes available for swapping
	 * @return true if there are reserve athletes available, false otherwise
	 * @throws NoReserveAthletesException if there are no reserve athletes available to swap
	 */
	public Boolean checkSwappable() throws NoReserveAthletesException {
	    if (reservesList.isEmpty() == true) {
	        throw new NoReserveAthletesException();
	    }
	    return true;
	}
	
	/**
	 * Adds a new athlete to the team by swapping them with another athlete and moving that athlete to the reserve team
	 * @param added the athlete to be added to the team
	 * @param swapped the athlete to be swapped out from the team
	 */
	public void addAthleteAndSwap(Athlete added, Athlete swapped) {
		String position = swapped.getPosition();
		int swappedIndex = teamList.indexOf(swapped);
		added.setPosition(position);
		reservesList.add(swapped);
		swapped.setPosition("Reserve");
		teamList.set(swappedIndex, added);
		}
		
	
	/**
	 * Checks if the team is ready for a match
	 * @return true if the team has two attackers and two defenders, false otherwise
	 */
	public boolean checkTeamReady() {
		int defenderCount = 0;
		int attackerCount = 0;
		for (Athlete athlete : teamList) {
			String position = athlete.getPosition();
			if (athlete.isInjured()) {
				return false;
			} else if (position == "Defender") {
				defenderCount += 1;
			} else if (position == "Attacker") {
				attackerCount += 1;
			} 
		}
		return (defenderCount == 2 && attackerCount == 2);
	}
	
	/**
	 * Restores the stamina of all athletes in the team and reserves
	 * After calling this method all athletes will have their stamina restored
	 */
	public void restoreAthletes() {
		for (Athlete athlete : teamList) {
			athlete.restoreStamina();
		} 
		for (Athlete reserve : reservesList) {
			reserve.restoreStamina();
		}
	}
	
	/**
	 * Checks if the team is full, meaning both the team and reserves list have reached their maximum capacity
	 * @return true if both reserves list and team lists are full, false otherwise
	 */
	public Boolean isTeamFull() {
		if (reservesList.size() == maxSizeReserveList && teamList.size() == maxSizeTeamList) {
			return true;
		}
		return false;
	}


}


	
	
	
	
