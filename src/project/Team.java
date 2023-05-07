package project;

import java.util.ArrayList;

/// TEAM CLASS TO BE REMOVED DO NOT USE

import java.util.Scanner;

public class Team {
	
	private String teamName;
	private ArrayList<Athlete> teamList;
	private ArrayList<Athlete> reservesList;
	
	public Team() {
		teamList = new ArrayList<Athlete>();
		reservesList = new ArrayList<Athlete>();
	}
	
	public void setTeamName(String nameInput) {
		teamName = nameInput;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	
	public ArrayList<Athlete> getReservesList() {
		return reservesList;
	}
	
	public ArrayList<Athlete> getTeamList() {
		// gets the athlete list containing the list of team that is playing, not including reserves
		return teamList;
	}
	

	
	public void setPositions() {
		for (Athlete athlete: teamList) {
			Scanner myObj = new Scanner(System.in);
			System.out.println(athlete);
			System.out.println("Enter the following number for positions: 1. Offensive, 2. Defensive");
			int position = myObj.nextInt();
			//myObj.close();
			// JR NOTE: need to create exceptions here in case it isnt one of the two options.. potentially or replace with buttons later?
			
			if (position == 1) {
					athlete.setPosition("Offensive");
			}
			else {
				athlete.setPosition("Defensive");
			}
			
			System.out.println(athlete);
			System.out.println("\n");
		}

	}
	
	public void addReserve(Athlete athlete) {
		reservesList.add(athlete);
	}
	
	public void addToTeam(Athlete athlete) {
		teamList.add(athlete);
	}
	
	public static void main(String[] args) {
		Athlete athlete1 = new Athlete();
		Athlete athlete2 = new Athlete();
		Athlete athlete3 = new Athlete();	
		Team team = new Team();
		team.addToTeam(athlete1);
		team.addToTeam(athlete2);
		team.addReserve(athlete3);
		
		team.setPositions();
	}
	


}
