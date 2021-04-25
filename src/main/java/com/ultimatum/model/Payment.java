package com.ultimatum.model;

import org.json.JSONArray;

import java.sql.*;

public class Payment {


    Connection con;
    DBConnection dbCon;

    public Payment(){
        dbCon = new DBConnection();
        con = dbCon.connect();
    }

    public void insert(String pname, String paddress, String pemail, String ptel, String pamount)
    {
        try
        {
            if (con == null) {
                System.out.println("Error while connecting to the database for inserting.");
                return;
            }
            String query = " insert into payment(`pname`,`paddress`,`pemail`,`ptel`,`pamount`)"+ " values (?, ?, ?, ?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, pname);
            preparedStmt.setString(2, paddress);
            preparedStmt.setString(3, pemail);
            preparedStmt.setString(4, ptel);
            preparedStmt.setString(5, pamount);


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

            String query = "select * from payment";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            output = dbCon.resultSetToJson(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void update(String pid, String pname, String paddress, String pemail, String ptel, String pamount)
    {
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for updating.");
                return;
            }

            String query = "UPDATE payment SET pname=?, paddress=?, pemail=?, ptel=?, pamount=? WHERE pid=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, pname);
            preparedStmt.setString(2, paddress);
            preparedStmt.setString(3, pemail);
            preparedStmt.setString(4, ptel);
            preparedStmt.setString(5, pamount);
            preparedStmt.setInt(6, Integer.parseInt(pid));

            preparedStmt.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String pid)
    {
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for deleting.");
                return;
            }

            String query = "DELETE FROM payments WHERE pid = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(pid));
            preparedStmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getPayment(int pid) {
        JSONArray output = null;
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database.");
                return output;
            }
            String query = "SELECT * FROM payment WHERE pid = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,pid);
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

