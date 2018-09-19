package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import interfaces.ProjectDao;
import model.Project;
import model.SqlConnection;

public class ProjectDaoImpl implements ProjectDao{

	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addProject(Project project) {
		try {
			Connection con = new SqlConnection().getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO tbProject VALUES (?, ?, ?)");
			ps.setInt(1, project.getProject_id());
			ps.setString(2, project.getName());
			ps.setString(3, project.getClient());
	        ps.executeUpdate();
	    
	        JOptionPane.showMessageDialog(null, "New project has been created!");
		
			con.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Project getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProject(Project employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(int id) {
		// TODO Auto-generated method stub
		
	}

}
