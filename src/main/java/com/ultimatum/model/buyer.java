package com.ultimatum.model;

import java.sql.*;

public class buyer {

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
	public String insertItem(String name1, String descri1,String address1,String email1,String pwd1,String tp1,String gender1) 
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
	 String query = " insert into buyer(`bid`,`name`,`descri`,`address`,`email`,`pwd`,`tp`,`gender`)"+ " values (?, ?, ?, ?, ?, ?, ?,?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name1); 
	 preparedStmt.setString(3, descri1);
	 preparedStmt.setString(4, address1); 
	 preparedStmt.setString(5, email1); 
	 preparedStmt.setString(6, pwd1); 
	 preparedStmt.setString(7, tp1);
	 preparedStmt.setString(8, gender1);
	 
	 
	// execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
		 output = "Error while inserting the Buyer Details."; 
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
		 output = "<table border='1'><tr><th>Buyer Name</th><th>Description</th><th>Address</th><th>E-mail</th><th>Password</th><th>Telephone</th><th>Gender</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from buyer"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String bid = Integer.toString(rs.getInt("bid")); 
			 String name = rs.getString("name"); 
			 String descri = rs.getString("descri");
			 String address = rs.getString("address"); 
			 String email = rs.getString("email"); 
			 String pwd = rs.getString("pwd"); 
			 String tp = rs.getString("tp"); 
			 String gender = rs.getString("gender"); 
			 
			 // Add into the html table
			 output += "<tr><td>" + name + "</td>"; 
			 output += "<td>" + descri + "</td>";
			 output += "<td>" + address + "</td>";
			 output += "<td>" + email + "</td>";
			 output += "<td>" + pwd + "</td>";
			 output += "<td>" + tp + "</td>";
			 output += "<td>" + gender + "</td>";
			 
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='buyers.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + bid + "'>" + "</form></td></tr>"; 
		 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the Buyer Details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	public String updateItem(String bid1, String name1, String descri1, String address1, String email1, String pwd1, String tp1, String gender1)
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
		 String query = "UPDATE buyer SET name=?,descri=?,address=?,email=?,pwd=?,tp=?,gender=? WHERE bid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, name1); 
		 preparedStmt.setString(2, descri1);
		 preparedStmt.setString(3, address1); 
		 preparedStmt.setString(4, email1); 
		 preparedStmt.setString(5, pwd1); 
		 preparedStmt.setString(6, tp1); 
		 preparedStmt.setString(7, gender1); 
		 preparedStmt.setInt(8, Integer.parseInt(bid1)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the Buyer Details."; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 
	
		public String deleteItem(String bid1) 
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
				 String query = "delete from buyer where bid=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(bid1)); 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Buyer Details Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while deleting the Byuer Details."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
		 }
		public String viewBuyerProfile(int buyerId) {
			// TODO Auto-generated method stub
			String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Buyer Name</th><th>Description</th><th>Address</th><th>E-mail</th><th>Password</th><th>Telephone</th><th>Gender</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from buyer where bid=?"; 
			 
			 PreparedStatement stmt = con.prepareStatement(query);

			 stmt.setInt(1,buyerId);
			 ResultSet rs = stmt.executeQuery();
			 
			 
	
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String bid = Integer.toString(rs.getInt("bid")); 
				 String name = rs.getString("name"); 
				 String descri = rs.getString("descri");
				 String address = rs.getString("address"); 
				 String email = rs.getString("email"); 
				 String pwd = rs.getString("pwd"); 
				 String tp = rs.getString("tp"); 
				 String gender = rs.getString("gender"); 
				 // Add into the html table
				 output += "<tr><td>" + name + "</td>"; 
				 output += "<td>" + descri + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + pwd + "</td>";
				 output += "<td>" + tp + "</td>";
				 output += "<td>" + gender + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='buyers.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + bid + "'>" + "</form></td></tr>"; 
			 } 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the Buyer Details."; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output;
		}
		public String purchaseProducts(int buyerId) {
			// TODO Auto-generated method stub
			 String status1="sold";
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
			 } 
			 
			 // create a prepared statement
			 String query = "UPDATE buyerproduct SET status=? WHERE bid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, status1); 
			 preparedStmt.setInt(2, buyerId); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while updating the Buyer Details."; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
			 
		}
		public String viewBuyiedProductReport(String status1) {
			// TODO Auto-generated method stub

			String output = "";
			//String state = "sold"; 
			 try
			 { 
				 Connection con = connect(); 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Resercher ID</th><th>Product Name</th><th>Price</th><th>Description</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from product RIGHT JOIN buyerproduct ON product.proCode = buyerproduct.proCode having buyerproduct.status =?";
			 
			 PreparedStatement stmt = con.prepareStatement(query);

			 stmt.setString(1,status1);
			 ResultSet rs = stmt.executeQuery();
			 
			 
	
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
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='report.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + proCode + "'>" + "</form></td></tr>"; 
			 } 
				 con.close(); 
				 // Complete the html table
				 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the Sold Product Details."; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output;
		}
		
	
}
