package com.ultimatum.model;

import java.sql.Connection;
import java.sql.SQLException;

public class Innovator {
    Connection con;

    public Innovator(){
         DBConnection dbCon = new DBConnection();
         con = dbCon.connect();
    }

    protected void finalize(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
