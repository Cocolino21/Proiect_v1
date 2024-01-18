package Controller;

import Model.AsistentMedicalModel;
import Model.BigModel;
import View.AsistentMedicalView;
import View.BasicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AsistentMedicalController extends BasicController implements ActionListener {

    AsistentMedicalModel am;
    AsistentMedicalView av;
    public AsistentMedicalController(BasicView view, BigModel model) {
        super(view, model);
        av = (AsistentMedicalView) view;
        am = (AsistentMedicalModel) model;
        av.setProgramariRowData(am.getProgramari(am.getCurrentAngajat().getId()));
        av.getButtonCompletareAnalize().addActionListener(this);
        av.getSubmitButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if(e.getSource() == av.getButtonCompletareAnalize())
        {
            av.getCompletareAnalizeFrame().setVisible(true);
        }
        if(e.getSource() == av.getSubmitButton()){
            int selectedRow = av.getProgramariTable().getSelectedRow();
            int selectedID = (int) av.getProgramariTable().getValueAt(selectedRow, 0);
            if(!am.insertInRaportAnaliza(selectedID, av.getDetaliiTF().getText(), am.getCurrentAngajat().getId())){
                av.BV_showErrorMessage("Nu s-a putut adauga analiza");
            }
            else{

                av.BV_showSuccesMessage("Analiza adaugata cu success");
                av.getCompletareAnalizeFrame().setVisible(false);
            }

        }

    }
}
