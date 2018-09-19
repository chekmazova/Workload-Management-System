package interfaces;

import java.util.List;

import model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public void addEmployee (Employee employee);
	public Employee getEmployeeById (int id);
	public void updateEmployee (Employee employee);
	public void deleteEmployee (int id);
	public int getEmployeeByName (String fullName);
}
