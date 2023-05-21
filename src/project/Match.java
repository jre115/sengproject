package project;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;


/**
 * Represents a match between a player's team and an opposition team
 * 
 * @author Jordan Redfern 
 * @version 1.1, May 2023.
 */
public class Match {
	
	/**
	 * The prize money for winning the match
	 */
	int prizeMoney;
	
	/**
	 * The player's score in the current match
	 */
	int playerScore;
	
	/**
	 * The opposition's score in the current match
	 */
	int oppositionScore;
	
	/**
	 * The number of points gained for winning the match
	 */
	int points;
	
	/**
	 * The list of unplayed athletes for the player's team
	 */
	ArrayList<Athlete> unplayedAthletes;
	
	/**
	 * The list of unplayed athletes for the opposition team
	 */
	ArrayList<Athlete> unplayedOpposition;
	
	/**
	 * The list of athletes that have played in the match from the player team
	 */
	ArrayList<Athlete> playedTeamAthletes;
	
	/**
	 * The name of player's team
	 */
	String playerTeamName;
	
	/**
	 * The name of the opposition team
	 */
	String oppositionTeamName;
	
	/**
	 * The opposition team
	 */
	Team oppositionTeam;
	
	
	/**
	 * Constructs new Match object with the specified player team, opposition team, prize money and points available to be won. 
	 * @param playerTeam the player's team
	 * @param oppositionTeam the opposition team that the match is against
	 * @param money the money reward on offer for winning the game
	 * @param points the points reward on offer for winning the game
	 */
	public Match(Team playerTeam, Team oppositionTeam, int money, int points) {
		this.prizeMoney = money;
		this.points = points;
		this.oppositionTeam = oppositionTeam;
		
		playerTeamName = playerTeam.getTeamName();
		oppositionTeamName = oppositionTeam.getTeamName();
		
		// Initialises the game scores to 0
		playerScore = 0;
		oppositionScore = 0;
		
		unplayedAthletes = new ArrayList<Athlete>();
		unplayedOpposition = new ArrayList<Athlete>();
		playedTeamAthletes = new ArrayList<Athlete>();
		
		unplayedAthletes.addAll(playerTeam.getTeamList());
		unplayedOpposition.addAll(oppositionTeam.getTeamList());
	}
	
	/**
	 * Returns the name of the opposition team
	 * @return oppositionTeamName the name belonging to the opposition team
	 */
	public String getOppositionTeamName() {
		return oppositionTeamName;
	}
	
	/**
	 * Returns the opposition team
	 * @return oppositionTeam the opposition team
	 */
	public Team getOppositionTeam() {
		return oppositionTeam;
	}
	
	/**
	 * Returns the value of the prize money
	 * @return prizeMoney the amount of money awarded to the winning team
	 */
	public int getMoney() {
		return prizeMoney;
	}
	
	/**
	 * Returns the value of the points reward
	 * @return points the amount of points awarded to the winning team
	 */
	public int getPointsValue() {
		return points;
	}
	
	/**
	 * Returns the value of the player team's score in the match
	 * @return playerScore the score for the player team
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Returns the value of the opposition team's score in the match
	 * @return oppositionScore the score for the opposition team
	 */
	public int getOppositionScore() {
		return oppositionScore;
	}
	
	
	
