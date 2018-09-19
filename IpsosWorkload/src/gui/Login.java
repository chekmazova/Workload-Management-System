package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.SqlConnection;


public class Login extends JPanel{

	public int ID =0;
	private JFrame frame;
	Connection con = null;
	private JTextField textFieldLogin;
	private JButton btnLogIn;
	private JPasswordField passwordField;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JLabel lblLogo;
	public Login (Connection con){
	        this.con=con;
	    }


	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		con = new SqlConnection().getConnection();
		frame = new JFrame();
		frame.setBounds(100, 100, 698, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(179, 192, 70, 24);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(179, 245, 70, 24);
		frame.getContentPane().add(lblNewLabel);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(279, 187, 205, 29);
		frame.getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		
		btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginVerification();
			}
		});
		frame.getRootPane().setDefaultButton(btnLogIn);;
		btnLogIn.setBounds(335, 312, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(279, 240, 205, 29);
		frame.getContentPane().add(passwordField);
		
		lblLogo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lblLogo.setIcon(new ImageIcon(img));
		lblLogo.setBounds(28, 37, 101, 97);
		frame.getContentPane().add(lblLogo);
		
		JLabel lblNewLabel_1 = new JLabel("Ipsos Russia");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(279, 52, 205, 43);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblWordload = new JLabel("Wordload Management ");
		lblWordload.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblWordload.setForeground(new Color(47, 79, 79));
		lblWordload.setHorizontalAlignment(SwingConstants.CENTER);
		lblWordload.setBounds(250, 106, 254, 24);
		frame.getContentPane().add(lblWordload);
	}
	
	private void loginVerification(){
		String query = "Select * from tbEmploye where login =? and password =?";
		try { 
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, textFieldLogin.getText());
			pst.setString(2, passwordField.getText());
			
			//store the data is a rs
			ResultSet rs = pst.executeQuery();
			//retrieve the data from rs object one by one
			int count = 0;
			while(rs.next()){
				count = count+1;
				
			}
			if(count==1) {
				JOptionPane.showMessageDialog(null, "Username and password is correct");
				//switching to the other page
				//first we close the existing page
	
				frame.dispose();
				if(rs.next())
					ID = rs.getInt("employee_ID");
				
				DailyStatusTable2 frame = new DailyStatusTable2(ID);
				frame.setVisible(true);
				
				//then we create an object of the class and calling it
				
			} else if (count>1){
				JOptionPane.showMessageDialog(null, "Duplicate Username and password");
			} else {
				JOptionPane.showMessageDialog(null, "Username and password is not correct. Try again!");
			}
			
			rs.close();
			pst.close();
		
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		} 
	}
}
