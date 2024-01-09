package Controller;

import Model.BigModel;
import Model.ReceptionerModel;
import View.BasicView;
import View.ReceptionerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionerController extends BasicController implements ActionListener {

    ReceptionerView rv;
    ReceptionerModel rm;

    public ReceptionerController(BasicView view, BigModel model) {
        super(view, model);
        rv = (ReceptionerView) view;
        rm = (ReceptionerModel) model;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == rv.getButtonAdaugaProgramare())
            ;
            //set visible JFrame
        if (e.getSource() == rv.getButtonAdaugaPacient())
            ;
            // set visible JFrame
    }

     // if buton apasat e create pacient/ create programare
    // set visibile JFrame craere pacient/programre
    //creez Jframe creare pacient/rpgoramare
}
