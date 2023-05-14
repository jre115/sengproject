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
		
		JPanel teamPropertiesPanel = new JPanel();
		teamPropertiesPanel.setBackground(new Color(255, 255, 255));
		teamPropertiesPanel.setBounds(0, 0, width, height);
		frame.getContentPane().add(teamPropertiesPanel);
		teamPropertiesPanel.setLayout(null);
		frame.getContentPane().add(teamPropertiesPanel);
		 
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
        
        
        JLabel reservesText = new JLabel("Reserves");
        reservesText.setHorizontalAlignment(SwingConstants.LEFT);
        reservesText.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        reservesText.setBounds(72, 425, 93, 24);
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
		reservesPanel.setSize(840, 48);
		reservesPanel.setLocation(72, 460);
		reservesPanel.setBackground(Color.WHITE);
		reservesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		teamPropertiesPanel.add(reservesPanel);
		
        JLabel errorText = new JLabel("There are no reserve athletes to swap with.");
        errorText.setHorizontalAlignment(SwingConstants.LEFT);
        errorText.setFont(new Font("Calibri", Font.PLAIN, 20));
        errorText.setSize(364, 25);
        errorText.setLocation((width - errorText.getWidth())/2, 668);
		errorText.setForeground(new Color(255, 0, 0));
		teamPropertiesPanel.add(errorText);
        //errorText.setVisible(false);
		

        
        
        int panelSpacing = 20; // adjust as desired
        int numAthletesPerRow = 4; // adjust as desired

        for (int i = 0; i < gameEnvironment.getTeamList().size(); i++) {
            Athlete athlete = gameEnvironment.getTeamList().get(i);
            
            JPanel athletePanel = new JPanel();
            athletePanel.setBounds((athletePanelWidth + panelSpacing) * (i % numAthletesPerRow) + panelSpacing, 
                                   (athletePanelHeight + panelSpacing) * (i / numAthletesPerRow) + panelSpacing, 
                                   athletePanelWidth, athletePanelHeight);
            teamPanel.add(athletePanel);
            athletePanel.setLayout(null);

            JTextField athleteName = new JTextField(athlete.getName());
            athleteName.setFont(new Font("Cooper Black", Font.PLAIN, 11));
            athleteName.setHorizontalAlignment(SwingConstants.CENTER);
            athleteName.setBounds(4, 4, 136, 23);
            athletePanel.add(athleteName);
            athleteName.setColumns(10);

            JPanel athleteImagePanel = new JPanel();
            athleteImagePanel.setSize(82, 66);
            athleteImagePanel.setLocation((athletePanel.getWidth() - athleteImagePanel.getWidth())/2, 35);
            athleteImagePanel.setBackground(Color.WHITE);
            athletePanel.add(athleteImagePanel);
            athleteImagePanel.setLayout(new BorderLayout(0, 0));

            ImageIcon icon = new ImageIcon(Temp.class.getResource("/Pictures/" + athlete.getImageName() + ".png"));
            Image img = icon.getImage().getScaledInstance(72, 48, Image.SCALE_SMOOTH); // reduced athlete image by 50%
            JLabel athleteImage = new JLabel(new ImageIcon(img));
            athleteImagePanel.add(athleteImage, BorderLayout.CENTER);
            athleteImage.setHorizontalAlignment(SwingConstants.CENTER);
                
            JLabel athleteInfo = new JLabel(athlete.toStringHTML());
            athleteInfo.setFont(new Font("Calibiri", Font.BOLD, 10));
            athleteInfo.setHorizontalAlignment(SwingConstants.CENTER);
            athleteInfo.setBounds(4, 98, 136, 85);
            athletePanel.add(athleteInfo);
        }

        
        

        

		
		
	}
}
