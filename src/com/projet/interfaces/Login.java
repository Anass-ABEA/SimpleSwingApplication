package com.projet.interfaces;

import com.projet.intermediary.AuthenticationForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends MyJDialog {
    private JPanel panel1;
    private JTextField textLogin;
    private JPasswordField textPassword;
    private JButton button1;
    private LoginListener listener;

    public interface LoginListener {
        boolean onLoginPressed(AuthenticationForm form);
        void onUserConnected();
    }

    public Login() {
        setup("Connexion", panel1);
        listeners();
    }

    public void init(LoginListener listener) {
        this.listener = listener;
        display();
    }

    private void listeners() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthenticationForm data = new AuthenticationForm(textLogin.getText(), textPassword.getText());
                if (listener.onLoginPressed(data)) {
                    listener.onUserConnected();
                } else {
                    System.err.println("WRONG");
                }
            }
        });
    }


}
