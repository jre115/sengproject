package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Temp {
	int width = 984;
	int height = 711;
	
	private JFrame frame;
	private GameEnvironment gameEnvironment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temp window = new Temp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Temp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		GameEnvironment gameEnvironment = new GameEnvironment(); 
        Athlete athlete = new Athlete();
        gameEnvironment.setTeamName("TESTING HERE");
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(new Color(255, 255, 255));
		mainMenuPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		frame.getContentPane().add(mainMenuPanel);
		
		int buttonHeight = 90;
		int buttonSpacing = 130;
		int buttonWidth = 367;
		int buttonX = (width - buttonWidth)/2;
		int buttonY = 180;
		
		JLabel titleText = new JLabel("Main Men");
		titleText.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		titleText.setSize(width, 70);
		titleText.setLocation(0, 29);
		mainMenuPanel.add(titleText);
		
		
		JButton clubButton = new JButton("Go to the Club");
		clubButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		clubButton.setSize(buttonWidth, buttonHeight);
		clubButton.setLocation(buttonX, buttonY);
		mainMenuPanel.add(clubButton);
		
		int textBoxWidth = (width - buttonWidth)/2;
		int textX = (width + buttonWidth) / 2;
		int textY = 70;
		
		JLabel playerBalance = new JLabel(gameEnvironment.getMoneyFormatted());
		playerBalance.setHorizontalAlignment(SwingConstants.TRAILING);
		playerBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		playerBalance.setSize(textBoxWidth/2, 70);
		playerBalance.setLocation(textX, 116);
		mainMenuPanel.add(playerBalance);
		
		JLabel pointsBalance = new JLabel("0 points");
		pointsBalance.setHorizontalAlignment(SwingConstants.CENTER);
		pointsBalance.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		pointsBalance.setSize(textBoxWidth/2, 70);
		pointsBalance.setLocation(textX + textBoxWidth/2, 116);
		mainMenuPanel.add(pointsBalance);
		
		JLabel weekValue = new JLabel("Week: " + gameEnvironment.getCurrentWeek() + " of " + gameEnvironment.getSeasonLength());
		weekValue.setHorizontalAlignment(SwingConstants.CENTER);
		weekValue.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		weekValue.setSize(textBoxWidth, 70);
		weekValue.setLocation(0, 116);
		mainMenuPanel.add(weekValue);
		
		
		
	}
}
