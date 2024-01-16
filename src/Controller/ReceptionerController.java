package Controller;

import Model.BigModel;
import Model.ReceptionerModel;
import View.BasicView;
import View.ReceptionerView;
import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ReceptionerController extends BasicController implements ActionListener {

    ReceptionerView rv;
    ReceptionerModel rm;

    public ReceptionerController(BasicView view, BigModel model) {
        super(view, model);
        rv = (ReceptionerView) view;
        rm = (ReceptionerModel) model;
        rv.getButtonAdaugaPacient().addActionListener(this);
        rv.getButtonAdaugaProgramare().addActionListener(this);
        rv.getButtonStergeProgramare().addActionListener(this);
        rv.getAdaugaPacient().addActionListener(this);
        rv.setProgramariRowData(rm.getProgramari(rm.getCurrentAngajat().getId()));
        rv.getAdaugaProgramare().addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == rv.getButtonAdaugaProgramare()) {
            rv.replaceSelectIdMedicCB(rm.getAngajatiNumePrenumeFromFunctieAndCentru("medic",rm.getCurrentAngajat().getId_centru()));
            rv.replaceSelectIdPacientCB(rm.getPacienti());
            rv.getAdaugaProgramareFrame().setVisible(true);
        }
        if (e.getSource() == rv.getButtonAdaugaPacient()){
            rv.getAdaugaPacientFrame().setVisible(true);
        }
        if(e.getSource() == rv.getBV_m3Button())
        {
            rv.setProgramariRowData(rm.getProgramari(rm.getCurrentAngajat().getId()));
            rv.updateProgramariTable();
            rv.reAddToReceptionerM3Panel();
            rv.replaceSelectNumeCB(rm.getPacienti());
        }
        if(e.getSource()==rv.getAdaugaPacient())
        {
            if(rm.insertPacient(rv.getNumeTF().getText(),rv.getPrenumeTF().getText(),rv.getCnpTF().getText(),rv.getNrTelefonTF().getText(),rv.getEmailTF().getText())) {
                rv.BV_showSuccesMessage("Pacient adaugat cu succes");
                rv.getAdaugaPacientFrame().setVisible(false);
            }
            else
            {
                rv.BV_showErrorMessage("Pacientul nu a putut fi adaugat");
            }
        }
        if(e.getSource()==rv.getAdaugaProgramare())
        {
            JXDatePicker datePicker = rv.getDataDP();
            Date sqlDate = (datePicker.getDate() != null) ? new Date(datePicker.getDate().getTime()) : null;

            if (sqlDate != null) {
                // Create a SimpleDateFormat for parsing time
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                try {
                    // Parse the time string and get a java.util.Date
                    java.util.Date utilDate = sdf.parse(rv.getOraTF().getText());

                    // Convert java.util.Date to java.sql.Time
                    Time sqlTime = new Time(utilDate.getTime());

                    if(rm.insertProgramare(mm.getIdPacientFromNumePrenume(Objects.requireNonNull(rv.getIdPacientCB().getSelectedItem()).toString()),mm.getIdAngajatFromNumePrenumeFunctie(Objects.requireNonNull(rv.getIdMedicCB().getSelectedItem()).toString(),"medic"),mm.getCurrentAngajat().getId(),sqlDate,sqlTime)) {
                        rv.BV_showSuccesMessage("Programare adaugata cu succes");
                        rv.setProgramariRowData(rm.getProgramari(rm.getCurrentAngajat().getId()));
                        rv.getAdaugaProgramareFrame().setVisible(false);
                        rv.updateProgramariTable();
                        rv.reAddToReceptionerM3Panel();
                    }
                    else
                        rv.BV_showErrorMessage("Programarea nu a putut fi adaugata");



                    System.out.println("Converted SQL Time: " + sqlTime);
                } catch (ParseException pe) {
                    // Handle parsing exception
                    pe.printStackTrace();
                }
            }
        }

        if(e.getSource()==rv.getButtonStergeProgramare())
        {
            int selectedRow = rv.getProgramariTable().getSelectedRow();
           // System.out.println(selectedRow);
            if (selectedRow != -1)
            {
                Object selectedValue = rv.getProgramariTable().getValueAt(selectedRow, 0);
                System.out.println((int)selectedValue);
                    if (rm.deleteProgramareFromDB((int) selectedValue)) {
                        rv.setProgramariRowData(rm.getProgramari(rm.getCurrentAngajat().getId()));
                        rv.updateProgramariTable();
                        rv.reAddToReceptionerM3Panel();


                    }
            }
        }

    }
}
