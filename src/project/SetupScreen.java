package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import lab.RocketManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSlider;

public class SetupScreen {

	private JFrame frame;
	private JTextField textField;
	private GameEnvironment gameEnvironment;


	/**
	 * Create the application.
	 */
	public SetupScreen(GameEnvironment environment) {
		gameEnvironment = environment;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 949, 719);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel welcomeText = new JLabel("Welcome!");
		welcomeText.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(309, 192, 298, 90);
		frame.getContentPane().add(welcomeText);
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setBackground(new Color(0, 171, 58));
		startButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		startButton.setBounds(385, 311, 168, 77);
		frame.getContentPane().add(startButton);

	    
	   
	    /** 
	    * When "start game" button is pressed, inputs for team name and week are visible and welcome text becomes not visible.
	    */
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    startButton.setVisible(false);
			    welcomeText.setVisible(false);
			    setupInput();
			    
			}
		});
		


	}
	
	private void setupInput() {
	    JLabel teamLabel = new JLabel("Please input a team name");
	    teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    teamLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    teamLabel.setBounds(33, 148, 847, 90);
	    frame.getContentPane().add(teamLabel);

	    
	    JTextField teamNameInput = new JTextField();
	    teamNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameInput.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		teamNameInput.setBounds(249, 242, 442, 40);
		frame.getContentPane().add(teamNameInput);
		teamNameInput.setColumns(10);


	    
	    JSlider weeksSlider = new JSlider(JSlider.HORIZONTAL, 5, 15, 10);
	    weeksSlider.setFont(new Font("Cooper Black", Font.PLAIN, 15));
	    weeksSlider.setBounds(309, 430, 338, 66);
	    weeksSlider.setMajorTickSpacing(1);
	    weeksSlider.setPaintTicks(true);
	    weeksSlider.setPaintLabels(true);
	    weeksSlider.setSnapToTicks(true);
	    frame.getContentPane().add(weeksSlider);

	    JButton nextButton = new JButton("Next");
	    nextButton.setForeground(Color.BLACK);
	    nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    nextButton.setBackground(new Color(0, 171, 58));
	    nextButton.setBounds(385, 561, 168, 46);
	    frame.getContentPane().add(nextButton);
	    
	    
	    JLabel weeksLabel = new JLabel("How many weeks would you like the season to last?");
	    weeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    weeksLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    weeksLabel.setBounds(33, 329, 847, 90);
	    frame.getContentPane().add(weeksLabel);

	    
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setForeground(new Color(255, 0, 0));
		errorText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorText.setBounds(249, 304, 442, 14);
		frame.getContentPane().add(errorText);
		
	    nextButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			String nameInput = teamNameInput.getText();
	    			gameEnvironment.setTeamName(nameInput);
	    			gameEnvironment.setSeasonLength(weeksSlider.getValue());
					weeksLabel.setVisible(false);
				    teamLabel.setVisible(false);
				    weeksSlider.setVisible(false);
				    teamNameInput.setVisible(false);
				    nextButton.setVisible(false);
				    errorText.setVisible(false);
				    buyAthletes();
	    			
	    		} catch (NameException e){
	    			errorText.setText((e.getMessage()));
	    			errorText.setVisible(true);
	    		} 
	    		}
	    });
	}
	
	private void buyAthletes() {
		gameEnvironment.startingList();
		
		JPanel panelAthlete1 = new JPanel();
		panelAthlete1.setBounds(150, 83, 245, 274);
		frame.getContentPane().add(panelAthlete1);
		panelAthlete1.setLayout(null);
		
		JPanel pictureAthlete1 = new JPanel();
		pictureAthlete1.setBounds(70, 30, 106, 101);
		panelAthlete1.add(pictureAthlete1);
		
		JLabel infoAthlete1 = new JLabel("");
		infoAthlete1.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete1.setBounds(24, 169, 197, 81);
		panelAthlete1.add(infoAthlete1);
		
		JLabel infoText = new JLabel("Now you need a team! Please purchase one of the following four athletes:");
		infoText.setBounds(35, 27, 869, 24);
		frame.getContentPane().add(infoText);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelAthlete2 = new JPanel();
		panelAthlete2.setLayout(null);
		panelAthlete2.setBounds(485, 83, 245, 274);
		frame.getContentPane().add(panelAthlete2);
		
		JPanel pictureAthlete2 = new JPanel();
		pictureAthlete2.setBounds(70, 30, 106, 101);
		panelAthlete2.add(pictureAthlete2);
		
		JLabel infoAthlete2 = new JLabel("");
		infoAthlete2.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete2.setBounds(24, 169, 197, 81);
		panelAthlete2.add(infoAthlete2);
		
		JPanel panelAthlete3= new JPanel();
		panelAthlete3.setLayout(null);
		panelAthlete3.setBounds(150, 380, 245, 274);
		frame.getContentPane().add(panelAthlete3);
		
		JPanel pictureAthlete3 = new JPanel();
		pictureAthlete3.setBounds(70, 30, 106, 101);
		panelAthlete3.add(pictureAthlete3);
		
		JLabel infoAthlete3 = new JLabel("");
		infoAthlete3.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete3.setBounds(24, 169, 197, 81);
		panelAthlete3.add(infoAthlete3);
		
		JPanel panelAthlete4 = new JPanel();
		panelAthlete4.setLayout(null);
		panelAthlete4.setBounds(485, 380, 245, 274);
		frame.getContentPane().add(panelAthlete4);
		
		JPanel pictureAthlete4 = new JPanel();
		pictureAthlete4.setBounds(70, 30, 106, 101);
		panelAthlete4.add(pictureAthlete4);
		
		JLabel infoAthlete4 = new JLabel("");
		infoAthlete4.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete4.setBounds(24, 169, 197, 81);
		panelAthlete4.add(infoAthlete4);
	}
}
