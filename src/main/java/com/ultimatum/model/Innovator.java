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

    public void update(String iid, String name, String address, String email, String pass, String tel, String gender)
    {
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for updating.");
                return;
            }

            String query = "UPDATE innovator SET name=?, address=?, email=?, pass=?, tel=?, gender=? WHERE iid=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, name);
            preparedStmt.setString(2, address);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, pass);
            preparedStmt.setString(5, tel);
            preparedStmt.setString(6, gender);
            preparedStmt.setInt(7, Integer.parseInt(iid));

            preparedStmt.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String iid)
    {
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for deleting.");
                return;
            }

            String query = "DELETE FROM innovator WHERE iid = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(iid));
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getInnovator(int iid) {
        JSONArray output = null;
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
                return output;
            }
            String query = "SELECT * FROM innovator WHERE iid = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,iid);
            ResultSet rs = stmt.executeQuery();
            output = dbCon.resultSetToJson(rs);
        } catch (Exception e) {
            e.printStackTrace();
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
