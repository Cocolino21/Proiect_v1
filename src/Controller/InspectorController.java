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
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
    }
}
