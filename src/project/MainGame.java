package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;


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
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		backButton.setBounds(10, 11, 81, 48);
		teamPropertiesPanel.add(backButton);
		
        JLabel teamNameText = new JLabel(gameEnvironment.getTeamName());
        teamNameText.setHorizontalAlignment(SwingConstants.CENTER);
        teamNameText.setFont(new Font("Cooper Black", Font.PLAIN, 40));
        teamNameText.setSize(469, 100);
        teamNameText.setLocation((width - teamNameText.getWidth())/2, 100);
        
        // Create a LineBorder with black color and 5 pixels of thickness
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        teamNameText.setBorder(border);
        
        teamPropertiesPanel.add(teamNameText);
        frame.getContentPane().add(teamPropertiesPanel);
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
		
	}
	private void marketScreen() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton Back = new JButton("back");
		Back.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	    		mainMenu();
				
			}
		});
		Back.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(Back);
		
		JLabel lblNewLabel = new JLabel("Market");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(220, 15, 190, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel Money = new JLabel(gameEnvironment.getMoneyFormatted());
		Money.setBounds(390, 11, 110, 23);
		frame.getContentPane().add(Money);
		
		JLabel Week = new JLabel("Week: " + gameEnvironment.getCurrentWeek() + " of " + gameEnvironment.getSeasonLength());
		Week.setBounds(535, 11, 105, 23);
		frame.getContentPane().add(Week);
		
		JButton btnNewButton_1 = new JButton("Sell");
		btnNewButton_1.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		btnNewButton_1.setBounds(264, 76, 110, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton Buyplayerbutton = new JButton("Buy Player");
		Buyplayerbutton.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		Buyplayerbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Buyplayerbutton.setBounds(264, 134, 110, 42);
		frame.getContentPane().add(Buyplayerbutton);
		
		JButton btnNewButton_3 = new JButton("Buy Item");
		btnNewButton_3.setFont(new Font("Cooper Black", Font.PLAIN, 11));
		btnNewButton_3.setBounds(264, 191, 110, 37);
		frame.getContentPane().add(btnNewButton_3);
	}
}
	
	

