package project;

import java.util.ArrayList;

/// TEAM CLASS TO BE REMOVED DO NOT USE

import java.util.Scanner;

public class Team {
	
	private String teamName;
	private ArrayList<Athlete> teamList;
	private ArrayList<Athlete> reservesList;
    private ArrayList<Athlete> initialAthleteOptions;
	
	public Team() {
		teamList = new ArrayList<Athlete>();
		reservesList = new ArrayList<Athlete>();
		
		/// TEST JR REMOVE THIS LATER
		Athlete athlete = new Athlete();
		athlete.setPosition("Reserve");
		reservesList.add(athlete);
		Athlete athlete1 = new Athlete();
		athlete1.setPosition("Reserve");
		reservesList.add(athlete1);
		
	}
	
	public void setTeamName(String nameInput) {
		teamName = nameInput;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void removeAthlete(Athlete athlete) {
	    if (reservesList.contains(athlete)) {
	        reservesList.remove(athlete);
	    } else if (teamList.contains(athlete)) {
	        teamList.remove(athlete);
	    } else {
	        throw new IllegalArgumentException("Athlete not found in any team.");
	    }
	}
	
	
	public ArrayList<Athlete> getReservesList() {
		return reservesList;
	}
	
	public ArrayList<Athlete> getTeamList() {
		// gets the athlete list containing the list of team that is playing, not including reserves
		return teamList;
	}
	
	public void addToInitialTeam(Athlete athlete, String position) {
		if (teamList.size() < 4) {
			refreshInitialAthletes();
			teamList.add(athlete);
			athlete.setPosition(position);
		}
	}
	
	public void addToReserves(Athlete athlete) throws LimitException {
		if (reservesList.size() >= 5) {
			throw new LimitException("Your reserve team is full!");
		} else {
			reservesList.add(athlete);
		}
	}
	
	public void addToTeam(Athlete athlete, String position) {
		if (teamList.size() < 4) {
			teamList.add(athlete);
			athlete.setPosition(position);
			for (int i = 0 ; i < reservesList.size(); i ++) {
				if (athlete.matchesAthlete(reservesList.get(i)))
				reservesList.remove(i);
		}
		}

	}
	

	public void refreshInitialAthletes() {
		ArrayList<Athlete> availableAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= 4; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			availableAthletes.add(athlete);
		}
		
		initialAthleteOptions = availableAthletes;
	}
	
	public ArrayList<Athlete> getInitialAthletes() {
		if (initialAthleteOptions == null) {
			refreshInitialAthletes();
		}
		return initialAthleteOptions;
	}
	
	public void swapAthletes(Athlete firstAthlete, Athlete secondAthlete) {
		
		if (firstAthlete.isReserve()) {
			int firstPlayerIndex = reservesList.indexOf(firstAthlete);
			int secondPlayerIndex = teamList.indexOf(secondAthlete);
			String positionInTeam = secondAthlete.getPosition();
			
			if (secondAthlete.isInjured()) {
				positionInTeam = secondAthlete.getPreviousPosition();
				secondAthlete.setPreviousPosition("Reserve");
			} else {
				secondAthlete.setPosition("Reserve");
			}
			
			if (firstAthlete.isInjured()) {
				firstAthlete.setPreviousPosition(positionInTeam);
			} else {
					firstAthlete.setPosition(positionInTeam);
			}
			teamList.set(secondPlayerIndex, firstAthlete);
			reservesList.set(firstPlayerIndex, secondAthlete);
			
		} else {
			int firstPlayerIndex = teamList.indexOf(firstAthlete);
			int secondPlayerIndex = reservesList.indexOf(secondAthlete);
			String positionInTeam = firstAthlete.getPosition();
			
			if (firstAthlete.isInjured()) {
				positionInTeam = firstAthlete.getPreviousPosition();
				firstAthlete.setPreviousPosition("Reserve");
			} else {
				firstAthlete.setPosition("Reserve");
			}
			
			if (secondAthlete.isInjured()) {
				secondAthlete.setPreviousPosition(positionInTeam);
			} else {
				secondAthlete.setPosition(positionInTeam);
			}
			reservesList.set(secondPlayerIndex, firstAthlete);
			teamList.set(firstPlayerIndex, secondAthlete);
				
		}
	}
	
	public void addAthleteAndSwap(Athlete added, Athlete swapped) {
		String position = null;
		if (swapped.isInjured()) {
			position = swapped.getPreviousPosition();
			swapped.setPreviousPosition("Reserve");
		} else {
			position = swapped.getPosition();
			swapped.setPosition("Reserve");
		}
		int swappedIndex = teamList.indexOf(swapped);
		added.setPosition(position);
		reservesList.add(swapped);
		teamList.set(swappedIndex, added);
		}
		

	
	
	public int getTeamScore() {
		int teamScore = 0;
		for (Athlete athlete : teamList) {
			teamScore += athlete.getOffensive();
			teamScore += athlete.getDefensive();
		}
		
		return teamScore;
	}
	
	public int getOffensiveScore() {
		int offensiveScore = 0;
		for (Athlete athlete : teamList) {
			if (athlete.getPosition() == "Attacker") {
				offensiveScore += athlete.getOffensive();
			}
		}
		
		return offensiveScore;
	}
	
	public int getDefensiveScore() {
		int defensiveScore = 0;
		for (Athlete athlete : teamList) {
			if (athlete.getPosition() == "Defender") {
				defensiveScore += athlete.getOffensive();
			}
		}
		
		return defensiveScore;
	}
	
	public boolean checkTeamReady() {
		int defenderCount = 0;
		int attackerCount = 0;
		for (Athlete athlete : teamList) {
			String position = athlete.getPosition();
			if (athlete.getStamina() == 0) {
				return false;
			} else if (position == "Defender") {
				defenderCount += 1;
			} else if (position == "Attacker") {
				attackerCount += 1;
			} 
		}
		return (defenderCount == 2 && attackerCount == 2);
	}
	
	public void updateTeamAfterMatch(ArrayList<Athlete> playedTeam) {
		teamList = playedTeam;
	}
	
	public void restoreAthletes() {
		for (Athlete athlete : teamList) {
			athlete.restoreStamina();
		} 
		for (Athlete reserve : reservesList) {
			reserve.restoreStamina();
		}
	}
	
	public Boolean isTeamFull() {
		if (reservesList.size() == 5 && teamList.size() == 4) {
			return true;
		}
		return false;
	}


}


	
	
	
	
