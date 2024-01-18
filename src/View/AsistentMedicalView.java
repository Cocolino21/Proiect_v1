package View;
import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


import Model.AuthCheck;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AsistentMedicalView extends BasicView {

    /////////// Completare Analize frame
    private JFrame completareAnalizeFrame;
    private JPanel completareAnalizePanel;
    private JTextField detaliiTF;
    private JButton submitButton;
    /////////// Final Completare Analize frame


    private JButton buttonCompletareAnalize;
    private JPanel asistentM3Panel;
    private JComboBox<String> selectNumeCB = new JComboBox<>();
    private DefaultTableModel programariTableModel;
    private JScrollPane programariSP;
    private JTable programariTable;
    Object[][] programariRowData = null;
    @Override
    public void reAddToBV_RP_m3Panel()
    {
        updateProgramariTable();

        reAddToAsistentRM3Panel();
        asistentM3Panel.setPreferredSize(new Dimension(675, 500));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(asistentM3Panel, "gapx 10");

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToAsistentRM3Panel()
    {
        asistentM3Panel.removeAll();
        asistentM3Panel.add(buttonCompletareAnalize, "span");
        asistentM3Panel.add(programariSP);
    }


    public AsistentMedicalView(CurrentAngajat currentAngajat) {
        super("Proiect_V1_Policlinici_Asistent_Medical", currentAngajat);
        asistentM3Panel = new JPanel(new MigLayout());
        buttonCompletareAnalize = new JButton("Completare Analize");

        updateProgramariTable();


        //////// INCEPUT Completare Analize Frame
        completareAnalizeFrame = new JFrame("Completare analize");
        completareAnalizeFrame.setSize(new Dimension(400, 100));
        completareAnalizeFrame.setResizable(false);
        completareAnalizePanel = new JPanel(new MigLayout());
        detaliiTF = new JTextField();
        detaliiTF.setPreferredSize(new Dimension(350, 70));
        submitButton = new JButton("Submit");

        updateCompletareAnalize();
        completareAnalizeFrame.pack();
        //////// Final Completare Analize Frame

    }
    public void updateAsistentM3Panel(){
        asistentM3Panel.add(buttonCompletareAnalize);
        asistentM3Panel.add(programariSP);
    }
    public void updateCompletareAnalize(){
        completareAnalizePanel.add(detaliiTF, "span");
        completareAnalizePanel.add(submitButton);
        completareAnalizeFrame.add(completareAnalizePanel);
    }

    public void updateProgramariTable() {
        String[] programari = new String[]{"id Pacient", "Nume", "Prenume", "Data", "Ora", "Nume Medic", "Prenume Medic","Finalizat"};

        programariTableModel = new DefaultTableModel(programariRowData, programari);
        programariTable = new JTable(programariTableModel);
        programariTable.setAutoCreateRowSorter(true);
        programariTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programariTable.setDefaultEditor(Object.class, null);
        programariTable.getTableHeader().setReorderingAllowed(false);
        programariTable.setColumnSelectionAllowed(false);

        programariSP = new JScrollPane(programariTable);

        programariSP.setAutoscrolls(true);
        programariSP.setPreferredSize(new Dimension(920, 370));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }


    public JFrame getCompletareAnalizeFrame() {
        return completareAnalizeFrame;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTable getProgramariTable() {
        return programariTable;
    }

    public void setProgramariRowData(Object[][] programariRowData) {
        this.programariRowData = programariRowData;
    }

    public Object[][] getProgramariRowData() {
        return programariRowData;
    }

    public JTextField getDetaliiTF() {
        return detaliiTF;
    }

    public JButton getButtonCompletareAnalize() {
        return buttonCompletareAnalize;
    }
}
