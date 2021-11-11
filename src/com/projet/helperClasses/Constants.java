package com.projet.helperClasses;

import com.projet.model.Specialite;

public class Constants {
    public static final String MAX_SESSIONS_STR = "Max Sessions: ";
    public static final String RDV_TODAY_PATIENT = "RDV Aujourd'hui: ";
    public static final String GREETINGS = "Bonjour, ";
    public static final String TOTAL_RDV_PATIENT = "Total RDV: ";
    public static final String SESSIONS_DU_JOUR = "Sessions Ce jour: ";
    public static final String[] MEDECIN_TABLE_HEADER = new String[]{"nom","prenom","motif","Date","Terminé"};
    public static final String[] PATIENT_TABLE_HEADER = new String[]{"Medecin","Specialite","Prix","motif","Date","Terminé"};
    public static final String[] LISTE_SPECIALITE = Specialite.list();
}
