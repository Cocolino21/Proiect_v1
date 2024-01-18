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
import java.util.*;


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

    ////////////// M3 Frame
        private JPanel medicM3Panel;
        private DefaultTableModel programariMedicTableModel;
        private JScrollPane programariMedicSP;
        private JTable programariMedicTable;
        Object[][] programariMedicRowData = null;
        private JButton raportButton;
        private JButton istoricButton;

    //////////////  SFARSIT M3 Frame


    //// RAPORT FRAME
        private JFrame raportFrame;
        private JPanel raportPanel;

        private JLabel medicCeAEfectuatLabel;
        private JLabel asistentMedicalLabel;
        private JComboBox<String> asistentMedicalCB;
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

        //// SFARSIT RAPORT FRAME


        //// ISTORIC frame
        private JFrame istoricFrame;
        private JButton veziRaportButton = new JButton("Vezi detalii raport");
        private JPanel istoricPanel;
        private DefaultTableModel istoricTableModel;
        private JScrollPane istoricSP;
        private JTable istoricTable;
        Object[][] istoricRowData = null;

        //// SFARIST ISTORIC frame

        ////
        private JFrame detaliiRaportFrame;
        private JPanel detaliiRaportPanel;
        private ArrayList<JLabel> detaliiRaportServiciiEfectuateArray = new ArrayList<>();



        ///// INVESTIGATII FRAME

        private JFrame investigatiiFrame;
        private JPanel investigatiiPanel;
        private JButton adaugaServiciuInInvestigatiiButton;
        DefaultListModel<String> serviciiListModel = new DefaultListModel<>();
        private JList<String> seriviciiAdaugateList;


        private JScrollPane serviciiAdaugateSP;
        private JButton adaugaServiciuButton;

        @Override
        public void reAddToBV_RP_m3Panel()
        {
            medicM3Panel.setPreferredSize(new Dimension(720,520));
            BV_rightPanel.removeAll();
            BV_rightPanel.add(medicM3Panel);
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        }

        public void reAddToMedicM3Panel()
        {
            medicM3Panel.removeAll();
            updateProgramariMedicTable();
            medicM3Panel.add(raportButton);
            medicM3Panel.add(istoricButton, "span");
            medicM3Panel.add(programariMedicSP);

        }



        public  MedicView(CurrentAngajat currentAngajat){
            super("Proiect_V1_Policlinici_Medic",currentAngajat);
            medicM3Panel = new JPanel(new FlowLayout());

            updateProgramariMedicTable();

            raportButton = new JButton("Raport");
            istoricButton = new JButton("Istoric");

            buildRaportFrame();

            updateIstoricTable();
            buildIstoricFrame();

            buildInvestigatiiFrame();

        }

    public void buildInvestigatiiFrame()
    {
        investigatiiFrame = new JFrame("Investigatii");
        investigatiiFrame.setPreferredSize(new Dimension(250,300));
        investigatiiPanel = new JPanel(new MigLayout("insets 10"));
        adaugaServiciuInInvestigatiiButton = new JButton("Adauga serviciile selectate");
        seriviciiAdaugateList = new JList<>(serviciiListModel);

        adaugaServiciuButton = new JButton("Adauga serviciile selectat");


        serviciiAdaugateSP = new JScrollPane(seriviciiAdaugateList);
        investigatiiPanel.add(serviciiAdaugateSP, "span");
        investigatiiPanel.add(adaugaServiciuButton);


        investigatiiFrame.setContentPane(investigatiiPanel);
        investigatiiFrame.pack();
        investigatiiFrame.setLocationRelativeTo(null);
        investigatiiFrame.setResizable(false);
    }

    public void setDetaliiRaportServiciiEfectuateArray(ArrayList<String> x)
    {
        detaliiRaportServiciiEfectuateArray = new ArrayList<JLabel>();
        for(String s : x)
        {
            detaliiRaportServiciiEfectuateArray.add(new JLabel(s));
        }
    }

    public void buildServiciiForRaportJFrame()
    {

        detaliiRaportFrame = new JFrame("Detalii raport");
        detaliiRaportPanel = new JPanel(new MigLayout("insets 10"));
        detaliiRaportPanel.add(new JLabel("Servicii efectuate : "),"span");
        for(JLabel j : detaliiRaportServiciiEfectuateArray)
        {
            detaliiRaportPanel.add(j,"span");
        }
        detaliiRaportFrame.setLocationRelativeTo(null);
        detaliiRaportFrame.setContentPane(detaliiRaportPanel);
        detaliiRaportFrame.setResizable(false);
        detaliiRaportFrame.pack();
        detaliiRaportFrame.revalidate();
        detaliiRaportFrame.repaint();

    }



    public void buildRaportFrame()
    {
        raportFrame = new JFrame("Raport medical");
        raportPanel = new JPanel(new MigLayout("insets 20"));


        asistentMedicalLabel = new JLabel("Asistent Medical");
        asistentMedicalCB = new JComboBox<>();
        dataEfectuariiLabel = new JLabel("Data efectuarii");
        dataEfectuariiDP = new JXDatePicker();
        istoricRelevantLabel = new JLabel("Istoric relevant");
        istoricRelevantTF =  new JTextField();
        investigatiiButton = new JButton("Investigatii");
        diagnosticLabel = new JLabel("Diagnostic");
        diagonsticTF = new JTextField();
        recomandariLabel = new JLabel("Recomandari");
        recomandariTF = new JTextField();
        submitButton = new JButton("Parafare");


        raportPanel.add(asistentMedicalLabel);
        raportPanel.add(asistentMedicalCB, "span");
        raportPanel.add(dataEfectuariiLabel);
        raportPanel.add(dataEfectuariiDP, "span");
        raportPanel.add(istoricRelevantLabel);
        raportPanel.add(istoricRelevantTF, "span");
        raportPanel.add(investigatiiButton, "span");
        raportPanel.add(diagnosticLabel);
        raportPanel.add(diagonsticTF, "span");
        raportPanel.add(recomandariLabel);
        raportPanel.add(recomandariTF, "span");
        raportPanel.add(submitButton);


        raportFrame.revalidate();
        raportFrame.repaint();
        raportFrame.setContentPane(raportPanel);
        raportFrame.pack();
        raportFrame.setLocationRelativeTo(null);
        raportFrame.setResizable(false);
    }
    public void buildIstoricFrame()
    {

        istoricFrame = new JFrame("Istoric Pacient");
        istoricPanel = new JPanel(new MigLayout("insets 0"));

        istoricPanel.add(veziRaportButton, "span");
        istoricPanel.add(istoricSP);

        istoricFrame.setContentPane(istoricPanel);
        istoricFrame.pack();
        istoricFrame.setLocationRelativeTo(null);
        istoricFrame.setResizable(false);
    }
    public void updateProgramariMedicTable() {
        String[] programariMedic = new String[]{"id Pacient", "Nume", "Prenume", "Data", "Ora","Finalizat","id Programare"};

        programariMedicTableModel = new DefaultTableModel(programariMedicRowData, programariMedic);
        programariMedicTable = new JTable(programariMedicTableModel);

        programariMedicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programariMedicTable.setDefaultEditor(Object.class, null);
        programariMedicTable.setAutoCreateRowSorter(true);
        programariMedicTable.getTableHeader().setReorderingAllowed(false);
        programariMedicTable.setColumnSelectionAllowed(false);

        programariMedicSP = new JScrollPane(programariMedicTable);
        programariMedicSP.setAutoscrolls(true);
        programariMedicSP.setPreferredSize(new Dimension(720, 520));
    }

    public void updateIstoricTable() {

        String[] istoric = new String[]{"Raport ID","Pacient ID", "Asistent ID", "Recomandari","Istoric Relevant","Diagnostic","Data Completare"};

        istoricTableModel = new DefaultTableModel(istoricRowData, istoric);
        istoricTable = new JTable(istoricTableModel);

        istoricTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        istoricTable.setDefaultEditor(Object.class, null);
        istoricTable.getTableHeader().setReorderingAllowed(false);
        istoricTable.setColumnSelectionAllowed(false);

        istoricSP = new JScrollPane(istoricTable);

        istoricSP.setAutoscrolls(true);
        istoricSP.setPreferredSize(new Dimension(700, 400));

        if(istoricFrame!=null) {

            istoricFrame.revalidate();
            istoricFrame.repaint();
            this.revalidate();
            this.repaint();
        }
    }

    public void replaceAsistentMedicalCB(ArrayList<String> strings)
    {
        asistentMedicalCB.removeAllItems();
        for(String s: strings)
        {
            asistentMedicalCB.addItem(s);

        }
    }

    public void replaceServiciiAdaugateList(ArrayList<String> strings)
    {
        serviciiListModel.removeAllElements();
        for(String s: strings)
        {
            serviciiListModel.addElement(s);
        }
    }


    public JList<String> getSeriviciiAdaugateList() {
        return seriviciiAdaugateList;
    }

    public JXDatePicker getDataEfectuariiDP() {
        return dataEfectuariiDP;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }



    public JTextField getIstoricRelevantTF() {
        return istoricRelevantTF;
    }

    public JTextField getDiagonsticTF() {
        return diagonsticTF;
    }

    public JTextField getRecomandariTF() {
        return recomandariTF;
    }

    public JComboBox<String> getAsistentMedicalCB() {
        return asistentMedicalCB;
    }

    public DefaultListModel<String> getServiciiListModel() {
        return serviciiListModel;
    }

    public JButton getAdaugaServiciuButton() {
        return adaugaServiciuButton;
    }

    public JButton getVeziRaportButton() {
        return veziRaportButton;
    }

    public JFrame getDetaliiRaportFrame() {
        return detaliiRaportFrame;
    }

    public void setProgramariMedicRowData(Object[][] programariMedicRowData) {
        this.programariMedicRowData = programariMedicRowData;
    }

    public void setIstoricRowData(Object[][] istoricRowData) {
        this.istoricRowData = istoricRowData;
    }

    public DefaultTableModel getIstoricTableModel() {
        return istoricTableModel;
    }

    public JTable getIstoricTable() {
        return istoricTable;
    }

    public Object[][] getIstoricRowData() {
        return istoricRowData;
    }

    public JScrollPane getIstoricSP() {
        return istoricSP;
    }

    public JButton getInvestigatiiButton() {
        return investigatiiButton;
    }

    public JFrame getInvestigatiiFrame() {
        return investigatiiFrame;
    }

    public JFrame getIstoricFrame() {
        return istoricFrame;
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
