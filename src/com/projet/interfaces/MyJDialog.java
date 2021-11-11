package com.projet.interfaces;

import javax.swing.*;

public class MyJDialog extends JDialog {

    public void setup(String title,JPanel panel){
        setTitle(title);
        setContentPane(panel);
        setModal(true);
    }

    public void display(){
        this.pack();
        this.setVisible(true);
    }

}
