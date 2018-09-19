package impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.DailyWorkloadDao;
import model.DailyWorkload;
import model.SqlConnection;

public class DailyWorkloadDaoImpl implements DailyWorkloadDao{

	@Override
	public ArrayList<DailyWorkload> getAllWorkload() {
		Connection con = new SqlConnection().getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<DailyWorkload> overallStatus = new ArrayList<DailyWorkload>();
		try {
			String query = "SELECT * FROM vw_EmployeesStatus";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				DailyWorkload wl = new DailyWorkload();
				wl.setEmployeeFullName(rs.getString("full_name"));
				wl.setMonStat(rs.getString("Monday"));
				wl.setMonStat(rs.getString("Tuesday"));
				wl.setMonStat(rs.getString("Wednsday"));
				wl.setMonStat(rs.getString("Thursday"));
				wl.setMonStat(rs.getString("Friday"));
				wl.setWeekNumber(rs.getInt("Week"));
				wl.setEmployee_id(rs.getInt("Employee_id"));
			overallStatus.add(wl);	
				
			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Object not found in DB");
		} finally {
			try {
				con.close();
				rs.close();
			} catch (Exception e){
				
			}
		}
		
		return overallStatus;
	}
	
	@Override
	public ArrayList<DailyWorkload> getWeeklyWorkload(int week) {
		Connection con = new SqlConnection().getConnection();
		CallableStatement stmt;
		ResultSet rs = null;
		ArrayList<DailyWorkload> weeklyStatus = new ArrayList<DailyWorkload>();
		try {
			stmt = con.prepareCall("{ call sp_WorkloadForWeek(?)}");
			stmt.setInt(1, week);
			stmt.execute();
			rs = (ResultSet) stmt.getObject(1);
			while(rs.next()) {
				DailyWorkload wl = new DailyWorkload();
				wl.setEmployeeFullName(rs.getString("full_name"));
				wl.setMonStat(rs.getString("Monday"));
				wl.setMonStat(rs.getString("Tuesday"));
				wl.setMonStat(rs.getString("Wednsday"));
				wl.setMonStat(rs.getString("Thursday"));
				wl.setMonStat(rs.getString("Friday"));
				weeklyStatus.add(wl);	
				
			} 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Object not found in DB");
		} finally {
			try {
				con.close();
				rs.close();
			} catch (Exception e){
				
			}
		}
		
		return weeklyStatus;
	}

	@Override
	public void addWorkStatus(DailyWorkload wload) {
		try {
			Connection con = new SqlConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO tbEmploye VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, wload.getEmployee_id());
			ps.setString(2, wload.getMonStat());
			ps.setString(3, wload.getTueStat());
			ps.setString(4, wload.getWedStat());
			ps.setString(5, wload.getThuStat());
			ps.setString(6, wload.getFriStat());
			ps.setInt(9, wload.getWeekNumber());
	        
	        ps.executeUpdate();

	        JOptionPane.showMessageDialog(null, "New user was created!");
		
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateWorkStatus(DailyWorkload wload) {
		Connection con = new SqlConnection().getConnection();
	    try {
	        PreparedStatement ps = con.prepareStatement("UPDATE tbDailyWorkload SET Monday=?, Tuesday=?, Wednsday=?, Thursday=?, Friday=? WHERE Employee_id=? AND Week=?");
	        ps.setString(1, wload.getMonStat());
	        ps.setString(2, wload.getTueStat());
	        ps.setString(3, wload.getWedStat());
	        ps.setString(4, wload.getThuStat());
	        ps.setString(5, wload.getFriStat());
	        ps.setInt(6, wload.getEmployee_id());
	        ps.setInt(7, wload.getWeekNumber());
	        ps.executeUpdate();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	    	try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }

		
	}

}
