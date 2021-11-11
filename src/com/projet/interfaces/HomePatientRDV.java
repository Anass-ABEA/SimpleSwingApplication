package com.projet.interfaces;

import com.projet.helperClasses.Constants;
import com.projet.model.Patient;
import com.projet.model.RendezVous;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class HomePatientRDV extends MyJDialog {

    private JPanel parents;
    private JTable listeRendezVous;

    public HomePatientRDV(){
        setup("Liste Rendez-Vous",parents);
    }

    public void init( Patient patient){
        ArrayList<ArrayList<String>> data = patient.getRDVDataPatient();
        listeRendezVous.setRowHeight(30);
        listeRendezVous.setRowSelectionAllowed(false);
        listeRendezVous.setIntercellSpacing(new Dimension(10,0));
        listeRendezVous.setModel(new TableModel() {
            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return Constants.PATIENT_TABLE_HEADER.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return Constants.PATIENT_TABLE_HEADER[columnIndex];
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
        });
        display();
    }

}
