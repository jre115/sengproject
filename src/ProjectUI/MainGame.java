package ProjectUI;

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

import ProjectExceptions.IllegalTeamException;
import ProjectExceptions.InsufficientFundsException;
import ProjectExceptions.InventoryFullException;
import ProjectExceptions.NameException;
import ProjectExceptions.NoReserveAthletesException;
import project.Athlete;
import project.GameEnvironment;
import project.Item;
import project.LimitException;
import project.Match;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JTextArea;

/**
 * This class implements the GUI for running the entire main game of the game environment
 *
 * @author Jordan Redfern and Cameron Pellett
 * @version 1.1, May 2023.
 */
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
	 * The width for athlete panel cards to be used on screens with a singular athlete (full size).
	 */
	int athletePanelWidth = 360;
	
	/**
	 * The height for athlete panel cards to be used on screens with a singular athlete (full size).
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
	 *@param panel as JPanel to add the athlete cards to.
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
            
        
            // Evenly spaces athlete card panels across the panel
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            panel.add(athletePanel);
            athletePanel.setLayout(null);
            
            panels.add(athletePanel);

            refreshAthletePanel(athletePanel, athlete, 0.4);
        }
        
        return panels;
	}
        
	/**
	 * Adds item cards from inputed items list to a selected JPanel and returns the list of JPanels of each item panel card.
	 *
	 *@param panel as JPanel to add the item cards to.
	 *@param items as ArrayList<Item> to create the item cards for
	 *@param numItemsPerRow the number of item cards that should be able to fit on the panel
	 * @return an ArrayList of JPanels of each item card panel
	 */
    private ArrayList<JPanel> addItemsToPanel(JPanel panel, ArrayList<Item> items, int numItemsPerRow) {
            int panelSpacing = 20; 
            // Item card panels will be the same size as athlete panels for consistency
    		int athletePanelWidth = 144;
    		int athletePanelHeight = 183;
    		
            ArrayList<JPanel> panels = new ArrayList<JPanel>();

            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                
            
                // Evenly spaces item card panels across the panel
                JPanel itemPanel = new JPanel();
                itemPanel.setBounds((athletePanelWidth + panelSpacing) * (i % numItemsPerRow) + panelSpacing, 
                                       (athletePanelHeight + panelSpacing) * (i / numItemsPerRow) + panelSpacing, 
                                       athletePanelWidth, athletePanelHeight);
                panel.add(itemPanel);
                itemPanel.setLayout(null);
                
                panels.add(itemPanel);

                refreshItemPanel(itemPanel, item, 0.4);
            }
            
            return panels;
	}
    	
	/**
	 * Populates the item panel card with the item's information with the formatting and locations scaled to the size of the item card
	 *
	 *@param panel as JPanel to add in item's information
	 *@param item to populate the panel with the itme's information
	 *@param sizeMultiplier scales all items on the panel to size.
	 */
    private void refreshItemPanel(JPanel itemPanel, Item item, double sizeMultiplier) {
    		
    	    itemPanel.removeAll();
    		int nameFontSize = 20;
    		int bodyFontSize = 17;
    		
    		if (sizeMultiplier == 1) {
    			nameFontSize = 20;
    			bodyFontSize = 17;
    		} else if (sizeMultiplier == 0.75) {
    			nameFontSize = 16;
    			bodyFontSize = 14;
    		} else if (sizeMultiplier == 0.4) {
    			nameFontSize = 11;
    			bodyFontSize = 10;
    		}
    		
    	    JLabel itemName = new JLabel(gameEnvironment.getItemName(item));
    	    itemName.setFont(new Font("Cooper Black", Font.PLAIN, nameFontSize));
    	    itemName.setHorizontalAlignment(SwingConstants.CENTER);
    	    itemName.setBackground(Color.WHITE); 
    	    itemName.setOpaque(true); 
    	    itemName.setBounds((int)(10 * sizeMultiplier), (int)(11 * sizeMultiplier), (int)(340 * sizeMultiplier), (int)( 57 * sizeMultiplier));
    	    itemPanel.add(itemName);

    	    JLabel itemInfo = new JLabel(gameEnvironment.itemToStringHTML(item));
    	    itemInfo.setFont(new Font("Calibiri", Font.BOLD, bodyFontSize));
    	    itemInfo.setHorizontalAlignment(SwingConstants.CENTER);
    	    itemInfo.setBounds((int)(23 * sizeMultiplier), (int)((21) * sizeMultiplier), (int)(311 * sizeMultiplier), (int)((athletePanelHeight - 20) * sizeMultiplier));
    	    itemPanel.add(itemInfo);

    	    itemPanel.revalidate();
    	    itemPanel.repaint();
    	    
    	}
	
	/**
	 * Populates the athlete panel card with the athlete's information with the formatting and locations scaled to the size of the athlete card
	 *
	 *@param panel as JPanel to add in athlte's information
	 *@param athlete to populate the panel with the athlete's information
	 *@param sizeMultiplier scales all items on the panel to size where 1 is full size.
	 */
	private void refreshAthletePanel(JPanel athletePanel, Athlete athlete, double sizeMultiplier) {
		
	    athletePanel.removeAll();

		double imageMultiplier = 1;
		int nameFontSize = 20;
		int bodyFontSize = 17;
		
		if (sizeMultiplier == 1) {
			nameFontSize = 20;
			bodyFontSize = 17;
			imageMultiplier = 1;
		} else if (sizeMultiplier == 0.75) {
			nameFontSize = 16;
			bodyFontSize = 14;
			imageMultiplier = 0.8;
		} else if (sizeMultiplier == 0.4) {
			nameFontSize = 11;
			bodyFontSize = 10;
			imageMultiplier = 0.55;
		} else if (sizeMultiplier == 0.7) {
			nameFontSize = 15;
			bodyFontSize = 15;
			imageMultiplier = 0.8;
		}
		
		// Updates athlete panel background colour to reflect the position of the athlete: red for defenders and blue for attackers.
		String position = gameEnvironment.getAthletePosition(athlete);
		if (!(position == null)) {
			if (position.equals(("Attacker"))) {
		        athletePanel.setBackground(new Color(173, 216, 230));
		    } else if (position.equals("Defender")) {
		        athletePanel.setBackground(new Color(255, 204, 204)); 
		    }
		}
	    

	    JLabel athleteName = new JLabel(gameEnvironment.getAthleteName(athlete));
	    athleteName.setFont(new Font("Cooper Black", Font.PLAIN, nameFontSize));
	    athleteName.setHorizontalAlignment(SwingConstants.CENTER);
	    athleteName.setBackground(Color.WHITE); 
	    athleteName.setOpaque(true); 
	    athleteName.setBounds((int)(10 * sizeMultiplier), (int)(11 * sizeMultiplier), (int)(340 * sizeMultiplier), (int)( 57 * sizeMultiplier));
	    athletePanel.add(athleteName);

	    JPanel athleteImagePanel = new JPanel();
	    athleteImagePanel.setBounds((int) (58 * sizeMultiplier), (int) (79 * sizeMultiplier), (int) (247 * sizeMultiplier), (int) (203 * sizeMultiplier));
	    athleteImagePanel.setBackground(Color.WHITE);
	    athletePanel.add(athleteImagePanel);
	    athleteImagePanel.setLayout(new BorderLayout(0, 0));

		JLabel athleteImage = new JLabel("");
		ClassLoader classLoader = getClass().getClassLoader();
		ImageIcon icon = new ImageIcon(classLoader.getResource("Pictures/" + gameEnvironment.getAthleteImageName(athlete) + ".png"));
		Image image = icon.getImage().getScaledInstance((int)((icon.getIconWidth()*imageMultiplier)), (int)((icon.getIconHeight()*imageMultiplier)), Image.SCALE_SMOOTH);
		athleteImage.setIcon(new ImageIcon(image));
		athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImagePanel.add(athleteImage, BorderLayout.CENTER);

	    JLabel athleteInfo = new JLabel(gameEnvironment.athleteToStringHTML(athlete));
	    athleteInfo.setFont(new Font("Calibiri", Font.BOLD, bodyFontSize));
	    athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
	    athleteInfo.setBounds((int)(23 * sizeMultiplier), (int)(305 * sizeMultiplier), (int)(311 * sizeMultiplier), (int)(143 * sizeMultiplier));
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
		
		JLabel playerBalance = new JLabel(gameEnvironment.getMoneyFormatted());
		playerBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playerBalance.setSize(textBoxWidth/2, 70);
		playerBalance.setLocation(textX, 116);
		mainMenuPanel.add(playerBalance);
		
		JLabel weekValue = new JLabel("Week: " + gameEnvironment.getCurrentWeek() + " of " + gameEnvironment.getSeasonLength());
		weekValue.setHorizontalAlignment(SwingConstants.CENTER);
		weekValue.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekValue.setSize(textBoxWidth, textBoxHeight);
		weekValue.setLocation(0, textY);
		mainMenuPanel.add(weekValue);
		
		// Creates a list to store the buttons for the action listener
		ArrayList<JButton> buttonList = new ArrayList<>(); 

		String[] buttonLabels = { "Go to the Club", "Go to the Stadium", "Visit the Market", "Take a bye" };
		

		 //If the season is in the final week then the bye button will update to "end season" and the user will move to the end game confirmation
		if (gameEnvironment.isFinalWeek()) {
			buttonLabels[3] = "End Season";
		}
		
		//Creates 4 buttons evenly spaced with the labels from buttonLabels
		for (int i = 0; i < buttonLabels.length; i++) {
		    JButton button = new JButton(buttonLabels[i]);
		    button.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		    button.setSize(buttonWidth, buttonHeight);
		    button.setLocation(buttonX, buttonY + (buttonSpacing * i));
		    mainMenuPanel.add(button);
		    buttonList.add(button);
		}
		
		
		 // Takes player to the respective menu depending on which button was selected.
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
	
	/**
	 * Displays the club screen with options to view the team properties, player inventory or go back to the main menu
	 */
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
	    		inventoryScreen();
	    		}
	    });
		
		
	}
	
	/**
	 * Displays the team properties screen, showing team name, team members and team reserves
	 * Each athlete panel card can be clicked to  view the details of the selected athlete or the back button returns to the main screen.
	 */
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
		
	   
		// Go to singleAthleteView display depending on which of the athlete panels is selected.
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
	
	/**
	 * Displays the screen for a single specific athlete. It displays the athlete panel card with athlete's name, picture and information.
	 * The athlete's name can be updated if the athlete name is changed and update athlete name is selected.
	 * Different player types such as injured, reserve and team athletes will show different options such as swapping a player with a reserve and changing player position. 
	 * The screen also displays error and result messages.
	 * 
	 * @param athlete the athlete to display the details for
	 */
	private void clubSingleAthleteScreen(Athlete athlete) {
    	int playerTeamSize = gameEnvironment.getTeamList().size();
		
		JPanel clubSingleAthletePanel = createScreenPanel();
		frame.getContentPane().add(clubSingleAthletePanel);
		
	    JPanel athletePanel = new JPanel();
	    athletePanel.setSize(360, 459);
	    athletePanel.setLocation(312, 98);
	    athletePanel.setLayout(null);
	    clubSingleAthletePanel.add(athletePanel);
	    
	    refreshAthletePanel(athletePanel, athlete, 1);
	    
	    // JTextField athleteName will sit over the athlete name from refresh athlete panel so that the name is able to be updated to set athlete's name
	    JTextField athleteName = new JTextField(gameEnvironment.getAthleteName(athlete));
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
    	
    	JButton addToTeamButton = createButtonBelowAthleteCard(312);
    	clubSingleAthletePanel.add(addToTeamButton);
    	addToTeamButton.setVisible(false);
    	
    	
    	/*
    	 * Updates button names for different player types
    	 * If the player is a reserve, the swap button is hidden and the add to team button is shown
    	 * If the player is not injured and is in the main team, adds button to swap athlete's position in the team
    	 * If swap athlete button is added, buttons will move X-coordinates so they will continue to be evenly spaced in the centre of the panel
    	 */
        if (gameEnvironment.athleteIsReserve(athlete)) {
        	if (playerTeamSize < 4) {
        		addToTeamButton.setText("<html><center>"+"Add reserve"+"<br>"+"to team"+"</center></html>");
        		addToTeamButton.setVisible(true);
        		swapButton.setVisible(false);
        	} else {
        		swapButton.setText("<html><center>"+"Swap reserve"+"<br>"+"with player"+"</center></html>");
        	}
        }
        else {
        	swapButton.setText("<html><center>"+"Swap reserve"+"<br>"+"with player"+"</center></html>");
        	
        	if (!gameEnvironment.athleteIsInjured(athlete)) {
        		swapButton.setLocation(centreButtonX - buttonWidth - 20, buttonY);
				setAthleteNameButton.setLocation(centreButtonX + buttonWidth + 20, buttonY);
				
        		JButton swapPositionButton = createButtonBelowAthleteCard(centreButtonX);
        		clubSingleAthletePanel.add(swapPositionButton);
        		swapPositionButton.setText("<html><center>"+"Swap position"+"<br>"+"to " + gameEnvironment.getAthleteAlternatePosition(athlete) +"</center></html>");
        		
        		swapPositionButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent a) {
			    		gameEnvironment.setAthletePosition(athlete, gameEnvironment.getAthleteAlternatePosition(athlete));
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
		
		// Updates the name of the athlete to the input from JTextField if athlete's name has been changed and error text if name input does not meet requirements
		setAthleteNameButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			boolean nameChanged = gameEnvironment.setAthleteName(athlete, athleteName.getText());
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
		

		
		// If there as an available reserve (checkSwappable is true) then it takes the player to the clubAthleteSwapScreen to select athlete to swap with
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
		
		// If the athlete is a reserve and the team is not full, the athlete will be added to the team.
		addToTeamButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
    			gameEnvironment.addAthleteToTeam(athlete, null);
    			clubSingleAthletePanel.setVisible(false);
    			clubTeamPropertiesScreen();
	    	}
	    });
		
	}
	
	/**
	 * Displays the athlete swap screen for swapping an athlete with another player.
	 * The screen includes a text indicating the type of player to swap with, which is the player being swapped and panels for the players that can be swapped. 
	 * Selecting one of the athlete panels will swap the athletes between the team and reserves. The back button returns to the single athlete view.
	 * 
	 * @param athlete is the athlete being swapped with another player
	 * 
	 */
	public void clubAthleteSwapScreen(Athlete athlete) {
		JPanel athleteSwapPanel = createScreenPanel();
		frame.getContentPane().add(athleteSwapPanel);
		
		JLabel swapText = new JLabel();
		swapText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		swapText.setHorizontalAlignment(SwingConstants.CENTER);
		swapText.setSize(width, 70);
		swapText.setLocation(0, 29);
		athleteSwapPanel.add(swapText);
		
		JButton backButton = createBackButton();
		athleteSwapPanel.add(backButton);
				
		JPanel mainAthletePanel = new JPanel();
		mainAthletePanel.setSize((int)(athletePanelWidth*0.75), (int)(athletePanelHeight*0.75));
		mainAthletePanel.setLocation((width - mainAthletePanel.getWidth())/2, 98);
		athleteSwapPanel.add(mainAthletePanel);
		mainAthletePanel.setLayout(null);
		
		refreshAthletePanel(mainAthletePanel, athlete, 0.75);
		
		int numberAthletesPerRow = 0;
		ArrayList<Athlete> swappableList = null;
		
		
		// If the athlete is a reserve, the swappable athletes be on the team, otherwise the swappable players will be reserves.
		if (!gameEnvironment.athleteIsReserve(athlete)) {
			swapText.setText("Select a reserve player to swap with");
			swappableList = gameEnvironment.getReservesList();
			numberAthletesPerRow = swappableList.size();
		} else {
			swapText.setText("Select a player to swap with");
			numberAthletesPerRow = 4;
			swappableList = gameEnvironment.getTeamList();
		}
		
		JPanel athletesPanel = createTeamPanel(numberAthletesPerRow, 460);
        athleteSwapPanel.add(athletesPanel);
		
        ArrayList<JPanel> panels = addAthletesToPanel(athletesPanel, swappableList, numberAthletesPerRow);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		athleteSwapPanel.setVisible(false);
	    		clubSingleAthleteScreen(athlete);
	    		}
	    });
		

		// the player card panel selected will be swapped with the athlete
		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	athleteSwapPanel.setVisible(false);
		            if (!gameEnvironment.athleteIsReserve(athlete)) {
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
	
	/**
	 * Displays the inventory screen.
	 * The screen shows the list of items in the player's inventory.
	 * The screen includes a back button to return to the club screen, the title text indicating where the player is located in the game (inventory)
	 * Also includes the number of items in the inventory and the items in the inventory. Items can be selected and the user will be taken to a screen to use the selected item.
	 */
	private void inventoryScreen() {
		JPanel useItemDisplayPanel = createScreenPanel();
		frame.getContentPane().add(useItemDisplayPanel);
		 
		JButton backButton = createBackButton();
		useItemDisplayPanel.add(backButton);

		
        JLabel InventoryText = createTitleText("Inventory");
        useItemDisplayPanel.add(InventoryText);

		ArrayList<Item> inventoryItems = gameEnvironment.getInventory();
		
		JPanel useItemPanel = createTeamPanel(4, height/2 - 100);
		useItemDisplayPanel.add(useItemPanel);
		
		// Shows the number of items in player inventory and how many spaces left. Inventory is capped at 4 items.
        JLabel inventorySizeText = new JLabel(inventoryItems.size() + "/4 items");
        inventorySizeText.setHorizontalAlignment(SwingConstants.CENTER);
        inventorySizeText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        inventorySizeText.setBounds(155, 210, 100, 24);
        useItemDisplayPanel.add(inventorySizeText);
        
        ArrayList<JPanel> panels = addItemsToPanel(useItemPanel, inventoryItems, 4);
   
        
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	useItemDisplayPanel.setVisible(false);
		        	useSingleItemScreen(inventoryItems.get(index));
		        }
		    });
		}
        
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useItemDisplayPanel.setVisible(false);
				clubScreen();
			}
		});
		
		
	}
	
	/**
	 * Displays the panel to use a single item from the player's inventory 
	 * If use item button is selected, the user will be taken to a screen prompting for a player to use the item on.
	 * Also displays a back button to return to the inventory
	 * 
	 * @param item the item to be used from inventory
	 */
	public void useSingleItemScreen(Item item) {
		JPanel UseSingleItemPanel = createScreenPanel();
		frame.getContentPane().add(UseSingleItemPanel);
		
		JPanel itemPanel = new JPanel();
		itemPanel.setSize(360, 459);
		itemPanel.setLocation(312, 98);
		UseSingleItemPanel.add(itemPanel);
		itemPanel.setLayout(null);
		
		refreshItemPanel(itemPanel, item, 1);
		
		JButton useItemButton = new JButton("Use item");
		useItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		useItemButton.setSize(166, 48);
		useItemButton.setLocation((width - useItemButton.getWidth())/2, 600);
		UseSingleItemPanel.add(useItemButton);
		
		useItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseSingleItemPanel.setVisible(false);
				useItemPlayersDisplay(item);
			}
		});

		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		UseSingleItemPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UseSingleItemPanel.setVisible(false);
				inventoryScreen();
			}
		});
	}

	/**
	 * Displays the panel to select a specific athlete for using the item on
	 * Athletes can be selected from reserves or team list.
	 * The user can select the athlete to use the item on by selecting the athlete's panel.
	 * If the back button is selected the item will not be used and user will return to useSingleItemScreen
	 * 
	 * @param item the item to be used.
	 */
	public void useItemPlayersDisplay(Item item) {
		JPanel UseItemPlayerPanel = new JPanel();
		UseItemPlayerPanel.setBackground(new Color(255, 255, 255));
		UseItemPlayerPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseItemPlayerPanel);
		UseItemPlayerPanel.setLayout(null);
		frame.getContentPane().add(UseItemPlayerPanel);
		 
		JButton backButton = createBackButton();
		UseItemPlayerPanel.add(backButton);
		
		String itemName = gameEnvironment.getItemName(item);
		
        JLabel useLabel = new JLabel("Who do you want to use " + itemName + " on?");
        useLabel.setHorizontalAlignment(SwingConstants.CENTER);
        useLabel.setFont(new Font("Cooper Black", Font.PLAIN, 20));
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
		
		 ArrayList<JPanel> panels = new ArrayList<JPanel>();
		 
		JPanel teamPanel = createTeamPanel(maxAthletesInTeam, teamPanelY);
		UseItemPlayerPanel.add(teamPanel);
		panels.addAll(addAthletesToPanel(teamPanel, gameEnvironment.getTeamList(), maxAthletesInTeam));
		
		JPanel reservesPanel = createTeamPanel(maxAthletesInReserve, reservesPanelY);
		UseItemPlayerPanel.add(reservesPanel);
		panels.addAll(addAthletesToPanel(reservesPanel, gameEnvironment.getReservesList(), maxAthletesInReserve));
        
	    /**
		* Go to useItemSingleAthleteScreen display depends on which of the athlete panels is selected
	    */

		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            UseItemPlayerPanel.setVisible(false);
		            if (index >= gameEnvironment.getTeamList().size()) {
		            	useItemSingleAthleteScreen(gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size()),item);
		            } else {
		            	useItemSingleAthleteScreen(gameEnvironment.getTeamList().get(index),item);
		            }
		            
		        }
		    });
		}
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		UseItemPlayerPanel.setVisible(false);
	    		useSingleItemScreen(item);
	    		}
	    });
	}
	
	/**
	 * Displays the panel for using an item on the specific athlete.
	 * It shows athlete's information on the athlete panel with a button for using the item or returning back to the useItemPlayersDisplay.
	 * If the item is used, the athlete panel card will refresh with the increased statistics and a result text will show. 
	 * The button will then appear to return the user back to the inventory screen.
	 * 
	 * @param athlete the athlete displayed on the panel card that you can use the item on
	 * @param item the item that can be used on the selected athlete
	 */
	public void useItemSingleAthleteScreen(Athlete athlete, Item item) {
		
		
		JPanel UseItemSingleAthletePanel = new JPanel();
		UseItemSingleAthletePanel.setBackground(new Color(255, 255, 255));
		UseItemSingleAthletePanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(UseItemSingleAthletePanel);
		UseItemSingleAthletePanel.setLayout(null);
		
		JButton backButton = createBackButton();
		UseItemSingleAthletePanel.add(backButton);
		
        String itemName = gameEnvironment.getItemName(item);
        
        JLabel resultText = createResultText();
        resultText.setText(itemName + " has been used on athlete!");
        UseItemSingleAthletePanel.add(resultText);
		
		JPanel athletePanel = new JPanel();
		athletePanel.setSize(360, 459);
		athletePanel.setLocation(312, 98);
		UseItemSingleAthletePanel.add(athletePanel);
		athletePanel.setLayout(null);
		
		refreshAthletePanel(athletePanel, athlete, 1);
		
        JButton UseItemButton = new JButton("Use on " + gameEnvironment.getAthleteName(athlete));
        UseItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        UseItemButton.setBounds(312, 586, 360, 47);
        UseItemSingleAthletePanel.add(UseItemButton);
        
        JButton okButton = new JButton("Return to inventory");
        okButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        okButton.setBounds(312, 586, 360, 47);
        UseItemSingleAthletePanel.add(okButton);
		okButton.setVisible(false);
        
		/*
		 *  If use item button is clicked, it will be replaced by a return to inventory button.
		 *  The text showing the result of the action will be shown and item will be used to increase ahtlete's statistics
		 *  The item will be used on the athlete and the athlete will be refreshed to show the updated statistics.
		 */
        UseItemButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.useItemOnAthlete(item, athlete);
	    		refreshAthletePanel(athletePanel, athlete, 1);
	    		UseItemButton.setVisible(false);
	    		okButton.setVisible(true);
	    		backButton.setVisible(false);
	            resultText.setVisible(true);
	    		}
	    });
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		UseItemSingleAthletePanel.setVisible(false);
	    		useItemPlayersDisplay(item);
	    		}
	    });
		
		okButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		UseItemSingleAthletePanel.setVisible(false);
	    		inventoryScreen();
	    		}
	    });
		
		
	}

	
	/**
	 * Displays the stadium screen showing the matches available.
	 * Each optional match shows the team name and the money and number of points available as a reward for winning. 
	 * The "Back" button returns the user to the main menu. If a match is selected, the user will be taken to the confirmMatchView to confirm their game.
	 * Each match can only be played once. If there are no matches left for the week the screen will display: "No matches left this week"
	*/
	public void stadiumScreen() {
		JPanel stadiumPanel = createScreenPanel();
		frame.getContentPane().add(stadiumPanel);
		
		JLabel stadiumText = createTitleText("STADIUM");
		stadiumPanel.add(stadiumText);
		
		JLabel infoText = new JLabel("Select one of the following matches...");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 122);
		stadiumPanel.add(infoText);
		
		int numMatches = gameEnvironment.getStadiumMatches().size();
		if (numMatches == 0) {
			infoText.setText("No matches left this week");
		}
		
		JButton backButton = createBackButton();
		stadiumPanel.add(backButton);
		
		
		int matchPanelDistance = 180;
		int matchPanelWidth = 500;
		int matchPanelHeight = 130;
		
        ArrayList<JPanel> matchPanels = new ArrayList<JPanel>();
        
        // Displays each match available on a panel with the information related to the match including money and points reward and team name.
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
		* Go to confirmMatchView display depending on which of the athlete panels is selected
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
	
	/**
	 * Displays the match confirmation view showing the details of the specific selected match.
	 * Details of the match include player team name, opposition team name, the points and money reward
	 * The user can select between starting the match using the start button or returning to the stadium screen by selecting back.
	 *
	 * @param matchNumber the integer reflecting the position of the selected match in the list of available matches for the week. To be used to select the correct match from gameEnvironment.
	 */
	public void confirmMatchView(int matchNumber) {
		Match match = gameEnvironment.getStadiumMatches().get(matchNumber);
		String oppositionName = gameEnvironment.getMatchTeamName(match);
		int pointsReward = gameEnvironment.getMatchPoints(match);
		String moneyReward = gameEnvironment.getMatchPrize(match);
		String teamName = gameEnvironment.getTeamName();
		
		JPanel matchConfirmView = createScreenPanel();
		frame.getContentPane().add(matchConfirmView);
		
		JLabel matchText = createTitleText("MATCH");
		matchConfirmView.add(matchText);
		
		JLabel errorText = new JLabel("");
		errorText.setHorizontalAlignment(SwingConstants.CENTER);
		errorText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		errorText.setSize(984, 48);
		errorText.setLocation(0, 634);
		errorText.setForeground(Color.RED);
		matchConfirmView.add(errorText);
		
		JButton backButton = createBackButton();
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
	    			playMatchScreen();
	    		} catch (IllegalTeamException e) {
	    			errorText.setText(e.getMessage());
	    			errorText.setVisible(true);
	    		}
	    		}
	    });
		
	}
	
	/**
	 * Displays the screen for playing a match with each encounter within the match
	 * The team names, scores for each team and each athlete in the current encounter is shown.
	 * Each encounter between a player from the user's team and the opposition team will be shown with a text stating the result of the encounter
	 * The match score will be updated after each encounter in the match.
	 * Once all encounters are finished, the end Match screen will be shown with match results.
	 */
	public void playMatchScreen() {
		ArrayList<Athlete> athletes = gameEnvironment.getEncounterAthletes();
		
		JPanel encounterPanel = createScreenPanel();
		frame.getContentPane().add(encounterPanel);
		
		JTextArea encounterText = new JTextArea();
		encounterText.setFont(new Font("Calibri", Font.BOLD, 20));
		encounterText.setBounds(120, 533, 757, 50);
		encounterText.setLineWrap(true); 
		encounterText.setWrapStyleWord(true); 
		encounterText.setEditable(false); 
		encounterText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		encounterPanel.add(encounterText);

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
		
		/*
		 * The athlete panels will be decreased by 30% of the full size athlete card.
		 * An athlete panel for each of the two athletes in the encounter will be shown and the corresponding information updated on their player cards
		 */
		double decreaseSizeMultiplier = 0.7;
		
		for (int i = 0 ; i < 2 ; i ++) {
			JPanel athletePanel = new JPanel();
			athletePanel.setSize((int) (athletePanelWidth * decreaseSizeMultiplier),(int) (athletePanelHeight * decreaseSizeMultiplier));
			athletePanel.setLocation((width/2 - athletePanel.getWidth())/2 + ((width/2) * i), 150);
			encounterPanel.add(athletePanel);
			athletePanel.setLayout(null);
			
			refreshAthletePanel(athletePanel, athletes.get(i), decreaseSizeMultiplier);
		}
		
        int buttonWidth = ((int) (360 * decreaseSizeMultiplier)/2) - 10;
        int buttonHeight = 50;
        int centreButtonX = (width - buttonWidth)/2;
        
		JButton nextButton = new JButton("next");
		nextButton.setSize(buttonWidth, buttonHeight);
		nextButton.setLocation(centreButtonX, 617);
		encounterPanel.add(nextButton);
		nextButton.setFont(new Font("Cooper Black", Font.PLAIN, 21));
		
		/*
		 * Before next is selected, the athletes have not begun the encounter yet
		 * Selecting next for the first time will perform the encounter and update the scores accordingly, as well as show the text result for the encounter.
		 * Selecting next again will refresh the playMatchScreen to show the next encounter and repeat until all encounters and their results have been shown.
		 * When all encounters are complete and the match is finished, the next button will take the user to the end match screen with the results of the match.
		 */
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
	    	    			playMatchScreen();
	    	    		} else {
	    	    			encounterPanel.setVisible(false);
	    	    			endMatch();
	    	    		}
	    	    		
	    	    		}
	    	    });

	    		}
	    });
	}
	
	/**
	 * Displays the end match screen.
	 * The screen shows the winner and score set with specific colours reflecting the result of the match.
	 * If the game results in a win or draw for the user's team, the amount of points and money gained will be shown.
	 * A next button is displayed and will take the user to see the updated athlete panel cards of their team after the match with updated statistics affected by their performance in the match.
	 * End match will then appear to redirect the user to the main menu.
	 */
	public void endMatch() {
		JPanel matchResultPanel = createScreenPanel();
		frame.getContentPane().add(matchResultPanel);
		
		// The results of the game
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
		// Set the colour for the amount of points won to be green
		winningPoints.setForeground(new Color(0, 200, 0)); 

		
		JLabel winningMoney = new JLabel("+ " + gameEnvironment.getMoneyFormatted(prizeMoney));
		winningMoney.setHorizontalAlignment(SwingConstants.CENTER);
		winningMoney.setFont(new Font("Cooper Black", Font.PLAIN, 40));
		winningMoney.setBounds(491, 389, 493, 130);
		matchResultPanel.add(winningMoney);
		// Set the colour for the amount of money won to be green
		winningMoney.setForeground(new Color(0, 200, 0)); 

		 /*
		  * Sets colour of the winning team to reflect the result of the game: green if the user's team won and red in the case of a loss
		  * If the game was a draw, there was no winning team and the name of the winning team is hidden
		  */
		 if (winner.equals(gameEnvironment.getTeamName() )) {
			 winningTeamName.setForeground(new Color(0, 200, 0)); 
		 } else if (winner == "Draw") {
			winnerText.setVisible(false);
		 } else {
			 winningTeamName.setForeground(new Color(200, 0, 0)); 
		     winningMoney.setVisible(false);
		     winningPoints.setVisible(false);
		 }
		 
	    /*
	     * Selecting the next button shows the view of the panel of athlete panel cards in the team with their updated statistics after the match.
	     * The endMatchButton will then be displayed, and if selected redirects to the main menu.
	     */
 		nextButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		matchResultPanel.setVisible(false);
	    		
	    		JPanel updatedTeamPanel = createScreenPanel();
	    		frame.getContentPane().add(updatedTeamPanel);
	    		
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
	            
	    		ArrayList<Athlete> teamList = gameEnvironment.getTeamList();
	    		
	    		addAthletesToPanel(teamPanel, teamList, 4);
	            	            
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
	
	

	private void marketScreen() {
		
		JPanel marketpanel = new JPanel();
		marketpanel.setBackground(new Color(255, 255, 255));
		marketpanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(marketpanel);
		marketpanel.setLayout(null);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		marketpanel.add(balanceText);
		
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
		
		
		JLabel marketlabel = createTitleText("Market");
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
				buyItemsScreen();
				
			}
		});
		
		
	
		
		
	}
	
	public void buyItemsScreen() {
		JPanel itemDisplayPanel = createScreenPanel();
		frame.getContentPane().add(itemDisplayPanel);
		 
		JButton backButton = createBackButton();
		itemDisplayPanel.add(backButton);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		itemDisplayPanel.add(balanceText);
		
        JLabel buyItemText = createTitleText("Buy Items");
        itemDisplayPanel.add(buyItemText);
        
		JLabel infoText = new JLabel("");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 122);
		itemDisplayPanel.add(infoText);
        
        ArrayList<Item> shopItems = gameEnvironment.getShopItems();

		JPanel ItemPanel = createTeamPanel(shopItems.size(), height/2 - 100);
		itemDisplayPanel.add(ItemPanel);

        ArrayList<JPanel> panels = addItemsToPanel(ItemPanel, shopItems, shopItems.size());
        
        if (shopItems.size() == 0) {
        	infoText.setText("No items left to purchase this week. Come back next week and the market will be restocked");
        	ItemPanel.setVisible(false);
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
        
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemDisplayPanel.setVisible(false);
				marketScreen();
			}
		});
		
		
	}
	
	public void buySingleItemScreen(Item item) {
		JPanel BuySingleItemPanel = createScreenPanel();
		frame.getContentPane().add(BuySingleItemPanel);
		
		
		JButton backButton = createBackButton();
		BuySingleItemPanel.add(backButton);
		
        String itemName = gameEnvironment.getItemName(item);
        
        JLabel resultText = createResultText();
        resultText.setText(itemName + " has purchased and added to inventory!");
        BuySingleItemPanel.add(resultText);
        
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		BuySingleItemPanel.add(balanceText);
		
        
        JLabel errorText = createErrorText();
        BuySingleItemPanel.add(errorText);
        
		JPanel itemPanel = new JPanel();
		itemPanel.setSize(360, 459);
		itemPanel.setLocation(312, 98);
		BuySingleItemPanel.add(itemPanel);
		itemPanel.setLayout(null);
		
		refreshItemPanel(itemPanel, item, 1);
		
        JButton purchaseItemButton = new JButton("Purchase Item");
        purchaseItemButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        purchaseItemButton.setBounds(312, 586, 360, 47);
        BuySingleItemPanel.add(purchaseItemButton);
        

        purchaseItemButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		try {
	    			gameEnvironment.purchaseItem(item);
	    			errorText.setVisible(false);
	    			purchaseItemButton.setVisible(false);
		            resultText.setVisible(true);
		            balanceText.setText("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
					} catch (InventoryFullException e) {
						errorText.setVisible(true);
		    			errorText.setText(e.getMessage());
					} catch (InsufficientFundsException e) {
						errorText.setVisible(true);
		    			errorText.setText(e.getMessage());
					}
	    	}
	    });
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		BuySingleItemPanel.setVisible(false);
	    		buyItemsScreen();
	    		}
	    });
	}
		
	

	public void  buyPlayerScreen(){
		JPanel BuyPlayerPanel = createScreenPanel();
		frame.getContentPane().add(BuyPlayerPanel);
		
		JLabel BuyPlayerLabel = createTitleText("Purchase athlete");
		BuyPlayerPanel.add(BuyPlayerLabel);
		
		JButton backButton = createBackButton();
		BuyPlayerPanel.add(backButton);
		
		JLabel infoText = new JLabel("");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 122);
		BuyPlayerPanel.add(infoText);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		BuyPlayerPanel.add(balanceText);
		
		ArrayList<Athlete> shopAthletes = gameEnvironment.getShopAthletes();
		gameEnvironment.updateShopStats();

		JPanel athletesPanel = createTeamPanel(shopAthletes.size(),  height/2 - 100);
		BuyPlayerPanel.add(athletesPanel);
		
		ArrayList<JPanel> panels = addAthletesToPanel(athletesPanel, shopAthletes, shopAthletes.size());
		
		if (shopAthletes.size() == 0) {
        	infoText.setText("No athletes left to purchase this week. Come back next week and the market will be restocked");
        	athletesPanel.setVisible(false);
        }
        
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	BuyPlayerPanel.setVisible(false);
		        	buySingleAthletePanel(shopAthletes.get(index));
		        }
		    });
		}
        
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyPlayerPanel.setVisible(false);
				marketScreen();
				
			}
		});
	}
	
	public void buySingleAthletePanel(Athlete athlete) {
		JPanel BuySingleAthletePanel = createScreenPanel();
		frame.getContentPane().add(BuySingleAthletePanel);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		BuySingleAthletePanel.add(balanceText);
		
		JButton backButton = createBackButton();
		BuySingleAthletePanel.add(backButton);
		
		JLabel errorText = createErrorText();
        BuySingleAthletePanel.add(errorText);
        
        JLabel resultText = createResultText();
        resultText.setText("You have purchased " + gameEnvironment.getAthleteName(athlete));
        BuySingleAthletePanel.add(resultText);        
		
		JPanel athletePanel = new JPanel();
		athletePanel.setSize(360, 459);
		athletePanel.setLocation(312, 98);
		BuySingleAthletePanel.add(athletePanel);
		athletePanel.setLayout(null);
		
		refreshAthletePanel(athletePanel, athlete, 1);
        

        JButton purchaseAthleteButton = new JButton("Purchase Athlete");
        purchaseAthleteButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        purchaseAthleteButton.setBounds(312, 586, 360, 47);
        BuySingleAthletePanel.add(purchaseAthleteButton);
        
    	JButton addToReserves = createButtonBelowAthleteCard(502);
    	addToReserves.setText("<html><center>"+"Add athlete"+"<br>"+"as reserve"+"</center></html>");
    	BuySingleAthletePanel.add(addToReserves);
    	addToReserves.setVisible(false);
    	
    	JButton addToTeam = createButtonBelowAthleteCard(312);
    	addToTeam.setText("<html><center>"+"Add athlete"+"<br>"+"to team"+"</center></html>");
    	BuySingleAthletePanel.add(addToReserves);
    	addToTeam.setVisible(false);
    	
    	JButton addToTeamFull = createButtonBelowAthleteCard(312);
    	addToTeamFull.setText("<html><center>"+"Add athlete"+"<br>"+"to team"+"</center></html>");
    	BuySingleAthletePanel.add(addToTeamFull);
    	addToTeamFull.setVisible(false);
    	
    	JButton addAsDefender = createButtonBelowAthleteCard(502);
    	addAsDefender.setText("<html><center>"+"Add athlete"+"<br>"+"as defender"+"</center></html>");
    	BuySingleAthletePanel.add(addAsDefender);
    	addAsDefender.setVisible(false);
    	
    	JButton addAsAttacker = createButtonBelowAthleteCard(312);
    	addAsAttacker.setText("<html><center>"+"Add athlete"+"<br>"+"as attacker"+"</center></html>");
    	BuySingleAthletePanel.add(addAsAttacker);
    	addAsAttacker.setVisible(false);
    	
        

        purchaseAthleteButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent a) {
	    		try {
	    			gameEnvironment.purchaseAthlete(athlete);
	    			errorText.setVisible(false);
	    			purchaseAthleteButton.setVisible(false);
		            resultText.setVisible(true);
		            backButton.setVisible(false);
		            balanceText.setText("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		        	addToReserves.setVisible(true);
		        	if (gameEnvironment.getTeamList().size() < 4) {
		            	addToTeam.setVisible(false);
		        	} else {
		        		addToTeamFull.setVisible(true);
		        	}
					} catch (InsufficientFundsException e) {
						errorText.setVisible(true);
		    			errorText.setText(e.getMessage());
					} catch (LimitException e) {
						errorText.setVisible(true);
		    			errorText.setText(e.getMessage());
					}
	    	}
	    });
        
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuySingleAthletePanel.setVisible(false);
				buyPlayerScreen();
				
			}
		});
        
		
		addToReserves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameEnvironment.addAthleteAsReserve(athlete);
					resultText.setText("Athlete added as reserve");
					backButton.setVisible(true);
					addToReserves.setVisible(false);
					addToTeam.setVisible(false);
					addToTeamFull.setVisible(false);
					
				} catch (LimitException err) {
					resultText.setVisible(false);
					errorText.setText(err.getMessage());
					errorText.setVisible(true);
				}
					
				}
		});
		
		
		addToTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	addAsAttacker.setVisible(true);
		    	addAsDefender.setVisible(true);
				addToReserves.setVisible(false);
				addToTeam.setVisible(false);
				addToTeamFull.setVisible(false);
				errorText.setVisible(false);
				resultText.setVisible(false);
			}
		});
		
		addAsAttacker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.addAthleteToTeam(athlete, "Attacker");
		    	addAsAttacker.setVisible(false);
		    	addAsDefender.setVisible(false);
		    	backButton.setVisible(true);
				resultText.setText("Athlete added to team as an attacker");
				resultText.setVisible(true);
				backButton.setVisible(true);
			}
		});
		
		addAsDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnvironment.addAthleteToTeam(athlete, "Defender");
		    	addAsAttacker.setVisible(false);
		    	addAsDefender.setVisible(false);
		    	backButton.setVisible(true);
				resultText.setText("Athlete added to team as a defender");
				resultText.setVisible(true);
				backButton.setVisible(true);
			}
		});
		
		// If the team is full, user will be taken to swapPurchaseAthleteScreen to select which player to swap with.
		
		addToTeamFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuySingleAthletePanel.setVisible(false);
				buyAthleteSwapScreen(athlete);
			}
		});

	}
	
	private void buyAthleteSwapScreen(Athlete athlete) {
		JPanel athleteSwapPanel = createScreenPanel();
		frame.getContentPane().add(athleteSwapPanel);
		
		JLabel swapText = new JLabel("Select a player to swap with, this player will be moved to the reserves");
		swapText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		swapText.setHorizontalAlignment(SwingConstants.CENTER);
		swapText.setSize(width, 70);
		swapText.setLocation(0, 29);
		athleteSwapPanel.add(swapText);
		
		JPanel mainAthletePanel = new JPanel();
		mainAthletePanel.setSize((int)(athletePanelWidth*0.75), (int)(athletePanelHeight*0.75));
		mainAthletePanel.setLocation((width - mainAthletePanel.getWidth())/2, 98);
		athleteSwapPanel.add(mainAthletePanel);
		mainAthletePanel.setLayout(null);
		
		refreshAthletePanel(mainAthletePanel, athlete, 0.75);
		
		int numberAthletesPerRow = 4;
		ArrayList<Athlete> swappableList = gameEnvironment.getTeamList();
		
		JPanel athletesPanel = createTeamPanel(numberAthletesPerRow, 460);
        athleteSwapPanel.add(athletesPanel);
		
        ArrayList<JPanel> panels = addAthletesToPanel(athletesPanel, swappableList, numberAthletesPerRow);
		
		// the player card panel selected will be swapped with the athlete
		for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	athleteSwapPanel.setVisible(false);
	            	Athlete swappedAthlete = (swappableList.get(index));
		            gameEnvironment.addAthleteToTeamFull(athlete, swappedAthlete);

		            clubTeamPropertiesScreen();
		        }
		    });
		}
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
		
		JButton sellPlayer = new JButton("Sell Player");
		sellPlayer.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		sellPlayer.setBounds(297, 107, 308, 122);
		sellScreenpannel.add(sellPlayer);
		
		sellPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellScreenpannel.setVisible(false);
				sellPlayerScreen();
			}
		});
		
		
		JButton sellItemButton = new JButton("Sell Item");
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
		JPanel sellPlayerPanel = createScreenPanel();
		frame.getContentPane().add(sellPlayerPanel);
		
		JLabel sellPlayerLabel = createTitleText("Sell Player");
		sellPlayerPanel.add(sellPlayerLabel);
		
		JButton backButton = createBackButton();
		sellPlayerPanel.add(backButton);
		
		ArrayList<Athlete> teamList = gameEnvironment.getTeamList();
		ArrayList<Athlete> reservesList = gameEnvironment.getReservesList();
		
		JPanel teamPanel = createTeamPanel(teamList.size(), 191);
		sellPlayerPanel.add(teamPanel);
		
		JPanel reservesPanel = createTeamPanel(reservesList.size(), 460);
		sellPlayerPanel.add(reservesPanel);
		
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		panels.addAll(addAthletesToPanel(teamPanel, teamList, teamList.size()));
		panels.addAll(addAthletesToPanel(reservesPanel, reservesList, reservesList.size()));
    
        for (int i = 0; i < panels.size(); i++) {
			final int index = i;
		    JPanel panel = panels.get(i);
		    panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		        	sellPlayerPanel.setVisible(false);
		        	Athlete athlete = null;
		        	 if (index >= gameEnvironment.getTeamList().size()) {
		        		 athlete = gameEnvironment.getReservesList().get(index - gameEnvironment.getTeamList().size());
		        	 } else {
		        		 athlete = gameEnvironment.getTeamList().get(index);
		        	 }
		        	 sellSingleAthleteView(athlete);
		        }
		    });
		}
            
    		backButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				sellPlayerPanel.setVisible(false);
    				marketScreen();
    			}
    		});
		
	}
	
	public void sellSingleAthleteView(Athlete athlete) {
		JPanel sellSingleAthletePanel = createScreenPanel();
		frame.getContentPane().add(sellSingleAthletePanel);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		sellSingleAthletePanel.add(balanceText);
		
		JButton backButton = createBackButton();
		sellSingleAthletePanel.add(backButton);
		
		JLabel errorText = createErrorText();
		sellSingleAthletePanel.add(errorText);
        
        JLabel resultText = createResultText();
        resultText.setText("You have sold " + gameEnvironment.getAthleteName(athlete) + ". They have be removed from your team");
        sellSingleAthletePanel.add(resultText);        
		
		JPanel athletePanel = new JPanel();
		athletePanel.setSize(360, 459);
		athletePanel.setLocation(312, 98);
		sellSingleAthletePanel.add(athletePanel);
		athletePanel.setLayout(null);
		
		refreshAthletePanel(athletePanel, athlete, 1);
        
        JButton sellButton = new JButton("Sell Athlete");
        sellButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        sellButton.setBounds(312, 586, 360, 47);
        sellSingleAthletePanel.add(sellButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		sellSingleAthletePanel.setVisible(false);
	    		marketScreen();
	    		}
	    });

		sellButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.sellPlayer(athlete);
	    		sellButton.setVisible(false);
	    		resultText.setVisible(true);
	    		balanceText.setText("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
	    		}
	    });
	}
	
	public void sellItemspanel() {
		JPanel sellItemDisplayPanel = createScreenPanel();
		frame.getContentPane().add(sellItemDisplayPanel);
		 
		JButton backButton = createBackButton();
		sellItemDisplayPanel.add(backButton);

        JLabel SellItemText = createTitleText("Sell Items");
        sellItemDisplayPanel.add(SellItemText);

                                
        ArrayList<Item> inventoryItems = gameEnvironment.getInventory();
		
		JPanel itemsPanel = createTeamPanel(inventoryItems.size(), 191);
		sellItemDisplayPanel.add(itemsPanel);
				
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		panels.addAll(addItemsToPanel(itemsPanel, inventoryItems, inventoryItems.size()));
        
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		sellItemDisplayPanel.add(balanceText);
        
		JLabel infoText = new JLabel("");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 122);
		sellItemDisplayPanel.add(infoText);
        
        if (inventoryItems.size() == 0) {
        	infoText.setText("No items in inventory to sell");
        	itemsPanel.setVisible(false);
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
        
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sellItemDisplayPanel.setVisible(false);
				marketScreen();
			}
		});
		
		
	}
	
	public void sellSingleItemScreen(Item item) {
		
		
		// JR PLACEHOLDER
		
		JPanel sellSingleItemPanel = createScreenPanel();
		frame.getContentPane().add(sellSingleItemPanel);
		
		JLabel balanceText = new JLabel("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
		balanceText.setHorizontalAlignment(SwingConstants.TRAILING);
		balanceText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		balanceText.setSize(width, 25);
		balanceText.setLocation(0, 122);
		sellSingleItemPanel.add(balanceText);
		
		JButton backButton = createBackButton();
		sellSingleItemPanel.add(backButton);
        
        JLabel resultText = createResultText();
        resultText.setText("You have sold " + gameEnvironment.getItemName(item) + ". It has been removed from your inventory.");
        sellSingleItemPanel.add(resultText);        
		
		JPanel itemPanel = new JPanel();
		itemPanel.setSize(360, 459);
		itemPanel.setLocation(312, 98);
		sellSingleItemPanel.add(itemPanel);
		itemPanel.setLayout(null);
		
		refreshItemPanel(itemPanel, item, 1);
        
        JButton sellButton = new JButton("Sell Item");
        sellButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        sellButton.setBounds(312, 586, 360, 47);
        sellSingleItemPanel.add(sellButton);
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		sellSingleItemPanel.setVisible(false);
	    		marketScreen();
	    		}
	    });

		sellButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.sellItem(item);
	    		sellButton.setVisible(false);
	    		resultText.setVisible(true);
	    		balanceText.setText("Player balance: " + gameEnvironment.getMoneyFormatted() + "   ");
	    		}
	    });
		
	}
	
	/**
	 * Displays the screen to confirm if the user selects to move to the next week.
	 * The user has the option to return to the menu screen and continue in the week or move to the next week in the season.
	 */
	private void byeConfirmationScreen() {
		JPanel byeConfirmationPanel = createScreenPanel();
		frame.getContentPane().add(byeConfirmationPanel);
		
		JLabel byeText = createTitleText("BYE");
		byeConfirmationPanel.add(byeText);
		
		JLabel infoText = new JLabel("Take a bye? The season will move to the next week");
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		infoText.setSize(width, 25);
		infoText.setLocation(0, 110);
		byeConfirmationPanel.add(infoText);
		
		JButton yesButton = new JButton("Yes");
		yesButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		yesButton.setSize(192, 48);
		yesButton.setLocation((width - yesButton.getWidth())/2, 321);
		byeConfirmationPanel.add(yesButton);
		
		JButton backButton = createBackButton();
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
	    		if (!gameEnvironment.checkGameContinues()) {
	    			endGameSummaryScreen();
	    		}
	    		byeInfoScreen();
	    		}
	    });
		
	}
	
	/**
	 * Displays the information for the bye including the current week
	 * Each athlete panel card for all athletes in the team is shown with their statistics updated to reflect the restoration of their stamina after the week had ended. 
	 * Selecting a the panel card for an athlete will redirect the user to the train athlete screen.
	 */
	private void byeInfoScreen() {
		gameEnvironment.increaseWeek();
		
		JPanel byeInfoPanel = createScreenPanel();
		frame.getContentPane().add(byeInfoPanel);
		
		JLabel weekText = new JLabel("Season increased to week " + gameEnvironment.getCurrentWeek());
		weekText.setHorizontalAlignment(SwingConstants.CENTER);
		weekText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekText.setSize(width, 25);
		weekText.setForeground(Color.GREEN);
		weekText.setLocation(0, 50);
		byeInfoPanel.add(weekText);
		
		JLabel staminaText = new JLabel("All athlete's stamina have been increased");
		staminaText.setHorizontalAlignment(SwingConstants.CENTER);
		staminaText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		staminaText.setSize(width, 25);
		staminaText.setForeground(Color.GREEN); 
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
	                byeTrainAthleteScreen(trainedAthlete);
		            
		        }
		    });
		}
	}
	
	/**
	 * Displays the screen showing the selected athlete's panel card with specific information about the athlete
	 * Options to train the athlete selecting the train button or return to the bye info screen to select another athlete
	 * If the athlete is trained, the athlete's panel card will refresh to reflect their updated statistics and show text with the result of the action.
	 * A button with text "ok" will appear and when selected will move to a random event screen if a random event occurs, otherwise returns to the main menu to play the next week.
	 * @param athlete the select athlete that can be trained
	 */
	private void byeTrainAthleteScreen(Athlete athlete) {
        
		JPanel singleAthletePanel = createScreenPanel();
		frame.getContentPane().add(singleAthletePanel);		
		
	    JPanel athletePanel = new JPanel();
	    athletePanel.setSize(athletePanelWidth, athletePanelHeight);
	    athletePanel.setLocation(312, 98);
	    athletePanel.setLayout(null);
	    singleAthletePanel.add(athletePanel);
		
		refreshAthletePanel(athletePanel, athlete, 1);
		
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
		
		JButton backButton = createBackButton();
		singleAthletePanel.add(backButton);
		
        JLabel resultText = createResultText();
        resultText.setText("Athlete has been trained");
        singleAthletePanel.add(resultText);        
        
        /*
         * If the athlete is selected to be trained, the athlete's panel card will update to reflect their updated statistics. 
         * The ok button will appear and the train and back buttons will not be visible
         */
		trainButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		gameEnvironment.trainAthlete(athlete);
	    		refreshAthletePanel(athletePanel, athlete, 1);
	    		trainButton.setVisible(false);
	    		okButton.setVisible(true);
	    		backButton.setVisible(false);
	            resultText.setVisible(true);
	    		}
	    });
		
		backButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		byeInfoScreen();
	    		}
	    });
		
		/*
		 * If a random event occurs in the game environment at the end of the week, the user will move to the random events screen when selecting ok
		 * Otherwise, the user will return to the main menu and the game will continue in the next week.
		 */
		okButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent a) {
	    		singleAthletePanel.setVisible(false);
	    		if(gameEnvironment.getTeamList().size() == 0) {
	    			mainMenu();
	    		}else {
	    		Map<String, Object> randomEvents = gameEnvironment.getRandomEvent();
	    		String result = (String) randomEvents.get("eventType");
	    		if (result == "rest") {
	    		mainMenu();
	    		}else {
	    			randomEventScreen(result);
	    			
	    		}
	    }}});
		
	}
	
	/**
	 * Displays the end game confirmation screen, prompting a user to confirm ending the game or returning to the main menu using the back button.
	 * The end game confirmation screen is only displayed when the user has reached their last week of the season and selects end game.
	 */
	private void endGameConfirmationScreen() {
		JPanel endConfirmationScreen = createScreenPanel();
		frame.getContentPane().add(endConfirmationScreen);
		
		JLabel endText = createTitleText("END");
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
	    		endGameSummaryScreen();
	    		}
	    });
	}
	
	/**
	 * Displays the summary of the game played.
	 * If the game ended early due to a lack of money and players to play any matches, the game ended early text will be shown.
	 * Summary of the game includes the number of weeks played out of the season length, points and money earned over the game and the name of the user's team.
	 * The user can select between quitting the game or playing again, in the latter case a new game will begin and a setup screen will appear.
	 */
	private void endGameSummaryScreen() {
		JPanel gameSummaryPanel = createScreenPanel();
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
		
		if (!gameEnvironment.checkGameContinues()) {
			JLabel infoText = new JLabel("Game ended early. You didn't have enough players!");
			infoText.setHorizontalAlignment(SwingConstants.CENTER);
			infoText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			infoText.setSize(width, 25);
			infoText.setLocation(0, 110);
			gameSummaryPanel.add(infoText);
		}
		
		JLabel teamNameText = new JLabel(teamName);
		teamNameText.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		teamNameText.setSize(984, 91);
		teamNameText.setLocation(0, 155);
		gameSummaryPanel.add(teamNameText);
		
		JLabel seasonLength = new JLabel("Season length: " + weeks + "/" + gameEnvironment.getSeasonLength() + " weeks");
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
		randomEventText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		
		JButton continueButton = new JButton("Continue");
		continueButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		continueButton.setBounds(328, 434, 322, 62);
		randomEventPanel.add(continueButton);
		continueButton.setVisible(false);
		
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomEventPanel.setVisible(false);
				mainMenu();
				
			}
		});
		
		JButton randomEventsButton = new JButton("Rest");
		randomEventsButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		randomEventsButton.setBounds(328, 359, 322, 62);
		randomEventPanel.add(randomEventsButton);
		randomEventsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				continueButton.setVisible(true);
				randomEventsButton.setVisible(false);
				
				
		        if (result.equals("increaseStat")) {
		            randomEventText.setText("An athlete in your team trained with a pro, thier stats have increased ");
		        } else if (result.equals("athleteJoins")) {
		            randomEventText.setText("An aspiring quditch player has snuck into your team");
		        }else if (result.equals("athleteQuits")) {
		        	randomEventText.setText("an athlete has left the Team, be sure to manage your stamina");
		        	
		        	
		        }else if (result.equals("athleteQuitsFalse")) {
		        	randomEventText.setText("An athlete in your Team considered leaving");
		        	
		        }
		    }
		});
		}

		
	
	/**
	 * Closes the application window by disposing the main frame
	 */
	public void closeWindow() {
		frame.dispose();
	}
		
	
		




	

}

		
		
		
		


		
		
	
	
	
	



