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
import java.util.Map;

public class MainGame {
	
	// The following integers are used throughout MainGame for consistency in sizing and placement of the user interface.
	/**
	 * The width for all full-sized panels within the JFrame
	 */
	int width = 984;
	
	/**
	 * The height for all full-sized panels within the JFrame
	 */
	int height = 711;
	
	/**
	 * The width for small-sized athlete panel cards to be used on screens with multiple athletes.
	 */
	int smallAthletePanelWidth = 144;
	
	/**
	 * The height for small-sized athlete panel cards to be used on screens with multiple athletes.
	 */
	int smallAthletePanelHeight = 183;
	
	/**
	 * The width for athlete panel cards to be used on screens with a singular athlete.
	 */
	int athletePanelWidth = 360;
	
	/**
	 * The height for athlete panel cards to be used on screens with a singular athlete.
	 */
	int athletePanelHeight = 459;
	
	/**
	 * The width for buttons on a screen showing an athlete player card.
	 */
    int buttonWidth = (athletePanelWidth/2) - 10;
    
	/**
	 * The height for buttons on a screen showing an athlete player card.
	 */
    int buttonHeight = 50;
    
	/**
	 * The Y-coordinate for buttons on a screen showing an athlete player card.
	 */
    int buttonY = 590;
    
	/**
	 * The X-coordinate for the central button on a screen showing an athlete player card.
	 */
    int centreButtonX = (width - buttonWidth)/2;
    
	/**
	 * The Y-coordinate for the team panel with athlete cards on a screen with a full team displayed.
	 */
    int teamPanelY = 191;
    
	/**
	 * The Y-coordinate for the reserves panel with athlete cards on a screen with a full team displayed.
	 */
    int reservesPanelY = 460;
    
	/**
	 * The maximum number of athletes allowed to be players in a team.
	 */
    int maxAthletesInTeam = 4;
	/**
	 * The maximum number of reserves allowed in a team.
	 */
    int maxAthletesInReserve = 5;
    
	/**
	 * The main frame of the game.
	 */
	private JFrame frame;
	
	/**
	 * The game environment that holds the state and all game functionality.
	 */
	private GameEnvironment gameEnvironment;
	
	////private JTextField athleteNameTextField; JR REMOVE THIS
	
	/**
	 * Constructs a new instance of the MainGame class with the incoming GameEnvironment.
	 * It initializes the game environment, starts the game, initializes the main JFrame, and makes the frame visible.
	 *
	 * @param incomingGame the GameEnvironment instance for the game
	 */
	public MainGame(GameEnvironment incomingGame) {
		gameEnvironment = incomingGame;
		gameEnvironment.startGame();
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Initializes the main JFrame and sets its properties, including the background color, size, layout.
	 * It also calls the mainMenu method to set up the main menu screen.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		mainMenu();
		
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
	 * Creates a JLabel with a specified text input for titles used frequently in the MainGame.
	 *
	 * @param input the text to be displayed as the title
	 * @return the JLabel with the specified title text
	 */
	private JLabel createTitleText(String input) {
		JLabel titleText = new JLabel(input);
		titleText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		titleText.setSize(width, 70);
		titleText.setLocation(0, 29);
		return titleText;
	}
	
	/**
	 * Creates a JLabel for error text which is blank and not visible
	 *
	 *@return the JLabel which will show the error text
	 */
	private JLabel createErrorText() {
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.CENTER);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(width, 24);
        errorText.setLocation(0, 668);
		errorText.setForeground(new Color(255, 0, 0));
        errorText.setVisible(false);
        
        return errorText;
	}
	
	/**
	 * Creates a JLabel for results text which is blank and not visible
	 *
	 *@return the JLabel which will show the result of the action
	 */
	private JLabel createResultText() {
        JLabel resultText = new JLabel("");
        resultText.setHorizontalAlignment(SwingConstants.CENTER);
        resultText.setFont(new Font("Calibri", Font.PLAIN, 20));
        resultText.setSize(width, 24);
        resultText.setLocation(0, 668);
        resultText.setForeground(new Color(0, 255, 0));
        resultText.setVisible(false);
        
        return resultText;
	}
	
	/**
	 * Creates a new JButton which will be located at an inputed x-coordinate and the y-coordinate will be centered below the single athlete player card
	 *
	 *@return the JButton that will be positioned below the single athlete player card
	 */
	private JButton createButtonBelowAthleteCard(int buttonX) {
		JButton button = new JButton("");
		button.setSize(buttonWidth, buttonHeight);
		button.setLocation(buttonX, buttonY);
		button.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		return button;
	}
	
	/**
	 * Creates a JButton with the text "Back" to be used throughout the MainGame.
	 *
	 * @return the "Back" JButton
	 */
	private JButton createBackButton() {
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		return backButton;
	}
	
	/**
	 * Creates a JPanel to be sized to hold a specific number of athletes at an inputed value for the y-coordinate.
	 *
	 *@param numberOfAthletes the number of athletes that will be added to the teamPanel so that the panel is wide enough to fit all athlete cards.
	 *@param yValue the value for the Y-coordinate for the athletePanel
	 * @return the JPanel to hold a specific number of athlete cards at a designated height
	 */
	private JPanel createTeamPanel(int numberOfAthletes, int yValue) {
		int panelWidth = ((smallAthletePanelWidth + 20) * numberOfAthletes) + 20;
		int panelHeight = smallAthletePanelHeight + 40;
		int panelX = (width - panelWidth)/2;
				
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setSize(panelWidth, panelHeight);
		teamPanel.setLocation(panelX, yValue);
		teamPanel.setBackground(Color.WHITE);
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		return teamPanel;
	}
	
