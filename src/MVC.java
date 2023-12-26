import Controller.AuthController;
import Controller.HashIt;
import Model.AuthCheck;
import View.*;
import com.formdev.flatlaf.FlatDarkLaf;

import java.io.IOException;

public class MVC {
    public static void main(String[] args){
        FlatDarkLaf.setup();
        AuthView av = new AuthView();
        AuthCheck ac = new AuthCheck();
        AuthController controller = new AuthController(ac,av);
        System.out.println(HashIt.hashThePass("pass123"));
    }
}
