package interfaces;

import java.util.List;

import model.Project;

public interface ProjectDao {
	public List<Project> getAllProjects();
	public void addProject (Project project);
	public Project getEmployeeById (int id);
	public void updateProject (Project employee);
	public void deleteProject (int id);
}
