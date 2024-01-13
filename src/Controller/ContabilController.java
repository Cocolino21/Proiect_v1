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

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        cv.getProfitMedicButton().addActionListener(this);
        // aici trebuie puse actionlistenere pe butoaneee

    }
}
