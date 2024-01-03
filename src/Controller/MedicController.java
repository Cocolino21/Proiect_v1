package Controller;

import Model.MedicModel;
import View.MedicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MedicController extends BigController implements ActionListener {

    MedicView mv;
    MedicModel mm;

    public MedicController(MedicView view, MedicModel model) {
        this.mv = view;
        this.mm = model;

        mv.getBV_logOutButton().addActionListener(this);
        mv.getBV_profileButton().addActionListener(this);
        mv.getBV_m1Button().addActionListener(this);
        mv.getBV_creeazaCerereConcediuButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mv.getBV_logOutButton())
            logOut(mv);
        if(e.getSource()==mv.getBV_profileButton())
        {

            mv.reAddToBV_RP_profilePanel();
            mv.getBV_userInfoTextFields()[11].setText(mm.getCurrentAngajat().getCentruNumeFromCentruId(mm.getCurrentAngajat().getId_centru()));
        }
        if(e.getSource()==mv.getBV_m1Button())
        {
            for(int i = 0 ;i < 7; i++)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                ArrayList<Time> temp = mm.getOrarForSpecificAngajatId(mm.getCurrentAngajat().getId(),mv.getBV_orar()[i].getText());
                mv.getBV_userOrarTextFields_begin()[i].setText( sdf.format(temp.getFirst()));
                mv.getBV_userOrarTextFields_end()[i].setText(sdf.format(temp.getLast()));

            }
            mv.reAddToBV_RP_m1Panel();
        }
        if(e.getSource()==mv.getBV_creeazaCerereConcediuButton())
        {
            mv.getBV_ccjf().setVisible(true);
        }
    }

}
