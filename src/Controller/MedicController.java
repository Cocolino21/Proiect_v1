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

public class MedicController extends BasicController implements ActionListener {

    BasicView mv;
    BigModel mm;

    public MedicController(MedicView view, MedicModel model) {
        super(view,model);

        this.mv = view;
        this.mm = model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }

}
