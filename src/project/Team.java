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
		
		/// TEST
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
	
	public void removeFromTeam(Athlete athlete) {
		teamList.remove(athlete);
	}
	public void removeFromReserve(Athlete athlete) {
		reservesList.remove(athlete);
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
	
	
	
	public void addToTeam(Athlete athlete, String position) throws LimitException {
		if (teamList.size() < 4) {
			teamList.add(athlete);
			athlete.setPosition(position);
			for (int i = 0 ; i < reservesList.size(); i ++) {
				if (athlete.matchesAthlete(reservesList.get(i)))
				reservesList.remove(i);
			}
		} else {
			if (reservesList.size() == 5) {
				throw new LimitException();
			} else {
				athlete.setPosition("Reserve");
				reservesList.add(athlete);
			}
		}

	}
	
<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://eng-git.canterbury.ac.nz/jre115/project.git
	

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
	
	public void swapAthletes(Athlete playerAthlete, Athlete reserveAthlete) {
		int playerIndex = teamList.indexOf(playerAthlete);
		int reserveIndex = reservesList.indexOf(reserveAthlete);
		String position = playerAthlete.getPosition();
		
		playerAthlete.setPosition("Reserve");
		reserveAthlete.setPosition(position);
		
		teamList.set(playerIndex, reserveAthlete);
		reservesList.set(reserveIndex, playerAthlete);
	}
	

	
	
	
	


}