	/**
	 * Adds athlete cards from inputed athletes to a selected JPanel and returns the list of JPanels of each athlete card.
	 *
	 *@param pannel as JPanel to add the athlete cards to.
	 *@param athletes as ArrayList<Athlete> to create the athlete cards for
	 *@param numAthletesPerRow the number of athlete cards that should be able to fit on the panel
	 * @return an ArrayList of JPanels of each athlete card panel
	 */
	private ArrayList<JPanel> addAthletesToPanel(JPanel panel, ArrayList<Athlete> athletes, int numAthletesPerRow) {
        int panelSpacing = 20; 
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
        ArrayList<JPanel> panels = new ArrayList<JPanel>();

        for (int i = 0; i < athletes.size(); i++) {
            Athlete athlete = athletes.get(i);
            
            // evenly spaces athletePanels on the teamPanel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            panel.add(athletePanel);
            athletePanel.setLayout(null);
            
            if (athlete.getPosition() == "Attacker") {
            	athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
            } else if (athlete.getPosition() == "Defender"){
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
        
        return panels;
		
        
	}
	
	private void refreshAthletePanel(JPanel athletePanel, Athlete athlete, double size) {
		
	    athletePanel.removeAll(); // Clear the existing components

	    if (athlete.getPosition().equals("Attacker")) {
	        athletePanel.setBackground(new Color(173, 216, 230)); // sets background color to light blue
	    } else if (athlete.getPosition().equals("Defender")) {
	        athletePanel.setBackground(new Color(255, 204, 204)); // light red
	    }

	    JLabel athleteName = new JLabel(athlete.getName());
	    athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    athleteName.setHorizontalAlignment(SwingConstants.CENTER);
	    athleteName.setBackground(Color.WHITE); 
	    athleteName.setOpaque(true); 
	    athleteName.setBounds(10, 11, 340, 57);
	    athletePanel.add(athleteName);

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

	    athletePanel.revalidate();
	    athletePanel.repaint();
	    
	}

	
	
	
	/**
	 * Displays the main menu screen with buttons for different actions and the current week, player balance and points earned.
	 * The buttons include options to go to the club, stadium, market, and take a bye.
	 * If it is the final week of the season, the "Take a bye" button is updated to "End Season".
	 * Each button is assigned an ActionListener to take the user to the selected screen.
	 */
	private void mainMenu() {
		
		int buttonHeight = 90;
		int buttonSpacing = 130;
		int buttonWidth = 367;
		int buttonX = (width - buttonWidth)/2;
		int buttonY = 180;
		int textBoxWidth = (width - buttonWidth)/2;
		int textBoxHeight = 70;
		int textX = (width + buttonWidth) / 2;
		int textY = 116;
		
		JPanel mainMenuPanel = createScreenPanel();
		frame.getContentPane().add(mainMenuPanel);
		
		JLabel mainMenuText = createTitleText("Main Menu");
		mainMenuPanel.add(mainMenuText);
		
		JLabel pointsBalance = new JLabel("" + gameEnvironment.getPlayerPoints() + " points");
		pointsBalance.setHorizontalAlignment(SwingConstants.CENTER);
		pointsBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		pointsBalance.setSize(textBoxWidth/2, textBoxHeight);
		pointsBalance.setLocation(textX + textBoxWidth/2, textY);
		mainMenuPanel.add(pointsBalance);
		
		JLabel weekValue = new JLabel("Week: " + gameEnvironment.getCurrentWeek() + " of " + gameEnvironment.getSeasonLength());
		weekValue.setHorizontalAlignment(SwingConstants.CENTER);
		weekValue.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekValue.setSize(textBoxWidth, textBoxHeight);
		weekValue.setLocation(0, textY);
		mainMenuPanel.add(weekValue);
		
		// Creates a list to store the buttons for the action listener
		ArrayList<JButton> buttonList = new ArrayList<>(); 

		String[] buttonLabels = { "Go to the Club", "Go to the Stadium", "Visit the Market", "Take a bye" };
		
		// If the season is in the final week then bye button will update to "end season" and the player will move to the end game confirmation
		if (gameEnvironment.isFinalWeek()) {
			buttonLabels[3] = "End Season";
		}

		for (int i = 0; i < buttonLabels.length; i++) {
		    JButton button = new JButton(buttonLabels[i]);
		    button.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		    button.setSize(buttonWidth, buttonHeight);
		    button.setLocation(buttonX, buttonY + (buttonSpacing * i));
		    mainMenuPanel.add(button);
		    buttonList.add(button);
		}
		
		for (JButton button : buttonList) {
		    button.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            String buttonText = button.getText();
		    		mainMenuPanel.setVisible(false);
		    		
		            if (buttonText.equals(buttonLabels[0])) {
		                clubScreen();
		            } else if (buttonText.equals(buttonLabels[1])) {
		            	stadiumScreen();
		            } else if (buttonText.equals(buttonLabels[2])) {
		                marketScreen();
		            } else if (buttonText.equals("Take a bye")) {
		            	byeConfirmationScreen();
		            } else if (buttonText.equals("End Season")) {
		            	endGameConfirmationScreen();
		            }
		        }
		    });
		}
		
	}
	
	private void clubScreen() {

		int buttonHeight = 90;
		int buttonSpacing = 130;
		int buttonWidth = 367;
		int buttonX = (width - buttonWidth)/2;
		int buttonY = 250;
		
		JPanel clubMenuPanel = createScreenPanel();
		frame.getContentPane().add(clubMenuPanel);
		
		JLabel clubText = createTitleText("CLUB");
		clubMenuPanel.add(clubText);
		
		
		JButton teamPropertiesButton = new JButton("View team properties");
		teamPropertiesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		teamPropertiesButton.setSize(buttonWidth, buttonHeight);
		teamPropertiesButton.setLocation(buttonX, buttonY);
		clubMenuPanel.add(teamPropertiesButton);
		
		JButton inventoryButton = new JButton("View player inventory");
		inventoryButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		inventoryButton.setSize(buttonWidth, buttonHeight);
		inventoryButton.setLocation(buttonX, buttonY + buttonSpacing);
		clubMenuPanel.add(inventoryButton);
		
		JButton backButton = createBackButton();
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
	    		clubTeamPropertiesScreen();
	    		}
	    });
		
		inventoryButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		clubMenuPanel.setVisible(false);
	    		useItemScreen();
	    		}
	    });
		
		
	}
	
	private void clubTeamPropertiesScreen() {
		JPanel teamPropertiesPanel = createScreenPanel();
		frame.add(teamPropertiesPanel);
		 
		JButton backButton = createBackButton();
		teamPropertiesPanel.add(backButton);

        JLabel teamNameText = new JLabel(gameEnvironment.getTeamName());
        teamNameText.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        teamNameText.setSize(450, 100);
        teamNameText.setLocation((width - teamNameText.getWidth())/2, 29);
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
		
        ArrayList<JPanel> panels = new ArrayList<JPanel>();

		JPanel teamPanel = createTeamPanel(maxAthletesInTeam, teamPanelY);
		teamPropertiesPanel.add(teamPanel);
        panels.addAll(addAthletesToPanel(teamPanel, gameEnvironment.getTeamList(), maxAthletesInTeam));
		
		JPanel reservesPanel = createTeamPanel(maxAthletesInReserve, reservesPanelY);
		teamPropertiesPanel.add(reservesPanel);
        panels.addAll(addAthletesToPanel(reservesPanel, gameEnvironment.getReservesList(), maxAthletesInReserve));


		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		teamPropertiesPanel.setVisible(false);
	    		clubScreen();
	    		}
	    });
		
	    /**
		* Go to singleAthleteView display depending on which of the athlete panels is selected.
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            teamPropertiesPanel.setVisible(false);
		            if (index >= gameEnvironment.getTeamList().size()) {
		            	clubSingleAthleteScreen(gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()));
		            } else {
		            	clubSingleAthleteScreen(gameEnvironment.getTeamList().get(index));
		            }
		            
		        }
		    });
		}
		
		
	}
	
	
	private void clubSingleAthleteScreen(Athlete athlete) {
    	int playerTeamSize = gameEnvironment.getTeamList().size();
		
		JPanel clubSingleAthletePanel = createScreenPanel();
		frame.getContentPane().add(clubSingleAthletePanel);
		
	    JPanel athletePanel = new JPanel();
	    athletePanel.setSize(360, 459);
	    athletePanel.setLocation(312, 98);
	    athletePanel.setLayout(null);
	    clubSingleAthletePanel.add(athletePanel);
	    
	    refreshAthletePanel(athletePanel, athlete);
	    
	    JTextField athleteName = new JTextField(athlete.getName());
	    athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    athleteName.setHorizontalAlignment(SwingConstants.CENTER);
	    athleteName.setBounds(10, 11, 340, 57);
	    athletePanel.add(athleteName);
	    athleteName.setColumns(10);
	    athletePanel.setComponentZOrder(athleteName, 0);

		JButton backButton = createBackButton();
		clubSingleAthletePanel.add(backButton);
		
        JLabel errorText = createErrorText();
        clubSingleAthletePanel.add(errorText);
        
        JLabel resultText = createResultText();
        clubSingleAthletePanel.add(resultText);
        
    	JButton setAthleteNameButton = createButtonBelowAthleteCard(502);
    	setAthleteNameButton.setText("<html><center>"+"Set athlete"+"<br>"+"name"+"</center></html>");
		clubSingleAthletePanel.add(setAthleteNameButton);

    	JButton swapButton = createButtonBelowAthleteCard(312);
    	clubSingleAthletePanel.add(swapButton);
    	
    	/*
    	 * Updates button names for different player types
    	 * If the player is a reserve, the swap button is updated to add the reserve to the team
    	 * If the player is not injured and is in the main team, adds button to swap athlete's position in the team
    	 */
        if (athlete.isReserve()) {
        	if (playerTeamSize < 4) {
    			swapButton.setText("<html><center>"+"Add reserve"+"<br>"+"to team"+"</center></html>");
    			
    			swapButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent a) {
			    		try {
		    				gameEnvironment.addReserveToTeam(athlete);
		    				clubSingleAthletePanel.setVisible(false);
		    				clubTeamPropertiesScreen();
		    			} catch (LimitException e){
			    			errorText.setText(e.getMessage());
			    			errorText.setVisible(true);
		    			}
			    	}
			    });
        	} else {
        		swapButton.setText("<html><center>"+"Swap reserve"+"<br>"+"with player"+"</center></html>");
        	}
        }
        else {
        	swapButton.setText("<html><center>"+"Swap reserve"+"<br>"+"with player"+"</center></html>");
        	
        	if (!athlete.isInjured()) {
        		swapButton.setLocation(centreButtonX - buttonWidth - 20, buttonY);
				setAthleteNameButton.setLocation(centreButtonX + buttonWidth + 20, buttonY);
				
        		JButton swapPositionButton = createButtonBelowAthleteCard(centreButtonX);
        		clubSingleAthletePanel.add(swapPositionButton);
        		swapPositionButton.setText("<html><center>"+"Swap position"+"<br>"+"to " + athlete.getAlternatePosition() +"</center></html>");
        		
        		swapPositionButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent a) {
			    		athlete.setPosition(athlete.getAlternatePosition());
			    		resultText.setText("Athlete position updated");
			    		clubSingleAthletePanel.setVisible(false);
			    		clubSingleAthleteScreen(athlete);
		    	        
			    	}
			    });
        	}
        }
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		clubSingleAthletePanel.setVisible(false);
	    		clubTeamPropertiesScreen();
	    		}
	    });
		
		
		/*
		 * Updates the name of the athlete to the input from JTextField if athlete's name has been changed and error text if name input does not meet requirements
		 */
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
		

		/*
		 * If there as an available reserve (checkSwappable is true) then it takes the player to the clubAthleteSwapScreen to select athlete to swap with
		 */
		swapButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			gameEnvironment.checkSwappable();
	    			clubSingleAthletePanel.setVisible(false);
		    		clubAthleteSwapScreen(athlete);
		    		
	    		} catch(NoReserveAthletesException e) {
	    			errorText.setText(e.getMessage());
	    			errorText.setVisible(true);
	    		}
	    		}
	    });
		
	}
	
	public void clubAthleteSwapScreen(Athlete athlete) {
		JPanel athleteSwapPanel = createScreenPanel();
		frame.getContentPane().add(athleteSwapPanel);
		
		JLabel swapText = new JLabel("Select a player to swap with");
		swapText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		swapText.setHorizontalAlignment(SwingConstants.CENTER);
		swapText.setSize(width, 70);
		swapText.setLocation(0, 29);
		athleteSwapPanel.add(swapText);
		
		JButton backButton = createBackButton();
		athleteSwapPanel.add(backButton);
				
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
	    		clubSingleAthleteScreen(athlete);
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
		            clubTeamPropertiesScreen();
		        }
		    });
		}
		
	}
	
	
	
	
	
	
	private void useItemScreen() {
		JPanel useItemDisplayPanel = new JPanel();
		useItemDisplayPanel.setBackground(new Color(255, 255, 255));
		useItemDisplayPanel.setBounds(-108, 11, width, height);
		frame.getContentPane().add(useItemDisplayPanel);
		useItemDisplayPanel.setLayout(null);
		frame.getContentPane().add(useItemDisplayPanel);
		 
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useItemDisplayPanel.setVisible(false);
				mainMenu();
			}
		});
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(108, 11, 81, 48);
		useItemDisplayPanel.add(backButton);

		
        JLabel InventoryText = new JLabel("Inventory");
        InventoryText.setHorizontalAlignment(SwingConstants.CENTER);
        InventoryText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        InventoryText.setSize(450, 100);
        InventoryText.setLocation((width - InventoryText.getWidth())/2, 29);
        // Create a LineBorder with black color and 4 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        InventoryText.setBorder(border);
        useItemDisplayPanel.add(InventoryText);

                                
        JLabel ItemsText = new JLabel("Items");
        ItemsText.setHorizontalAlignment(SwingConstants.CENTER);
        ItemsText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        ItemsText.setBounds(155, 160, 81, 24);
        useItemDisplayPanel.add(ItemsText);
        
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		JPanel useItemPanel = new JPanel();
		useItemPanel.setLayout(null);
		useItemPanel.setSize(677, 223);
		useItemPanel.setLocation(154, 191);
		useItemPanel.setBackground(Color.WHITE);
		useItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		useItemDisplayPanel.add(useItemPanel);

        
        
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
            useItemPanel.add(athletePanel);
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
		        	useItemDisplayPanel.setVisible(false);
		        	useSingleItemPanel(inventoryItems.get(index));
		        }
		    });
		}
		
		
	}
	
	public void useSingleItemPanel(Item item) {
		JPanel UseSingleItemPanel = new JPanel();
		UseSingleItemPanel.setBackground(new Color(255, 255, 255));
		UseSingleItemPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseSingleItemPanel);
		UseSingleItemPanel.setLayout(null);
		
		JPanel BuyItemPanel = new JPanel();
		BuyItemPanel.setSize(360, 459);
		BuyItemPanel.setLocation(312, 98);
		UseSingleItemPanel.add(BuyItemPanel);
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
		UseSingleItemPanel.add(errorText);
		errorText.setForeground(new Color(255, 0, 0));
		 errorText.setVisible(false);
		
		JButton UseItemButton = new JButton("Use item");
		UseItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseSingleItemPanel.setVisible(false);
				useItemPlayersDisplay(item);
			}
		});
		UseItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		UseItemButton.setBounds(392, 600, 166, 48);
		UseSingleItemPanel.add(UseItemButton);
		
				
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		UseSingleItemPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseSingleItemPanel.setVisible(false);
				useItemScreen();
				
				
			}
		});
	
}
	public void useItemPlayersDisplay(Item item) {
		JPanel UseItemPlayerPanel = new JPanel();
		UseItemPlayerPanel.setBackground(new Color(255, 255, 255));
		UseItemPlayerPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseItemPlayerPanel);
		UseItemPlayerPanel.setLayout(null);
		frame.getContentPane().add(UseItemPlayerPanel);
		 
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		UseItemPlayerPanel.add(backButton);

		
        JLabel useLabel = new JLabel("Who Do You Want To Use Item On");
        useLabel.setHorizontalAlignment(SwingConstants.CENTER);
        useLabel.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        useLabel.setSize(735, 100);
        useLabel.setLocation(136, 29);
        // Create a LineBorder with black color and 4 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        useLabel.setBorder(border);
        UseItemPlayerPanel.add(useLabel);

                                
        JLabel teamText = new JLabel("Team");
        teamText.setHorizontalAlignment(SwingConstants.CENTER);
        teamText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        teamText.setBounds(155, 160, 55, 24);
        UseItemPlayerPanel.add(teamText);
        
        
        JLabel reservesText = new JLabel("Reserves: " + gameEnvironment.getReservesList().size() + "/5");
        reservesText.setHorizontalAlignment(SwingConstants.LEFT);
        reservesText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        reservesText.setBounds(72, 425, 200, 24);
        UseItemPlayerPanel.add(reservesText);
        
		int athletePanelWidth = 144;
		int athletePanelHeight = 183;
		
		JPanel teamPanel = new JPanel();
		teamPanel.setLayout(null);
		teamPanel.setSize((athletePanelWidth + 20)*4 + 20, athletePanelHeight + 40);
		teamPanel.setLocation(155, 191);
		teamPanel.setBackground(Color.WHITE);
		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		UseItemPlayerPanel.add(teamPanel);

		JPanel reservesPanel = new JPanel();
		reservesPanel.setLayout(null);
		reservesPanel.setSize((athletePanelWidth + 20)*5 + 20, athletePanelHeight + 40);
		reservesPanel.setLocation((width - reservesPanel.getWidth())/2, 460);
		reservesPanel.setBackground(Color.WHITE);
		reservesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		UseItemPlayerPanel.add(reservesPanel);

        
        
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

		
		
	    /**
		* Go to singleAthleteView display depends on which of the athlete panels is selected
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            UseItemPlayerPanel.setVisible(false);
		            if (index >= gameEnvironment.getTeamList().size()) {
		            	useItemSingleAthleteViewReserve(gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()),item);
		            } else {
		            	useItemSingleAthleteView(gameEnvironment.getTeamList().get(index),item);
		            }
		            
		        }
		    });
		}
		
		
	}
	public void useItemSingleAthleteView(Athlete athlete, Item item) {
		
		
		JPanel UseItemSingleAthletePanel = new JPanel();
		UseItemSingleAthletePanel.setBackground(new Color(255, 255, 255));
		UseItemSingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseItemSingleAthletePanel);
		UseItemSingleAthletePanel.setLayout(null);
		
		JPanel UseItemSingleAthletePanel1 = new JPanel();
		UseItemSingleAthletePanel1.setSize(360, 459);
		UseItemSingleAthletePanel1.setLocation(312, 98);
		UseItemSingleAthletePanel.add(UseItemSingleAthletePanel1);
		UseItemSingleAthletePanel1.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	UseItemSingleAthletePanel1.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	UseItemSingleAthletePanel1.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		UseItemSingleAthletePanel1.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		UseItemSingleAthletePanel1.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		UseItemSingleAthletePanel1.add(athleteInfo);
		
		
		
		
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(532, 48);
        errorText.setLocation(230, 644);
		errorText.setForeground(new Color(255, 0, 0));
        UseItemSingleAthletePanel.add(errorText);
        
        JButton UseItemButton = new JButton("Use on " + athlete.getName());
        UseItemButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		athlete.useItem(item);
        		gameEnvironment.removeItem(item);
        		UseItemSingleAthletePanel.setVisible(false);
        		mainMenu();
        		
        		
        		
        	}
        });
        UseItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        UseItemButton.setBounds(312, 586, 360, 47);
        UseItemSingleAthletePanel.add(UseItemButton);
        
        
		
	}

		
	
	public void useItemSingleAthleteViewReserve(Athlete athlete, Item item) {
		
		
		JPanel UseItemSingleReservePanel = new JPanel();
		UseItemSingleReservePanel.setBackground(new Color(255, 255, 255));
		UseItemSingleReservePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseItemSingleReservePanel);
		UseItemSingleReservePanel.setLayout(null);
		
		JPanel UseItemSingleReservePanel1 = new JPanel();
		UseItemSingleReservePanel1.setSize(360, 459);
		UseItemSingleReservePanel1.setLocation(312, 98);
		UseItemSingleReservePanel.add(UseItemSingleReservePanel1);
		UseItemSingleReservePanel1.setLayout(null);
		
        if (athlete.getPosition() == "Attacker") {
        	UseItemSingleReservePanel1.setBackground(new Color(173, 216, 230)); // sets background color to light blue
        } else {
        	UseItemSingleReservePanel1.setBackground(new Color(255, 204, 204)); // light red

        }
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		UseItemSingleReservePanel1.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImagePanel = new JPanel();
		athleteImagePanel.setBounds(58, 79, 247, 203);
		athleteImagePanel.setBackground(Color.WHITE);
		UseItemSingleReservePanel1.add(athleteImagePanel);
		athleteImagePanel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel athleteImage = new JLabel("");
		athleteImage.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png")));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
		
		JLabel athleteInfo = new JLabel(athlete.toStringHTML());
		athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 17));
		athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
		athleteInfo.setBounds(23, 305, 311, 143);
		UseItemSingleReservePanel1.add(athleteInfo);
		
		
		
		
		
        JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(532, 48);
        errorText.setLocation(230, 644);
		errorText.setForeground(new Color(255, 0, 0));
        UseItemSingleReservePanel.add(errorText);
        
        JButton UseItemReserveButton = new JButton("Use on" + athlete.getName());
        UseItemReserveButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		athlete.useItem(item);
        		gameEnvironment.removeItem(item);
        		
        		UseItemSingleReservePanel.setVisible(false);
        		mainMenu();
        		
        		
        		
        	}
        });
        UseItemReserveButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        UseItemReserveButton.setBounds(312, 586, 360, 47);
        UseItemSingleReservePanel.add(UseItemReserveButton);
        
        
		
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
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemDisplayPanel.setVisible(false);
				mainMenu();
			}
		});
		

		
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
							
							errorText.setText(e1.getMessage());
			    			errorText.setVisible(true);
			    			return;
						}
					} catch (InsufficientFundsException e1) {
						
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
		
		JLabel errorText = new JLabel("");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(464, 24);
        errorText.setLocation(264, 668);
		errorText.setForeground(new Color(255, 0, 0));
        BuySingleAthletePanel.add(errorText);
        errorText.setVisible(false);
		
		JPanel BuyAthletePanel = new JPanel();
		BuyAthletePanel.setSize(360, 459);
		BuyAthletePanel.setLocation(312, 98);
		BuySingleAthletePanel.add(BuyAthletePanel);
		BuyAthletePanel.setLayout(null);
		
        
		
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
		
		JButton addToReservesButton = new JButton("Add To Reserve");
		addToReservesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnvironment.getTeamList().size() == 4) {
					BuySingleAthletePanel.setVisible(false);
					try {
						gameEnvironment.purchaseAthlete(athlete, null);
					} catch (InsufficientFundsException e1) {
						
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
					} catch (LimitException e1) {
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
						
					}
					marketScreen();
					
					
					
					
				
						
					
					
					
				}
				
					
				
			}
		});
		
		
		
		
	
			
		addToReservesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		addToReservesButton.setBounds(213, 595, 218, 55);
		BuySingleAthletePanel.add(addToReservesButton);
		
		JButton addToTeamButton = new JButton("Add To Team");
		addToTeamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnvironment.getTeamList().size() == 4) {
					BuySingleAthletePanel.setVisible(false);
					try {
						gameEnvironment.purchaseAthlete(athlete, null);
					} catch (InsufficientFundsException e1) {
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
						
					} catch (LimitException e1) {
						errorText.setText(e1.getMessage());
		    			errorText.setVisible(true);
		    			return;
						
					}
					singleAthleteView(athlete);
				}else {
					BuySingleAthletePanel.setVisible(false);
					BuySingleAthleteSetPostion(athlete);
				}
				
				
				
				
			}
		});
		addToTeamButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		addToTeamButton.setBounds(496, 595, 218, 55);
		BuySingleAthletePanel.add(addToTeamButton);
		
		
        
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
				} catch (LimitException e1) {
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
					
				} catch (LimitException e1) {
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
	
	
	private void endGameConfirmationScreen() {
		JPanel endConfirmationScreen = new JPanel();
		endConfirmationScreen.setBackground(new Color(255, 255, 255));
		endConfirmationScreen.setBounds(0, 0, width, height);
		frame.getContentPane().add(endConfirmationScreen);
		endConfirmationScreen.setLayout(null);
		
		JLabel endText = new JLabel("END");
		endText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		endText.setHorizontalAlignment(SwingConstants.CENTER);
		endText.setSize(139, 70);
		endText.setLocation((width - endText.getWidth())/2, 29);
		endConfirmationScreen.add(endText);
		
		JLabel infoText = new JLabel("This is the last week of the season. Are you sure you want to finish?");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 110);
		endConfirmationScreen.add(infoText);
		
		JButton yesButton = new JButton("Yes");
		yesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		yesButton.setBounds(289, 321, 192, 48);
		endConfirmationScreen.add(yesButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backButton.setBounds(501, 321, 192, 48);
		endConfirmationScreen.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		endConfirmationScreen.setVisible(false);
	    		mainMenu();
	    		}
	    });
		
		yesButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		endConfirmationScreen.setVisible(false);
	    		endGameSummary();
	    		}
	    });
		
	}
	
	private void endGameSummary() {
		JPanel gameSummaryPanel = new JPanel();
		gameSummaryPanel.setBackground(new Color(255, 255, 255));
		gameSummaryPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(gameSummaryPanel);
		gameSummaryPanel.setLayout(null);
		
		Map<String, Object> results = gameEnvironment.endGame();
		String teamName = (String) results.get("name");
		int weeks = (int) results.get("weeks");
		int points = (int) results.get("points");
		int money = (int) results.get("money");
		
		JLabel gameOverText = new JLabel("GAME OVER");
		gameOverText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		gameOverText.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverText.setSize(390, 70);
		gameOverText.setLocation((width - gameOverText.getWidth())/2, 29);
		gameSummaryPanel.add(gameOverText);
		
		JLabel teamNameText = new JLabel(teamName);
		teamNameText.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		teamNameText.setSize(984, 91);
		teamNameText.setLocation(0, 155);
		gameSummaryPanel.add(teamNameText);
		
		JLabel seasonLength = new JLabel("Season length: " + weeks + " weeks");
		seasonLength.setHorizontalAlignment(SwingConstants.CENTER);
		seasonLength.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		seasonLength.setBounds(0, 288, 984, 64);
		gameSummaryPanel.add(seasonLength);
		
		JLabel pointsText = new JLabel("Points gained: " + points);
		pointsText.setHorizontalAlignment(SwingConstants.CENTER);
		pointsText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		pointsText.setBounds(0, seasonLength.getY() + 40, 984, 64);
		gameSummaryPanel.add(pointsText);
		
		JLabel moneyText = new JLabel("Money earned: " + money);
		moneyText.setHorizontalAlignment(SwingConstants.CENTER);
		moneyText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		moneyText.setBounds(0, seasonLength.getY() + 40 + 40, 984, 64);
		gameSummaryPanel.add(moneyText);
		
		JButton playAgainButton = new JButton("Play Again");
		playAgainButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playAgainButton.setBounds(289, 550, 192, 48);
		gameSummaryPanel.add(playAgainButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		quitButton.setBounds(501, 550, 192, 48);
		gameSummaryPanel.add(quitButton);
		
		playAgainButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameSummaryPanel.setVisible(false);
	    		gameEnvironment.playAgain();
	    		}
	    });
		
		quitButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameSummaryPanel.setVisible(false);
	    		gameEnvironment.closeMainScreen();
	    		}
	    });
		
	}
	
	private void byeConfirmationScreen() {
		JPanel byeConfirmationPanel = new JPanel();
		byeConfirmationPanel.setBackground(new Color(255, 255, 255));
		byeConfirmationPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(byeConfirmationPanel);
		byeConfirmationPanel.setLayout(null);
		
		JLabel byeText = new JLabel("BYE");
		byeText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		byeText.setHorizontalAlignment(SwingConstants.CENTER);
		byeText.setSize(131, 70);
		byeText.setLocation((width - byeText.getWidth())/2, 29);
		byeConfirmationPanel.add(byeText);
		
		JLabel infoText = new JLabel("Take a bye? The season will move to the next week");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 110);
		byeConfirmationPanel.add(infoText);
		
		JButton yesButton = new JButton("Yes");
		yesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		yesButton.setBounds(289, 321, 192, 48);
		byeConfirmationPanel.add(yesButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backButton.setBounds(501, 321, 192, 48);
		byeConfirmationPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		byeConfirmationPanel.setVisible(false);
	    		mainMenu();
	    		}
	    });
		
		yesButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		byeConfirmationPanel.setVisible(false);
	    		byeInfoScreen();
	    		}
	    });
		
	}
	
	private void byeInfoScreen() {
		gameEnvironment.increaseWeek();
		
		JPanel byeInfoPanel = new JPanel();
		byeInfoPanel.setBackground(new Color(255, 255, 255));
		byeInfoPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(byeInfoPanel);
		byeInfoPanel.setLayout(null);
		
		JLabel weekText = new JLabel("Season increased to week " + gameEnvironment.getCurrentWeek());
		weekText.setHorizontalAlignment(SwingConstants.CENTER);
		weekText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekText.setSize(width, 25);
		weekText.setForeground(Color.GREEN); // Set foreground color to green
		weekText.setLocation(0, 50);
		byeInfoPanel.add(weekText);
		
		JLabel staminaText = new JLabel("All athlete's stamina have been increased");
		staminaText.setHorizontalAlignment(SwingConstants.CENTER);
		staminaText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		staminaText.setSize(width, 25);
		staminaText.setForeground(Color.GREEN); // Set foreground color to green
		staminaText.setLocation(0, 50 + 25);
		byeInfoPanel.add(staminaText);
		
		JLabel infoText = new JLabel("Please select one of the following athletes to train this week");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 50 + 50 + 50);
		byeInfoPanel.add(infoText);
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		
		JPanel teamPanel = createTeamPanel(maxAthletesInTeam, teamPanelY);
		byeInfoPanel.add(teamPanel);
        panels.addAll(addAthletesToPanel(teamPanel, gameEnvironment.getTeamList(), maxAthletesInTeam));
		
		JPanel reservesPanel = createTeamPanel(maxAthletesInReserve, reservesPanelY);
		byeInfoPanel.add(reservesPanel);
        panels.addAll(addAthletesToPanel(reservesPanel, gameEnvironment.getReservesList(), maxAthletesInReserve));

        
        /**
		* trains specific athlete depends on which of the athlete panels is selected
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            Athlete trainedAthlete = null;
		            if (index >= gameEnvironment.getTeamList().size()) {
		            	trainedAthlete = (gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()));		                
		            } else {
		            	trainedAthlete = (gameEnvironment.getTeamList().get(index));
		            }
	                byeInfoPanel.setVisible(false);
		            byeTrainAthlete(trainedAthlete);
		            
		        }
		    });
		}
        
        
	}
	
	private void byeTrainAthlete(Athlete athlete) {
        
		JPanel singleAthletePanel = new JPanel();
		singleAthletePanel.setBackground(new Color(255, 255, 255));
		singleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(singleAthletePanel);
		singleAthletePanel.setLayout(null);
		
		
	    JPanel athletePanel = new JPanel();
	    athletePanel.setSize(360, 459);
	    athletePanel.setLocation(312, 98);
	    athletePanel.setLayout(null);
	    singleAthletePanel.add(athletePanel);
		
		refreshAthletePanel(athletePanel, athlete);
		
		JButton trainButton = new JButton("Train");
		trainButton.setSize(buttonWidth, buttonHeight);
		trainButton.setLocation(centreButtonX, buttonY);
		singleAthletePanel.add(trainButton);
		trainButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		
		JButton okButton = new JButton("ok");
		okButton.setSize(buttonWidth, buttonHeight);
		okButton.setLocation(centreButtonX, buttonY);
		singleAthletePanel.add(okButton);
		okButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		okButton.setVisible(false);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		singleAthletePanel.add(backButton);
		
        JLabel resultText = new JLabel("Athlete has been trained");
        resultText.setHorizontalAlignment(SwingConstants.CENTER);
        resultText.setFont(new Font("Calibri", Font.PLAIN, 20));
        resultText.setSize(width, 24);
        resultText.setLocation(0, 668);
        resultText.setForeground(new Color(0, 255, 0));
        singleAthletePanel.add(resultText);
        resultText.setVisible(false);
        
        
		trainButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.trainAthlete(athlete);
	    		refreshAthletePanel(athletePanel, athlete);
	    		trainButton.setVisible(false);
	    		okButton.setVisible(true);
	            resultText.setVisible(true);
	    		}
	    });
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		byeInfoScreen();
	    		}
	    });
		
		okButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		Map<String, Object> randomEvents = gameEnvironment.preformRandomEvent());
	    		String result = (String) randomEvents.get("result");
	    		if (result == "rest") {
	    		mainMenu();
	    		}else {
	    			randomEventScreen(result);
	    			
	    		}
	    }});
	}
	
	public void randomEventScreen(String result) {
		
		JPanel randomEventPanel = new JPanel();
		randomEventPanel.setBounds(0, 0, 984, 711);
		frame.getContentPane().add(randomEventPanel);
		randomEventPanel.setLayout(null);
		
		
	    
		
		JLabel randomEventsLabel = new JLabel("Resting");
		randomEventsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		randomEventsLabel.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		randomEventsLabel.setBounds(163, 11, 644, 68);
		randomEventPanel.add(randomEventsLabel);
		
		
		
	
		
		JLabel randomEventText = new JLabel("");
		randomEventText.setHorizontalAlignment(SwingConstants.CENTER);
		randomEventText.setBounds(173, 90, 732, 179);
		randomEventPanel.add(randomEventText);
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton randomEventsButton = new JButton("Continue");
		randomEventsButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		randomEventsButton.setBounds(328, 359, 322, 62);
		randomEventPanel.add(randomEventsButton);
		randomEventsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (result =="increased stat" ) {
				randomEventText.setText("a random players stats have increased");
				}else if(result == "athleteJoins") {
					if()
				}
				
				
				
				
			}
		});
	}
		
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	public void stadiumScreen() {
		JPanel stadiumPanel = new JPanel();
		stadiumPanel.setBackground(new Color(255, 255, 255));
		stadiumPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(stadiumPanel);
		stadiumPanel.setLayout(null);
		
		JLabel stadiumText = new JLabel("STADIUM");
		stadiumText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		stadiumText.setHorizontalAlignment(SwingConstants.CENTER);
		stadiumText.setSize(306, 70);
		stadiumText.setLocation((width - stadiumText.getWidth())/2, 29);
		stadiumPanel.add(stadiumText);
		
		JLabel infoText = new JLabel("Select one of the following matches...");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 122);
		stadiumPanel.add(infoText);
		
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		stadiumPanel.add(backButton);
		
		
		int numMatches = gameEnvironment.getStadiumMatches().size();
		if (numMatches == 0) {
			infoText.setText("No matches left this week");
		}
		
		int matchPanelDistance = 180;
		int matchPanelWidth = 500;
		int matchPanelHeight = 130;
		
        ArrayList<JPanel> matchPanels = new ArrayList<JPanel>();

		int i = 0;
		while (i < numMatches) {
			Match match = gameEnvironment.getStadiumMatches().get(i);
			
			JPanel matchPanel = new JPanel();
			matchPanel.setSize(matchPanelWidth, matchPanelHeight);
			matchPanel.setLocation((width - matchPanelWidth)/2, 180 + (matchPanelDistance * i));
			stadiumPanel.add(matchPanel);
			matchPanel.setLayout(null);
			
			matchPanels.add(matchPanel);
			
			JLabel rewardText = new JLabel("REWARD");
			rewardText.setHorizontalAlignment(SwingConstants.CENTER);
			rewardText.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			rewardText.setBounds(208, 54, 74, 33);
			matchPanel.add(rewardText);
			
			JLabel rewardPointsText = new JLabel(gameEnvironment.getMatchPoints(match) + " points");
			rewardPointsText.setHorizontalAlignment(SwingConstants.CENTER);
			rewardPointsText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			rewardPointsText.setBounds(10, 75, 188, 44);
			matchPanel.add(rewardPointsText);
			
			JLabel rewardMoneyText = new JLabel(gameEnvironment.getMatchPrize(match));
			rewardMoneyText.setHorizontalAlignment(SwingConstants.CENTER);
			rewardMoneyText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			rewardMoneyText.setBounds(292, 75, 198, 44);
			matchPanel.add(rewardMoneyText);
			
			JLabel oppositionNameText = new JLabel(gameEnvironment.getMatchTeamName(match).toUpperCase());
			oppositionNameText.setHorizontalAlignment(SwingConstants.CENTER);
			oppositionNameText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			oppositionNameText.setBounds(0, 0, 500, 55);
			matchPanel.add(oppositionNameText);
			
			i += 1;
		}
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		stadiumPanel.setVisible(false);
	    		mainMenu();
	    		}
	    });
		
		
	    /**
		* Go to confirmMatchView display depends on which of the athlete panels is selected
	    */
		
		for (int j = 0; j < matchPanels.size(); j++) {
			int index = j;
		    JPanel panel = matchPanels.get(j);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	stadiumPanel.setVisible(false);
		            confirmMatchView(index);
		        }
		    });
		}
		
		

		
	}
	
	public void confirmMatchView(int matchNumber) {
		Match match = gameEnvironment.getStadiumMatches().get(matchNumber);
		String oppositionName = gameEnvironment.getMatchTeamName(match);
		int pointsReward = gameEnvironment.getMatchPoints(match);
		String moneyReward = gameEnvironment.getMatchPrize(match);
		String teamName = gameEnvironment.getTeamName();
		
		JPanel matchConfirmView = new JPanel();
		matchConfirmView.setBackground(new Color(255, 255, 255));
		matchConfirmView.setBounds(0, 0, width, height);
		frame.getContentPane().add(matchConfirmView);
		matchConfirmView.setLayout(null);
		
		JLabel matchText = new JLabel("MATCH");
		matchText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		matchText.setHorizontalAlignment(SwingConstants.CENTER);
		matchText.setSize(236, 70);
		matchText.setLocation((width - matchText.getWidth())/2, 29);
		matchConfirmView.add(matchText);
		
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		errorText.setSize(984, 48);
		errorText.setLocation(0, 634);
		errorText.setForeground(Color.RED); // Set text color to red
		matchConfirmView.add(errorText);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		matchConfirmView.add(backButton);
		

		JLabel playerTeamName = new JLabel(teamName.toUpperCase());
		playerTeamName.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		playerTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		playerTeamName.setSize(width, 130);
		playerTeamName.setLocation(0, 219);
		matchConfirmView.add(playerTeamName);
		
		JLabel opppositionTeamName = new JLabel(oppositionName.toUpperCase());
		opppositionTeamName.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		opppositionTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		opppositionTeamName.setSize(width, 130);
		opppositionTeamName.setLocation(0, 328);
		matchConfirmView.add(opppositionTeamName);

		
		JLabel vsText = new JLabel("vs");
		vsText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		vsText.setHorizontalAlignment(SwingConstants.CENTER);
		vsText.setSize(width, 130);
		vsText.setLocation(0, 180 + 180/2);
		matchConfirmView.add(vsText);

		JLabel rewardText = new JLabel("Reward: " + pointsReward + " points, " + moneyReward);
		rewardText.setFont(new Font("Cooper Black", Font.PLAIN, 25));
		rewardText.setHorizontalAlignment(SwingConstants.CENTER);
		rewardText.setSize(984, 115);
		rewardText.setLocation(0, 128);
		matchConfirmView.add(rewardText);

		JButton startButton = new JButton("Start Match");
		startButton.setForeground(new Color(0, 0, 0));
		startButton.setBackground(new Color(0, 171, 58));
		startButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		startButton.setSize(168, 77);
		startButton.setLocation(408, 507);
		matchConfirmView.add(startButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		matchConfirmView.setVisible(false);
	    		stadiumScreen();
	    		}
	    });
		
		startButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			gameEnvironment.startMatch(match);
	    			matchConfirmView.setVisible(false);
	    			playMatch();
	    		} catch (IllegalTeamException e) {
	    			errorText.setText(e.getMessage());
	    			errorText.setVisible(true);
	    		}
	    		}
	    });
		
	}
	
	public void playMatch() {
		encounterScreen();

	}
	
	public void encounterScreen() {
		ArrayList<Athlete> athletes = gameEnvironment.getEncounterAthletes();
		
		JPanel encounterPanel = new JPanel();
		encounterPanel.setBackground(new Color(255, 255, 255));
		encounterPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(encounterPanel);
		encounterPanel.setLayout(null);
		
		JPanel encounterTextPanel = new JPanel();
		encounterTextPanel.setBackground(Color.WHITE);
		encounterTextPanel.setBounds(120, 533, 757, 40);
		encounterTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		encounterPanel.add(encounterTextPanel);
		encounterTextPanel.setLayout(null);
		
		JLabel encounterText = new JLabel("");
		encounterText.setFont(new Font("Calibri", Font.PLAIN, 13));
		encounterText.setBounds(10, 0, 737, 40);
		encounterTextPanel.add(encounterText);

		JLabel playerTeamName = new JLabel(gameEnvironment.getTeamName());
		playerTeamName.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		playerTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		playerTeamName.setSize(493, 73);
		playerTeamName.setLocation(0, 0);
		encounterPanel.add(playerTeamName);
		
		JLabel opppositionTeamName = new JLabel(gameEnvironment.getCurrentMatchTeamName());
		opppositionTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		opppositionTeamName.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		opppositionTeamName.setBounds(491, 0, 493, 73);
		encounterPanel.add(opppositionTeamName);
		
		ArrayList<Integer> matchScore = gameEnvironment.getMatchScore();
		int playerScore = matchScore.get(0);
		int oppositionScore = matchScore.get(1);

		
		JLabel playerTeamScore = new JLabel("" + playerScore);
		playerTeamScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerTeamScore.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		playerTeamScore.setBounds(0, 53, 493, 40);
		encounterPanel.add(playerTeamScore);
		
		JLabel oppositionTeamScore = new JLabel("" + oppositionScore);
		oppositionTeamScore.setHorizontalAlignment(SwingConstants.CENTER);
		oppositionTeamScore.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		oppositionTeamScore.setBounds(491, 53, 493, 40);
		encounterPanel.add(oppositionTeamScore);
		
		JLabel vsText = new JLabel("vs");
		vsText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		vsText.setHorizontalAlignment(SwingConstants.CENTER);
		vsText.setSize(33, 35);
		vsText.setLocation((width - vsText.getWidth())/2, 180 + 180/2);
		encounterPanel.add(vsText);
		
		double decreaseSizeMultiplier = 0.7;
		
		for (int i = 0 ; i < 2 ; i ++) {
			JPanel athletePanel = new JPanel();
			athletePanel.setSize((int) (360 * decreaseSizeMultiplier),(int) (459 * decreaseSizeMultiplier));
			athletePanel.setLocation((width/2 - athletePanel.getWidth())/2 + ((width/2) * i), 150);
			encounterPanel.add(athletePanel);
			athletePanel.setLayout(null);
			
			JTextField athleteName = new JTextField(athletes.get(i).getName());
			athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 15));
			athleteName.setHorizontalAlignment(SwingConstants.CENTER);
			athleteName.setBounds(10, 11, 231, 39);
			athletePanel.add(athleteName);
			athleteName.setColumns(10);
			
			
			JPanel athleteImagePanel = new JPanel();
			athleteImagePanel.setBounds((int) (58 * 0.7), (int) (79 * 0.7), (int) (247 * 0.7), (int) (203 * 0.7));
			athleteImagePanel.setBackground(Color.WHITE);
			athletePanel.add(athleteImagePanel);
			athleteImagePanel.setLayout(new BorderLayout(0, 0));

			JLabel athleteImage = new JLabel("");
			ImageIcon imageIcon = new ImageIcon(Temp.class.getResource("/Pictures/" + athletes.get(i).getImageName() + ".png"));
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance((int) (247 * 0.7), (int) (203 * 0.7), Image.SCALE_SMOOTH);
			athleteImage.setIcon(new ImageIcon(scaledImage));
			athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
			athleteImagePanel.add(athleteImage, BorderLayout.CENTER);

			JLabel athleteInfo = new JLabel(athletes.get(i).toStringHTML());
			athleteInfo.setFont(new Font("Calibri", Font.BOLD, 15));
			athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
			athleteInfo.setBounds((int) (23 * 0.7), (int) (305 * 0.7), (int) (311 * 0.7), (int) (143 * 0.7));
			athletePanel.add(athleteInfo);
		}
		
        int buttonWidth = ((int) (360 * decreaseSizeMultiplier)/2) - 10;
        int buttonHeight = 50;
        int centreButtonX = (width - buttonWidth)/2;
        
		JButton nextButton = new JButton("next");
		nextButton.setSize(buttonWidth, buttonHeight);
		nextButton.setLocation(centreButtonX, 617);
		encounterPanel.add(nextButton);
		nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 21));
		

		nextButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		String result = gameEnvironment.matchEncounter(athletes);
	    		encounterText.setText(result);
	    		
	    		ArrayList<Integer> matchScore = gameEnvironment.getMatchScore();
	    		int playerScore = matchScore.get(0);
	    		int oppositionScore = matchScore.get(1);

	    		playerTeamScore.setText("" + playerScore);
	    		oppositionTeamScore.setText("" + oppositionScore);
	    		
	    		nextButton.setVisible(false);
	    		
	    		JButton nextButtonCopy = new JButton("next");
	    		nextButtonCopy.setSize(buttonWidth, buttonHeight);
	    		nextButtonCopy.setLocation(centreButtonX, 617);
	    		encounterPanel.add(nextButtonCopy);
	    		nextButtonCopy.setFont(new Font("Cooper Black", Font.PLAIN, 21));
	    		
	    		nextButtonCopy.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent a) {
	    	    		if (gameEnvironment.isMatchRunning()) {
	    	    			encounterPanel.setVisible(false);
	    	    			encounterScreen();
	    	    		} else {
	    	    			encounterPanel.setVisible(false);
	    	    			endMatch();
	    	    		}
	    	    		
	    	    		}
	    	    });

	    		}
	    });
	}
	
	public void endMatch() {
		JPanel matchResultPanel = new JPanel();
		matchResultPanel.setBackground(new Color(255, 255, 255));
		matchResultPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(matchResultPanel);
		matchResultPanel.setLayout(null);
		
		Map<String, Object> results = gameEnvironment.endMatch();
	    String winner = (String) results.get("winner");
	    String score = (String) results.get("score");
	    int prizeMoney = (int) results.get("money");
	    int points = (int) results.get("points");
	    
        int buttonWidth = ((int) (360 * 0.7)/2) - 10;
        int buttonHeight = 50;
        int centreButtonX = (width - buttonWidth)/2;
		
		JButton nextButton = new JButton("next");
		nextButton.setSize(buttonWidth, buttonHeight);
		nextButton.setLocation(centreButtonX, 617);
		matchResultPanel.add(nextButton);
		nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 21));
		
		JLabel winningTeamName = new JLabel(winner);
		winningTeamName.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		winningTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		winningTeamName.setSize(width, 130);
		winningTeamName.setLocation(0, 269);
		matchResultPanel.add(winningTeamName);
		
		JLabel winnerText = new JLabel("Winner:");
		winnerText.setHorizontalAlignment(SwingConstants.CENTER);
		winnerText.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		winnerText.setBounds(0, 230, 984, 84);
		matchResultPanel.add(winnerText);
		

		JLabel scoreText = new JLabel(score);
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setFont(new Font("Cooper Black", Font.PLAIN, 35));
		scoreText.setBounds(0, 89, 984, 130);
		matchResultPanel.add(scoreText);


		
		JLabel winningPoints = new JLabel("+" + points);
		winningPoints.setHorizontalAlignment(SwingConstants.CENTER);
		winningPoints.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		winningPoints.setBounds(0, 389, 493, 130);
		matchResultPanel.add(winningPoints);
		winningPoints.setForeground(new Color(0, 200, 0)); // Set green color for winning points

		
		JLabel winningMoney = new JLabel("+ " + gameEnvironment.getMoneyFormatted(prizeMoney));
		winningMoney.setHorizontalAlignment(SwingConstants.CENTER);
		winningMoney.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		winningMoney.setBounds(491, 389, 493, 130);
		matchResultPanel.add(winningMoney);
		winningMoney.setForeground(new Color(0, 200, 0)); // Set green color for winning money

		 // Set the background color based on the winner
		 if (winner.equals(gameEnvironment.getTeamName() )) {
			 winningTeamName.setForeground(new Color(0, 200, 0)); // Set green color for winning team name
		 } else if (winner == "Draw") {
			winnerText.setVisible(false);
		 } else {
			 winningTeamName.setForeground(new Color(200, 0, 0)); // Set red color for losing team name
		     winningMoney.setVisible(false);
		     winningPoints.setVisible(false);
		 }
		 
	    
 		nextButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		matchResultPanel.setVisible(false);
	    		
	    		JPanel updatedTeamPanel = new JPanel();
	    		updatedTeamPanel.setBackground(new Color(255, 255, 255));
	    		updatedTeamPanel.setBounds(0, 0, width, height);
	    		frame.getContentPane().add(updatedTeamPanel);
	    		updatedTeamPanel.setLayout(null);
	    		
	    		JLabel infoText = new JLabel("Athlete's statistics have been updated...");
	    		infoText.setHorizontalAlignment(SwingConstants.CENTER);
	    		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    		infoText.setSize(width, 25);
	    		infoText.setLocation(0, 122);
	    		updatedTeamPanel.add(infoText);
	    		
	    		
	    		int athletePanelWidth = 144;
	    		int athletePanelHeight = 183;
	    		
	    		JPanel teamPanel = new JPanel();
	    		teamPanel.setLayout(null);
	    		teamPanel.setSize((athletePanelWidth + 20)*4 + 20, athletePanelHeight + 40);
	    		teamPanel.setLocation(155, (height-teamPanel.getHeight())/2);
	    		teamPanel.setBackground(Color.WHITE);
	    		teamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    		updatedTeamPanel.add(teamPanel);
	            
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
	                } else if (athlete.getPosition() == "Defender"){
	                	athletePanel.setBackground(new Color(255, 204, 204)); // light red

	                }
	                

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
	            
	    		JButton endMatchButton = new JButton("End match");
	    		endMatchButton.setSize((int) ((int) buttonWidth*1.5), buttonHeight);
	    		endMatchButton.setLocation((int) (width - (buttonWidth * 1.5))/2, (int) (height* (0.75) + (endMatchButton.getHeight()/2)));
	    		updatedTeamPanel.add(endMatchButton);
	    		endMatchButton.setFont(new Font("Cooper Black", Font.PLAIN, 21));
	    		
	    		endMatchButton.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent a) {
	    	    		updatedTeamPanel.setVisible(false);
	    	    		teamPanel.setVisible(false);
	    	    		mainMenu();
	    	    		
	    	    		}
	    	    });
	    	}
	    });
 		

		
	}
	
	public void closeWindow() {
		frame.dispose();
	}
		
	
		




	

}

		
		
		
		


		
		
	
	
	
	



