package com.projet.controller;

import java.sql.*;

public class DBConntector {
    private static final String CONECTION_URL = "jdbc:sqlite:./database.db";
    private Connection conn;
    private Statement statement;

    public DBConntector connect() {
        try {
            conn = DriverManager.getConnection(CONECTION_URL);
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this;
    }

    public void fillDb() throws SQLException {
       statement.execute("INSERT INTO users values (1,'m','password', 'Amine', 'Alami',0,0,300, 10)");
       statement.execute("INSERT INTO users values (2,'m1','password', 'Yousef', 'Alami',0,1,310, 10)");
       statement.execute("INSERT INTO users values (3,'m2','password', 'Ibrahim', 'Alami',0,2,330, 10)");
       statement.execute("INSERT INTO users values (4,'p','password', 'Ilias', 'Amani',1,NULL,NULL,NULL)");
    }

    public void close() {
        try {
            statement.close();
            conn.close();
        } catch (SQLException ignored) {
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void test() {
        try {
            ResultSet rs = statement.executeQuery("SELECT * from users");
            while (rs.next()){
                System.err.println(rs.getString(1)+ " "+ rs.getString(2));
            }
            System.err.println();
            rs = statement.executeQuery("SELECT * from appoitnments");
            while (rs.next()){
                System.err.println(rs.getString(1)+ " "+ rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
