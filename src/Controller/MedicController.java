package Controller;

import Model.BigModel;
import Model.MedicModel;
import Model.ReceptionerModel;
import View.BasicView;
import View.MedicView;
import View.ReceptionerView;
import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class MedicController extends BasicController implements ActionListener {

//    BasicView mv;
//    BigModel mm;
    MedicView mv;
    MedicModel mm;

    ArrayList<String> temp1_AS = new ArrayList<>();
    ArrayList<Integer> temp2_AS = new ArrayList<>();

    public MedicController(MedicView view, MedicModel model) {
        super(view,model);

        mv = view;
        mm = model;
        mv.getRaportButton().addActionListener(this);
        mv.getIstoricButton().addActionListener(this);
        mv.getInvestigatiiButton().addActionListener(this);
        mv.getVeziRaportButton().addActionListener(this);
        mv.getAdaugaServiciuButton().addActionListener(this);
        mv.getSubmitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == mv.getRaportButton()) {


          /*  for(String s : mm.getAngajatiNumePrenumeFromFunctieAndCentru("asistent_medical",mm.getCurrentAngajat().getId_centru()))
            {
                System.out.println(s);
            }
            System.out.println("2323");*/

            mv.replaceAsistentMedicalCB(mm.getAngajatiNumePrenumeFromFunctieAndCentru("asistent_medical",mm.getCurrentAngajat().getId_centru()));
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

            mv.replaceServiciiAdaugateList(mm.getServiciiForMedic(mm.getCurrentAngajat().getId()));
            mv.buildServiciiForRaportJFrame();
            mv.getInvestigatiiFrame().setVisible(true);
           // mm.insertInvestigatieInDB(2,al);
        }
        if(e.getSource()==mv.getAdaugaServiciuButton())
        {
            for( String s : temp1_AS)
            {
                temp1_AS.remove(s);
            }
            for(Integer i : temp2_AS)
            {
                temp2_AS.remove(i);
            }

            temp1_AS = (ArrayList<String>) mv.getSeriviciiAdaugateList().getSelectedValuesList();

            for(String s : temp1_AS)
            {
                temp2_AS.add(mm.getServiciuIdFromServiciuNume(s));
            }

            if(!temp2_AS.isEmpty())
            {
                mv.BV_showSuccesMessage("Servicii memorate");
                mv.getInvestigatiiFrame().setVisible(false);
            }
            else {
                mv.BV_showErrorMessage("Eroare");
            }

        }
        if(e.getSource()==mv.getSubmitButton())
        {
            int selectedRow = mv.getProgramariMedicTable().getSelectedRow();
            // System.out.println(selectedRow);
            if (selectedRow != -1) {

                Object selectedValueIDRAPORT = mv.getProgramariMedicTable().getValueAt(selectedRow, 6);
                Object selectedValueIDPACIENT = mv.getProgramariMedicTable().getValueAt(selectedRow,0);
                JXDatePicker datePicker = mv.getDataEfectuariiDP();


                java.util.Date utilDate = datePicker.getDate();

                // Convert java.util.Date to java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                if(mm.insertRaport((int)selectedValueIDRAPORT,(int)selectedValueIDPACIENT,mm.getIdAngajatFromNumePrenumeFunctie(Objects.requireNonNull(mv.getAsistentMedicalCB().getSelectedItem()).toString(),"asistent_medical"),mv.getRecomandariTF().getText(),mv.getIstoricRelevantTF().getText(),mv.getDiagonsticTF().getText(),sqlDate,temp2_AS))
                {
                    mv.BV_showSuccesMessage("Raport adaugat cu succes");
                    mv.getRaportFrame().setVisible(false);
                    mv.setProgramariMedicRowData(mm.getProgramariForMedic(mm.getCurrentAngajat().getId()));
                    mv.reAddToMedicM3Panel();
                    mv.reAddToBV_RP_m3Panel();
                }
                else {
                    mv.BV_showErrorMessage("Raportul nu a putut fi adaugat");
                }
            }
        }
        if(e.getSource()==mv.getBV_m3Button())
        {
            mv.setProgramariMedicRowData(mm.getProgramariForMedic(mm.getCurrentAngajat().getId()));
            mv.reAddToMedicM3Panel();
            mv.reAddToBV_RP_m3Panel();
        }



    }

}
