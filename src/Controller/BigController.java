package Controller;

import Model.AuthCheck;
import View.AuthView;
import View.BasicView;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BigController implements ActionListener {



    public void logOut(JFrame currentView)
    {
        currentView.setVisible(false);

        FlatDarkLaf.setup();
        AuthView av = new AuthView();
        AuthCheck ac = new AuthCheck();
        AuthController controller = new AuthController(ac, (AuthView) av);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
