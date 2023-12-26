package Controller;

import Model.AuthCheck;
import View.AuthView;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==av.getLoginButton())
        {
            char[] pf = av.getPasswordField().getPassword();
            String temp = new String(pf);
            if(ac.CheckLoginInformation(av.getUsernameField().getText(), temp, Objects.requireNonNull(av.getSelectCentruCB().getSelectedItem()).toString()))
            {
                System.out.println("succes");
                new AuthView();
            }
            else {
                System.out.println("error");

            }

        }
    }


}
