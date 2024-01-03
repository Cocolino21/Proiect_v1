package Controller;

import Model.*;
import View.AuthView;
import View.MedicView;
import View.SuperAdminView;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Objects;

public class AuthController implements ActionListener {

    AuthCheck ac;
    AuthView av;
    public AuthController(AuthCheck ac, AuthView av) {
        this.ac = ac;
        this.av = av;
        av.replaceComboBoxItems(ac.getCenters());
        av.getLoginButton().addActionListener(this);
    }

    public void changeState(int state, CurrentAngajat currentAngajat)
    {

       // FlatDarkLaf.setup();
        switch(state) {

            case 0:
                SuperAdminView view = new SuperAdminView();
                SuperAdminModel model = new SuperAdminModel();
                SuperAdminController controller = new SuperAdminController(view,model);
                break;
            case 1:
                MedicView view_medic = new MedicView(currentAngajat);
                MedicModel model_medic = new MedicModel();
                model_medic.setCurrentAngajat(currentAngajat);
                MedicController controller_medic= new MedicController(view_medic,model_medic);

                break;
            case 2:
                break;

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==av.getLoginButton())
        {
            char[] pf = av.getPasswordField().getPassword();
            String temp = new String(pf);
            if(ac.CheckLoginInformation(av.getUsernameField().getText(), temp, Objects.requireNonNull(av.getSelectCentruCB().getSelectedItem()).toString()))
            {
                int x = ac.getFunctieAngajat(av.getUsernameField().getText());
                changeState(x, ac.getCurrentAngajat());
                av.setVisible(false);

            }
            else {
                av.showErrorMessage("Nu exista acest utilizator in acest centru");

            }

        }
    }


}
