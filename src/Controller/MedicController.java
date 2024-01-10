package Controller;

import Model.BigModel;
import Model.MedicModel;
import Model.ReceptionerModel;
import View.BasicView;
import View.MedicView;
import View.ReceptionerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MedicController extends BasicController implements ActionListener {

//    BasicView mv;
//    BigModel mm;
    MedicView mv;
    MedicModel mm;

    public MedicController(MedicView view, MedicModel model) {
        super(view,model);

        mv = view;
        mm = model;
        mv.getRaportButton().addActionListener(this);
        mv.getIstoricButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == mv.getRaportButton()) {
            mv.getRaportFrame().setVisible(true);
        }
        if (e.getSource() == mv.getIstoricButton()){
            mv.getIstoricPacientFrame().setVisible(true);
        }
    }

}
