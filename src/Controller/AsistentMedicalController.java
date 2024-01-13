package Controller;

import Model.AsistentMedicalModel;
import Model.BigModel;
import View.AsistentMedicalView;
import View.BasicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsistentMedicalController extends BasicController implements ActionListener {

    AsistentMedicalModel am;
    AsistentMedicalView av;
    public AsistentMedicalController(BasicView view, BigModel model) {
        super(view, model);
        av = (AsistentMedicalView) view;
        am = (AsistentMedicalModel) model;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);


    }
}
