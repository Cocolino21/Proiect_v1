package Controller;

import Model.BigModel;
import Model.ContabilModel;
import View.BasicView;
import View.ContabilView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContabilController extends BasicController implements ActionListener {

    ContabilModel cm;
    ContabilView cv;

    public ContabilController(BasicView view, BigModel model) {
        super(view, model);
        cm = (ContabilModel)model;
        cv = (ContabilView)view;
        cv.replaceSelectMedicCB(cm.getAngajatiNumePrenumeFromFunctieAndCentru("medic",cm.getCurrentAngajat().getId_centru()));
        cv.getProfitMedicButton().addActionListener(this);
        cv.getProfitCentruButton().addActionListener(this);
        cv.getProfitSpecialitateButton().addActionListener(this);
        cv.getSalarAngajatButton().addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == cv.getProfitMedicButton()) {
            cv.getProfitMedicFrame().setVisible(true);
        }
        if (e.getSource() == cv.getProfitSpecialitateButton()) {
            cv.getProfitSpecialitateFrame().setVisible(true);
        }
        if (e.getSource() == cv.getProfitCentruButton()) {
            cv.getProfitCentruFrame().setVisible(true);
        }
        if(e.getSource()==cv.getBV_m2Button())
        {
            cv.replaceSelectMedicCB(cm.getAngajatiNumePrenumeFromFunctieAndCentru("medic",cm.getCurrentAngajat().getId_centru()));
        }


    }
}
