package com.projet.model;

public enum Specialite {
    Cardiovasculaire,Neurologie,Psychiatre,Generaliste;

    public static String[] list() {
        String[] list = new String[4];
        int i = 0;
        for(Specialite sp:Specialite.values()){
            list[i] = sp.toString();
            i++;
        }
        return list;
    }
}
