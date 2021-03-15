package project.java.swing;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.*;

public class NOIAssignment {

	public static void main(String[] args) throws SQLException, IOException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ noi_assignments","root","");
		String query1 = "INSERT INTO `personal_info`(`FirstName`, `LastName`,`dob`,`Age`,`id`) VALUES (?,?,?,?,?);";
		String query2 = "INSERT INTO `address_info`(`id`,`address_line_1`, `address_line_2`,`city`,`state`,`country`,`postel_code`) VALUES (?,?,?,?,?,?,?);";
		PreparedStatement statement1 = con.prepareStatement(query1);
		PreparedStatement statement2 = con.prepareStatement(query2);
		
		FileReader fr1 = new FileReader("E:\\Java File\\PersonalCSVFile.csv");
		FileReader fr2 = new FileReader("E:\\Java File\\AddressCSVFile.csv");
		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);
		
		String line = null;
		
		while ((line = br1.readLine()) != null) {
			 try {
				 if(line != null) {
			 String[] data = line.split(",");
			 for(String result:data) {
				// System.out.println(result);
			statement1.setString(1,data[0]);
			  statement1.setString(2,data[1]);
			  statement1.setString(3,data[2]);
			  statement1.setString(4,data[3]);
			  statement1.setString(5,data[4]);
			  
			 
			  
		 }
			 statement1.executeUpdate();
			 
				 }
				 
			 
		 }
			 
			 finally
	            {
	               if (br1== null) 
	                {
	                    br1.close();
	                    statement1.close();
	                }
		 }
		

	}
		while ((line = br2.readLine()) != null) {
			 try {
				 if(line != null) {
			 String[] data = line.split(",");
			 for(String result:data) {
				 //System.out.println(result);
			statement2.setString(1,data[0]);
			  statement2.setString(2,data[1]);
			  statement2.setString(3,data[2]);
			  statement2.setString(4,data[3]);
			  statement2.setString(5,data[4]);
			  statement2.setString(6,data[5]);
			  statement2.setString(7,data[6]);
			  
			  
		 }
			 statement2.executeUpdate();
			 
				 }
				 
			 
		 }
			 finally
	            {
	               if (br2== null) 
	                {
	                    br2.close();
	                    statement2.close();
	                }
		 }

}
		 
		
		showTableData();
		
		
		
}
	 static String column[]={"ID","First Name","Last Name","Date Of Birth","Age","Address Line 1","Address Line 2",
			 "City","State","Country","Postel Code"};  
	
	public static void  showTableData() {
		String query3 = " select * from personal_info NATURAL JOIN address_info";
	
		JFrame frame = new JFrame("Show Data");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  DefaultTableModel model = new DefaultTableModel();
		  model.setColumnIdentifiers(column);
		  JTable table = new JTable();
		  table.setModel(model);
		  table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		  JScrollPane scroll = new JScrollPane(table);
		  int id;
		  String fname="";
		  String lname = "";
		  String dob ="";
		  int age ;
		  String addressLine1="";
		  String addressLine2="";
		  String city ="";
		  String state ="";
		  String country ="";
		  int postelcode;
		  try {
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ noi_assignments","root","");
			  PreparedStatement statement3 = con.prepareStatement(query3);

	            ResultSet rs = statement3.executeQuery();
	            
	           

	            //if (rs.next()) {
	            	while(rs.next()) {
	            	id= rs.getInt("id");
	            	fname = rs.getString("FirstName");
	            	lname = rs.getString("LastName");
	            	dob = rs.getString("dob");
	            	age = rs.getInt("Age");
	            	addressLine1 = rs.getString("address_line_1");
	            	addressLine2 = rs.getString("address_line_2");
	            	city = rs.getString("city");
	            	state = rs.getString("state");
	            	country = rs.getString("country");
	            	postelcode = rs.getInt("postel_code");

	                model.addRow(new Object[]{id,fname, lname, dob,age,addressLine1,addressLine2,city,state,country,postelcode});

	              }	
		
		}
		  catch(Exception e)
		  {
			  System.out.println(e);
			  }
		  frame.add(scroll);

	        frame.setVisible(true);

	        frame.setSize(800, 700);
		  }
}