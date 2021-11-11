package com.projet;

import com.projet.controller.DBConntector;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            new DBConntector().connect().test();
            new DBConntector().connect().fillDb();
        } catch (SQLException ignored) { // fill DB with values to use the application

        }


        new Application();

    }
}
