package project;

import java.util.ArrayList;
import java.util.Random;

public class Market {
	/// generates random number between 3,5
	Random R = new Random();
	int min = 3;
	int max = 5;
	
	int Num = R.nextInt(max - min + 1) + min;
	
	///array list of athletes between 3-5 athletes
	public ArrayList<Athlete> Shop() {
		ArrayList<Athlete> ShopAthletes = new ArrayList<Athlete>();
		
		
		for (int i = 1; i <= Num; i ++) {
			Athlete athlete = new Athlete();
			athlete.setContractPrice(5000);
			ShopAthletes.add(athlete);
		}
		
		return ShopAthletes;
		
		/// to do test if works, make items, random array list of items
		
	

}}
