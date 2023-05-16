package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;

public class MainGame {
	
	int width = 984;
	int height = 711;
	
	private JFrame frame;
	private GameEnvironment gameEnvironment;

	public MainGame(GameEnvironment incomingGame) {
		gameEnvironment = incomingGame;
		gameEnvironment.startGame();
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		mainMenu();
		
	}
	
	private void mainMenu() {
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(new Color(255, 255, 255));
		mainMenuPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		int buttonHeights = 90;
		int buttonDistances = 130;
		
		JLabel mainMenuText = new JLabel("Main Menu");
		mainMenuText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		mainMenuText.setHorizontalAlignment(SwingConstants.CENTER);
		mainMenuText.setSize(329, 70);
		mainMenuText.setLocation(327, 29);
		mainMenuPanel.add(mainMenuText);
		
		JButton clubButton = new JButton("Go to the Club");
		clubButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		clubButton.setSize(367, buttonHeights);
		clubButton.setLocation((width - clubButton.getWidth())/2, 180);
		mainMenuPanel.add(clubButton);
		
		JButton stadiumButton = new JButton("Go to the Stadium");
		stadiumButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		stadiumButton.setSize(367, buttonHeights);
		stadiumButton.setLocation((width - stadiumButton.getWidth())/2, clubButton.getY() + buttonDistances);
		mainMenuPanel.add(stadiumButton);
		
		JButton marketButton = new JButton("Visit the Market");
		marketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				marketScreen();
			}
		});
		marketButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		marketButton.setSize(367, buttonHeights);
		marketButton.setLocation((width - marketButton.getWidth())/2, stadiumButton.getY() + buttonDistances);
		mainMenuPanel.add(marketButton);
		
		JButton byeButton = new JButton("Take a bye");
		byeButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		byeButton.setSize(367, buttonHeights);
		byeButton.setLocation((width - byeButton.getWidth())/2, marketButton.getY() + buttonDistances);
		mainMenuPanel.add(byeButton);
		
		JLabel playerBalance = new JLabel(gameEnvironment.getMoneyFormatted());
		playerBalance.setHorizontalAlignment(SwingConstants.CENTER);
		playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playerBalance.setBounds(655, 95, 329, 70);
		mainMenuPanel.add(playerBalance);
		
		JLabel weekValue = new JLabel("Week: " + gameEnvironment.getCurrentWeek() + " of " + gameEnvironment.getSeasonLength());
		weekValue.setHorizontalAlignment(SwingConstants.CENTER);
		weekValue.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekValue.setBounds(0, 101, 329, 59);
		mainMenuPanel.add(weekValue);
		
		clubButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		mainMenuPanel.setVisible(false);
	    		clubScreen();
	    		}
	    });
		
	}
	
	private void clubScreen() {

		int buttonHeights = 90;
		int buttonDistances = 130;
		
		JPanel clubMenuPanel = new JPanel();
		clubMenuPanel.setBackground(new Color(255, 255, 255));
		clubMenuPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(clubMenuPanel);
		clubMenuPanel.setLayout(null);
		
		JLabel clubText = new JLabel("CLUB");
		clubText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		clubText.setHorizontalAlignment(SwingConstants.CENTER);
		clubText.setSize(173, 70);
		clubText.setLocation((width - clubText.getWidth())/2, 29);
		clubMenuPanel.add(clubText);
		
		
		JButton teamPropertiesButton = new JButton("View team properties");
		teamPropertiesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		teamPropertiesButton.setSize(367, buttonHeights);
		teamPropertiesButton.setLocation((width - teamPropertiesButton.getWidth())/2, 250);
		clubMenuPanel.add(teamPropertiesButton);
		
		JButton inventoryButton = new JButton("View player inventory");
		inventoryButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		inventoryButton.setSize(367, buttonHeights);
		inventoryButton.setLocation((width - inventoryButton.getWidth())/2, teamPropertiesButton.getY() + buttonDistances);
		clubMenuPanel.add(inventoryButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		clubMenuPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		clubMenuPanel.setVisible(false);
	    		mainMenu();
	    		}
	    });
		
		teamPropertiesButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		clubMenuPanel.setVisible(false);
	    		teamPropertiesScreen();
	    		}
	    });
		
	}
	
	private void teamPropertiesScreen() {
		JPanel teamPropertiesPanel = new JPanel();
		teamPropertiesPanel.setBackground(new Color(255, 255, 255));
		teamPropertiesPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(teamPropertiesPanel);
		teamPropertiesPanel.setLayout(null);
		frame.add(teamPropertiesPanel);
		 
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		teamPropertiesPanel.add(backButton);

		
        JLabel teamNameText = new JLabel(gameEnvironment.getTeamName());
        teamNameText.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        teamNameText.setSize(450, 100);
        teamNameText.setLocation((width - teamNameText.getWidth())/2, 29);
        // Create a LineBorder with black color and 4 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        teamNameText.setBorder(border);
        teamPropertiesPanel.add(teamNameText);

                                
        JLabel teamText = new JLabel("Team");
        teamText.setHorizontalAlignment(SwingConstants.CENTER);
        teamText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        teamText.setBounds(155, 160, 55, 24);
        teamPropertiesPanel.add(teamText);
        
        
        JLabel reservesText = new JLabel("Reserves: " + gameEnvironment.getReservesList().size() + "/5");
        reservesText.setHorizontalAlignment(SwingConstants.LEFT);
        reservesText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        reservesText.setBounds(72, 425, 200, 24);
        teamPropertiesPanel.add(reservesText);
        
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setSize((athletePanelWidth + 20)*4 + 20, athletePanelHeight + 40);
		teamPanel.setLocation(155, 191);
		teamPanel.setBackground(Color.WHITE);
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		teamPropertiesPanel.add(teamPanel);

		JPanel reservesPanel = new JPanel();
		reservesPanel.setLayout(null);
		reservesPanel.setSize((athletePanelWidth + 20)*5 + 20, athletePanelHeight + 40);
		reservesPanel.setLocation((width - reservesPanel.getWidth())/2, 460);
		reservesPanel.setBackground(Color.WHITE);
		reservesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		teamPropertiesPanel.add(reservesPanel);

        
        
        int panelSpacing = 20; 
        int numAthletesPerRow = 4; 
        
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        for (int i = 0; i < gameEnvironment.getTeamList().size(); i++) {
            Athlete athlete = gameEnvironment.getTeamList().get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            teamPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
            if (athlete.getPosition() == "Attacker") {
            	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
            } else {
            	athletePanel.setBackground(new Color(255, 204, 204)); // light red

            }
            
            panels.add(athletePanel);


            JLabel athleteName = new JLabel(athlete.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
            // reduce athlete image by 50%
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(athlete.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
        }
        
        numAthletesPerRow = 5; 
        
        for (int i = 0; i < gameEnvironment.getReservesList().size(); i++) {
            Athlete athlete = gameEnvironment.getReservesList().get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            reservesPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
            panels.add(athletePanel);

            JLabel athleteName = new JLabel(athlete.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
            // reduce athlete image by 50%
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(athlete.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
        }

		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		teamPropertiesPanel.setVisible(false);
	    		clubScreen();
	    		}
	    });
		
	    /**
		* Go to singleAthleteView display depends on which of the athlete panels is selected
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            teamPropertiesPanel.setVisible(false);
		            if (index >= gameEnvironment.getTeamList().size()) {
		            	singleAthleteView(gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()));
		            } else {
		            	singleAthleteView(gameEnvironment.getTeamList().get(index));
		            }
		            
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
		
		// Checking whether athlete is a reserve or player - display and options depends on which type
		Boolean isReserve = gameEnvironment.athleteIsReserve(athlete);

        if (isReserve == false && athlete.getPosition() == "Attacker") {
        	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else if (isReserve == false && athlete.getPosition() == "Defender") {
        	athletePanel.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		athletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		athletePanel.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		athletePanel.add(athleteInfo);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		singleAthletePanel.add(backButton);
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.CENTER);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(width, 24);
        errorText.setLocation((width - errorText.getWidth())/2, 668);
		errorText.setForeground(new Color(255, 0, 0));
        singleAthletePanel.add(errorText);
        errorText.setVisible(false);
        
        JLabel resultText = new JLabel("");
        resultText.setHorizontalAlignment(SwingConstants.CENTER);
        resultText.setFont(new Font("Calibri", Font.PLAIN, 20));
        resultText.setSize(width, 24);
        resultText.setLocation((width - errorText.getWidth())/2, 668);
        resultText.setForeground(new Color(0, 255, 0));
        singleAthletePanel.add(resultText);
        resultText.setVisible(false);
        
        int buttonWidth = (athletePanel.getWidth()/2) - 10;
        int buttonHeight = 50;
        int buttonY = 590;
        int centreButtonX = (width - buttonWidth)/2;
        
		JButton swapButton = new JButton("<html><center>"+"Swap reserve"+"<br>"+"with player"+"</center></html>");
		swapButton.setSize(buttonWidth, buttonHeight);
		swapButton.setLocation(312, buttonY);
		singleAthletePanel.add(swapButton);
		swapButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		JButton setAthleteNameButton = new JButton("<html><center>"+"Set athlete"+"<br>"+"name"+"</center></html>");
		setAthleteNameButton.setSize(buttonWidth, buttonHeight);
		setAthleteNameButton.setLocation(502, buttonY);
		singleAthletePanel.add(setAthleteNameButton);
		setAthleteNameButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
        
		// If the athlete is not a reserve player, the swap athlete position button is added with corresponding action listener and athlete name and swap buttons are moved to make space for the new button
		if (isReserve == false) {
			swapButton.setText("<html><center>"+"Swap player"+"<br>"+"with reserve"+"</center></html>");
			
			JButton swapPositionButton = new JButton("<html><center>"+"Swap position"+"<br>"+"to " + athlete.getAlternatePosition() +"</center></html>");
			swapPositionButton.setSize(buttonWidth, buttonHeight);
			swapPositionButton.setLocation(centreButtonX, buttonY);
			singleAthletePanel.add(swapPositionButton);
			swapPositionButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			
			swapPositionButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent a) {
		    		athlete.setPosition(athlete.getAlternatePosition());
		    		resultText.setText("Athlete position updated");
	    			errorText.setVisible(false); 
	    			resultText.setVisible(true);
	    			swapPositionButton.setText("<html><center>"+"Swap position"+"<br>"+"to " + athlete.getAlternatePosition() +"</center></html>");
	    	        if (athlete.getPosition() == "Attacker") {
	    	        	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
	    	        } else {
	    	        	athletePanel.setBackground(new Color(255, 204, 204)); // light red
	    	        }
	    	        athleteInfo.setText(athlete.toStringHTML());
		    		}
		    });
			
			// Moves buttons to be on either sides of the swapPositionButton
			swapButton.setLocation(centreButtonX - buttonWidth - 20, buttonY);
			setAthleteNameButton.setLocation(centreButtonX + buttonWidth + 20, buttonY);
		}

		
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		teamPropertiesScreen();
	    		}
	    });
		
		// updates name of athlete to the input from JTextField - shows error text if name input does not meet requirements
		setAthleteNameButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			boolean nameChanged = athlete.setName(athleteName.getText());
	    			if (nameChanged == true) {
		    			resultText.setText("Athlete nickname updated");
		    			errorText.setVisible(false); 
		    			resultText.setVisible(true);
	    			}
	    		} catch (NameException e) {
	    			errorText.setText(e.getMessage());
	    			resultText.setVisible(false);
	    			errorText.setVisible(true); 
	    		}
	    		}
	    });
		

		
		// gives view of reserves to swap with player
		swapButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			gameEnvironment.checkSwappable();
		    		singleAthletePanel.setVisible(false);
		    		athleteSwapPanel(athlete);
		    		
	    		} catch(NoReserveAthletesException e) {
	    			errorText.setText(e.getMessage());
	    			errorText.setVisible(true);
	    		}
	    		
	    		}
	    });
		
	}
	
	public void athleteSwapPanel(Athlete athlete) {
		JPanel athleteSwapPanel = new JPanel();
		athleteSwapPanel.setBackground(new Color(255, 255, 255));
		athleteSwapPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(athleteSwapPanel);
		athleteSwapPanel.setLayout(null);
		
		JLabel swapText = new JLabel("Select a player to swap with");
		swapText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		swapText.setHorizontalAlignment(SwingConstants.CENTER);
		swapText.setSize(width, 70);
		swapText.setLocation((width - swapText.getWidth())/2, 29);
		athleteSwapPanel.add(swapText);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		athleteSwapPanel.add(backButton);
		
		// The athlete that you are wanting to swap
		
		JPanel mainAthletePanel = new JPanel();
		mainAthletePanel.setSize((int)(360*0.75), (int)(459*0.75));
		mainAthletePanel.setLocation((width - mainAthletePanel.getWidth())/2, 98);
		athleteSwapPanel.add(mainAthletePanel);
		mainAthletePanel.setLayout(null);

		Boolean isReserve = gameEnvironment.athleteIsReserve(athlete);

		if (isReserve == false && athlete.getPosition() == "Attacker") {
		    mainAthletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
		} else if (isReserve == false && athlete.getPosition() == "Defender") {
		    mainAthletePanel.setBackground(new Color(255, 204, 204)); // light red
		}

		JTextField mainAthleteName = new JTextField(athlete.getName());
		mainAthleteName.setFont(new Font("Cooper Black", Font.PLAIN, (int)(20*0.75)));
		mainAthleteName.setHorizontalAlignment(SwingConstants.CENTER);
		mainAthleteName.setBounds((int)(10*0.75), (int)(11*0.75), (int)(340*0.75), (int)(57*0.75));
		mainAthletePanel.add(mainAthleteName);
		mainAthleteName.setColumns(10);

		JPanel mainAthleteImagePanel = new JPanel();
		mainAthleteImagePanel.setBounds((int)(58*0.75), (int)(79*0.75), (int)(247*0.75), (int)(203*0.75));
		mainAthleteImagePanel.setBackground(Color.WHITE);
		mainAthletePanel.add(mainAthleteImagePanel);
		mainAthleteImagePanel.setLayout(new BorderLayout(0, 0));

		JLabel mainAthleteImage = new JLabel("");
		mainAthleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		mainAthleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		mainAthleteImagePanel.add(mainAthleteImage, BorderLayout.CENTER);

		JLabel mainAthleteInfo = new JLabel(athlete.toStringHTML());
		mainAthleteInfo.setFont(new Font("Calibiri", Font.BOLD, (int)(17*0.75)));
		mainAthleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		mainAthleteInfo.setBounds((int)(23*0.75), (int)(305*0.75), (int)(311*0.75), (int)(143*0.75));
		mainAthletePanel.add(mainAthleteInfo);

		
		
		// Reserves athletes displayed
		
        int panelSpacing = 20; 
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		// if we are swapping a reserve athlete with a player from the team list
		int numAthletesPerRow = 4; 
		ArrayList<Athlete> swappableList = gameEnvironment.getTeamList();
		
		// if we are swapping the player athlete with a reserve athlete
		if (isReserve == false) {
			swapText.setText("Select a reserve player to swap with");
			numAthletesPerRow = 5; 
			swappableList = gameEnvironment.getReservesList();
		}
		
		JPanel athletesPanel = new JPanel();
		athletesPanel.setLayout(null);
		athletesPanel.setSize((athletePanelWidth + 20)*numAthletesPerRow + 20, athletePanelHeight + 40);
		athletesPanel.setLocation((width - athletesPanel.getWidth())/2, 460);
		athletesPanel.setBackground(Color.WHITE);
		athletesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		athleteSwapPanel.add(athletesPanel);
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
		
        for (int i = 0; i < swappableList.size(); i++) {
            Athlete swappable = swappableList.get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            athletesPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
            panels.add(athletePanel);
            
            if (isReserve == true && swappable.getPosition() == "Attacker") {
            	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
            } else if (isReserve == true && swappable.getPosition() == "Defender") {
            	athletePanel.setBackground(new Color(255, 204, 204)); // light red

            }

            JLabel athleteName = new JLabel(swappable.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);
            

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + swappable.getImageName() + ".png"));
            // reduce athlete image by 50%
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(swappable.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
        }
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		athleteSwapPanel.setVisible(false);
	    		singleAthleteView(athlete);
	    		}
	    });
		
	    /**
		* Select athlete to swap with
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	athleteSwapPanel.setVisible(false);
		            if (isReserve == false) {
		            	Athlete swappedAthlete = (gameEnvironment.getReservesList().get(index));
		            	gameEnvironment.swapAthletes(athlete, swappedAthlete);
		            } else {
		            	Athlete swappedAthlete = (gameEnvironment.getTeamList().get(index));
		            	gameEnvironment.swapAthletes(swappedAthlete, athlete);
		            }
		            teamPropertiesScreen();
		        }
		    });
		}
		
	}
	

	
	private void marketScreen() {
		
		JPanel marketpanel = new JPanel();
		marketpanel.setBackground(new Color(255, 255, 255));
		marketpanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(marketpanel);
		marketpanel.setLayout(null);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		marketpanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marketpanel.setVisible(false);
				mainMenu();
			}
		});
		
		
		JLabel marketlabel = new JLabel("Market");
		marketlabel.setHorizontalAlignment(SwingConstants.CENTER);
		marketlabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		marketlabel.setBounds(260, 12, 459, 48);
		marketpanel.add(marketlabel);
		
		JButton sellButton = new JButton("SELL");
		sellButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		sellButton.setBounds(72, 325, 267, 111);
		marketpanel.add(sellButton);
		
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marketpanel.setVisible(false);
				sellScreen();
			}
		});
		
		
			
		
		
		JButton Buyplayerbutton = new JButton("BUY PLAYER");
		Buyplayerbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		Buyplayerbutton.setBounds(626, 325, 267, 111);
		marketpanel.add(Buyplayerbutton);

		Buyplayerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marketpanel.setVisible(false);
				buyPlayerScreen();
			}
		});
		
		JButton buyItemButton = new JButton("BUY ITEM");
		buyItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		buyItemButton.setBounds(349, 325, 267, 111);
		marketpanel.add(buyItemButton);
		buyItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marketpanel.setVisible(false);
				buyItemScreen();
				
			}
		});
		
		JLabel moneyLabel = new JLabel(gameEnvironment.getMoneyFormatted());
		moneyLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		moneyLabel.setBounds(121, 9, 201, 48);
		marketpanel.add(moneyLabel);
		
	
		
		
	}
	
	
	public void buyItemScreen() {
		JPanel itemDisplayPanel = new JPanel();
		itemDisplayPanel.setBackground(new Color(255, 255, 255));
		itemDisplayPanel.setBounds(-76, 0, width, height);
		frame.getContentPane().add(itemDisplayPanel);
		itemDisplayPanel.setLayout(null);
		frame.getContentPane().add(itemDisplayPanel);
		 
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(108, 11, 81, 48);
		itemDisplayPanel.add(backButton);

		
        JLabel buyItemText = new JLabel("Buy Items");
        buyItemText.setHorizontalAlignment(SwingConstants.CENTER);
        buyItemText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        buyItemText.setSize(450, 100);
        buyItemText.setLocation((width - buyItemText.getWidth())/2, 29);
        // Create a LineBorder with black color and 4 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        buyItemText.setBorder(border);
        itemDisplayPanel.add(buyItemText);

                                
        JLabel ItemsText = new JLabel("Items");
        ItemsText.setHorizontalAlignment(SwingConstants.CENTER);
        ItemsText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        ItemsText.setBounds(155, 160, 81, 24);
        itemDisplayPanel.add(ItemsText);
        
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		JPanel ItemPanel = new JPanel();
		ItemPanel.setLayout(null);
		ItemPanel.setSize(677, 223);
		ItemPanel.setLocation(154, 191);
		ItemPanel.setBackground(Color.WHITE);
		ItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		itemDisplayPanel.add(ItemPanel);

        
        
        int panelSpacing = 20; 
        int numAthletesPerRow = 5; 
        
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        ArrayList<Item> shopItems = gameEnvironment.getShopItems();
        for (int i = 0; i < shopItems.size(); i++) {
            final Item item = shopItems.get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            ItemPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
           
            
            panels.add(athletePanel);


            JLabel athleteName = new JLabel(item.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            

            
            // reduce athlete image by 50%
            
                
            JLabel athleteInfo = new JLabel(item.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setVerticalAlignment(SwingConstants.TOP); // Align text to the top
            athleteInfo.setVerticalTextPosition(SwingConstants.TOP);
            athleteInfo.setBounds(4, 40, 136, 200);
            athletePanel.add(athleteInfo);
        }
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	itemDisplayPanel.setVisible(false);
		        	buySingleItemScreen(shopItems.get(index));
		        }
		    });
		}
		
		
	}
	
	public void buySingleItemScreen(Item item) {
		
		
			
			JPanel BuySingleItemPanel = new JPanel();
			BuySingleItemPanel.setBackground(new Color(255, 255, 255));
			BuySingleItemPanel.setBounds(0, 0, width, height);
			frame.getContentPane().add(BuySingleItemPanel);
			BuySingleItemPanel.setLayout(null);
			
			JPanel BuyItemPanel = new JPanel();
			BuyItemPanel.setSize(360, 459);
			BuyItemPanel.setLocation(312, 98);
			BuySingleItemPanel.add(BuyItemPanel);
			BuyItemPanel.setLayout(null);
			
			JTextField athleteName = new JTextField(item.getName());
			athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			athleteName.setHorizontalAlignment(SwingConstants.CENTER);
			athleteName.setBounds(10, 11, 340, 57);
			BuyItemPanel.add(athleteName);
			athleteName.setColumns(10);
			
			JLabel athleteInfo = new JLabel(item.toStringHTML());
			athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
			athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
			athleteInfo.setBounds(23, 305, 311, 143);
			BuyItemPanel.add(athleteInfo);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setBounds(23, 128, 311, 281);
			BuyItemPanel.add(lblNewLabel);
			
			JLabel errorText = new JLabel("");
			errorText.setHorizontalAlignment(SwingConstants.CENTER);
			errorText.setBounds(236, 564, 567, 26);
			BuySingleItemPanel.add(errorText);
			errorText.setForeground(new Color(255, 0, 0));
			 errorText.setVisible(false);
			
			JButton buySingleItemButton = new JButton("Buy");
			buySingleItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			buySingleItemButton.setBounds(392, 600, 166, 48);
			BuySingleItemPanel.add(buySingleItemButton);
			buySingleItemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						try {
							gameEnvironment.purchaseItem(item);
						} catch (InventoryFullException e1) {
							// TODO Auto-generated catch block
							errorText.setText(e1.getMessage());
			    			errorText.setVisible(true);
			    			return;
						}
					} catch (InsufficientFundsException e1) {
						// TODO Auto-generated catch block
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
					}
					BuySingleItemPanel.setVisible(false);
					marketScreen();
					return;
					
				}
			});
			
			JButton backButton = new JButton("Back");
			backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			backButton.setBounds(10, 11, 81, 48);
			BuySingleItemPanel.add(backButton);
			
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuySingleItemPanel.setVisible(false);
					marketScreen();
					
					
				}
			});
		
	}
		
	

	public void  buyPlayerScreen(){
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		
		JPanel BuyPlayerPanel = new JPanel();
		BuyPlayerPanel.setBackground(new Color(255, 255, 255));
		BuyPlayerPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(BuyPlayerPanel);
		BuyPlayerPanel.setLayout(null);
		
		JLabel BuyPlayerLabel = new JLabel("Wich Player Do You Want To Buy");
		BuyPlayerLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		BuyPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BuyPlayerLabel.setBounds(271, 11, 531, 60);
		BuyPlayerPanel.add(BuyPlayerLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyPlayerPanel.setVisible(false);
				marketScreen();
				
			}
		});
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backButton.setBounds(10, 11, 129, 46);
		BuyPlayerPanel.add(backButton);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setSize((athletePanelWidth + 20)*4 + 20, athletePanelHeight + 40);
		teamPanel.setLocation(155, 191);
		teamPanel.setBackground(Color.WHITE);
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		BuyPlayerPanel.add(teamPanel);
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		int panelSpacing = 20; 
		int numAthletesPerRow = 5;
        
		ArrayList<Athlete> shopAthletes = gameEnvironment.getShopAthletes();
		for (int i = 0; i < shopAthletes.size(); i++) {
		    Athlete athlete = shopAthletes.get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            teamPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
            if (athlete.getPosition() == "Attacker") {
            	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
            } else {
            	athletePanel.setBackground(new Color(255, 204, 204)); // light red

            }
            
            panels.add(athletePanel);


            JLabel athleteName = new JLabel(athlete.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
            // reduce athlete image by 50%
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(athlete.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
            
            JButton BuyButton = new JButton("Buy");
    		BuyButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				BuyPlayerPanel.setVisible(false);
    				BuySingleAthletePanel(athlete);
    			}
    		});
        }
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	BuyPlayerPanel.setVisible(false);
		        	BuySingleAthletePanel(shopAthletes.get(index));
		        }
		    });
		}
	}
	
	public void BuySingleAthletePanel(Athlete athlete) {
		JPanel BuySingleAthletePanel = new JPanel();
		BuySingleAthletePanel.setBackground(new Color(255, 255, 255));
		BuySingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(BuySingleAthletePanel);
		BuySingleAthletePanel.setLayout(null);
		
		JPanel BuyAthletePanel = new JPanel();
		BuyAthletePanel.setSize(360, 459);
		BuyAthletePanel.setLocation(312, 98);
		BuySingleAthletePanel.add(BuyAthletePanel);
		BuyAthletePanel.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	BuyAthletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	BuyAthletePanel.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		BuyAthletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		BuyAthletePanel.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		BuyAthletePanel.add(athleteInfo);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		BuySingleAthletePanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marketScreen();
				BuySingleAthletePanel.setVisible(false);
				
				
			}
		});
		
		
		
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(364, 24);
        errorText.setLocation((width - errorText.getWidth())/2, 668);
		errorText.setForeground(new Color(255, 0, 0));
        BuySingleAthletePanel.add(errorText);
        errorText.setVisible(false);
		
		JButton BuyButton = new JButton("Buy");
		BuyButton.setSize((BuyAthletePanel.getWidth()/2) - 10, 50);
		BuyButton.setLocation(402, 568);
		BuySingleAthletePanel.add(BuyButton);
		BuyButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		BuyButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (gameEnvironment.getTeamList().size() < 4) {
					BuySingleAthletePanel.setVisible(false);
					BuySingleAthleteSetPostion(athlete);
					
					
					
				}else {
					try {
						gameEnvironment.purchaseAthlete(athlete, null);
						
					} catch (InsufficientFundsException e1) {
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
					} catch (ReservesLimitException e1) {
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
					}
					BuySingleAthletePanel.setVisible(false);
					teamPropertiesScreen();
					
					
				}
				
					
				
			}
		});
		
		
		
		
	}
	
	public void BuySingleAthleteSetPostion(Athlete athlete) {
		JPanel BuySingleAthletePanel = new JPanel();
		BuySingleAthletePanel.setBackground(new Color(255, 255, 255));
		BuySingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(BuySingleAthletePanel);
		BuySingleAthletePanel.setLayout(null);
		
		JPanel BuySingleAthletePanel1 = new JPanel();
		BuySingleAthletePanel1.setSize(360, 459);
		BuySingleAthletePanel1.setLocation(312, 98);
		BuySingleAthletePanel.add(BuySingleAthletePanel1);
		BuySingleAthletePanel1.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	BuySingleAthletePanel1.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	BuySingleAthletePanel1.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		BuySingleAthletePanel1.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		BuySingleAthletePanel1.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		BuySingleAthletePanel1.add(athleteInfo);
		
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		BuySingleAthletePanel.add(backButton);
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(532, 48);
        errorText.setLocation(230, 644);
		errorText.setForeground(new Color(255, 0, 0));
        BuySingleAthletePanel.add(errorText);
        
        JButton buyPlayerSetDefButton = new JButton("Set As Deffender");
        buyPlayerSetDefButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					gameEnvironment.purchaseAthlete(athlete, "Defender");
				} catch (InsufficientFundsException e1) {
					errorText.setText(e1.getMessage());
	    			errorText.setVisible(true);
	    			return;
				} catch (ReservesLimitException e1) {
					errorText.setText(e1.getMessage());
	    			errorText.setVisible(true);
	    			return;
					
				}
        		BuySingleAthletePanel.setVisible(false);
        		teamPropertiesScreen();
				return;
        	}
        });
        buyPlayerSetDefButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        buyPlayerSetDefButton.setBounds(291, 568, 205, 65);
        BuySingleAthletePanel.add(buyPlayerSetDefButton);
        
        JButton buyPlayerSetAtkButton = new JButton("Set As Attacker");
        buyPlayerSetAtkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					gameEnvironment.purchaseAthlete(athlete, "Attacker");
				} catch (InsufficientFundsException e1) {
					errorText.setText(e1.getMessage());
	    			errorText.setVisible(true);
	    			return;
					// TODO Auto-generated catch block
					
				} catch (ReservesLimitException e1) {
					errorText.setText(e1.getMessage());
	    			errorText.setVisible(true);
	    			return;
					// TODO Auto-generated catch block
					
				}
        		BuySingleAthletePanel.setVisible(false);
        		teamPropertiesScreen();
				return;
        		
        	}});
        
        buyPlayerSetAtkButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        buyPlayerSetAtkButton.setBounds(539, 568, 205, 65);
        BuySingleAthletePanel.add(buyPlayerSetAtkButton);
        errorText.setVisible(false);
		
	}
	
	
		
	
	
	private void sellScreen(){
		JPanel sellScreenpannel = new JPanel();
		sellScreenpannel.setBackground(new Color(255, 255, 255));
		sellScreenpannel.setBounds(0, 0, width, height);
		frame.getContentPane().add(sellScreenpannel);
		sellScreenpannel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("What Do You Want To Sell");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(265, 11, 364, 51);
		sellScreenpannel.add(lblNewLabel);
		
		JButton sellPlayer = new JButton("SellPlayer");
		sellPlayer.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		sellPlayer.setBounds(297, 107, 308, 122);
		sellScreenpannel.add(sellPlayer);
		
		sellPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellScreenpannel.setVisible(false);
				sellPlayerScreen();
			}
		});
		
		
		JButton sellItemButton = new JButton("SellItem");
		sellItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		sellItemButton.setBounds(297, 264, 308, 122);
		sellScreenpannel.add(sellItemButton);
		
		sellItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellScreenpannel.setVisible(false);
				sellItemspanel();
			}
		});
		
		
		
			
		
		
		JButton backbutton = new JButton("Back");
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellScreenpannel.setVisible(false);
				marketScreen();
			}
		});
		backbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backbutton.setBounds(10, 11, 141, 51);
		sellScreenpannel.add(backbutton);
		
		
		
	}
	public void sellPlayerScreen() {
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		JPanel sellPlayerPanel = new JPanel();
		sellPlayerPanel.setBackground(new Color(255, 255, 255));
		sellPlayerPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(sellPlayerPanel);
		sellPlayerPanel.setLayout(null);
		
		JLabel sellPlayerLabel = new JLabel("Wich Player Do You Want To Sell");
		sellPlayerLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		sellPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellPlayerLabel.setBounds(271, 11, 531, 60);
		sellPlayerPanel.add(sellPlayerLabel);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellPlayerPanel.setVisible(false);
				marketScreen();
			}
		});
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backButton.setBounds(10, 11, 129, 46);
		sellPlayerPanel.add(backButton);
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setSize((athletePanelWidth + 20)*4 + 20, athletePanelHeight + 40);
		teamPanel.setLocation(155, 191);
		teamPanel.setBackground(Color.WHITE);
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sellPlayerPanel.add(teamPanel);
		
		JPanel reservesPanel = new JPanel();
		reservesPanel.setLayout(null);
		reservesPanel.setSize((athletePanelWidth + 20)*5 + 20, athletePanelHeight + 40);
		reservesPanel.setLocation((width - reservesPanel.getWidth())/2, 460);
		reservesPanel.setBackground(Color.WHITE);
		reservesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sellPlayerPanel.add(reservesPanel);
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		int panelSpacing = 20; 
		int numAthletesPerRow = 4;
        
        for (int i = 0; i < gameEnvironment.getTeamList().size(); i++) {
            Athlete athlete = gameEnvironment.getTeamList().get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            teamPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
            if (athlete.getPosition() == "Attacker") {
            	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
            } else {
            	athletePanel.setBackground(new Color(255, 204, 204)); // light red

            }
            
            panels.add(athletePanel);


            JLabel athleteName = new JLabel(athlete.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
            // reduce athlete image by 50%
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(athlete.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
        }
            
            numAthletesPerRow = 5; 
            
            for (int i = 0; i < gameEnvironment.getReservesList().size(); i++) {
                Athlete athlete = gameEnvironment.getReservesList().get(i);
                
                // evenly spaces athletePanels on the teamPanel
                JPanel athletePanel = new JPanel();
                athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                       (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                       athletePanelWidth, athletePanelHeight);
                reservesPanel.add(athletePanel);
                athletePanel.setLayout(null);
                
                panels.add(athletePanel);

                JLabel athleteName = new JLabel(athlete.getName());
                athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
                athleteName.setHorizontalAlignment(SwingConstants.CENTER);
                athleteName.setBounds(4, 4, 136, 23);
                athleteName.setOpaque(true);
                athleteName.setBackground(Color.WHITE);
                athletePanel.add(athleteName);

                JPanel athleteImagePanel = new JPanel();
                athleteImagePanel.setSize(82, 66);
                athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
                athleteImagePanel.setBackground(Color.WHITE);
                athletePanel.add(athleteImagePanel);
                athleteImagePanel.setLayout(new BorderLayout(0, 0));

                ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
                // reduce athlete image by 50%
                Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); 
                JLabel athleteImage = new JLabel(new ImageIcon(img));
                athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
                athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                    
                JLabel athleteInfo = new JLabel(athlete.toStringHTML());
                athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
                athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
                athleteInfo.setBounds(4, 98, 136, 85);
                athletePanel.add(athleteInfo);
            }
            
            
        
            for (int i = 0; i < panels.size(); i++) {
    			final int index = i;
    		    JPanel panel = panels.get(i);
    		    panel.addMouseListener(new MouseAdapter() {
    		        @Override
    		        public void mouseClicked(MouseEvent e) {
    		        	sellPlayerPanel.setVisible(false);
    		            if (index >= gameEnvironment.getTeamList().size()) {
    		            	sellSingleReserveView(gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()));
    		            } else {
    		            	sellSingleAthleteView(gameEnvironment.getTeamList().get(index));
    		            }
    		            
    		        }
    		    });
    		}
		
	}
	
	public void sellSingleAthleteView(Athlete athlete) {
		JPanel sellSingleAthletePanel = new JPanel();
		sellSingleAthletePanel.setBackground(new Color(255, 255, 255));
		sellSingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(sellSingleAthletePanel);
		sellSingleAthletePanel.setLayout(null);
		
		JPanel sellAthletePanel = new JPanel();
		sellAthletePanel.setSize(360, 459);
		sellAthletePanel.setLocation(312, 98);
		sellSingleAthletePanel.add(sellAthletePanel);
		sellAthletePanel.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	sellAthletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	sellAthletePanel.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		sellAthletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		sellAthletePanel.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		sellAthletePanel.add(athleteInfo);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		sellSingleAthletePanel.add(backButton);
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(364, 24);
        errorText.setLocation((width - errorText.getWidth())/2, 668);
		errorText.setForeground(new Color(255, 0, 0));
        sellSingleAthletePanel.add(errorText);
        errorText.setVisible(false);
		
		JButton sellButton = new JButton("Sell");
		sellButton.setSize((sellAthletePanel.getWidth()/2) - 10, 50);
		sellButton.setLocation(402, 568);
		sellSingleAthletePanel.add(sellButton);
		sellButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		sellSingleAthletePanel.setVisible(false);
	    		marketScreen();
	    		}
	    });
		/// bug money not updating
		sellButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.sellPlayer(athlete);
	    		sellSingleAthletePanel.setVisible(false);
	    		marketScreen();
	    		}
	    });
	}
	
	
	public void sellSingleReserveView(Athlete athlete) {
		JPanel sellSingleAthletePanel = new JPanel();
		sellSingleAthletePanel.setBackground(new Color(255, 255, 255));
		sellSingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(sellSingleAthletePanel);
		sellSingleAthletePanel.setLayout(null);
		
		JPanel sellAthletePanel = new JPanel();
		sellAthletePanel.setSize(360, 459);
		sellAthletePanel.setLocation(312, 98);
		sellSingleAthletePanel.add(sellAthletePanel);
		sellAthletePanel.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	sellAthletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	sellAthletePanel.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		sellAthletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		sellAthletePanel.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		sellAthletePanel.add(athleteInfo);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		sellSingleAthletePanel.add(backButton);
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(364, 24);
        errorText.setLocation((width - errorText.getWidth())/2, 668);
		errorText.setForeground(new Color(255, 0, 0));
        sellSingleAthletePanel.add(errorText);
        errorText.setVisible(false);
		
		JButton sellButton = new JButton("Sell");
		sellButton.setSize((sellAthletePanel.getWidth()/2) - 10, 50);
		sellButton.setLocation(402, 568);
		sellSingleAthletePanel.add(sellButton);
		sellButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		sellSingleAthletePanel.setVisible(false);
	    		marketScreen();
	    		}
	    });
		/// bug money not updating
		sellButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.sellReservePlayer(athlete);
	    		sellSingleAthletePanel.setVisible(false);
	    		marketScreen();
	    		}
	    });
	}
	
	public void sellItemspanel() {
		JPanel sellItemDisplayPanel = new JPanel();
		sellItemDisplayPanel.setBackground(new Color(255, 255, 255));
		sellItemDisplayPanel.setBounds(-76, 0, width, height);
		frame.getContentPane().add(sellItemDisplayPanel);
		sellItemDisplayPanel.setLayout(null);
		frame.getContentPane().add(sellItemDisplayPanel);
		 
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItemDisplayPanel.setVisible(false);
				marketScreen();
			}
		});
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(108, 11, 81, 48);
		sellItemDisplayPanel.add(backButton);

		
        JLabel SellItemText = new JLabel("Sell Items");
        SellItemText.setHorizontalAlignment(SwingConstants.CENTER);
        SellItemText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        SellItemText.setSize(450, 100);
        SellItemText.setLocation((width - SellItemText.getWidth())/2, 29);
        // Create a LineBorder with black color and 4 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        SellItemText.setBorder(border);
        sellItemDisplayPanel.add(SellItemText);

                                
        JLabel ItemsText = new JLabel("Items");
        ItemsText.setHorizontalAlignment(SwingConstants.CENTER);
        ItemsText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        ItemsText.setBounds(155, 160, 81, 24);
        sellItemDisplayPanel.add(ItemsText);
        
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		JPanel sellItemPanel = new JPanel();
		sellItemPanel.setLayout(null);
		sellItemPanel.setSize(677, 223);
		sellItemPanel.setLocation(154, 191);
		sellItemPanel.setBackground(Color.WHITE);
		sellItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sellItemDisplayPanel.add(sellItemPanel);

        
        
        int panelSpacing = 20; 
        int numAthletesPerRow = 5; 
        
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        ArrayList<Item> inventoryItems = gameEnvironment.getInventory();
        for (int i = 0; i < inventoryItems.size(); i++) {
            final Item item = inventoryItems.get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            sellItemPanel.add(athletePanel);
            athletePanel.setLayout(null);
            
           
            
            panels.add(athletePanel);


            JLabel athleteName = new JLabel(item.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athleteName.setOpaque(true);
            athleteName.setBackground(Color.WHITE);
            athletePanel.add(athleteName);

            

            
            // reduce athlete image by 50%
            
                
            JLabel athleteInfo = new JLabel(item.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setVerticalAlignment(SwingConstants.TOP); // Align text to the top
            athleteInfo.setVerticalTextPosition(SwingConstants.TOP);
            athleteInfo.setBounds(4, 40, 136, 200);
            athletePanel.add(athleteInfo);
        }
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	sellItemDisplayPanel.setVisible(false);
		        	sellSingleItemScreen(inventoryItems.get(index));
		        }
		    });
		}
		
		
	}
	
	public void sellSingleItemScreen(Item item) {
		JPanel BuySingleItemPanel = new JPanel();
		BuySingleItemPanel.setBackground(new Color(255, 255, 255));
		BuySingleItemPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(BuySingleItemPanel);
		BuySingleItemPanel.setLayout(null);
		
		JPanel BuyItemPanel = new JPanel();
		BuyItemPanel.setSize(360, 459);
		BuyItemPanel.setLocation(312, 98);
		BuySingleItemPanel.add(BuyItemPanel);
		BuyItemPanel.setLayout(null);
		
		JTextField athleteName = new JTextField(item.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		BuyItemPanel.add(athleteName);
		athleteName.setColumns(10);
		
		JLabel athleteInfo = new JLabel(item.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		BuyItemPanel.add(athleteInfo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(23, 128, 311, 281);
		BuyItemPanel.add(lblNewLabel);
		
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setBounds(236, 564, 567, 26);
		BuySingleItemPanel.add(errorText);
		errorText.setForeground(new Color(255, 0, 0));
		 errorText.setVisible(false);
		
		JButton SellSingleItemButton = new JButton("Sell");
		SellSingleItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		SellSingleItemButton.setBounds(392, 600, 166, 48);
		BuySingleItemPanel.add(SellSingleItemButton);
		SellSingleItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.sellItem(item);
				BuySingleItemPanel.setVisible(false);
				marketScreen();
				
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		BuySingleItemPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuySingleItemPanel.setVisible(false);
				marketScreen();
				
				
			}
		});
		
	}
	

}

		
		
		
		


		
		
	
	
	
	



