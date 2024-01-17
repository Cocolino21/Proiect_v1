package Controller;

import Model.AdminModel;
import Model.BigModel;
import Model.CurrentAngajat;
import View.AdminView;
import View.BasicView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AdminController extends BasicController implements ActionListener {

    AdminView av;
    AdminModel am;
    boolean nowModifAngajat=false;
    public AdminController(BasicView view, BigModel model) {
        super(view, model);
        av = (AdminView) view;
        am = (AdminModel) model;
        av.getAdaugaAngajatButton().addActionListener(this);
        av.getModificaAngajatButton().addActionListener(this);
        av.getStergeAngajatButton().addActionListener(this);
        av.getSelectDeptCB2().addActionListener(this);
        av.getAajf_SubmitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == av.getBV_m1Button()){
            av.setAngajatiRowData(am.getAngajati(am.getCurrentAngajat().getId_centru()));
            av.reAddToBV_RP_m1Panel();
        }

        if(e.getSource()==av.getSelectDeptCB2())
        {
            if(av.getSelectDeptCB2().getSelectedItem()!=null)
            {
                av.replaceComboBoxItems_FCT(am.getFunctionsForDept(Objects.requireNonNull(av.getSelectDeptCB2().getSelectedItem()).toString()));
            }

        }
        if(e.getSource() == av.getAdaugaAngajatButton())
        {
            nowModifAngajat = false;
            av.getAdaugaAngajatJFrame().setVisible(true);
            av.getAdaugaAngajatJFrame().setTitle("Adauga angajat");
            for(int i=0;i<13;i++)
            {
                if(i!=8&&i!=12) {
                    ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                    if (i == 11 || i == 10)
                        ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                }
                if(i == 12)
                {
                    int temp = am.getCurrentAngajat().getId_centru();
                    String temp1 = String.valueOf(temp);
                    ((JTextField) av.getAajf_tfOrCb()[i]).setText(temp1);
                    ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(false);
                }
            }
            av.replaceComboBoxItems_DEPT2(am.getDepts());
            av.replaceComboBoxItems_FCT(am.getFunctionsForDept(Objects.requireNonNull(av.getSelectDeptCB2().getSelectedItem()).toString()));

        }
        if(e.getSource()==av.getAajf_SubmitButton())
        {
            if((!Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString().equals("medic")) &&  (av.getAajf_codParafaTF().getText().equals("")) && (av.getAajf_titluStiintificTF().getText().equals(""))  &&  (av.getAajf_postDidacticTF().getText().equals(""))  &&  (av.getAajf_procentTF().getText().equals(""))  )
            {
                if(!nowModifAngajat)
                {
                    if (am.insertAngajat(((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText())) {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToBV_RP_m1Panel();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11||i==10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAdaugaAngajatJFrame().setVisible(false);

                    }else {
                        av.showErrorMessage("Nu s-a putut adauga angajatul!");
                    }
                }else {
                    if((!Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString().equals("medic")) &&  (av.getAajf_codParafaTF().getText().equals("")) && (av.getAajf_titluStiintificTF().getText().equals(""))  &&  (av.getAajf_postDidacticTF().getText().equals(""))  &&  (av.getAajf_procentTF().getText().equals(""))  ) {
                        if (am.updateAngajat(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText())) {
                            av.setAngajatiRowData(am.getAngajati(-1));
                            am.clearSearch(av.getAngajatiTable());
                            av.reAddToBV_RP_m1Panel();

                            for (int i = 0; i < 13; i++) {
                                if (i != 8 && i != 12)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                                if (i == 11 || i == 10)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                            }
                            av.getAdaugaAngajatJFrame().setVisible(false);
                        } else {
                            av.showErrorMessage("Nu s-a putut edita angajatul!");
                        }
                    } else if(am.updateMedic(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText(), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), Integer.parseInt(av.getAajf_procentTF().getText())))
                    {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToBV_RP_m1Panel();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11 || i == 10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAdaugaAngajatJFrame().setVisible(false);
                    } else {
                        av.showErrorMessage("Nu s-a putut edita angajatul!");
                    }
                }
            } else if(av.getSelectFunctiiCB().getSelectedItem().equals("medic")){
                if(!nowModifAngajat)
                {
                    if (am.insertMedicAngajat(((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText(), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), av.getAajf_procentTF().getText() )) {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToBV_RP_m1Panel();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11||i==10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAajf_codParafaTF().setText("");
                        av.getAajf_titluStiintificTF().setText("");
                        av.getAajf_procentTF().setText("");
                        av.getAajf_postDidacticTF().setText("");

                        av.getAdaugaAngajatJFrame().setVisible(false);
                    }else {

                        av.showErrorMessage("Nu s-a putut adauga angajatul!");
                    }
                }else {
                    if((!Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString().equals("medic")) &&  (av.getAajf_codParafaTF().getText().equals("")) && (av.getAajf_titluStiintificTF().getText().equals(""))  &&  (av.getAajf_postDidacticTF().getText().equals(""))  &&  (av.getAajf_procentTF().getText().equals(""))  ) {
                        if (am.updateAngajat(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText())) {
                            av.setAngajatiRowData(am.getAngajati(-1));
                            am.clearSearch(av.getAngajatiTable());
                            av.reAddToBV_RP_m1Panel();

                            for (int i = 0; i < 13; i++) {
                                if (i != 8 && i != 12)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                                if (i == 11 || i == 10)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                            }
                            av.getAdaugaAngajatJFrame().setVisible(false);
                            nowModifAngajat = false;
                        } else {
                            av.showErrorMessage("Nu s-a putut edita angajatul!");
                        }
                    } else if(am.updateMedic(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), av.getSelectCentruTF().getText(), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), Integer.parseInt(av.getAajf_procentTF().getText())))
                    {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToBV_RP_m1Panel();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11 || i == 10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAdaugaAngajatJFrame().setVisible(false);
                    } else {
                        av.showErrorMessage("Nu s-a putut edita angajatul!");
                    }
                }
            }
        }

        if(e.getSource()==av.getStergeAngajatButton())
        {
            int selectedRow = av.getAngajatiTable().getSelectedRow();
            if (selectedRow != -1)
            {
                Object selectedValue = av.getAngajatiTable().getValueAt(selectedRow, 0);
                Object checkForSuper = av.getAngajatiTable().getValueAt(selectedRow, 3);
                if(!checkForSuper.toString().equals("super_admin")) {
                    if (am.deleteAngajatFromDB((int) selectedValue)) {
                        av.setAngajatiRowData(am.getAngajati(-1));

                        av.reAddToBV_RP_m1Panel();
                        am.clearSearch(av.getAngajatiTable());
                    }
                    else {
                        av.showErrorMessage("Nu se poate sterge angajatul");
                    }
                }
                else
                {
                    av.showErrorMessage("Nu poti sterge super-admin");
                }
            }
        }

        if(e.getSource() == av.getModificaAngajatButton())
        {
            nowModifAngajat = true;
            int selectedRow = av.getAngajatiTable().getSelectedRow();

            if (selectedRow != -1)
            {
                Object checkForSuper = av.getAngajatiTable().getValueAt(selectedRow, 3);
                if(!checkForSuper.toString().equals("super_admin"))
                {

                    av.getAdaugaAngajatJFrame().setVisible(true);
                    av.getAdaugaAngajatJFrame().setTitle("Modifica Angajat");
                    for (int i = 0; i < 13; i++) {
                        if (i != 8 && i != 12)
                            ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                    }
                    av.replaceComboBoxItems_DEPT2(am.getDepts());

                    int selectedID = (int) av.getAngajatiTable().getValueAt(selectedRow, 0);
                    ArrayList<String> temp = am.getInfoForAngajat(selectedID);
                    if (temp != null) {
                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12) {
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText(temp.get(i));
                                if (i == 11||i==10) {
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(false);
                                }
                            } else if (i == 8) {
                                String functie = temp.get(i);
                                String dept = am.getDeptForFunctie(functie);
                                av.getSelectDeptCB2().setSelectedItem(dept);
                                av.replaceComboBoxItems_FCT(am.getFunctionsForDept(Objects.requireNonNull(av.getSelectDeptCB2().getSelectedItem()).toString()));
                                av.getSelectFunctiiCB().setSelectedItem(functie);

                            } else {
                                String centru = am.getCentruNumeFromCentruId(Integer.parseInt(temp.get(i)));

                            }
                        }
                    }

                }
                else {
                    av.getAdaugaAngajatJFrame().setVisible(false);
                    av.showErrorMessage("Nu poti edita super_admin");
                }
            }
            else{
                av.getAdaugaAngajatJFrame().setVisible(false);
                av.showErrorMessage("Nu ai selectat un angajat");
            }

        }
    }
}
