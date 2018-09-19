package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import impl.EmployeeDaoImpl;
import interfaces.EmployeeDao;
import model.Employee;
import model.SqlConnection;

public class RegisterNewEmployee extends MainFrame {

	private JLabel nameLabel;
	private JLabel phoneNumberLabel;
	private JLabel emailLabel;
	private JTextField nameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField hourlyRateField;
	private JTextField loginField;
	private JTextField passwordField;
	private JButton okBtn;
	private JComboBox positionCombo;
	private JComboBox workTypeCombo;
	private JCheckBox isAdminCheck;
	JScrollPane sp;
	int count=8;
	
	
	private JRadioButton qualRadio;
	private JRadioButton quantRadio;
	private ButtonGroup deptGroup;
	
	private String[] positionList = {"Head_of_Department", "Head_of_Innovations", "Client_Service_Director",
			"Senior_Project_Manager", "Project_Manager", "Junior_Project_Manager", "Assistant"};
	private String[] workTypeList = {"full-time", "part-time"};


	public RegisterNewEmployee(int id) throws Exception {
		super(id);
		initialize();
		
	}
		
		
	private void initialize() {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		
		setLayout(new BorderLayout());
		//Panel for Employee details
		JPanel empPanel = new JPanel();
		empPanel.setLayout(new GridLayout(8, 2, 3, 7));
		
		Dimension dim = getPreferredSize();
		dim.width = 400;
		empPanel.setPreferredSize(dim);
		empPanel.setBorder(new EmptyBorder(50, 10, 10, 10));
		
		
		nameLabel = new JLabel("Full Name ");
		phoneNumberLabel = new JLabel("Phone # ");
		emailLabel = new JLabel("Email");
		nameField = new JTextField(10);
		phoneNumberField = new JTextField(10);
		hourlyRateField = new JTextField(10);
		loginField = new JTextField(20);
		passwordField = new JTextField(20);
		emailField = new JTextField(10);
		positionCombo = new JComboBox(positionList);
		workTypeCombo = new JComboBox(workTypeList);
		isAdminCheck = new JCheckBox();
		okBtn = new JButton("Register");
		
		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);
		okBtn.setBackground(Color.LIGHT_GRAY);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		qualRadio = new JRadioButton("Qaulitative");
		quantRadio = new JRadioButton("Quantitative");
		
		qualRadio.setActionCommand("qual");
		quantRadio.setActionCommand("quant");
		
		deptGroup = new ButtonGroup();
		
		qualRadio.setSelected(true);
		
		// Set up department radios
		deptGroup.add(qualRadio);
		deptGroup.add(quantRadio);



		// Set up combo box.
		positionCombo.setSelectedIndex(0);
		positionCombo.setEditable(true);
		

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = count;
				count++;
				String name = nameField.getText().toString();
				String phoneNumber = phoneNumberField.getText().toString();
				String email = emailField.getText().toString();
				String position = (String) positionCombo.getSelectedItem().toString();
				String workingHours = workTypeCombo.getSelectedItem().toString();
				Double hourlyRate = Double.valueOf(hourlyRateField.getText());
				String department = deptGroup.getSelection().getActionCommand();
				String login = loginField.getText().toString();
				String password = passwordField.getText().toString();
				boolean isAdmin = isAdminCheck.isSelected();
				
		
				Employee emp = new Employee(login, password, id, name, position, phoneNumber, email, workingHours, 
						hourlyRate, department,isAdmin);
				employeeDao.addEmployee(emp);
				
			}
		});

		empPanel.add(nameLabel);
		empPanel.add(nameField);
		empPanel.add(phoneNumberLabel);
		empPanel.add(phoneNumberField);
		empPanel.add(emailLabel);
		empPanel.add(emailField);
		empPanel.add(new JLabel("Position "));
		empPanel.add(positionCombo);
		empPanel.add(new JLabel("Work type "));
		empPanel.add(workTypeCombo);
		empPanel.add(new JLabel("Rate per hour, RUR "));
		empPanel.add(hourlyRateField);
		empPanel.add(qualRadio);
		empPanel.add(quantRadio);
		empPanel.add(new JLabel("IsAdmin "));
		empPanel.add(isAdminCheck);
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Employee");
		Border outerBorder = BorderFactory.createEmptyBorder(30, 10, 30, 10);
		empPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		JPanel loginPanel = new JPanel();
		Dimension dim1 = getPreferredSize();
		dim1.width = 350;
		dim1.height =150;
		loginPanel.setPreferredSize(dim1);
		//loginPanel.setLayout(new GridLayout(4, 1, 5, 5));
		
		loginPanel.setLayout(new FlowLayout());
		loginPanel.add(new JLabel("Create user login"));
		loginPanel.add(loginField);
		loginPanel.add(new JLabel("Create user password"));
		loginPanel.add(passwordField);
		Border innerBorder1 = BorderFactory.createTitledBorder("Create User Login");
		Border outerBorder1 = BorderFactory.createEmptyBorder(10, 10, 30, 10);
		loginPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder1, innerBorder1));
		
		JLabel lblLogo = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lblLogo.setIcon(new ImageIcon(img));
		lblLogo.setBorder(new EmptyBorder(10, 10, 10, 10));
		//lblLogo.setHorizontalAlignment(JTextField.CENTER);
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(lblLogo, BorderLayout.NORTH);
		leftPanel.add(loginPanel, BorderLayout.CENTER);

	        
		add(leftPanel, BorderLayout.WEST);
		add(empPanel, BorderLayout.EAST);
		add(okBtn, BorderLayout.SOUTH);


		//layoutComponents();
		
	}
}


