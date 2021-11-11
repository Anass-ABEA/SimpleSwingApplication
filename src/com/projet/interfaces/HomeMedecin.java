package com.projet.interfaces;

import com.projet.helperClasses.Constants;
import com.projet.model.Medecin;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomeMedecin extends MyJDialog {
    private JLabel drName;
    private JLabel drMaxSessions;
    private JLabel drSessions;
    private JPanel parent;
    private JButton enregistrerButton;
    private JSpinner spinner1;
    private JTable listPatients;
    private JScrollPane scroll;

    private Medecin medecin;
    private MedecinListener listener;

    public interface MedecinListener{
        Medecin onMaxSessionChanged(int value);
    }

    public HomeMedecin() {
        setup("Accueil Medecin",parent);
        listeners();
    }

    private void setupTable(ArrayList<ArrayList<String>> data) {
        listPatients.setRowHeight(30);
        listPatients.setRowSelectionAllowed(false);
        listPatients.setIntercellSpacing(new Dimension(10,0));
        TableModel model = new TableModel() {
            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return Constants.MEDECIN_TABLE_HEADER.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return Constants.MEDECIN_TABLE_HEADER[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return data.get(rowIndex).get(columnIndex);
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                data.get(rowIndex).set(columnIndex,(String) aValue);
            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };
        listPatients.setModel(model);
    }

    private void listeners(){
        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxSessions = (int) spinner1.getValue();
                medecin =  listener.onMaxSessionChanged(maxSessions);
                medecin.setMaxRDV(maxSessions);
                drMaxSessions.setText(Constants.MAX_SESSIONS_STR+maxSessions);
            }
        });
    }

    public void init(Medecin medecin, MedecinListener listener){
        this.listener = listener;
        this.medecin = medecin;
        setupHeader();
        setupTable(medecin.getRDVDataMedecin());
        display();
    }

    private void setupHeader(){
        drName.setText(Constants.GREETINGS+medecin.getFullName());
        drMaxSessions.setText(Constants.MAX_SESSIONS_STR+medecin.getMaxRDV());
        drSessions.setText(Constants.SESSIONS_DU_JOUR+medecin.getCurrentRDV());
        spinner1.setValue(medecin.getMaxRDV());
    }
}
