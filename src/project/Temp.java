package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class Temp {
	int width = 984;
	int height = 711;
	
	private JFrame frame;

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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setSize(1000, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
		
		JTextField athleteName = new JTextField(athlete.getName());
		athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		athleteName.setHorizontalAlignment(SwingConstants.CENTER);
		athleteName.setBounds(10, 11, 340, 57);
		athletePanel.add(athleteName);
		athleteName.setColumns(10);
		
		JPanel athleteImage = new JPanel();
		athleteImage.setBackground(Color.WHITE);
		athleteImage.setBounds(58, 79, 247, 203);
		athletePanel.add(athleteImage);
		athleteImage.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Temp.class.getResource("/Pictures/female14.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		athleteImage.add(lblNewLabel, BorderLayout.CENTER);
		
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

		
		
	}

}