	/**
	 * Generates a random string describing a scenario where the attacker wins the encounter by a certain score difference.
	 *
	 * @param attackerName a String representing the name of the attacker
	 * @param scoreDifference an integer representing the difference in score between the attacker and defender
	 * @param defenderName a String representing the name of the defender
	 * @return a String representing a description of the attacker winning the encounter
	 */
	public String attackerWinString(String attackerName, int scoreDifference,  String defenderName) {
		
		scoreDifference = Math.abs(scoreDifference);

		// Strings for depicting the attacker winning the encounter by a small amount - the contest was close
		ArrayList<String> closeStrings = new ArrayList<String>();
		closeStrings.add(attackerName + " narrowly evades " + defenderName + "'s tackle attempt and scores with the ball");
		closeStrings.add( attackerName + " manages to slip past " + defenderName + "'s tackle and scores with the ball");
		closeStrings.add(attackerName + " breaks free from " + defenderName + "'s grasp and scores with the ball!");
		closeStrings.add(attackerName + " slips past " + defenderName + "'s tackle and scores with the ball, just managing to secure the point!");
		
		
		// Strings for depicting the attacker winning the encounter by a medium amount
		ArrayList<String> mediumStrings = new ArrayList<String>();
		mediumStrings.add(attackerName + " darts past " + defenderName + " with a sudden burst of speed, throwing the ball through the hoop for a goal!");
		mediumStrings.add(attackerName + " performs a sharp turn and evades " + defenderName + "'s grasp, flying towards the goal and scoring");
		mediumStrings.add(attackerName + " fakes out " + defenderName + " with a clever feint, throwing the ball through the goal and scoring a point for their team!");
		mediumStrings.add(defenderName + " tries to block " + attackerName + ", but misses, allowing the attacker to dart past and score a goal with the ball!");
		mediumStrings.add(defenderName + " tries to intercept the ball, but " + attackerName + " changes course quickly and flies around the defender, throwing the ball through the hoop");
		mediumStrings.add(attackerName + " executes a daring dive to avoid " + defenderName + "'s tackle and manages to throw the ball through the goal for a point!");
		mediumStrings.add("With a swift move, " + attackerName + " dodges " + defenderName + "'s tackle and scores with the ball!");

		
		// Strings for depicting the attacker winning the encounter by a large amount
		ArrayList<String> largeStrings = new ArrayList<String>();
		largeStrings.add("With lightning-fast reflexes, " + attackerName + " easily dodges " + defenderName + "'s attempted tackle and scores an effortless goal with the ball!");
		largeStrings.add("With a quick burst of speed, " + attackerName + " zooms past " + defenderName + ", leaving the defender behind and scoring a goal with the ball without any difficulty!");
		largeStrings.add("Despite " + defenderName + "'s best efforts to block, " + attackerName + " flawlessly executes a series of maneuvers and scores a goal with the ball through the hoop with ease!");
		largeStrings.add(attackerName + " showcases their incredible aerial skills, leaving " + defenderName + " in the dust and scoring an effortless goal with the ball!");
		largeStrings.add("In a display of pure athleticism, " + attackerName + " expertly maneuvers around " + defenderName);
		largeStrings.add("A critical error from " + defenderName + " allows " + attackerName + " to fly right past them and score with the ball");
		largeStrings.add("With " + defenderName + " completely missing their tackle attempt, " + attackerName + " scores with the ball without any trouble");
		largeStrings.add("A momentary lapse in concentration from " + defenderName + " gives " + attackerName + " an opening, and they take advantage of it by scoring with the ball with ease!");
		
		Random random = new Random();
		String result = null;
		
		/*
		 * If the difference in the scores of each team is less than five it is considered a close game
		 * If the score difference is between 5 and 15, it is considered a win by a medium amount
		 * If the score difference is greater than 15, the encounter is considered to be won by a large amount
		 */
		if (scoreDifference < 5) {
			int randomIndex = random.nextInt(closeStrings.size());
			result = closeStrings.get(randomIndex);
		} else if (scoreDifference >= 5 && scoreDifference < 15) {
			int randomIndex = random.nextInt(mediumStrings.size());
			result = mediumStrings.get(randomIndex);
		} else {
			int randomIndex = random.nextInt(largeStrings.size());
			result = largeStrings.get(randomIndex);
		}
		
		return result;
	}
	
	
	/**
	 * Generates a random string describing a scenario where the defender wins the encounter by a certain score difference.
	 *
	 * @param defenderName a String representing the name of the defender
	 * @param scoreDifference an integer representing the difference in score between the defender and attacker
	 * @param attackerName a String representing the name of the attacker
	 * @return a String representing a description of the defender winning the encounter
	 */
	public String defenderWinString(String defenderName, int scoreDifference, String attackerName) {
		
		scoreDifference = Math.abs(scoreDifference);
		
		// Strings for depicting the defender winning the encounter by a small amount - the contest was close
		ArrayList<String> closeStrings = new ArrayList<String>();
		closeStrings.add(defenderName + " just manages to block " + attackerName + "'s shot, preventing a goal");
		closeStrings.add(attackerName + " tries to juke past " + defenderName + " but is tackled just short of the goal");
		closeStrings.add(attackerName + " makes a break for the goal but is foiled by " + defenderName + "'s well-timed tackle");
		closeStrings.add("Attempting to evade " + defenderName + ", " + attackerName + " jukes left and right but ultimately gets taken down by a hard tackle.");
		closeStrings.add(defenderName + " manages to block " + attackerName + "'s shot with a well-placed foot.");
		
		
		// Strings for depicting the defender winning the encounter by a medium amount
		ArrayList<String> mediumStrings = new ArrayList<String>();
		mediumStrings.add("As " + attackerName + " takes aim for the shot, " + defenderName + " anticipates the move and manages to block the ball");
		mediumStrings.add("As " + attackerName + " weaves through the opposition, " + defenderName + " manages to catch up and steal the ball");
		mediumStrings.add(defenderName + " manages to fly in front of the ball and knock it off course, preventing a score by " + attackerName);
		mediumStrings.add("With a well-timed block, " + defenderName + " prevents " + attackerName + " from making a crucial shot and secures the ball for their team.");
		mediumStrings.add("In a moment of quick thinking, " + defenderName + " dives in front of " + attackerName + "'s shot and deflects the ball away from the goalpost.");
		mediumStrings.add(defenderName + " manages to outfly " + attackerName + " and intercepts the ball before they can make a shot.");
		mediumStrings.add("With a well-timed tackle, " + defenderName + " knocks the ball out of " + attackerName + "'s hands.");

		
		// Strings for depicting the defender winning the encounter by a large amount
		ArrayList<String> largeStrings = new ArrayList<String>();
		largeStrings.add("In a one-sided contest, " + defenderName + " effortlessly steals the ball from " + attackerName + " and gains possession for their team.");
		largeStrings.add("Unable to keep up with " + defenderName + "'s quick reflexes, " + attackerName + " fumbles the ball and loses possession");
		largeStrings.add(defenderName + " easily knocks the ball out of " + attackerName + "'s grasp");
		largeStrings.add(defenderName + " blocks " + attackerName + "'s shot easily, causing the ball to fly off course");
		largeStrings.add("Without breaking a sweat, " + defenderName + " intercepts " + attackerName + "'s pass");
		
		Random random = new Random();
		String result = null;
		
		if (scoreDifference < 5) {
			int randomIndex = random.nextInt(closeStrings.size());
			result = closeStrings.get(randomIndex);
		} else if (scoreDifference >= 5 && scoreDifference < 15) {
			int randomIndex = random.nextInt(mediumStrings.size());
			result = mediumStrings.get(randomIndex);
		} else {
			int randomIndex = random.nextInt(largeStrings.size());
			result = largeStrings.get(randomIndex);
		}
		
		return result;
	}

