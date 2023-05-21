package ProjectUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ProjectExceptions.NameException;
import project.GameEnvironment;
import project.Athlete;

import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.ImageIcon;

public class SetupScreen2 {
	
	int width = 984;
	int height = 711;

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
	 * Creates a JPanel with the specified width and height within the frame to be used as background panels throughout MainGame.
	 *
	 * @return the JPanel with the specified properties
	 */
	private JPanel createScreenPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, width, height);
		panel.setLayout(null);
		
		return panel;
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel startPanel = createScreenPanel();
		frame.getContentPane().add(startPanel);
		
		JLabel titleText1 = new JLabel("Broomstick");
		titleText1.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		titleText1.setHorizontalAlignment(SwingConstants.CENTER);
		titleText1.setSize(359, 70);
		titleText1.setLocation((width - titleText1.getWidth()) / 2, 200);
		startPanel.add(titleText1);
		
		JLabel titleText2 = new JLabel("Blitz");
		titleText2.setHorizontalAlignment(SwingConstants.CENTER);
		titleText2.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		titleText2.setSize(145, 70);
		titleText2.setLocation(((width - titleText2.getWidth()) / 2) - 20, 270);
		startPanel.add(titleText2);
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setBackground(new Color(0, 171, 58));
		startButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		startButton.setSize(168, 77);
		startButton.setLocation((width - startButton.getWidth())/ 2, 370);
		startPanel.add(startButton);
		
		ClassLoader classLoader = getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(classLoader.getResource("Pictures/broomstick.jpg"));
		Image originalImage = icon.getImage();
		int scaledWidth = (int) (icon.getIconWidth() * 0.2);
		int scaledHeight = (int) (icon.getIconHeight() * 0.15);
		Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(scaledIcon);
		imageLabel.setBounds(545, 265, scaledWidth, scaledHeight);
		startPanel.add(imageLabel);

	   
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
		JPanel setupInputPanel = createScreenPanel();
		frame.getContentPane().add(setupInputPanel);
		
	    JLabel teamLabel = new JLabel("Please input a team name");
	    teamLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    teamLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    teamLabel.setSize(255, 24);
	    teamLabel.setLocation((width - teamLabel.getWidth()) / 2, 167);
	    setupInputPanel.add(teamLabel);

	    JTextField teamNameInput = new JTextField();
	    teamNameInput.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameInput.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		teamNameInput.setSize(442, 40);
		teamNameInput.setLocation((width - teamNameInput.getWidth())/ 2, 242);
		setupInputPanel.add(teamNameInput);
		teamNameInput.setColumns(10);

	    JSlider weeksSlider = new JSlider(JSlider.HORIZONTAL, 5, 15, 10);
	    weeksSlider.setFont(new Font("Cooper Black", Font.PLAIN, 15));
	    weeksSlider.setSize(338, 66);
	    weeksSlider.setLocation((width - weeksSlider.getWidth())/2, 430);
	    weeksSlider.setMajorTickSpacing(1);
	    weeksSlider.setPaintTicks(true);
	    weeksSlider.setPaintLabels(true);
	    weeksSlider.setSnapToTicks(true);
	    setupInputPanel.add(weeksSlider);

	    JButton nextButton = new JButton("Next");
	    nextButton.setForeground(Color.BLACK);
	    nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    nextButton.setBackground(new Color(0, 171, 58));
	    nextButton.setSize(168, 46);
	    nextButton.setLocation((width - nextButton.getWidth()) / 2, 561);
	    setupInputPanel.add(nextButton);
	    
	    JLabel weeksLabel = new JLabel("How many weeks would you like the season to last?");
	    weeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    weeksLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    weeksLabel.setSize(528, 24);
	    weeksLabel.setLocation((width - weeksLabel.getWidth())/2, 356);
	    setupInputPanel.add(weeksLabel);

	    
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setForeground(new Color(255, 0, 0));
		errorText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorText.setSize(442, 20);
		errorText.setLocation((width - errorText.getWidth()) /2, 304);
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
		
		JPanel athletesViewPanel = createScreenPanel();
		frame.getContentPane().add(athletesViewPanel);
		athletesViewPanel.setLayout(null);
		
		JLabel infoText = new JLabel("Now you need a team! Please purchase one of the following four athletes:");
		infoText.setSize(742, 24);
		infoText.setLocation((width - infoText.getWidth())/2, 27);
		athletesViewPanel.add(infoText);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		
		if (gameEnvironment.getTeamList().size() > 0) {
			infoText.setVisible(false);
			
			JLabel playerBalance = new JLabel("Your balance: " + gameEnvironment.getMoneyFormatted());
			playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			playerBalance.setBounds(650, 11, 291, 59);
			athletesViewPanel.add(playerBalance);
			
			JLabel teamSize = new JLabel("Team filled: " + gameEnvironment.getTeamList().size() + "/4 players");
			teamSize.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			teamSize.setBounds(100, 11, 400, 59);
			athletesViewPanel.add(teamSize);
		}
		
		JPanel panelAthlete1 = new JPanel();
		panelAthlete1.setBounds(226, 84, 245, 274);
		athletesViewPanel.add(panelAthlete1);
		panelAthlete1.setLayout(null);
		
		JPanel picturePanelAthlete1 = new JPanel();
		picturePanelAthlete1.setBounds(48, 55, 151, 93);
		panelAthlete1.add(picturePanelAthlete1);
		picturePanelAthlete1.setBackground(Color.WHITE);
		picturePanelAthlete1.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAthlete1 = new JLabel("");
		infoAthlete1.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete1.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete1.setBounds(0, 158, 245, 116);
		panelAthlete1.add(infoAthlete1);
		
		JLabel nameAthlete1 = new JLabel("");
		nameAthlete1.setForeground(new Color(0, 0, 0));
		nameAthlete1.setBackground(new Color(255, 255, 255));
		nameAthlete1.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete1.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete1.setBounds(10, 11, 225, 32);
		panelAthlete1.add(nameAthlete1);
		nameAthlete1.setOpaque(true);
		
		JPanel panelAthlete2 = new JPanel();
		panelAthlete2.setLayout(null);
		panelAthlete2.setBounds(521, 84, 245, 274);
		athletesViewPanel.add(panelAthlete2);
		
		JPanel picturePanelAthlete2 = new JPanel();
		picturePanelAthlete2.setBounds(48, 55, 151, 93);
		panelAthlete2.add(picturePanelAthlete2);
		picturePanelAthlete2.setBackground(Color.WHITE);
		picturePanelAthlete2.setLayout(new BorderLayout(0, 0));
		
		
		JLabel infoAthlete2 = new JLabel("");
		infoAthlete2.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete2.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete2.setBounds(0, 158, 245, 116);
		panelAthlete2.add(infoAthlete2);
		
		JLabel nameAthlete2 = new JLabel("");
		nameAthlete2.setOpaque(true);
		nameAthlete2.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete2.setForeground(Color.BLACK);
		nameAthlete2.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete2.setBackground(Color.WHITE);
		nameAthlete2.setBounds(10, 11, 225, 32);
		panelAthlete2.add(nameAthlete2);
		
		JPanel panelAthlete3 = new JPanel();
		panelAthlete3.setLayout(null);
		panelAthlete3.setBounds(226, 383, 245, 274);
		athletesViewPanel.add(panelAthlete3);
		
		JPanel picturePanelAthlete3 = new JPanel();
		picturePanelAthlete3.setBounds(48, 55, 151, 93);
		panelAthlete3.add(picturePanelAthlete3);
		picturePanelAthlete3.setBackground(Color.WHITE);
		picturePanelAthlete3.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAthlete3 = new JLabel("");
		infoAthlete3.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete3.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete3.setBounds(0, 158, 245, 116);
		panelAthlete3.add(infoAthlete3);
		
		JLabel nameAthlete3 = new JLabel("");
		nameAthlete3.setOpaque(true);
		nameAthlete3.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete3.setForeground(Color.BLACK);
		nameAthlete3.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete3.setBackground(Color.WHITE);
		nameAthlete3.setBounds(10, 11, 225, 32);
		panelAthlete3.add(nameAthlete3);
		
		JPanel panelAthlete4 = new JPanel();
		panelAthlete4.setLayout(null);
		panelAthlete4.setBounds(521, 383, 245, 274);
		athletesViewPanel.add(panelAthlete4);
		
		JPanel picturePanelAthlete4 = new JPanel();
		picturePanelAthlete4.setBounds(48, 55, 151, 93);
		panelAthlete4.add(picturePanelAthlete4);
		picturePanelAthlete4.setBackground(Color.WHITE);
		picturePanelAthlete4.setLayout(new BorderLayout(0, 0));
		
		JLabel infoAthlete4 = new JLabel("");
		infoAthlete4.setHorizontalAlignment(SwingConstants.CENTER);
		infoAthlete4.setFont(new Font("Calibri", Font.BOLD, 15));
		infoAthlete4.setBounds(0, 158, 245, 116);
		panelAthlete4.add(infoAthlete4);
		
		JLabel nameAthlete4 = new JLabel("");
		nameAthlete4.setOpaque(true);
		nameAthlete4.setHorizontalAlignment(SwingConstants.CENTER);
		nameAthlete4.setForeground(Color.BLACK);
		nameAthlete4.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		nameAthlete4.setBackground(Color.WHITE);
		nameAthlete4.setBounds(10, 11, 225, 32);
		panelAthlete4.add(nameAthlete4);
		
		
	    /**
		* Set the text of jlabel to the information from toStringHTML for each athlete and set name of each athlete
	    */
		
		ArrayList<Athlete> athletes = gameEnvironment.getInitialAthletes();
		
		for (int i = 0; i < athletes.size(); i++) {
		    JLabel infoAthlete = null;
		    JLabel nameAthlete = null;
		    JPanel picturePanelAthlete = null;
		    
		    if (i == 0) {
		        infoAthlete = infoAthlete1;
		        nameAthlete = nameAthlete1;
		        picturePanelAthlete = picturePanelAthlete1;
		    } else if (i == 1) {
		        infoAthlete = infoAthlete2;
		        nameAthlete = nameAthlete2;
		        picturePanelAthlete = picturePanelAthlete2;
		    } else if (i == 2) {
		        infoAthlete = infoAthlete3;
		        nameAthlete = nameAthlete3;
		        picturePanelAthlete = picturePanelAthlete3;
		    } else if (i == 3) {
		        infoAthlete = infoAthlete4;
		        nameAthlete = nameAthlete4;
		        picturePanelAthlete = picturePanelAthlete4;
		    }
		    infoAthlete.setText(athletes.get(i).toStringHTML());
		    nameAthlete.setText(athletes.get(i).getName());
		    
		    
			JLabel athleteImage = new JLabel("");
			ClassLoader classLoader = getClass().getClassLoader();
			ImageIcon icon = new ImageIcon(classLoader.getResource("Pictures/" + athletes.get(i).getImageName() + ".png"));
			Image image = icon.getImage().getScaledInstance((int)(icon.getIconWidth()*0.7), (int)(icon.getIconHeight()*0.7), Image.SCALE_SMOOTH);
			athleteImage.setIcon(new ImageIcon(image));
			athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
			picturePanelAthlete.add(athleteImage, BorderLayout.CENTER);
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
		singleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(singleAthletePanel);
		singleAthletePanel.setLayout(null);
		
		JPanel athletePanel = new JPanel();
		athletePanel.setSize(360, 459);
		athletePanel.setLocation(312, 98);
		singleAthletePanel.add(athletePanel);
		athletePanel.setLayout(null);
		
		JLabel athleteName = new JLabel(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		athleteName.setOpaque(true);
        athleteName.setBackground(Color.WHITE);
        athletePanel.add(athleteName);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		athletePanel.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		ClassLoader classLoader = getClass().getClassLoader();
		athleteImage.setIcon(new ImageIcon(classLoader.getResource("Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		athletePanel.add(athleteInfo);
		
		JLabel playerBalance = new JLabel("Your balance: " + gameEnvironment.getMoneyFormatted());
		playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playerBalance.setBounds(650, 11, 291, 59);
		singleAthletePanel.add(playerBalance);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		singleAthletePanel.add(backButton);
		
		JButton attackerButton = new JButton("<html><center>"+"Buy as"+"<br>"+"attacker"+"</center></html>");
		attackerButton.setSize((athletePanel.getWidth()/2) - 10, 50);
		attackerButton.setLocation(312, 581);
		singleAthletePanel.add(attackerButton);
		attackerButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		JButton defenderButton = new JButton("<html><center>"+"Buy as"+"<br>"+"defender"+"</center></html>");
		defenderButton.setSize((athletePanel.getWidth()/2) - 10, 50);
		defenderButton.setLocation(502, 581);
		singleAthletePanel.add(defenderButton);
		defenderButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		buyAthletes();
	    		}
	    });
		
		attackerButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		gameEnvironment.purchaseInitialAthlete(athlete, "Attacker");
	    		if (gameEnvironment.getTeamList().size() == 4) {
	    			singleAthletePanel.setVisible(false);
	    			gameDifficulty();
	    		} else {
	    			buyAthletes();
	    		}
	    		}
	    });
		
		defenderButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		gameEnvironment.purchaseInitialAthlete(athlete, "Defender");
	    		if (gameEnvironment.getTeamList().size() == 4) {
	    			singleAthletePanel.setVisible(false);
	    			gameDifficulty();
	    		} else {
	    			buyAthletes();
	    		}
	    		}
	    });
	}
	
	private void gameDifficulty() {
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setBackground(new Color(255, 255, 255));
		difficultyPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(difficultyPanel);
		difficultyPanel.setLayout(null);
		
		JButton normalButton = new JButton("Normal");
		normalButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		normalButton.setBounds(290, 321, 192, 48);
		difficultyPanel.add(normalButton);
		
		JButton hardButton = new JButton("Hard");
		hardButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		hardButton.setBounds(501, 321, 192, 48);
		difficultyPanel.add(hardButton);
		
		JLabel infoText = new JLabel("Choose a difficulty setting");
		infoText.setSize(403, 35);
		infoText.setLocation((width - infoText.getWidth())/2, 252);
		difficultyPanel.add(infoText);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		

		normalButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		difficultyPanel.setVisible(false);
	    		gameEnvironment.setGameDifficulty("Normal");
	    		finishSetup();
	    	}
		});
		
		hardButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		difficultyPanel.setVisible(false);
	    		gameEnvironment.setGameDifficulty("Hard");
	    		finishSetup();
	    	}
		});
		
	}
	
	private void finishSetup() {
		JPanel finishSetupPanel = new JPanel();
		finishSetupPanel.setBackground(new Color(255, 255, 255));
		finishSetupPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(finishSetupPanel);
		finishSetupPanel.setLayout(null);
		
		JLabel infoText = new JLabel("Great! Ready to start the game?");
		infoText.setSize(481, 35);
		infoText.setLocation((width - infoText.getWidth())/2, 59);
		finishSetupPanel.add(infoText);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton startButton = new JButton("Start Game");
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setBackground(new Color(0, 171, 58));
		startButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		startButton.setSize(168, 77);
		startButton.setLocation((width - startButton.getWidth())/2, 456);
		finishSetupPanel.add(startButton);
		
		JLabel playerInfo = new JLabel(gameEnvironment.toStringHTML());
		playerInfo.setHorizontalAlignment(SwingConstants.CENTER);
		playerInfo.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		playerInfo.setBounds(0, 123, 984, 322);
		finishSetupPanel.add(playerInfo);
		
		startButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		finishSetupPanel.setVisible(false);
				finishedWindow();
	    	}
		});

	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	
	//// JR NOTE: do i need this ???
	public void finishedWindow() {
		gameEnvironment.closeSetupScreen();
	}


		
	
}

