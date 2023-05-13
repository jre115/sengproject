package project;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class BuyPlayer {
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyPlayer window = new BuyPlayer();
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
	public BuyPlayer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 675, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("select athlete");
		lblNewLabel.setBounds(240, 11, 201, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel ath1 = new JPanel();
		ath1.setBounds(21, 59, 117, 97);
		frame.getContentPane().add(ath1);
		ath1.setLayout(null);
		
		JLabel ath1name = new JLabel("New label");
		ath1name.setBounds(10, 5, 107, 14);
		ath1.add(ath1name);
		
		JLabel ath1info = new JLabel("New label");
		ath1info.setBounds(10, 30, 97, 56);
		ath1.add(ath1info);
		
		JPanel ath2 = new JPanel();
		ath2.setBounds(148, 59, 117, 97);
		frame.getContentPane().add(ath2);
		ath2.setLayout(null);
		
		JLabel ath2name = new JLabel("New label");
		ath2name.setBounds(10, 5, 97, 14);
		ath2.add(ath2name);
		
		JLabel ath2info = new JLabel("New label");
		ath2info.setBounds(10, 30, 97, 56);
		ath2.add(ath2info);
		
		JPanel ath3 = new JPanel();
		ath3.setBounds(275, 59, 117, 97);
		frame.getContentPane().add(ath3);
		ath3.setLayout(null);
		
		JLabel ath3name = new JLabel("New label");
		ath3name.setBounds(10, 0, 97, 14);
		ath3.add(ath3name);
		
		JLabel ath3info = new JLabel("New label");
		ath3info.setBounds(10, 25, 97, 61);
		ath3.add(ath3info);
		
		JPanel ath4 = new JPanel();
		ath4.setBounds(402, 59, 117, 97);
		frame.getContentPane().add(ath4);
		ath4.setLayout(null);
		
		JLabel ath4name = new JLabel("New label");
		ath4name.setBounds(10, 0, 97, 14);
		ath4.add(ath4name);
		
		JLabel ath4info = new JLabel("New label");
		ath4info.setBounds(10, 25, 97, 61);
		ath4.add(ath4info);
		
		JPanel ath5 = new JPanel();
		ath5.setBounds(529, 59, 120, 97);
		frame.getContentPane().add(ath5);
		ath5.setLayout(null);
		
		JLabel ath5name = new JLabel("New label");
		ath5name.setBounds(10, 0, 100, 14);
		ath5.add(ath5name);
		
		JLabel ath5info = new JLabel("New label");
		ath5info.setBounds(10, 25, 100, 61);
		ath5.add(ath5info);
		
		JButton Buybutton = new JButton("Buy");
		Buybutton.setBounds(21, 211, 120, 55);
		frame.getContentPane().add(Buybutton);
		
		JButton backbutton = new JButton("Back");
		backbutton.setBounds(546, 211, 103, 55);
		frame.getContentPane().add(backbutton);
		
		JLabel money = new JLabel("money");
		money.setBounds(34, 18, 117, 22);
		frame.getContentPane().add(money);
		
		ArrayList<Athlete> marketPlayers = gameEnviroment.getShopAthletes();
		
		for (int i = 0; i < marketPlayers.size(); i++) {
		    JLabel infoAthlete = null;
		    JLabel nameAthlete = null;
		    JPanel picturePanelAthlete = null;
		    
		    if (i == 0) {
		        infoAthlete = ath1info;
		        nameAthlete = ath1name;
		        
		    } else if (i == 1) {
		        infoAthlete = ath2info;
		        nameAthlete = ath2name;
		        
		    } else if (i == 2) {
		        infoAthlete = ath3info;
		        nameAthlete = ath3name;
		        
		    } else if (i == 3) {
		        infoAthlete = ath4info;
		        nameAthlete = ath4name;
		        
		    }else if (i==4) {
		    	infoAthlete = ath5info;
		    	nameAthlete = ath5name;
		    	
		    }
		    infoAthlete.setText(marketPlayers.get(i).toStringHTML());
		    nameAthlete.setText(marketPlayers.get(i).getName());
	}
}}
