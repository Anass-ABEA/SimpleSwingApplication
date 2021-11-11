package com.projet.controller;

import com.projet.helperClasses.StringFormatter;
import com.projet.intermediary.AuthenticationForm;
import com.projet.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends Controller {

    public User connectUser(AuthenticationForm form) {
        String SQL = "SELECT * FROM users WHERE login='" + form.getLogin() + "' and password='" + form.getPassword() + "';";
        try {
            ResultSet rs = statement.executeQuery(SQL);

            int type = rs.getInt(6);
            if (type == 0) {
                return Medecin.fromResultSet(rs);
            } else if (type == 1) {
                return Patient.fromResultSet(rs);
            }

        } catch (SQLException throwables) {
            return null;
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (form.getLogin().equals("p") && form.getPassword().equals("admin")) {
            return new Patient("nom P", "prenom");
        }
        if (form.getLogin().equals("m") && form.getPassword().equals("admin")) {
            return new Medecin("nom M", "prenom", new ArrayList<>(), Specialite.Generaliste, 200, 3);
        }
        return null;
    }

    public ArrayList<RendezVous> getRendezVous(User user) {
        ArrayList<RendezVous> list = new ArrayList<>();

        try {
            if (user.getType().isMed()) {
                ResultSet rs = statement.executeQuery("SELECT * from users U join appoitnments A on A.id_pat = U.id where A.id_med = "+user.getId());
                while(rs.next()){
                    Patient patient = new Patient(rs.getString("nom"),rs.getString("prenom"));
                    RendezVous rdv = new RendezVous((Medecin) user,patient,StringFormatter.formatDate(rs.getString("date_app")),rs.getString("motif"),rs.getInt("state")==1);
                    list.add(rdv);
                }
            } else if (user.getType().isPatient()) {
                ResultSet rs = statement.executeQuery("SELECT * from users U join appoitnments A on A.id_med = U.id where A.id_pat = "+user.getId());
                while(rs.next()){
                    Medecin medecin = new Medecin(rs.getString("nom"),rs.getString("prenom"));
                    RendezVous rdv = new RendezVous(medecin,(Patient) user, StringFormatter.formatDate(rs.getString("date_app")),rs.getString("motif"),rs.getInt("state")==1);
                    list.add(rdv);
                }
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


        return list;
    }
}