	/**
	 * Returns the encounter athletes for the match
	 * Selects encounter athletes by taking the first remaining unplayed athlete from the unplayed player's list, 
	 * The selected athlete is removed from the unplayed athletes list and added to the list of encounter athletes.
	 * If the athlete selected is an attacker, a defender from the opposition team will be selected for the encounter,
	 * removed from the unplayed opposition athletes list and vice versa if the athlete was a defender.
	 * @return a list of athletes: one from each team in opposing positions that will play each other in the encounter
	 */
	public ArrayList<Athlete> getEncounterAthletes() {
		ArrayList<Athlete> encounterAthletes = new ArrayList<Athlete>();
		Athlete athlete = unplayedAthletes.get(0);
		encounterAthletes.add(athlete);
		unplayedAthletes.remove(athlete);
		String position = athlete.getPosition();
		
		if (position.equals("Attacker")) {
			for (Athlete oppositionAthlete : unplayedOpposition) {
				if (oppositionAthlete.getPosition().equals("Defender")) {
					encounterAthletes.add(oppositionAthlete);
					unplayedOpposition.remove(oppositionAthlete);
					break;
				}
			}
		} else {
			for (Athlete oppositionAthlete : unplayedOpposition) {
				if (oppositionAthlete.getPosition() == "Attacker") {
					encounterAthletes.add(oppositionAthlete);
					unplayedOpposition.remove(oppositionAthlete);
					break;
				}
			}
		}
		
		return encounterAthletes;
				
	}
	
