package Controller;

import Model.AuthCheck;
import Model.SuperAdminModel;
import View.AuthView;
import View.SuperAdminView;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class SuperAdminController extends BigController implements ActionListener {

    SuperAdminModel am;
    SuperAdminView av;

    boolean nowModifAngajat=false;
    public SuperAdminController(SuperAdminView av, SuperAdminModel am)
    {
        this.av = av;
        this.am = am;

        av.getCentreButton().addActionListener(this);
        av.getAdminButton().addActionListener(this);
        av.getAngajatiButton().addActionListener(this);
        av.getFunctiiButton().addActionListener(this);
        av.getLogOutButton().addActionListener(this);
        av.getBackButton().addActionListener(this);
        av.getAddCentruButton().addActionListener(this);
        av.getRemoveCentruButton().addActionListener(this);
        av.getSearchCentruButton().addActionListener(this);
        av.getClearSearchCentruButton().addActionListener(this);
        av.getAcjf_SubmitButton().addActionListener(this);
        av.getAddAngajatButton().addActionListener(this);
        av.getRemoveAngajatButton().addActionListener(this);
        av.getSearchAngajatButton().addActionListener(this);
        av.getClearSearchAngajatButton().addActionListener(this);
        av.getEditAngajatButton().addActionListener(this);
        av.getAajf_SubmitButton().addActionListener(this);
        av.getEditAngajatButton().addActionListener(this);
        av.getSelectDeptCB2().addActionListener(this);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==av.getLogOutButton())
            logOut(av);
        if(e.getSource()==av.getCentreButton()) {
            av.setCentreRowData(am.getCentre());
            av.replaceComboBoxItems(am.getCenters());
            av.reAddToCentreView();
        }
        if(e.getSource()==av.getSelectDeptCB2())
        {
            if(av.getSelectDeptCB2().getSelectedItem()!=null)
            {
                av.replaceComboBoxItems_FCT(am.getFunctionsForDept(Objects.requireNonNull(av.getSelectDeptCB2().getSelectedItem()).toString()));
            }

        }
        if(e.getSource()==av.getSearchCentruButton())
        {
            am.searchInTable(Objects.requireNonNull(av.getSelectCentruCB().getSelectedItem()).toString(),av.getCentreTable(),av.getCentreTableModel());
        }
        if(e.getSource()==av.getClearSearchCentruButton())
        {
            am.clearSearch(av.getCentreTable());
        }
        if(e.getSource()==av.getAddCentruButton())
        {
            av.getAcjf_numeTF().setText(null);
            av.getAcjf_adresaTF().setText(null);
            av.getAcjf_programTF().setText(null);
            av.getAddCentruJFrame().setVisible(true);
        }
        if(e.getSource()==av.getAcjf_SubmitButton())
        {
            String nume = av.getAcjf_numeTF().getText();
            String adresa = av.getAcjf_adresaTF().getText();
            String program = av.getAcjf_programTF().getText();
            if(nume.length()>1&&adresa.length()>1&&program.length()>1)
            {
                if(am.insertCentruIntoDB(nume,adresa,program))
                {
                    av.setCentreRowData(am.getCentre());
                    av.replaceComboBoxItems(am.getCenters());
                    av.reAddToCentreView();
                    av.getAcjf_l4().setVisible(false);
                    av.getAddCentruJFrame().setVisible(false);
                }
                else
                {
                    av.showErrorMessage("Nu s-a putut adauga centrul!");
                }
            }
            else {
                av.showErrorMessage("Nu s-a putut adauga centrul!");
            }
        }
        if(e.getSource()==av.getRemoveCentruButton())
        {
            int selectedRow = av.getCentreTable().getSelectedRow();
            if (selectedRow != -1)
            {
                Object selectedValue = av.getCentreTable().getValueAt(selectedRow, 0);
                Object checkForSuper = av.getCentreTable().getValueAt(selectedRow, 1);
                if(!checkForSuper.toString().equals("Super")) {
                    if (am.deleteCentruFromDB((int) selectedValue)) {
                        av.setCentreRowData(am.getCentre());
                        av.replaceComboBoxItems(am.getCenters());
                        av.reAddToCentreView();
                    }
                    else {
                        av.showErrorMessage("Nu se poate sterge centrul, probabil inca are angajati");
                    }
                }
                else
                {
                    av.showErrorMessage("Nu poti sterge super-admin");
                }
            }
        }

        if(e.getSource()==av.getAngajatiButton()) {
            av.setAngajatiRowData(am.getAngajati(-1));
            av.replaceComboBoxItems2(am.getCenters());
            av.replaceComboBoxItems_DEPT(am.getDepts());
            av.reAddToAngajatiView();
        }
        if(e.getSource()==av.getSearchAngajatButton())
        {
            av.setAngajatiRowData(am.getAngajati(-1));
            if(av.getSelectCentruCB2().getSelectedItem()!=null) {
                av.setAngajatiRowData(am.getAngajati(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB2().getSelectedItem()).toString())));


            }
            av.reAddToAngajatiView();
            if(av.getSelectDeptCB().getSelectedItem()!=null) {
                am.searchInTable(av.getSelectDeptCB().getSelectedItem().toString(), av.getAngajatiTable(), av.getAngajatiTableModel());
            }

        }
        if(e.getSource()==av.getClearSearchAngajatButton())
        {
            av.setAngajatiRowData(am.getAngajati(-1));
            am.clearSearch(av.getAngajatiTable());
            av.getSelectDeptCB().setSelectedItem(null);
            av.getSelectCentruCB2().setSelectedItem(null);
            av.reAddToAngajatiView();

        }
        if(e.getSource()==av.getAddAngajatButton())
        {
            nowModifAngajat = false;
            av.getAddAngajatJFrame().setVisible(true);
            av.getAddAngajatJFrame().setTitle("Adauga Angajat");
            for(int i=0;i<13;i++)
            {
                if(i!=8&&i!=12) {
                    ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                    if (i == 11 || i == 10)
                        ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                }
            }
            av.replaceComboBoxItems_DEPT2(am.getDepts());
            av.replaceComboBoxItems_FCT(am.getFunctionsForDept(Objects.requireNonNull(av.getSelectDeptCB2().getSelectedItem()).toString()));
            av.replaceComboBoxItems3(am.getCenters());
        }

        if(e.getSource()==av.getAajf_SubmitButton())
        {
            if((!Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString().equals("medic")) &&  (av.getAajf_codParafaTF().getText().equals("")) && (av.getAajf_titluStiintificTF().getText().equals(""))  &&  (av.getAajf_postDidacticTF().getText().equals(""))  &&  (av.getAajf_procentTF().getText().equals(""))  )
            {
                if(!nowModifAngajat)
                 {
                     if (am.insertAngajat(((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), Integer.toString(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB3().getSelectedItem()).toString())))) {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToAngajatiView();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11||i==10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAddAngajatJFrame().setVisible(false);

                    }else {
                         av.showErrorMessage("Nu s-a putut adauga angajatul!");
                     }
                }else {
                    if((!Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString().equals("medic")) &&  (av.getAajf_codParafaTF().getText().equals("")) && (av.getAajf_titluStiintificTF().getText().equals(""))  &&  (av.getAajf_postDidacticTF().getText().equals(""))  &&  (av.getAajf_procentTF().getText().equals(""))  ) {
                        if (am.updateAngajat(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), Integer.toString(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB3().getSelectedItem()).toString())))) {
                            av.setAngajatiRowData(am.getAngajati(-1));
                            am.clearSearch(av.getAngajatiTable());
                            av.reAddToAngajatiView();

                            for (int i = 0; i < 13; i++) {
                                if (i != 8 && i != 12)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                                if (i == 11 || i == 10)
                                    ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                            }
                            av.getAddAngajatJFrame().setVisible(false);
                        } else {
                            av.showErrorMessage("Nu s-a putut edita angajatul!");
                        }
                    } else if(am.updateMedic(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), Integer.toString(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB3().getSelectedItem()).toString())), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), Integer.parseInt(av.getAajf_procentTF().getText())))
                    {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToAngajatiView();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11 || i == 10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAddAngajatJFrame().setVisible(false);


                    } else {
                        av.showErrorMessage("Nu s-a putut edita angajatul!");
                    }
                }
            } else if(av.getSelectFunctiiCB().getSelectedItem().equals("medic")){
                if(!nowModifAngajat)
                {
                    if (am.insertMedicAngajat(((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), Integer.toString(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB3().getSelectedItem()).toString())), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), av.getAajf_procentTF().getText() )) {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToAngajatiView();

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

                        av.getAddAngajatJFrame().setVisible(false);
                    }else {

                        av.showErrorMessage("Nu s-a putut adauga angajatul!");
                    }
                }else {
                     if(am.updateMedic(am.getAngajatIdFromUsername(((JTextField) av.getAajf_tfOrCb()[10]).getText()), ((JTextField) av.getAajf_tfOrCb()[0]).getText(), ((JTextField) av.getAajf_tfOrCb()[1]).getText(), ((JTextField) av.getAajf_tfOrCb()[2]).getText(), ((JTextField) av.getAajf_tfOrCb()[3]).getText(), ((JTextField) av.getAajf_tfOrCb()[4]).getText(), ((JTextField) av.getAajf_tfOrCb()[5]).getText(), ((JTextField) av.getAajf_tfOrCb()[6]).getText(), ((JTextField) av.getAajf_tfOrCb()[7]).getText(), Objects.requireNonNull(av.getSelectFunctiiCB().getSelectedItem()).toString(), ((JTextField) av.getAajf_tfOrCb()[9]).getText(), ((JTextField) av.getAajf_tfOrCb()[10]).getText(), ((JTextField) av.getAajf_tfOrCb()[11]).getText(), Integer.toString(am.getCentruIdFromNumeCentru(Objects.requireNonNull(av.getSelectCentruCB3().getSelectedItem()).toString())), av.getAajf_codParafaTF().getText(), av.getAajf_titluStiintificTF().getText(), av.getAajf_postDidacticTF().getText(), Integer.parseInt(av.getAajf_procentTF().getText())))
                    {
                        av.setAngajatiRowData(am.getAngajati(-1));
                        am.clearSearch(av.getAngajatiTable());
                        av.reAddToAngajatiView();

                        for (int i = 0; i < 13; i++) {
                            if (i != 8 && i != 12)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                            if (i == 11 || i == 10)
                                ((JTextField) av.getAajf_tfOrCb()[i]).setEditable(true);
                        }
                        av.getAddAngajatJFrame().setVisible(false);

                    } else {
                        av.showErrorMessage("Nu s-a putut edita angajatul!");
                    }
                }
            }

        }

        if(e.getSource()==av.getRemoveAngajatButton())
        {
            int selectedRow = av.getAngajatiTable().getSelectedRow();
            if (selectedRow != -1)
            {
                Object selectedValue = av.getAngajatiTable().getValueAt(selectedRow, 0);
                Object checkForSuper = av.getAngajatiTable().getValueAt(selectedRow, 3);
                if(!checkForSuper.toString().equals("super_admin")) {
                    if (am.deleteAngajatFromDB((int) selectedValue)) {
                        av.setAngajatiRowData(am.getAngajati(-1));

                        av.reAddToAngajatiView();
                        am.clearSearch(av.getAngajatiTable());
                        av.getAddAngajatJFrame().setVisible(false);
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
        if(e.getSource()==av.getEditAngajatButton())
        {
            nowModifAngajat = true;
            int selectedRow = av.getAngajatiTable().getSelectedRow();

            if (selectedRow != -1)
            {
                Object checkForSuper = av.getAngajatiTable().getValueAt(selectedRow, 3);
                if(!checkForSuper.toString().equals("super_admin"))
                {

                    av.getAddAngajatJFrame().setVisible(true);
                    av.getAddAngajatJFrame().setTitle("Modif. Angajat");
                    for (int i = 0; i < 13; i++) {
                        if (i != 8 && i != 12)
                            ((JTextField) av.getAajf_tfOrCb()[i]).setText("");
                    }
                    av.replaceComboBoxItems_DEPT2(am.getDepts());
                    av.replaceComboBoxItems3(am.getCenters());
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

                                av.getSelectCentruCB3().setSelectedItem(centru);
                            }
                        }
                    }

                }
                else {
                    av.getAddAngajatJFrame().setVisible(false);
                    av.showErrorMessage("Nu poti edita super_admin");
                }
            }
            else{
                av.getAddAngajatJFrame().setVisible(false);
                av.showErrorMessage("Nu ai selectat un angajat");
            }
        }

        if(e.getSource()==av.getBackButton())
            av.reAddToAdminView();


    }
}
