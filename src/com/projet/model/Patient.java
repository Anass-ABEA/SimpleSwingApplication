package com.projet.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Patient extends User {

    public Patient() {
    }

    public Patient(String nom, String prenom) {
        super(nom, prenom,UserType.Patient);
    }
    public Patient(String nom, String prenom,ArrayList<RendezVous> listeRendezVous ) {
        super(nom, prenom,UserType.Patient,listeRendezVous);
    }

    public static Patient fromResultSet(ResultSet rs) {
        Patient patient = new Patient();
        try{
            patient.setNom(rs.getString(4));
            patient.setPrenom(rs.getString(5));
            patient.setType(UserType.Patient);
            patient.setListeRendezVous(new ArrayList<>());
            patient.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }


    public String getFullName() {
        return getNom().charAt(0)+". "+getPrenom();
    }
}
