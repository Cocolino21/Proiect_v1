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

public class MedicView extends BasicView {
        private JPanel medicM3Panel;
    private DefaultTableModel programariMedicTableModel;
    private JScrollPane programariMedicSP;
    private JTable programariMedicTable;
    Object[][] programariMedicRowData = null;
    private JButton raportButton;
    private JButton istoricButton;

        //// PENTRU RAPORT FRAME
        private JFrame raportFrame;
        private JLabel recomandatDeMedicLabel;
        private JTextField recomandatDeMedicTF;
        private JLabel medicCeAEfectuatLabel;
        private JComboBox<String> medicCeAEfectuatCB;
        private JLabel asistentMedicalLabel;
        private JTextField asistentMedicalTF;
        private JLabel dataEfectuariiLabel;
        private JXDatePicker dataEfectuariiDP;
        private JLabel istoricRelevantLabel;
        private JTextField istoricRelevantTF;
        private JButton investigatiiButton;
        private JLabel diagnosticLabel;
        private JTextField diagonsticTF;
        private JLabel recomandariLabel;
        private JTextField recomandariTF;
        private JButton submitButton;

        //// AM INCHIS PENTRU RAPORT FRAME
        private JFrame istoricPacientFrame;
        @Override
        public void reAddToBV_RP_m3Panel()
        {
            medicM3Panel.setPreferredSize(new Dimension(720,520));
            BV_rightPanel.removeAll();
            BV_rightPanel.add(medicM3Panel,"gapx 10");
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        }
        public  MedicView(CurrentAngajat currentAngajat){
            super("Proiect_V1_Policlinici_Medic",currentAngajat);
            medicM3Panel = new JPanel(new FlowLayout());

            updateProgramariMedicTable();

            raportFrame = new JFrame("Raport Medical");
            raportFrame.setLayout(new MigLayout());

            istoricPacientFrame = new JFrame("Istoric");

            raportButton = new JButton("Raport");
            istoricButton = new JButton("Istoric");

            medicM3Panel.add(raportButton);
            medicM3Panel.add(istoricButton, "span");
            medicM3Panel.add(programariMedicSP);

            recomandatDeMedicLabel = new JLabel("Recomandat de");
            recomandatDeMedicTF = new JTextField("");
            medicCeAEfectuatLabel = new JLabel("Medic ce a efectuat");

        }

    public void updateProgramariMedicTable() {
        String[] programariMedic = new String[]{"Nume", "Prenume", "Data", "Ora", "Serviciu"};

        programariMedicTableModel = new DefaultTableModel(programariMedicRowData, programariMedic);
        programariMedicTable = new JTable(programariMedicTableModel);

        programariMedicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programariMedicTable.setDefaultEditor(Object.class, null);
        programariMedicTable.getTableHeader().setReorderingAllowed(false);
        programariMedicTable.setColumnSelectionAllowed(false);

        programariMedicSP = new JScrollPane(programariMedicTable);
        programariMedicSP.setAutoscrolls(true);
        programariMedicSP.setPreferredSize(new Dimension(720, 520));
    }

    public JFrame getIstoricPacientFrame() {
        return istoricPacientFrame;
    }

    public JFrame getRaportFrame() {
        return raportFrame;
    }

    public JScrollPane getProgramariMedicSP() {
        return programariMedicSP;
    }

    public Object[][] getProgramariMedicRowData() {
        return programariMedicRowData;
    }

    public JTable getProgramariMedicTable() {
        return programariMedicTable;
    }

    public JButton getRaportButton() {
        return raportButton;
    }

    public JButton getIstoricButton() {
        return istoricButton;
    }
}
