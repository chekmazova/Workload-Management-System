package impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.EmployeeDao;
import model.Employee;
import model.SqlConnection;

public class EmployeeDaoImpl implements EmployeeDao{
	int count=1;
	

	@Override
	public List<Employee> getAllEmployees() {
		Connection con = new SqlConnection().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM tbEmployee";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployee_id(rs.getInt("employee_ID"));
				emp.setFullName(rs.getString("full_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setPosition(rs.getString("position"));
				emp.setDepartment(rs.getString("department_id"));
				emp.setWorkingHours("working_hours_week");
				emp.setHourlyRate(rs.getDouble("hourly_rate"));
				emp.setLogin(rs.getString("login"));
				emp.setPassword("password");
				employeeList.add(emp);
				con.close();
				rs.close();
			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Object not found in DB");
		}
		return employeeList;
	}
	

	@Override
	public void addEmployee(Employee employee) {
		
		Statement stmt = null;
		try {
			Connection con = new SqlConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO tbEmploye VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, employee.getEmployee_id());
			ps.setString(2, employee.getFullName());
	        ps.setString(3, employee.getPosition());
	        ps.setString(4, employee.getPhoneNumber());
	        ps.setString(5, employee.getEmail());
	        ps.setString(6, employee.getWorkingHours());
	        ps.setDouble(7, employee.getHourlyRate());
	        ps.setString(8, employee.getDepartment());
	        ps.setString(9, employee.getLogin());
	        ps.setString(10, employee.getPassword());
	        ps.setBoolean(11, employee.isAdmin());
	        ps.executeUpdate();
	    
//			stmt = con.prepareStatement(sql);
//			stmt.executeUpdate(sql);
	        JOptionPane.showMessageDialog(null, "New user was created!");
		
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Employee getEmployeeById(int id) {
		Connection con = new SqlConnection().getConnection();
	        try {
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT * FROM tbEmploye WHERE employee_ID =" +id);
	            if(rs.next())
	            {
	            	Employee emp = new Employee();
					emp.setEmployee_id(rs.getInt("employee_ID"));
					emp.setFullName(rs.getString("full_name"));
					emp.setEmail(rs.getString("email"));
					emp.setPhoneNumber(rs.getString("phone_number"));
					emp.setPosition(rs.getString("position"));
					emp.setDepartment(rs.getString("department_id"));
					emp.setWorkingHours("working_hours_week");
					emp.setHourlyRate(rs.getDouble("hourly_rate"));
					emp.setAdmin(rs.getBoolean("isAdmin"));
					emp.setLogin(rs.getString("login"));
					emp.setPassword("password");
					return emp;
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    return null;
	}


	@Override
	public void updateEmployee(Employee employee) {
		Statement stmt = null;
		
		String sql = "UPDATE tbEmployee set position ="+ "'"+ employee.getPosition() +"'" + "WHERE employee_ID="+employee.getEmployee_id();
		
		try {
			Connection con = new SqlConnection().getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);
			
			System.out.println("Employee position has been updated");
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int id) {
		Statement stmt = null;
		
		String sql = "DELETE FROM tbEmployee WHERE employee_ID ="+ id;
		
		try {
			Connection con = new SqlConnection().getConnection();
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate(sql);
			
			System.out.println("Employee record was deleted!" );
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public int getEmployeeByName(String fullName) {
		Connection con = null;
		CallableStatement cs = null;
		int empId = 0;
		int id=0;
		try{			
			con = new SqlConnection().getConnection();	
			// Call a procedure with one IN parameter
		    cs = con.prepareCall("{call sp_getEmplIdByName(?, ?)}");
		    // Set the value for the IN parameter
		   // cs.setInt(1, Types.INTEGER);
		    cs.setString(1, fullName);
		    cs.registerOutParameter(2, Types.INTEGER);
		    // Execute the stored procedure
		    cs.execute();
		    id = cs.getInt(2);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}finally
		{
			// This finally clause is always executed - even in error
	        // conditions CallableStatement and Connections will always be closed
	        try
	        {
	                  if (cs != null)
	                	  cs.close();
	        }
	        catch(Exception e) {}

	        try
	        {
	                  if (con != null)
	                	  con.close();
	        }
	        catch (Exception e){}
	   }
		return id;
	}

}
