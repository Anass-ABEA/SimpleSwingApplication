package com.projet.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Medecin extends User {
    private Specialite specialite;
    private int prix;
    private int maxRDV;

    private Medecin(){
        super();
    }

    public Medecin(String nom, String prenom,ArrayList<RendezVous> listeRendezVous, Specialite specialite, int prix,int maxRDV) {
        super(nom,prenom,UserType.Med,listeRendezVous);
        this.specialite = specialite;
        this.prix = prix;
        this.maxRDV = maxRDV;
    }

    public Medecin(String nom , String prenom){
        super(nom,prenom,UserType.Med);
        this.specialite = Specialite.Generaliste;
    }

    public Medecin(String nom , String prenom,Specialite spec){
        super(nom,prenom,UserType.Med);
        this.specialite = spec;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public int getMaxRDV() {
        return maxRDV;
    }

    public void setMaxRDV(int maxRDV) {
        this.maxRDV = maxRDV;
    }



    public Specialite getSpecialite() {
        return specialite;
    }


    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getFullName() {
        return "Dr. "+this.getNom();
    }

    public static Medecin fromResultSet(ResultSet rs){
        Medecin medecin = new Medecin();
        try{
            medecin.setType(UserType.Med);
            medecin.setNom(rs.getString(4));
            medecin.setPrenom(rs.getString(5));
            medecin.setPrix(rs.getInt(8));
            medecin.setMaxRDV(rs.getInt(9));
            medecin.setSpecialite(Specialite.values()[rs.getInt(7)]);
            medecin.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return medecin;
    }


}
