package com.projet.test;

import java.util.Date;

public class Personne {

    String nom;
    String prenom;
    boolean isMale;
    Date birthdate;

    public Personne(String nom, String prenomn, boolean isMale, Date birthdate){
        this.nom = nom;
        this.prenom = prenomn;
        this.isMale = isMale;
        this.birthdate = birthdate;
    }

    public Personne(String nom, String prenom){
        this.nom = nom;
        this.prenom = prenom;
    }

    public String greeting(){
        if (isMale){
            return "Bonsoir Mr. "+ this.nom;
        }else{
            return "Bonsoir Mme. "+this.nom;
        }
    }

    public static void main(String[] args) {
        // John D.

        Personne p = new Personne("a","b");

        Personne personne1 = new Personne("DOE","John",true,new Date());

        Personne personne2 = new Personne("DOE", "Jane",false,new Date());

        String greet = personne1.greeting();
        System.err.println(greet);

        String greet2 = personne2.greeting();
        System.err.println(greet2);
    }


}