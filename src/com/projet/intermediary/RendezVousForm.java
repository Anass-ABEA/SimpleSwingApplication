package com.projet.intermediary;

import com.projet.model.Medecin;

import java.util.Date;

public class RendezVousForm {
    private Medecin medecin;
    private String motif;
    private Date date;

    public RendezVousForm(Medecin medecin, String motif, Date date) {
        this.medecin = medecin;
        this.motif = motif;
        this.date = date;
    }

}
