package model;

public class DailyWorkload {
	private int employee_id;
	private String employeeFullName;
	private String monStat;
	private String tueStat;
	private String wedStat;
	private String thuStat;
	private String friStat;
	private int weekNumber;
	
	
	public DailyWorkload() {
	}


	public DailyWorkload(int employee_id, int weekNumber) {
		this.employee_id = employee_id;
		this.weekNumber = weekNumber;
	}


	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}



	public String getMonStat() {
		return monStat;
	}


	public void setMonStat(String monStat) {
		this.monStat = monStat;
	}


	public String getTueStat() {
		return tueStat;
	}


	public void setTueStat(String tueStat) {
		this.tueStat = tueStat;
	}


	public String getWedStat() {
		return wedStat;
	}


	public void setWedStat(String wedStat) {
		this.wedStat = wedStat;
	}


	public String getThuStat() {
		return thuStat;
	}


	public void setThuStat(String thuStat) {
		this.thuStat = thuStat;
	}


	public String getFriStat() {
		return friStat;
	}


	public void setFriStat(String friStat) {
		this.friStat = friStat;
	}


	public int getWeekNumber() {
		return weekNumber;
	}


	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}


	public String getEmployeeFullName() {
		return employeeFullName;
	}


	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}

	

	
	
	

}
