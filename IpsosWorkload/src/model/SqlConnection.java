package model;

import java.sql.Connection;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class SqlConnection {
	
	public static Connection getConnection() {
		SQLServerDataSource dataSource = new SQLServerDataSource();
		Connection con = null;
		dataSource.setIntegratedSecurity(true);
		dataSource.setServerName("localhost");
		dataSource.setPortNumber(49634);
		dataSource.setDatabaseName("WorkloadIpsos");
//		dataSource.setDatabaseName("IpsosWorkload");
		try {
			con = dataSource.getConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
