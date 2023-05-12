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
import javax.swing.border.Border;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
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
        frame.add(teamPropertiesPanel);
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        JPanel athletePanel = new JPanel();
		athletePanel.setSize(360, 459);
		athletePanel.setLocation(312, 98);
		teamPropertiesPanel.add(athletePanel);
		athletePanel.setLayout(null);
		
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
		
		
		
	}
}
