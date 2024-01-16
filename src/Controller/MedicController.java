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
import java.util.Arrays;
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
        mv.getInvestigatiiButton().addActionListener(this);
        mv.getVeziRaportButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == mv.getRaportButton()) {
            mv.getRaportFrame().setVisible(true);

        }
        if (e.getSource() == mv.getIstoricButton()){
            int selectedRow = mv.getProgramariMedicTable().getSelectedRow();
            // System.out.println(selectedRow);
            if (selectedRow != -1) {

                Object selectedValue = mv.getProgramariMedicTable().getValueAt(selectedRow, 0);
                mv.setIstoricRowData(mm.getIstoricRapoarteForMedic((int)selectedValue));

                mv.updateIstoricTable();
                mv.buildIstoricFrame();
                mv.getIstoricFrame().setVisible(true);


            }

        }
        if(e.getSource()==mv.getVeziRaportButton())
        {
            int selectedRow = mv.getIstoricTable().getSelectedRow();

            if (selectedRow != -1) {

                Object selectedValue = mv.getIstoricTable().getValueAt(selectedRow, 0);

                mv.setDetaliiRaportServiciiEfectuateArray(mm.getServiciiForRaport((int)selectedValue));
                mv.buildServiciiForRaportJFrame();
                mv.getDetaliiRaportFrame().setVisible(true);

            }
        }
        if (e.getSource() == mv.getInvestigatiiButton()){
            mv.getInvestigatiiFrame().setVisible(true);
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(1);
            al.add(2);
            al.add(4);
           // mm.insertInvestigatieInDB(2,al);
        }
        if(e.getSource()==mv.getBV_m3Button())
        {
            mv.setProgramariMedicRowData(mm.getProgramariForMedic(mm.getCurrentAngajat().getId()));
            mv.reAddToMedicM3Panel();
            mv.reAddToBV_RP_m3Panel();
        }



    }

}
