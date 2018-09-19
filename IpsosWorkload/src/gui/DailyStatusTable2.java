package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import impl.DailyWorkloadDaoImpl;
import impl.EmployeeDaoImpl;
import interfaces.DailyWorkloadDao;
import interfaces.EmployeeDao;
import model.DailyWorkload;
import model.SqlConnection;

public class DailyStatusTable2 extends MainFrame{
	DailyWorkloadDao workloadDao = new DailyWorkloadDaoImpl();
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	private String[] header = {"Employee", "Mon", "Tue", "Wed", "Thu", "Fri"};
	private String[] statusList = {"very busy", "busy", "moderate", "available", "leave"};
	int week=2;
//	private Integer[] workTypeList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//	private JComboBox comboWeek;
	private JTable jTable1;
	private JTable jTable2;
	JScrollPane sp1;
	JPopupMenu popupMenu;
	
		  
	
	public DailyStatusTable2(int id) throws Exception{
		super(id);
		// Create an initial Table
		CreateTable obj = new CreateTable(new SqlConnection().getConnection());
		try {
			jTable1 = obj.getStatusTable(week);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create a spinner
		JSpinner jspinWeek = new JSpinner(new SpinnerNumberModel(2, 1, 52, 1));
		
	    jspinWeek.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	
	        	week =  ((Integer)(jspinWeek.getValue())).intValue();
				
				CreateTable obj = new CreateTable(new SqlConnection().getConnection());
				try {
					jTable2 = obj.getStatusTable(week);
					getContentPane().remove(sp1);
					JScrollPane sp2 = new JScrollPane(jTable2);
					
					jTable2.setComponentPopupMenu(popupMenu);
					jTable2.setComponentPopupMenu(popupMenu);
					
					jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
					    @Override
					    public void mouseClicked(java.awt.event.MouseEvent evt) {
					        int row = jTable2.rowAtPoint(evt.getPoint());
					        int col = jTable2.columnAtPoint(evt.getPoint());
					        if (row >= 0 && col >= 0) {
					        	String name = jTable2.getValueAt(row, 0).toString();
					        	String day = jTable2.getColumnName(col);
					        	int empId = employeeDao.getEmployeeByName(name);
					        	
					        	
					        	Object stausObject1 = JOptionPane.showInputDialog(null, "Select status for the day", day,
					        		      JOptionPane.QUESTION_MESSAGE, null, statusList, null);
					        	String status = stausObject1.toString(); 
					        	jTable2.setValueAt(status, row, col);
					        	DailyWorkload wload1 = new DailyWorkload(empId, week);
					        	wload1.setMonStat(jTable2.getValueAt(row, 1).toString());
					        	wload1.setTueStat(jTable2.getValueAt(row, 2).toString());
					        	wload1.setWedStat(jTable2.getValueAt(row, 3).toString());
					        	wload1.setThuStat(jTable2.getValueAt(row, 4).toString());
					        	wload1.setFriStat(jTable2.getValueAt(row, 5).toString());
					        	workloadDao.updateWorkStatus(wload1);
					        	jTable2.repaint();
					        }
					    }
					});
					
					sp2.setBorder(new EmptyBorder(20, 40, 20, 40));
					getContentPane().add(sp2, BorderLayout.CENTER);
					getContentPane().revalidate();
					
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
	        }
	      });
		
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = jTable1.rowAtPoint(evt.getPoint());
		        int col = jTable1.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	String name = jTable1.getValueAt(row, 0).toString();
		        	String day = jTable1.getColumnName(col);
		        	int empId = employeeDao.getEmployeeByName(name);
		        	
		        	Object stausObject = JOptionPane.showInputDialog(null, "Select status for the day", day,
		        		      JOptionPane.QUESTION_MESSAGE, null, statusList, null);
		        	String status = stausObject.toString(); 
		        	jTable1.setValueAt(status, row, col);
		        	DailyWorkload wload = new DailyWorkload(empId, week);
		        	wload.setMonStat(jTable1.getValueAt(row, 1).toString());
		        	wload.setTueStat(jTable1.getValueAt(row, 2).toString());
		        	wload.setWedStat(jTable1.getValueAt(row, 3).toString());
		        	wload.setThuStat(jTable1.getValueAt(row, 4).toString());
		        	wload.setFriStat(jTable1.getValueAt(row, 5).toString());
		        	workloadDao.updateWorkStatus(wload);
		        	jTable1.repaint();
		        }
		    }
		});

		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton calendarBtn = new JButton("Check Calendar");
		calendarBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar calendarWindow = new Calendar();
				calendarWindow.setSize(850, 550);
				calendarWindow.setVisible(true);
			}
		});
		
		panel.add(new JLabel("Select a week: "));
		panel.add(jspinWeek);
		panel.add(calendarBtn);
		
		sp1 = new JScrollPane(jTable1);
		sp1.setBorder(new EmptyBorder(20, 40, 20, 40));
	   
		
		JLabel lblEmployeeName = new JLabel("Katarina Jaksic");
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmployeeName.setForeground(new Color(47, 79, 79));
		//lblEmployeeName.setText(employeeDao.getEmployeeById(id).getFullName());
		
		JLabel lblEmpPosition = new JLabel("Project_Manager");
		lblEmpPosition.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblEmpPosition.setForeground(new Color(47, 79, 79));
		//lblEmpPosition.setText(employeeDao.getEmployeeById(id).getPosition());
		
		
		JPanel gridpanel = new JPanel(new GridLayout(2, 2));
		gridpanel.add(lblEmployeeName);
		gridpanel.add(new JLabel(""));
		gridpanel.add(lblEmpPosition);
		gridpanel.add(panel, BorderLayout.EAST);
		gridpanel.setBorder(new EmptyBorder(20, 40, 20, 40));
	    
	    add(gridpanel, BorderLayout.NORTH);
	    add(sp1, BorderLayout.CENTER);
	}
	

}
