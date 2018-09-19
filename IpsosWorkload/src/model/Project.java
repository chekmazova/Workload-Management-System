package model;

public class Project {
	private int project_id;
	private String client;
	private String name;
	
	
	
	public Project() {
		super();
	}
	

	public Project(int project_id, String client, String name) {
		super();
		this.project_id = project_id;
		this.client = client;
		this.name = name;
	}


	public int getProject_id() {
		return project_id;
	}


	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}
	

}
