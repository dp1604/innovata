package com.ultimatum.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBConnection {

    Connection con = null;

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/innova", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public JSONArray resultSetToJson(ResultSet rs){
        JSONArray json = new JSONArray();
        try{
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()) {
                int numColumns = rsmd.getColumnCount();
                JSONObject obj = new JSONObject();
                for (int i=1; i<=numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                }
                json.put(obj);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
}
