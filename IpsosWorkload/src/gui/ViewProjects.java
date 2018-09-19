package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ViewProjects extends MainFrame {

	JTextField projectIdField;
	JTextField nameField;
	JButton btnRegister;
	
	public ViewProjects(int id) throws Exception {
		super(id);
		projectIdField = new JTextField(10);
		nameField = new JTextField(20);
		btnRegister = new JButton("Create");
		JPanel newProjectPanel = new JPanel();

		
		newProjectPanel.setLayout(new FlowLayout());
		newProjectPanel.add(new JLabel("Project ID"));
		newProjectPanel.add(projectIdField);
		newProjectPanel.add(new JLabel("Project name"));
		newProjectPanel.add(nameField);
		newProjectPanel.add(btnRegister);
		Border innerBorder1 = BorderFactory.createTitledBorder("Create User Login");
		Border outerBorder1 = BorderFactory.createEmptyBorder(10, 10, 30, 10);
		newProjectPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder1, innerBorder1));
		
		setLayout(new BorderLayout());
		add(newProjectPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Main Method
	 */
	public static void main(String[] args) {
		ViewProjects frame;
			try {
				frame = new ViewProjects(1);
				frame.setTitle("Status 2");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 500);
				frame.setVisible(true);
				frame.setBackground(Color.WHITE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}

}
