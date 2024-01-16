package Controller;

import Model.BigModel;
import Model.InspectorModel;
import View.BasicView;
import View.InspectorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InspectorController extends BasicController implements ActionListener {
    InspectorView  iv;
    InspectorModel  im;
    public InspectorController(BasicView view, BigModel model) {
        super(view, model);
        iv = (InspectorView) view;
        im = (InspectorModel) model;
        iv.replaceTabelaAngajati(iv.getAngajatiRowData());
        iv.getVeziOrarButton().addActionListener(this);
        iv.getVeziCerereConcediuButton().addActionListener(this);
       // iv.getAcceptConcediuButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == iv.getBV_m1Button()){

            iv.setAngajatiRowData(im.getAngajatiForResurse(im.getCurrentAngajat().getId_centru()));
            iv.reAddToInspectorM1Panel();
            iv.reAddToBV_RP_m1Panel();
        }

        if(e.getSource() == iv.getVeziOrarButton()) {
            iv.getOrarFrame().setVisible(true);
        }

        if(e.getSource() == iv.getVeziCerereConcediuButton()) {

        }
    }
}



