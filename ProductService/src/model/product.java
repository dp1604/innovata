package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class product {

	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/group_04", "root", ""); 
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 	return con; 
	 } 
	public String insertItem(String proCode,String rid1, String pname1,String price1,String descri1) 
	 { 
	 String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for inserting.";
	 } 
	 
	 // create a prepared statement
	 String query = " insert into product(`proCode`,`rid`,`pname`,`price`,`descri`)"+ " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, rid1); 
	 preparedStmt.setString(3, pname1); 
	 preparedStmt.setString(4, price1); 
	 preparedStmt.setString(5, descri1);
	 
	 
	// execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while inserting the Product Details."; 
		 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 } 
	public String readItems() 
	 { 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Resercher ID</th><th>Product_Name</th><th>Price</th><th>Description</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from product"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String proCode = Integer.toString(rs.getInt("proCode")); 
			 String rid = rs.getString("rid"); 
			 String pname = rs.getString("pname"); 
			 String price = rs.getString("price"); 
			 String descri = rs.getString("descri"); 
			 // Add into the html table
			 output += "<tr><td>" + rid + "</td>"; 
			 output += "<td>" + pname + "</td>";
			 output += "<td>" + price + "</td>";
			 output += "<td>" + descri + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='products.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + proCode + "'>" + "</form></td></tr>"; 
		 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the Product Details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	public String updateItem(String proCode1, String rid1, String pname1, String price1, String descri1)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for updating."; 
		 } 
		 
		 // create a prepared statement
		 String query = "UPDATE product SET rid=?,pname=?,price=?,descri=? WHERE proCode=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, rid1); 
		 preparedStmt.setString(2, pname1); 
		 preparedStmt.setString(3, price1); 
		 preparedStmt.setString(4, descri1);  
		 preparedStmt.setInt(5, Integer.parseInt(proCode1)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the Product Details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 
	
		public String deleteItem(String proCode1) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting."; 
				 } 
				 // create a prepared statement
				 String query = "delete from product where proCode=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(proCode1)); 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Product Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while deleting the Product."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
		 }
	
}
