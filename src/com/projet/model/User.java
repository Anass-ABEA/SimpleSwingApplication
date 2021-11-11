package com.projet.model;

import com.projet.helperClasses.StringFormatter;

import java.util.ArrayList;

public abstract class User {
    private int id;
    private String nom;
    private String prenom;
    private UserType type;
    private ArrayList<RendezVous> listeRendezVous ;

    public User(String nom, String prenom,UserType type) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.listeRendezVous = new ArrayList<>();
    }

    public User() {
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public User(int id, String nom, String prenom, UserType type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.listeRendezVous = new ArrayList<>();
    }

    public User(String nom, String prenom,UserType type, ArrayList<RendezVous> list) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        listeRendezVous = list;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", type=" + type +
                ", listeRendezVous=" + listeRendezVous +
                '}';
    }

    public boolean isSecretary() {
        return type.equals(UserType.Sec);
    }

    public boolean isPatient() {
        return type.equals(UserType.Patient);
    }

    public UserType getType(){
        return this.type;
    }

    public ArrayList<RendezVous> getListeRendezVous() {
        return listeRendezVous;
    }

    public void setListeRendezVous(ArrayList<RendezVous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }


    public String getCurrentRDV() {
        int res = 0;
        for(RendezVous rdv :listeRendezVous){
            if (rdv.isToday() && !rdv.isDone()){
                res++;
            }
        }
        return String.valueOf(res);
    }

    public ArrayList<ArrayList<String>> getRDVDataMedecin() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(RendezVous rdv : listeRendezVous){
            ArrayList<String> list = new ArrayList<>();
            list.add(rdv.getPatient().getNom());
            list.add(rdv.getPatient().getPrenom());
            list.add(rdv.getMotif());
            list.add(StringFormatter.formatDate(rdv.getDate()));
            if(rdv.isDone()){
                list.add("✔");
            }else{
                list.add("✖");
            }
            res.add(list);
        }
        return res;
    }
    public ArrayList<ArrayList<String>> getRDVDataPatient() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(RendezVous rdv : listeRendezVous){
            ArrayList<String> list = new ArrayList<>();
            list.add(rdv.getMedecin().getFullName());
            list.add(rdv.getMedecin().getSpecialite().toString());
            list.add(rdv.getMedecin().getPrix()+" MAD");
            list.add(rdv.getMotif());
            list.add(StringFormatter.formatDate(rdv.getDate()));
            if(rdv.isDone()){
                list.add("✔");
            }else{
                list.add("✖");
            }
            res.add(list);
        }
        return res;
    }


    public int rdvSize(){
        return listeRendezVous.size();
    }

    public void addRDV(RendezVous rendezVous){
        this.listeRendezVous.add(rendezVous);
    }

    protected void setId(int anInt) {
        this.id = anInt;
    }

    public int getId() {
        return id;
    }
}
