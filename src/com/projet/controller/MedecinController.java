package com.projet.controller;

import com.projet.model.Medecin;
import com.projet.model.Specialite;
import com.projet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MedecinController extends Controller{

    public void changeMaxSessions(int value, Medecin medecin) {
        try {
            statement.execute("UPDATE users set max="+value+" where id="+medecin.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        medecin.setMaxRDV(value);
    }

    public ArrayList<Medecin> getMedecinList() {
        ArrayList<Medecin> listMedecins = new ArrayList<>(); // DELETE LATER

        try {
            ResultSet rs = statement.executeQuery("SELECT * from users where type = 0");
            while(rs.next()){
                listMedecins.add(Medecin.fromResultSet(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listMedecins;
    }
}
