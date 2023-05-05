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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSlider;

public class SetupScreen2 {

	private JFrame frame;
	private JTextField textField;
	private GameEnvironment gameEnvironment;


	/**
	 * Create the application.
	 */
	public SetupScreen2(GameEnvironment environment) {
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
		
		
		JPanel startPanel = new JPanel();
		startPanel.setBackground(new Color(255, 255, 255));
		startPanel.setBounds(0, 0, 923, 680);
		frame.getContentPane().add(startPanel);
		startPanel.setLayout(null);
		
		
		JLabel welcomeText = new JLabel("Welcome!");
		welcomeText.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeText.setBounds(309, 192, 298, 90);
		startPanel.add(welcomeText);
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setBackground(new Color(0, 171, 58));
		startButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		startButton.setBounds(385, 311, 168, 77);
		startPanel.add(startButton);


	   
	    /** 
	    * When "start game" button is pressed, setupInputPanel becomes visible 
	    * (showing inputs for team name and week) and startPanel becomes invisible (welcome text and startButton are not visible)
	    */
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(false);
			    setupInput();
			    
			}
		});
		


	}
	
	private void setupInput() {
		JPanel setupInputPanel = new JPanel();
		setupInputPanel.setBackground(new Color(255, 255, 255));
		setupInputPanel.setBounds(0, 0, 923, 680);
		frame.getContentPane().add(setupInputPanel);
		setupInputPanel.setLayout(null);
		
	    JLabel teamLabel = new JLabel("Please input a team name");
	    teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    teamLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    teamLabel.setBounds(33, 148, 847, 90);
	    setupInputPanel.add(teamLabel);

	    
	    JTextField teamNameInput = new JTextField();
	    teamNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameInput.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		teamNameInput.setBounds(249, 242, 442, 40);
		setupInputPanel.add(teamNameInput);
		teamNameInput.setColumns(10);

	    JSlider weeksSlider = new JSlider(JSlider.HORIZONTAL, 5, 15, 10);
	    weeksSlider.setFont(new Font("Cooper Black", Font.PLAIN, 15));
	    weeksSlider.setBounds(309, 430, 338, 66);
	    weeksSlider.setMajorTickSpacing(1);
	    weeksSlider.setPaintTicks(true);
	    weeksSlider.setPaintLabels(true);
	    weeksSlider.setSnapToTicks(true);
	    setupInputPanel.add(weeksSlider);

	    JButton nextButton = new JButton("Next");
	    nextButton.setForeground(Color.BLACK);
	    nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    nextButton.setBackground(new Color(0, 171, 58));
	    nextButton.setBounds(385, 561, 168, 46);
	    setupInputPanel.add(nextButton);
	    
	    
	    JLabel weeksLabel = new JLabel("How many weeks would you like the season to last?");
	    weeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    weeksLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    weeksLabel.setBounds(33, 329, 847, 90);
	    setupInputPanel.add(weeksLabel);

	    
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setForeground(new Color(255, 0, 0));
		errorText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorText.setBounds(249, 304, 442, 14);
		setupInputPanel.add(errorText);
		
	    nextButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			String nameInput = teamNameInput.getText();
	    			gameEnvironment.setTeamName(nameInput);
	    			gameEnvironment.setSeasonLength(weeksSlider.getValue());
	    			setupInputPanel.setVisible(false);
				    buyAthletes();
	    			
	    		} catch (NameException e){
	    			errorText.setText((e.getMessage()));
	    			errorText.setVisible(true);
	    		} 
	    		}
	    });
	}
	
	private void buyAthletes() {
		
		JPanel athletesViewPanel = new JPanel();
		athletesViewPanel.setBackground(new Color(255, 255, 255));
		athletesViewPanel.setBounds(0, 0, 923, 680);
		frame.getContentPane().add(athletesViewPanel);
		athletesViewPanel.setLayout(null);
		
		
		JPanel panelAthlete1 = new JPanel();
		panelAthlete1.setBounds(186, 84, 245, 274);
		athletesViewPanel.add(panelAthlete1);
		panelAthlete1.setLayout(null);
		
		JPanel pictureAthlete1 = new JPanel();
		pictureAthlete1.setBounds(48, 55, 151, 93);
		panelAthlete1.add(pictureAthlete1);
		
		JLabel infoAthlete1 = new JLabel("");
		infoAthlete1.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete1.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete1.setBounds(0, 158, 245, 116);
		panelAthlete1.add(infoAthlete1);
		
		JLabel nameAthlete1 = new JLabel("Name athlete1");
		nameAthlete1.setForeground(new Color(0, 0, 0));
		nameAthlete1.setBackground(new Color(255, 255, 255));
		nameAthlete1.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete1.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete1.setBounds(10, 11, 225, 32);
		panelAthlete1.add(nameAthlete1);
		nameAthlete1.setOpaque(true);
		
		JLabel infoText = new JLabel("Now you need a team! Please purchase one of the following four athletes:");
		infoText.setBounds(35, 27, 869, 24);
		athletesViewPanel.add(infoText);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelAthlete2 = new JPanel();
		panelAthlete2.setLayout(null);
		panelAthlete2.setBounds(481, 84, 245, 274);
		athletesViewPanel.add(panelAthlete2);
		
		JPanel pictureAthlete2 = new JPanel();
		pictureAthlete2.setBounds(48, 55, 151, 93);
		panelAthlete2.add(pictureAthlete2);
		
		JLabel infoAthlete2 = new JLabel("");
		infoAthlete2.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete2.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete2.setBounds(0, 158, 245, 116);
		panelAthlete2.add(infoAthlete2);
		
		JLabel nameAthlete2 = new JLabel("Name athlete1");
		nameAthlete2.setOpaque(true);
		nameAthlete2.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete2.setForeground(Color.BLACK);
		nameAthlete2.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete2.setBackground(Color.WHITE);
		nameAthlete2.setBounds(10, 11, 225, 32);
		panelAthlete2.add(nameAthlete2);
		
		JPanel panelAthlete3 = new JPanel();
		panelAthlete3.setLayout(null);
		panelAthlete3.setBounds(186, 383, 245, 274);
		athletesViewPanel.add(panelAthlete3);
		
		JPanel pictureAthlete3 = new JPanel();
		pictureAthlete3.setBounds(48, 55, 151, 93);
		panelAthlete3.add(pictureAthlete3);
		
		JLabel infoAthlete3 = new JLabel("");
		infoAthlete3.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete3.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete3.setBounds(0, 158, 245, 116);
		panelAthlete3.add(infoAthlete3);
		
		JLabel nameAthlete3 = new JLabel("Name athlete1");
		nameAthlete3.setOpaque(true);
		nameAthlete3.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete3.setForeground(Color.BLACK);
		nameAthlete3.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete3.setBackground(Color.WHITE);
		nameAthlete3.setBounds(10, 11, 225, 32);
		panelAthlete3.add(nameAthlete3);
		
		JPanel panelAthlete4 = new JPanel();
		panelAthlete4.setLayout(null);
		panelAthlete4.setBounds(481, 383, 245, 274);
		athletesViewPanel.add(panelAthlete4);
		
		JPanel pictureAthlete4 = new JPanel();
		pictureAthlete4.setBounds(48, 55, 151, 93);
		panelAthlete4.add(pictureAthlete4);
		
		JLabel infoAthlete4 = new JLabel("");
		infoAthlete4.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete4.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete4.setBounds(0, 158, 245, 116);
		panelAthlete4.add(infoAthlete4);
		
		JLabel nameAthlete4 = new JLabel("Name athlete1");
		nameAthlete4.setOpaque(true);
		nameAthlete4.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete4.setForeground(Color.BLACK);
		nameAthlete4.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete4.setBackground(Color.WHITE);
		nameAthlete4.setBounds(10, 11, 225, 32);
		panelAthlete4.add(nameAthlete4);
		
		
	    /**
		* Set the text of jlabel to the information from toStringHTML for each athlete
	    */
		
		ArrayList<Athlete> athletes = gameEnvironment.getStartingAthletes();
		
		for (int i = 0; i < athletes.size(); i++) {
		    JLabel infoAthlete = null;
		    if (i == 0) {
		        infoAthlete = infoAthlete1;
		    } else if (i == 1) {
		        infoAthlete = infoAthlete2;
		    } else if (i == 2) {
		        infoAthlete = infoAthlete3;
		    } else if (i == 3) {
		        infoAthlete = infoAthlete4;
		    }
		    infoAthlete.setText(athletes.get(i).toStringHTML());
		}
		
	    /**
		* Go to singleAthleteView display depends on which of the 4 athlete panels is selected.
	    */
		
		JPanel[] panels = {panelAthlete1, panelAthlete2, panelAthlete3, panelAthlete4};
		for (int i = 0; i < panels.length; i++) {
			final int index = i;
		    JPanel panel = panels[i];
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            athletesViewPanel.setVisible(false);
		            singleAthleteView(athletes.get(index));
		        }
		    });
		}
	}
	
	private void singleAthleteView(Athlete athlete) {
		
		JPanel singleAthletePanel = new JPanel();
		singleAthletePanel.setBackground(new Color(255, 255, 255));
		singleAthletePanel.setBounds(0, 0, 923, 680);
		frame.getContentPane().add(singleAthletePanel);
		singleAthletePanel.setLayout(null);
		
		JPanel athletePanel = new JPanel();
		athletePanel.setBounds(282, 88, 360, 459);
		singleAthletePanel.add(athletePanel);
		athletePanel.setLayout(null);
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		athletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImage = new JPanel();
		athleteImage.setBounds(58, 79, 247, 203);
		athletePanel.add(athleteImage);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.PLAIN, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		athletePanel.add(athleteInfo);
		
		JButton attackerButton = new JButton("<html><center>"+"Buy as"+"<br>"+"attacker"+"</center></html>");
		attackerButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		attackerButton.setBounds(282, 586, 168, 48);
		singleAthletePanel.add(attackerButton);
		
		JLabel playerBalance = new JLabel("Your balance: " + gameEnvironment.getMoneyFormatted());
		playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playerBalance.setBounds(650, 11, 273, 59);
		singleAthletePanel.add(playerBalance);
		
		JButton defenderButton = new JButton("<html><center>Buy as<br>defender</center></html>");
		defenderButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		defenderButton.setBounds(474, 586, 168, 48);
		singleAthletePanel.add(defenderButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		singleAthletePanel.add(backButton);
		//frame.setBounds(100, 100, 949, 719);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		buyAthletes();
	    		}
	    });
	}
}
