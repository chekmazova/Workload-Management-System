package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class MainFrame extends JFrame {

	public MainFrame(int id) throws Exception {
		setLayout(new BorderLayout());
		setJMenuBar(createMenuBar());
		

		setMinimumSize(new Dimension(500, 400));
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Ipsos Workload System");
	}
	

	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu workloadMenu = new JMenu("Workload");
		JMenuItem homeItem = new JMenuItem("Home");
		JMenuItem exitItem = new JMenuItem("Exit");

		workloadMenu.add(homeItem);
		workloadMenu.addSeparator();
		workloadMenu.add(exitItem);

		JMenu projectMenu = new JMenu("Project");
		
		JMenu employeeMenu = new JMenu("Employee");
		JMenuItem addEmplItem = new JMenuItem("Register NEW");
		JMenuItem showEmplItem = new JMenuItem("Show ALL");
		employeeMenu.add(addEmplItem);
		employeeMenu.add(showEmplItem);
		
		JMenuItem emplTableItem = new JMenuItem("Show");

		menuBar.add(workloadMenu);
		menuBar.add(projectMenu);
		menuBar.add(employeeMenu);
		

		homeItem.setMnemonic(KeyEvent.VK_H);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		emplTableItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
		homeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DailyStatusTable2 frame;
				try {
					frame = new DailyStatusTable2(1);
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}
		});
		
		projectMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewProjects projectFrame;
				try {
					projectFrame = new ViewProjects(1);
					projectFrame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		
		addEmplItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RegisterNewEmployee registerNewEmployee;
				try {
					registerNewEmployee = new RegisterNewEmployee(1);
					registerNewEmployee.setVisible(true);	
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		showEmplItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ShowAllEmployee frame = new ShowAllEmployee(1);
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		return menuBar;
	}
}
