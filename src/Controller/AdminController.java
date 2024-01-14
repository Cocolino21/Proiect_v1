package Controller;

import Model.AdminModel;
import Model.BigModel;
import View.AdminView;
import View.BasicView;

import java.awt.event.ActionListener;

public class AdminController extends BasicController implements ActionListener {

    AdminView av;
    AdminModel am;
    public AdminController(BasicView view, BigModel model) {
        super(view, model);
        av = (AdminView) view;
        am = (AdminModel) model;
    }
}
