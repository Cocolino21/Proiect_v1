package Controller;

import Model.BigModel;
import Model.InspectorModel;
import View.BasicView;
import View.InspectorView;
import com.sun.jdi.ObjectReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class InspectorController extends BasicController implements ActionListener {
    InspectorView  iv;
    InspectorModel  im;
    public InspectorController(BasicView view, BigModel model) {
        super(view, model);
        iv = (InspectorView) view;
        im = (InspectorModel) model;
        iv.getVeziOrarButton().addActionListener(this);
        iv.getVeziCerereConcediuButton().addActionListener(this);
        iv.getAcceptConcediuButton().addActionListener(this);
        iv.getRefuzConcediuButton().addActionListener(this);
        iv.setAngajatiRowData(im.getAngajatiForResurse(im.getCurrentAngajat().getId_centru()));
        iv.reAddToInspectorM1Panel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == iv.getBV_m1Button()){

            iv.setAngajatiRowData(im.getAngajatiForResurse(im.getCurrentAngajat().getId_centru()));
            iv.reAddToInspectorM1Panel();
            iv.reAddToBV_RP_m1Panel();
        }

        if(e.getSource() == iv.getVeziOrarButton()) {
            iv.getOrarFrame().setVisible(true);
        }

        if(e.getSource() == iv.getVeziCerereConcediuButton()) {
            int selectedRow = iv.getAngajatiTable().getSelectedRow();
            if (selectedRow != -1) {
                int selectedID = (int) iv.getAngajatiTable().getValueAt(selectedRow, 0);
                iv.setCereriRowData(im.getCereriForResurse(selectedID));
                iv.updateCereriPanel();
                iv.reAddToCereriPanel();
                iv.reAddToInspectorM1Panel();
                iv.getCerereConcediuFrame().setVisible(true);
            }
            else{
                iv.showErrorMessageIn("Nu ai selectat nici un angajat");
            }
        }

        if(e.getSource() == iv.getAcceptConcediuButton()){
            int selectedRow = iv.getCereriTable().getSelectedRow();
            if (selectedRow != -1) {
                Object selectedValue = iv.getCereriTable().getValueAt(selectedRow, 0);
               if(!im.insertInTabelaConcediu((int) selectedValue, (java.sql.Date) iv.getCereriTable().getValueAt(selectedRow, 1), ((java.sql.Date) iv.getCereriTable().getValueAt(selectedRow, 2)), ((String) iv.getCereriTable().getValueAt(selectedRow, 3)))){
                   iv.showErrorMessageIn("Nu s-a putut accepta cererea");
                }
               else {
                   iv.setCereriRowData(im.getCereriForResurse((int) selectedValue));
                   iv.reAddToCereriPanel();
                   iv.reAddToInspectorM1Panel();
                   iv.BV_showSuccesMessage("Concediu acceptat pentru angajatul cu ID " + (int) selectedValue);
               }
            }
            else{
                iv.showErrorMessageIn("Nu ai selectat nici o cerere");
            }
        }

        if(e.getSource() == iv.getRefuzConcediuButton()){
            int selectedRow = iv.getCereriTable().getSelectedRow();
            if (selectedRow != -1) {
                Object selectedValue = iv.getCereriTable().getValueAt(selectedRow, 0);
                if(!im.deleteCerereFromDB((int) selectedValue, (java.sql.Date) iv.getCereriTable().getValueAt(selectedRow, 1))){
                    iv.showErrorMessageIn("Nu s-a putut refuza cererea");
                }
                else{
                    iv.setCereriRowData(im.getCereriForResurse((int) selectedValue));
                    iv.reAddToCereriPanel();
                    iv.reAddToInspectorM1Panel();
                    iv.BV_showSuccesMessage("Concediu refuzat pentru angajatul cu ID " + (int) selectedValue);
                }
            }
            else{
                iv.showErrorMessageIn("Nu ai selectat nici o cerere");
            }
        }
    }
}



