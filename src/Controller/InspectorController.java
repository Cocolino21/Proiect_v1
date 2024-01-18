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
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

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
        iv.getPersonalizeazaPretServiciiButton().addActionListener(this);
        iv.getPersonalizeazaServiciiSubmitButton().addActionListener(this);
        iv.setAngajatiRowData(im.getAngajatiForResurse(im.getCurrentAngajat().getId_centru()));
        iv.getSearchAngajatButton().addActionListener(this);
        iv.getRefreshAngajatButton().addActionListener(this);
        iv.reAddToInspectorM1Panel();
        iv.getPersonalizeazaServiciiForMedicCB().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == iv.getBV_m1Button()){

            iv.setAngajatiRowData(im.getAngajatiForResurse(im.getCurrentAngajat().getId_centru()));
            iv.reAddToInspectorM1Panel();
            iv.reAddToBV_RP_m1Panel();
        }
        if(e.getSource()==iv.getSearchAngajatButton())
        {
            im.searchInTable(iv.getCautaAngajatTF().getText(),iv.getAngajatiTable(),iv.getAngajatiTableModel());
        }
        if(e.getSource()==iv.getRefreshAngajatButton())
        {
            im.clearSearch(iv.getAngajatiTable());
        }
        if(e.getSource() == iv.getVeziOrarButton()) {
            iv.getOrarFrame().setVisible(true);
            int selectedRow = iv.getAngajatiTable().getSelectedRow();
            int selectedID = (int) iv.getAngajatiTable().getValueAt(selectedRow, 0);
            for (int i = 0; i < 7; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                ArrayList<Time> temp = im.getOrarForSpecificAngajatId(selectedID, iv.getZileSaptamaniiLabel()[i].getText());
                iv.getZileSaptamaniiTF()[i].setText("%s-%s".formatted(sdf.format(temp.getFirst()), sdf.format(temp.getLast())));
            }
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
        if(e.getSource()==iv.getPersonalizeazaPretServiciiButton())
        {
            int selectedRow = iv.getAngajatiTable().getSelectedRow();
            if (selectedRow != -1) {
                Object selectedValue = iv.getAngajatiTable().getValueAt(selectedRow, 0);
                Object selectedValueFUNCTIE = iv.getAngajatiTable().getValueAt(selectedRow,3);
                if(((String)selectedValueFUNCTIE).equals("medic"))
                {
                    iv.buidServiciiJFrame();
                    iv.getPersonalizeazaServiciiJF().setVisible(true);
                    iv.replaceServiciiForAngajatCB(im.getServiciiForMedic((int)selectedValue));


                }
                else {
                    iv.BV_showErrorMessage("Angajatul selectat nu este medic!");
                }

            }
            else{
                iv.BV_showErrorMessage("Nu ai selectat niciun angajat");
            }
        }
        if(e.getSource()==iv.getPersonalizeazaServiciiForMedicCB())
        {
            int selectedRow = iv.getAngajatiTable().getSelectedRow();
            Object selectedValue = iv.getAngajatiTable().getValueAt(selectedRow, 0);
            System.out.println("1");
            iv.getPersonalizeazaServiciiPretTF().setText(Integer.valueOf(im.getPretForMedicServiciu((int)selectedValue,im.getServiciuIdFromServiciuNume(Objects.requireNonNull(iv.getPersonalizeazaServiciiForMedicCB().getSelectedItem()).toString()))).toString());
            iv.getPersonalizeazaServiciiDurataTF().setText(Integer.valueOf(im.getDurataMinForMedicServiciu((int)selectedValue,im.getServiciuIdFromServiciuNume(Objects.requireNonNull(iv.getPersonalizeazaServiciiForMedicCB().getSelectedItem()).toString()))).toString());
        }
        if(e.getSource()==iv.getPersonalizeazaServiciiSubmitButton())
        {
            int selectedRow = iv.getAngajatiTable().getSelectedRow();
            System.out.println(selectedRow);
            if (selectedRow != -1) {
                Object selectedValue = iv.getAngajatiTable().getValueAt(selectedRow, 0);
                if (im.updateInTablePersonalizareServiciiMedic((int) selectedValue, im.getServiciuIdFromServiciuNume(Objects.requireNonNull(iv.getPersonalizeazaServiciiForMedicCB().getSelectedItem()).toString()), Integer.parseInt(iv.getPersonalizeazaServiciiPretTF().getText()), Integer.parseInt(iv.getPersonalizeazaServiciiDurataTF().getText()))) {
                    iv.BV_showSuccesMessage("Actualizare reusita");
                    iv.getPersonalizeazaServiciiJF().setVisible(false);
                } else {
                    iv.BV_showErrorMessage("Nu s-a putut modifica");
                }
            }
        }
    }
}



