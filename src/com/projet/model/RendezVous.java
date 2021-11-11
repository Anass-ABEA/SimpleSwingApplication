package com.projet.model;

import com.projet.helperClasses.StringFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RendezVous {
    private Medecin medecin;
    private Patient patient;
    private Date date;
    private String motif;
    private boolean isDone;

    private  RendezVous(){}

    public RendezVous(Medecin medecin,Patient patient, Date date, String motif,boolean isDone) {
        this.medecin = medecin;
        this.date = date;
        this.patient = patient;
        this.motif = motif;
        this.isDone = isDone;
    }

    public static RendezVous fromResultSet(ResultSet rs, ResultSet rsMed, ResultSet rsPat){
        RendezVous rendezVous = new RendezVous();
        rendezVous.setMedecin(Medecin.fromResultSet(rsMed));
        rendezVous.setPatient(Patient.fromResultSet(rsPat));
        try {
            rendezVous.setDate(rs.getDate("date_app"));
            rendezVous.setMotif(rs.getString("motif"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  rendezVous;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public boolean isToday() {
        return StringFormatter.formatDate(date).equals(StringFormatter.formatDate(new Date()));
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "date=" + StringFormatter.formatDate(date) +
                ", motif='" + motif + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    public int getState() {
        if(isDone) return 1;
        return 0;
    }
}
