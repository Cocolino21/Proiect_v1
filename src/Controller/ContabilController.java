package Controller;

import Model.BigModel;
import Model.ContabilModel;
import View.BasicView;
import View.ContabilView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ContabilController extends BasicController implements ActionListener {

    ContabilModel cm;
    ContabilView cv;

    public ContabilController(BasicView view, BigModel model) {
        super(view, model);
        cm = (ContabilModel)model;
        cv = (ContabilView)view;
        cv.replaceSelectMedicCB(cm.getAngajatiNumePrenumeFromFunctieAndCentru("medic",cm.getCurrentAngajat().getId_centru()));
        cv.replaceSelectSpecialitateCB(cm.getSpecializariFromMedicId(cm.getIdMedicFromCentruIdSiFunctie(cm.getCurrentAngajat().getId_centru(),"medic")));
        cv.replaceSelectAngajatCB(cm.getAngajatiNumePrenumeFromCentru(cm.getCurrentAngajat().getId_centru()));
        cv.getProfitMedicButton().addActionListener(this);
        cv.getProfitCentruButton().addActionListener(this);
        cv.getProfitSpecialitateButton().addActionListener(this);
        cv.getSalarAngajatButton().addActionListener(this);
        cv.getVeziProfitCentruButton().addActionListener(this);
        cv.getVeziProfitMedicButton().addActionListener(this);
        cv.getVeziProfitSpecialitateButton().addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == cv.getProfitMedicButton()) {
            cv.replaceProfitMedicLunaCB(new ArrayList<String>(Arrays.asList("Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie")));
            cv.getProfitMedicFrame().setVisible(true);
        }
        if (e.getSource() == cv.getProfitSpecialitateButton()) {
            cv.replaceSpecialitateLunaCB(new ArrayList<String>(Arrays.asList("Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie")));
            cv.getProfitSpecialitateFrame().setVisible(true);
        }
        if (e.getSource() == cv.getProfitCentruButton()) {
            cv.replaceProfitCentruLunaCB(new ArrayList<String>(Arrays.asList("Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie", "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie")));
            cv.getProfitCentruFrame().setVisible(true);
        }
        if(e.getSource()==cv.getBV_m2Button())
        {
            cv.replaceSelectMedicCB(cm.getAngajatiNumePrenumeFromFunctieAndCentru("medic",cm.getCurrentAngajat().getId_centru()));
            cv.replaceSelectAngajatCB(cm.getAngajatiNumePrenumeFromCentru(cm.getCurrentAngajat().getId_centru()));
            cv.replaceSelectSpecialitateCB(cm.getSpecializariFromMedicId(cm.getIdMedicFromCentruIdSiFunctie(cm.getCurrentAngajat().getId_centru(),"medic")));
        }
        if(e.getSource() == cv.getSalarAngajatButton()){

            cv.getSalarAngajatTF().setText(Integer.valueOf(cm.getSalariuAngajatFromNumePrenume(Objects.requireNonNull(cv.getAngajatCB().getSelectedItem()).toString())).toString());
        }

        if(e.getSource() == cv.getVeziProfitSpecialitateButton()){
            //cm.getProfitForSpecializare(cv.getProfitSpecialitateTF(),cv.getProfitSpecialitateLunaCB());
        }

        if(e.getSource() == cv.getVeziProfitMedicButton()){

        }

        if(e.getSource() == cv.getVeziProfitCentruButton()){

        }


    }
}
