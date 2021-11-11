package com.projet.interfaces;

import com.projet.helperClasses.Constants;
import com.projet.helperClasses.StringFormatter;
import com.projet.intermediary.RendezVousForm;
import com.projet.model.Medecin;
import com.projet.model.Patient;
import com.projet.model.Specialite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePatient extends MyJDialog {
    private JPanel parent;
    private JLabel textName;
    private JLabel textTotalRDV;
    private JLabel textRDVToday;
    private JTextArea textMotif;
    private JComboBox comboSpecialite;
    private JButton btnNewRDV;
    private JButton btnListRDV;
    private JComboBox comboMedecin;
    private JTextField textDate;

    private Patient patient;
    private PatientListener listener;
    private ArrayList<Medecin> listeMedecins;
    private ArrayList<Medecin> displayedMedecins;

    public static abstract class PatientListener {
        public abstract void createNewRDV(RendezVousForm rendezVousForm);

        public void showPatientRDVList() {
            // DO smth;
            return;
        }
    }


    public HomePatient() {
        setup("Accueil Patient", parent);
        listeners();
    }

    public void init(Patient patient, PatientListener listener, ArrayList<Medecin> listeMedecins) {
        this.patient = patient;
        this.listener = listener;
        this.listeMedecins = listeMedecins;

        textName.setText(Constants.GREETINGS + patient.getFullName());
        textRDVToday.setText(Constants.RDV_TODAY_PATIENT + patient.getCurrentRDV());
        textTotalRDV.setText(Constants.TOTAL_RDV_PATIENT + patient.rdvSize());

        fillComboBox();
        display();
    }

    private void fillComboBox() {
        for (String spes : Constants.LISTE_SPECIALITE) {
            comboSpecialite.addItem(spes);
        }
    }

    private void listeners() {

        btnNewRDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medecin selected = displayedMedecins.get(comboMedecin.getSelectedIndex());
                RendezVousForm rendezVousForm = new RendezVousForm(selected, textMotif.getText(), StringFormatter.formatDate(textDate.getText()));
                listener.createNewRDV(rendezVousForm);
                textMotif.setText("");
                textDate.setText("");

            }
        });

        btnListRDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.showPatientRDVList();
            }
        });

        comboSpecialite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Specialite specialite = Specialite.valueOf(comboSpecialite.getSelectedItem().toString());
                comboMedecin.removeAllItems();
                fillDoctorComboBox(specialite);
            }
        });
    }

    private void fillDoctorComboBox(Specialite specialite) {
        displayedMedecins = new ArrayList<>();
        for (Medecin medecin : listeMedecins) {
            if (medecin.getSpecialite().equals(specialite)) {
                displayedMedecins.add(medecin);
                comboMedecin.addItem(medecin.getFullName() + " | " + medecin.getPrix() + " MAD");
            }
        }
    }
}
