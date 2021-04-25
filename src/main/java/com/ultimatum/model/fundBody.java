package com.ultimatum.model;

import org.json.JSONArray;

import java.sql.*;


public class fundBody {

    Connection con;
    DBConnection conDB;

    public fundBody(){
        conDB = new DBConnection();
        con = conDB.connect();
    }


    public void insert(String name, String address, String email, String projectname, String innovatorname, String fundaamount, String fundplan) {

        try
        {
            if (con == null) {
                System.out.println("Error while connecting to the database for inserting.");
                return;
            }
            String query = " insert into fundingBody(`name`,`address`,`email`,`projectname`,`innovatorname`,`fundaamount`,'fundplan')"+ " values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStm = con.prepareStatement(query);

            preparedStm.setString(1, name);
            preparedStm.setString(2, address);
            preparedStm.setString(3, email);
            preparedStm.setString(4, projectname);
            preparedStm.setString(5, innovatorname);
            preparedStm.setString(6, fundaamount);
            preparedStm.setString(7, fundplan);

            preparedStm.execute();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    public JSONArray read() {

        JSONArray output = null;
        try {
            if (con == null) {
                return output;
            }

            String query = "select * from fundingBody";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            output = conDB.resultSetToJson(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;

    }

    public void update(String fid, String name, String address, String email, String projectname, String innovatorname, String fundaamount, String fundplan) {

        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for updating.");
                return;
            }

            String query = "UPDATE fundingBody SET name=?, address=?, email=?, projectname=?, innovatorname=?, fundaamount=?, fundplan=? WHERE fid=?";
            PreparedStatement preparedStm = con.prepareStatement(query);

            preparedStm.setString(1, name);
            preparedStm.setString(2, address);
            preparedStm.setString(3, email);
            preparedStm.setString(4, projectname);
            preparedStm.setString(5, innovatorname);
            preparedStm.setString(6, fundaamount);
            preparedStm.setString(7, fundplan);
            preparedStm.setInt(8, Integer.parseInt(fid));

            preparedStm.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(String fid) {

        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for deleting.");
                return;
            }

            String query = "DELETE FROM fundingBody WHERE fid = ?";
            PreparedStatement preparedStm = con.prepareStatement(query);
            preparedStm.setInt(1, Integer.parseInt(fid));
            preparedStm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public JSONArray getInnovator(int fid) {

        JSONArray output = null;
        try {
            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
                return output;
            }
            String query = "SELECT * FROM fundBodyObj WHERE iid = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,fid);
            ResultSet rs = stm.executeQuery();
            output = conDB.resultSetToJson(rs);
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
