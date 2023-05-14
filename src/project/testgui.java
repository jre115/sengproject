

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class testgui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testgui window = new testgui();
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
	public testgui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 17));
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
