package com.projet;

import com.projet.controller.LoginController;
import com.projet.controller.MedecinController;
import com.projet.controller.RendezVousController;
import com.projet.interfaces.HomeMedecin;
import com.projet.interfaces.HomePatient;
import com.projet.interfaces.HomePatientRDV;
import com.projet.interfaces.Login;
import com.projet.intermediary.AuthenticationForm;
import com.projet.intermediary.RendezVousForm;
import com.projet.model.*;

import java.util.ArrayList;
import java.util.Date;

public class Application {
    private Login loginInterface;
    private HomeMedecin homeMedecinInterface;
    private HomePatient homePatientInterface;
    private HomePatientRDV homePatientRDVInterface;


    private User user = new Patient("nom M","prenom",new ArrayList<>()); // DELETE INSTANCE

    public Application() {
        setup();
        init();
    }


    private void setup() {
        loginInterface = new Login();
        homeMedecinInterface = new HomeMedecin();
        homePatientInterface = new HomePatient();
        homePatientRDVInterface = new HomePatientRDV();
    }

    private void init() {
        openLoginPage();
    }

    private void openLoginPage() {
        loginInterface.init(new Login.LoginListener() {
            @Override
            public boolean onLoginPressed(AuthenticationForm form) {
                user = new LoginController().connectUser(form);
                if(user!=null) user.setListeRendezVous(new LoginController().getRendezVous(user));
                return user != null;
            }

            @Override
            public void onUserConnected() {
                openHomePage(user.getType());
            }
        });
    }

    private void openHomePage(UserType type) {
        loginInterface.setVisible(false);
        if (type.isMed()) {
            homeMedecinInterface.init((Medecin) user, new HomeMedecin.MedecinListener() {
                @Override
                public Medecin onMaxSessionChanged(int value) {
                    new MedecinController().changeMaxSessions(value, (Medecin) user);
                    return (Medecin) user;
                }
            });
            return;
        }

        if(type.isPatient()){
            ArrayList<Medecin> listMedecins = new MedecinController().getMedecinList();
            /*homePatientInterface.init((Patient) user, new HomePatient.PatientListener() {
                @Override
                public void createNewRDV(RendezVousForm rendezVousForm) {
                    RendezVous rdv = new RendezVous(rendezVousForm.getMedecin(),(Patient) user,rendezVousForm.getDate(),rendezVousForm.getMotif(),false);
                    new RendezVousController().addNewRDV(rdv);
                    user.addRDV(rdv);
                }

                @Override
                public void showPatientRDVList() {
                    homePatientRDVInterface.init((Patient) user);
                }
            }, listMedecins);*/
            homePatientInterface.init((Patient) user, new HomePatient.PatientListener(){

                @Override
                public void createNewRDV(RendezVousForm rendezVousForm) {

                }

                @Override
                public void showPatientRDVList() {
                    //super.showPatientRDVList();

                }
            },listMedecins);
        }
    }

}