	/**
	 * Simulates an encounter between two athletes and determines the outcome.
	 * A defender will use their defensive score for the encounter and an attacker will use their attacking score.
	 * If an attacker wins their team gains 10 points. If a defender wins, their team does not gain points but the other team does not score. 
	 * A string will be generated based on the result of the encounter (which athlete won the encounter and based on how close the scores were)
	 * If an athlete loses the encounter, their stamina's will decrease more than if they win.
	 * @param athlete the player's athete;
	 * @param opposition the opposition athlete;
	 * @return a string describing the result of the encounter including which player won, depending on how close the score difference was.
	 */
	public String encounter(Athlete athlete, Athlete opposition) {
        playedTeamAthletes.add(athlete);
        
        /*
         *  If an athlete loses the encounter, they lose more energy (stamina) than if they won.
         *  The amount varies for each (win onr lost), inclusive of the following integers.
         */
		int minWinStaminaDecreaseVal = 10;
		int maxWinStaminaDecreaseVal = 50;
		int minLossStaminaDecreaseVal = 50;
		int maxLossStaminaDecreaseVal = 100;

				
		Random random = new Random();
		
		if (athlete.getPosition() == "Defender") {
			int result = athlete.getDefensive() - opposition.getOffensive();
			// in the case that it is a tie, the defender will "win" since the attacker will not have been able to score. 
			if (result >= 0) {
		        athlete.decreaseStamina(random.nextInt(maxWinStaminaDecreaseVal - minWinStaminaDecreaseVal + 1) + minWinStaminaDecreaseVal);
				return defenderWinString(athlete.getName(), result, opposition.getName());
			} else {
		        athlete.decreaseStamina(random.nextInt(maxLossStaminaDecreaseVal - minLossStaminaDecreaseVal + 1) + minLossStaminaDecreaseVal);
				oppositionScore += 10;
				return attackerWinString(opposition.getName(), result, athlete.getName());
			}
		} else {
			int result = athlete.getOffensive() - opposition.getDefensive();
			if (result > 0) {
		        athlete.decreaseStamina(random.nextInt(maxWinStaminaDecreaseVal - minWinStaminaDecreaseVal + 1) + minWinStaminaDecreaseVal);
				playerScore += 10;
				return attackerWinString(athlete.getName(), result, opposition.getName());
			} else {
		        athlete.decreaseStamina(random.nextInt(maxLossStaminaDecreaseVal - minLossStaminaDecreaseVal + 1) + minLossStaminaDecreaseVal);
				return defenderWinString(opposition.getName(), result, athlete.getName());
			}
		}

	}
	

	
	/**
	 * Increases the statistics of the athletes in the played team.
	 * The amount of increase is randomly generated within a range specified by the athlete's position that they played in.
	 */
	public void increaseTeamStats() {
		// Increase statistic amount for the position they played in
		int minIncreasePosition = 3;
		int maxIncreasePosition = 6;
		
		// Increase statistic amount for the position they did't play in
		int minIncreaseAlternative = 0;
		int maxIncreaseAlternative = 3;
		
		Random random = new Random();
		
		for (Athlete athlete : playedTeamAthletes) {
			if (athlete.getPosition().equals("Attacker")) {
				int increaseAmountOffensive = random.nextInt(maxIncreasePosition - minIncreasePosition + 1) + minIncreasePosition;
				athlete.increaseOffensive(increaseAmountOffensive);
				int increaseAmountDefensive = random.nextInt(maxIncreaseAlternative - minIncreaseAlternative + 1) + minIncreaseAlternative;
				athlete.increaseDefensive(increaseAmountDefensive);
			} else {
				int increaseAmountDefensive = random.nextInt(maxIncreasePosition - minIncreasePosition + 1) + minIncreasePosition;
				athlete.increaseDefensive(increaseAmountDefensive);
				int increaseAmountOffensive = random.nextInt(maxIncreaseAlternative - minIncreaseAlternative + 1) + minIncreaseAlternative;
				athlete.increaseOffensive(increaseAmountOffensive);
			}
		}
	}
	
