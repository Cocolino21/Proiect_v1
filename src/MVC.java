import Controller.AuthController;
import Controller.HashIt;
import Controller.SuperAdminController;
import Model.AuthCheck;
import Model.SuperAdminModel;
import View.*;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MVC {



    public static void main(String[] args){
        FlatDarkLaf.setup();
        AuthView av = new AuthView();

        AuthCheck ac = new AuthCheck();
        AuthController controller = new AuthController(ac, (AuthView) av);
        //System.out.println(HashIt.hashThePass("root_123"));
        //controller. changeState(0, null);;
       // controller.changeState(1,null);
    }
}
