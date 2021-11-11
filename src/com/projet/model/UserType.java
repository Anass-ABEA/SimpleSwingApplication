package com.projet.model;

public enum UserType {
    Med,Patient,Sec;

    public boolean isMed() {
        return this.equals(UserType.Med);
    }

    public boolean isSecretary() {
        return this.equals(UserType.Sec);
    }

    public boolean isPatient() {
        return this.equals(UserType.Patient);
    }


}
