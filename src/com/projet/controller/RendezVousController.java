package com.projet.controller;

import com.projet.helperClasses.StringFormatter;
import com.projet.model.RendezVous;
import java.sql.SQLException;

public class RendezVousController extends Controller{
    public void addNewRDV(RendezVous rdv){
        try {
            String SQL = "INSERT INTO appoitnments (id_med,id_pat, date_app,state, motif) values ("+rdv.getMedecin().getId()+","+
                    rdv.getPatient().getId()+", '"+ StringFormatter.formatDate(rdv.getDate())+"', "+rdv.getState()+", \""+rdv.getMotif()+"\");";
            statement.execute(SQL);
        } catch (SQLException throwables) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
