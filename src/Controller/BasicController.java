package Controller;

import Model.BigModel;
import Model.MedicModel;
import View.BasicView;
import View.MedicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BasicController extends BigController implements ActionListener {

    BasicView mv;
    BigModel mm;

    public BasicController(BasicView view, BigModel model) {

        this.mv = view;
        this.mm = model;

        mv.getBV_logOutButton().addActionListener(this);
        mv.getBV_profileButton().addActionListener(this);
        mv.getBV_m1Button().addActionListener(this);
        mv.getBV_m2Button().addActionListener(this);
        mv.getBV_m3Button().addActionListener(this);
        mv.getBV_creeazaCerereConcediuButton().addActionListener(this);
        mv.getBV_ccjf_submitButton().addActionListener(this);

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
            if(!(mm.getCurrentAngajat().getFunctie().equals("inspector_resurse_um"))) {
                for (int i = 0; i < 7; i++) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    ArrayList<Time> temp = mm.getOrarForSpecificAngajatId(mm.getCurrentAngajat().getId(), mv.getBV_orar()[i].getText());
                    mv.getBV_userOrarTextFields_begin()[i].setText( sdf.format(temp.getFirst()));
                    mv.getBV_userOrarTextFields_end()[i].setText(sdf.format(temp.getLast()));
                }
            }
            mv.reAddToBV_RP_m1Panel();
        }
        if(e.getSource()==mv.getBV_creeazaCerereConcediuButton())
        {
            mv.getBV_ccjf_beginDate().setDate(null);
            mv.getBV_ccjf_endDate().setDate(null);
            mv.getBV_ccjf_motivTF().setText("");
            mv.getBV_ccjf().setVisible(true);
        }
        if(e.getSource()==mv.getBV_ccjf_submitButton())
        {
            Date beginDate = mv.getBV_ccjf_beginDate().getDate();
            Date endDate = mv.getBV_ccjf_endDate().getDate();
            try {
                java.sql.Date sqlBeginDate = new java.sql.Date(beginDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                if (mm.insertCerereConcediu(mm.getCurrentAngajat().getId(), sqlBeginDate, sqlEndDate, mv.getBV_ccjf_motivTF().getText())) {
                    mv.getBV_ccjf().setVisible(false);
                    mv.BV_showSuccesMessage("Cererea ta a fost depusa");
                } else {
                    mv.BV_showErrorMessage("Nu s-a putut face cererea");
                }
            }catch(NullPointerException eb)
            {
                mv.BV_showErrorMessage("Nu ai selectat data");
            }

        }
        if(e.getSource()==mv.getBV_m2Button())
        {
            mv.reAddToBV_RP_m2Panel();
        }
        if(e.getSource()==mv.getBV_m3Button())
        {
            mv.reAddToBV_RP_m3Panel();
        }
    }

}