	/**
	 * Returns a whether all athletes in the player team are injured
	 * If so - the match will have to end early
	 * @return true if all players in the team are injured, false if at least one or more is not.
	 */
	public boolean allPlayersInjured() {
	    for (Athlete athlete : playedTeamAthletes) {
	        if (athlete.getStamina() != 0) {
	        	// At least one athlete has non-zero stamina, so return false
	            return false; 
	        }
	    }
	 // All athletes have stamina 0, so return true
	    return true; 
	}
	
	/**
	 * Ends the match and returns the result of the match. 
	 * @return a map containing the game result including winner (or draw), money and points won by the player team and the game score (or a string if the player team due to stamina loss)
	 * @throws IllegalStateException if the game is not over (there are less than 4 players in the played team)
	 */
	public Map<String, Object> endGame() throws IllegalStateException {
	    Map<String, Object> result = new HashMap<>();


	    if (playedTeamAthletes.size() < 4) {
	        throw new IllegalStateException("Game not over");
	    } else if (allPlayersInjured()) {
	        result.put("winner", oppositionTeamName);
	    	result.put("money", 0);
	    	result.put("points", 0);
	    	result.put("score", "All " + playerTeamName + " athletes were injured, resulting in a loss");
	    	// no stats increased for players if they are all injured
			return result;

	    } else if (playerScore > oppositionScore) {
	        result.put("winner", playerTeamName);
	    	result.put("money", prizeMoney);
	    	result.put("points", points);
		} else if (playerScore == oppositionScore) {
	        result.put("winner", "Draw");
	        // win a third of points and money on offer if game results in a draw
	    	result.put("money", (int) prizeMoney/3);
	    	result.put("points", (int)  points/3);
		} else {
	        result.put("winner", oppositionTeamName);
	    	result.put("money", 0);
	    	result.put("points", 0);
		}
		
	    result.put("score",  playerTeamName + ": " + playerScore + " " + oppositionTeamName + ": " + oppositionScore);	
	    
		increaseTeamStats();
		return result;
	}
	
	/**
	 * Checks that the match is currently running by checking if there are 4 players that have had encounters
	 * @return true if the match is running and not all athletes have been played and false otherwise
	 */
	public Boolean isMatchRunning() {
		if (playedTeamAthletes.size() < 4) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a string with the prize money formatted to include a dollar sign and separate the thousands by a comma
	 * @return formatted the prize money as a formatted string
	 */
	public String getMoneyFormatted() {
	    String formatted = "";
	    int absPrizeMoney = Math.abs(prizeMoney);
	    if (prizeMoney < 0) {
	        formatted += "-";
	    }
	    formatted += "$" + String.format("%,d", absPrizeMoney);
	    return formatted;
	}
	
	/**
	 * Returns a string representation of the Match object
	 * @return A string containing the team name, points and prize money for the match.
	 */
	public String toString() {
		String result = "Team name: " + oppositionTeamName;
		result += "\nPoints: " + points;
		result += "\nPrize money: " + getMoneyFormatted();
		
		return result;
	}

}
