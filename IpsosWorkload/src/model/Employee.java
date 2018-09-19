package model;

public class Employee {
	private static int count = 4;
	private int employee_id;
	private String fullName;
	private String position;
	private String phoneNumber;
	private String email;
	private String workingHours;
	private Double hourlyRate;
	private String department;
	private String login;
	private String password;
	private boolean isAdmin;

	
	public Employee() {
	}

	

	public Employee( String login, String password, int employee_id, String fullName, String position, String phoneNumber, String email,
			String workingHours, Double hourlyRate, String department, boolean isAdmin) {
		super();
		this.employee_id = employee_id;
		this.fullName = fullName;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.workingHours = workingHours;
		this.hourlyRate = hourlyRate;
		this.department = department;
		this.login = login;
		this.password = password;
		this.isAdmin = isAdmin;
	}



	public int getEmployee_id() {
		return employee_id;
	}



	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getWorkingHours() {
		return workingHours;
	}



	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}



	public Double getHourlyRate() {
		return hourlyRate;
	}



	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}


	

	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
