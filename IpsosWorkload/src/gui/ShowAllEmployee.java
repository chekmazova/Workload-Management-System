package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import impl.EmployeeDaoImpl;
import interfaces.EmployeeDao;
import model.DailyWorkload;
import model.SqlConnection;

public class ShowAllEmployee extends MainFrame {
	JTable showAllEmplTable;
	JLabel lblLogo;
	JPopupMenu popupMenu;
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	int empId;
	
	public ShowAllEmployee(int id) throws Exception{
		super(id);
		// Create an initial Table
		String table = "tbEmploye";
		CreateTable obj = new CreateTable(new SqlConnection().getConnection());
		try {
			showAllEmplTable = obj.getTable(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JScrollPane sp = new JScrollPane(showAllEmplTable);
		sp.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		lblLogo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lblLogo.setIcon(new ImageIcon(img));
		lblLogo.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		add(lblLogo, BorderLayout.NORTH);
		add(sp, BorderLayout.CENTER);
	}
	
}
