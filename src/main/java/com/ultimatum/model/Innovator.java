package com.ultimatum.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class Innovator {
    Connection con;
    DBConnection dbCon;

    public Innovator(){
         dbCon = new DBConnection();
         con = dbCon.connect();
    }

    public void insert(String name, String address,String email,String pass,String tel,String gender)
    {
        try
        {
            if (con == null) {
                System.out.println("Error while connecting to the database for inserting.");
                return;
            }
            String query = " insert into innovator(`name`,`address`,`email`,`pass`,`tel`,`gender`)"+ " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, name);
            preparedStmt.setString(2, address);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, pass);
            preparedStmt.setString(5, tel);
            preparedStmt.setString(6, gender);

            preparedStmt.execute();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public JSONArray read()
    {
        JSONArray output = null;
        try {
            if (con == null) {
                return output;
            }

            String query = "select * from innovator";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            output = dbCon.resultSetToJson(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void update(String name, String address, String email, String pass, String tel, String gender)
    {
        try {
            Connection con = connect();
            if (con == null)
            {
                System.out.println("Error while connecting to the database for updating.");
                return;
            }

            String query = "UPDATE innovator SET name=?, address=?, email=?, pass=?, tel=?, gender=? WHERE iid=?";
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

    protected void finalize(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
