package com.projet.controller;

import com.projet.model.RendezVous;

import java.sql.Statement;

public abstract class Controller {
    Statement statement;

    public Controller() {
        statement = new DBConntector().connect().getStatement();
    }

}
