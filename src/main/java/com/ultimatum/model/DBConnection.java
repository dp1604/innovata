package com.ultimatum.model;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
