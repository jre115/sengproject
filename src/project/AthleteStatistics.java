package project;

import java.util.Random;

public class AthleteStatistics {
	
	// Created separate class for AthleteStastistics to make it easier to add additional statistics in the future.
	
    private int offensiveStatistic;
    private int defensiveStatistic;
    private int staminaStatistic;
    
    public AthleteStatistics() {
    	setStatistics();
    	
 
    }
	
    public AthleteStatistics(int offensiveStatistic, int defensiveStatistic, int staminaStatistic) {
        this.offensiveStatistic = offensiveStatistic;
        this.defensiveStatistic = defensiveStatistic;
        this.staminaStatistic = staminaStatistic;
    }
    
    private void setStatistics() {
		Random offensive = new Random();
		int min = 30;
		int max = 70;
		
		offensiveStatistic = offensive.nextInt(max - min + 1) + min;
		defensiveStatistic = 100 - offensiveStatistic;
		
		Random stamina = new Random();
		staminaStatistic = stamina.nextInt(100 - 60 + 1) + 60;
		
    }
    
    public int getOffensiveStat() {

        return offensiveStatistic;
    }
    
    public int getDefensiveStat() {

        return defensiveStatistic;
    }
    
    public int getStaminaStat() {
    	return staminaStatistic;
    }
    
    
    public static void main(String[] args) {
        AthleteStatistics athleteStats = new AthleteStatistics();
        
        // test the get methods
        System.out.println("Offensive Statistic: " + athleteStats.getOffensiveStat());
        System.out.println("Defensive Statistic: " + athleteStats.getDefensiveStat());
        System.out.println("Stamina Statistic: " + athleteStats.getStaminaStat());
        
    }
	


}
