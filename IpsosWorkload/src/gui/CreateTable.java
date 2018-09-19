package gui;
import javax.swing.*;
import javax.swing.table.*;

import model.SqlConnection;

import java.awt.Color;
import java.awt.Component;
import java.sql.*;
/**
 * This class create JTable from Database table.
 */
public class CreateTable {
	    //private String table;
	    private Connection con;
	    public CreateTable (Connection con){
	        this.con=con;
	    }
	    
	    /**
	     * This method return JTable object created from Database table having same data structure
	     * as in original table into database.
	     * @param table Name of the database table to be converted to JTable
	     * @return JTable object that consist of data and structure of Database table
	     * @throws java.lang.Exception Original object is different, e.i either SQLException or NullPointerException
	     */
	    public JTable getTable(String table)throws Exception{
	    	
	        JTable t1=new JTable();
	        DefaultTableModel dm=new DefaultTableModel();
	        Statement st=con.createStatement();   
	        ResultSet rs=st.executeQuery("select * from ["+table+"]");
	        ResultSetMetaData rsmd=rs.getMetaData();
	        //Coding to get columns-
	        int cols=rsmd.getColumnCount();
	        String c[]=new String[cols];
	        for(int i=0;i<cols;i++){
	            c[i]=rsmd.getColumnName(i+1);
	            dm.addColumn(c[i]);
	        }
	        //get data from rows
	        Object row[]=new Object[cols];
	        while(rs.next()){
	             for(int i=0;i<cols;i++){
	                    row[i]=rs.getString(i+1);
	                }
	            dm.addRow(row);
	        }
	        t1.setModel(dm);
	        con.close();
	        return t1;
	    }
	    /**
	     * This method return JTable object created from Database table having selected data and structure
	     * as in original table into database.
	     */
	    public JTable getTable(String table,String query)throws Exception{
	    	JTable t1=new JTable();
	        DefaultTableModel dm=new DefaultTableModel();
	    	    
	    	    Statement st=con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        ResultSetMetaData rsmd=rs.getMetaData();
		        //Coding to get columns-
		        int cols=rsmd.getColumnCount();
		        String c[]=new String[cols];
		        for(int i=0;i<cols;i++){
		            c[i]=rsmd.getColumnName(i+1);
		            dm.addColumn(c[i]);
		        }


		        //get data from rows
		        Object row[]=new Object[cols];
		        while(rs.next()){
		             for(int i=0;i<cols;i++){
		                    row[i]=rs.getString(i+1);
		                }
		            dm.addRow(row);
		        }

		        con.close();
		        return t1;
	    	}
	        
	       
	    
	    
	    
	    public JTable getStatusTable(int week)throws Exception{
	    	DefaultTableModel dm=new DefaultTableModel();
	    	JTable t1=new JTable(dm) {
	    	        @Override
	    	        public Component prepareRenderer(TableCellRenderer renderer, int rowIndex,
	    	                int columnIndex) {
	    	            JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  

	    	            if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("busy")) {
	    	                component.setBackground(Color.ORANGE);
	    	                component.setForeground(Color.ORANGE);
	    	            } else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("very busy")){
	    	            	 component.setBackground(Color.RED);
		    	             component.setForeground(Color.RED);
	    	            } 
	    	            else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("moderate")){
	    	                component.setBackground(Color.YELLOW);
	    	                component.setForeground(Color.YELLOW);
	    	            }
	    	            else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("available")){
	    	                component.setBackground(Color.GREEN);
	    	                component.setForeground(Color.GREEN);
	    	            }
	    	            else if(getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("leave")){
	    	                component.setBackground(Color.GRAY);
	    	                component.setForeground(Color.GRAY);
	    	            }
	    	            else {
	    	            	component.setBackground(Color.WHITE);
	    	            	component.setForeground(Color.BLACK);
	    	            }
	    	            return component;
	    	        }
	    	    };
		        Connection con = new SqlConnection().getConnection();
		        Statement st=con.createStatement();   
		        ResultSet rs=st.executeQuery("SELECT [full_name], Monday, Tuesday, Wednsday, Thursday, Friday FROM vw_employee_status_week WHERE Week =" + week);
		        ResultSetMetaData rsmd=rs.getMetaData();
		        //Coding to get columns-
		        int cols=rsmd.getColumnCount();
		        String c[]=new String[cols];
		        for(int i=0;i<cols;i++){
		            c[i]=rsmd.getColumnName(i+1);
		            dm.addColumn(c[i]);
		        }
		        //get data from rows
		        Object row[]=new Object[cols];
		        while(rs.next()){
		             for(int i=0;i<cols;i++){
		                    row[i]=rs.getString(i+1);
		                }
		            dm.addRow(row);
		        }
		        t1.setModel(dm);
		        con.close();
		        return t1;
		    }

	}

