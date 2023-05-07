package project;

import java.security.SecureRandom;

public class SpaceSecureRandomiser {
    private SecureRandom random;
    
    public SpaceSecureRandomiser() {
        random = new SecureRandom();
    }

    public String assignSpacemenToJobs(String[] names, String[] chores) {
        String name = names[random.nextInt(names.length)];
        String chore = chores[random.nextInt(chores.length)];
        return (name + " assigned job " + chore);
        
    }
    
    public static void main(String[] args) {
    	System.out.println(new SpaceSecureRandomiser().assignSpacemenToJobs(new String[]{"Jess"}, new String[] {"pilot"}));
    }
}