package View;

import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ReceptionerView extends BasicView {
    private JPanel receptionerM3Panel;
    private JFrame adaugaPacientFrame;


    //////////////////////////////////////////////////////////
    private JFrame adaugaProgramareFrame;
    private JPanel adaugaProgramarePanel;
    private JComboBox<String> idMedicCB;
    private JComboBox<String> idPacientCB;
    private JXDatePicker dataDP;
    private JTextField oraTF;
    private JLabel idReceptioner;
    private JLabel idPacient;
    private JLabel idMedic;
    private JLabel ora;
    private JLabel data;
    private JButton adaugaProgramare;
    private JButton stergeProgramare;


///////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////
    private JPanel adaugaPacientPanel;
    private JButton adaugaPacient;
    private JLabel numeLabel;
    private JLabel prenumeLabel;
    private JLabel nrTelefonLabel;
    private JLabel cnpLabel;
    private JLabel emailLabel;
    private JTextField numeTF;
    private JTextField prenumeTF;
    private JTextField nrTelefonTF;
    private JTextField cnpTF;
    private JTextField emailTF;
///////////////////////////////////////////////////////////////
    private JButton buttonAdaugaPacient= new JButton("Adauga pacient");
    private JButton buttonAdaugaProgramare= new JButton("Adauga programare");
    private JButton buttonStergeProgramare=new JButton("Sterge programare");
    private JComboBox<String> selectNumeCB = new JComboBox<>();
    private DefaultTableModel programariTableModel;
    private JScrollPane programariSP;
    private JTable programariTable;
    private JButton buttonSearchProgramare;
    private Icon searchProgramareButtonIcon;
    Object[][] programariRowData = null;

    @Override
    public void reAddToBV_RP_m3Panel()
    {
        reAddToReceptionerM3Panel();
        receptionerM3Panel.setPreferredSize(new Dimension(700, 500));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(receptionerM3Panel, "gapx 10");

        updateProgramariTable();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToReceptionerM3Panel()
    {
        receptionerM3Panel.removeAll();
        receptionerM3Panel.add(buttonAdaugaPacient, "span");
        receptionerM3Panel.add(buttonAdaugaProgramare,"span");
        receptionerM3Panel.add(programariSP, "span");
        receptionerM3Panel.add(buttonStergeProgramare,"span");

    }

    public ReceptionerView(CurrentAngajat currentAngajat) {
        super("Proiect_V1_Policlinici_Receptioner", currentAngajat);
        receptionerM3Panel = new JPanel(new MigLayout("insets 0"));


        updateProgramariTable();

        adaugaPacientFrame = new JFrame("Adauga pacient");
        adaugaPacientPanel = new JPanel(new MigLayout());
        adaugaProgramareFrame = new JFrame ("Adauga programare");
        adaugaProgramarePanel = new JPanel(new MigLayout());
        buttonSearchProgramare = new JButton();
        searchProgramareButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/search_button_icon.png")));
        buttonSearchProgramare.setIcon(searchProgramareButtonIcon);
        /////  INCEPUT FEREASTRA CU ADAUGARE PROGRAMARE
        idReceptioner = new JLabel("id Receptioner");
        idPacient = new JLabel("id Pacient");
        idMedic = new JLabel("id Medic");
        ora = new JLabel("Ora");
        data = new JLabel("Data");
        adaugaProgramare = new JButton("Adauga");
        stergeProgramare = new JButton("Sterge");

        idPacientCB = new JComboBox();
        idMedicCB = new JComboBox();
        oraTF = new JTextField();
        PromptSupport.setPrompt("00:00:00", oraTF);
        dataDP = new JXDatePicker();


        adaugaProgramarePanel.add(idMedic);
        adaugaProgramarePanel.add(idMedicCB, "span");
        adaugaProgramarePanel.add(idPacient);
        adaugaProgramarePanel.add(idPacientCB, "span");
        adaugaProgramarePanel.add(data);
        adaugaProgramarePanel.add(dataDP, "span");
        adaugaProgramarePanel.add(ora);
        adaugaProgramarePanel.add(oraTF, "span");
        adaugaProgramarePanel.add(adaugaProgramare);

        adaugaProgramareFrame.setContentPane(adaugaProgramarePanel);
        adaugaProgramareFrame.pack();
        adaugaProgramareFrame.setLocationRelativeTo(null);
        adaugaProgramareFrame.setResizable(false);
        //// SFARSIT FEREASTRA ADAUGARE PROGRAMARE

        //// INCEPUT FEREASTRA ADAUGARE PACIENT
        adaugaPacientPanel = new JPanel(new MigLayout());

        numeLabel = new JLabel("Nume");
        prenumeLabel = new JLabel("Prenume");
        cnpLabel = new JLabel("CNP");
        nrTelefonLabel = new JLabel("Nr Telefon");
        emailLabel = new JLabel("Email");

        numeTF = new JTextField();
        prenumeTF = new JTextField();
        cnpTF = new JTextField();
        nrTelefonTF = new JTextField();
        emailTF = new JTextField();

        adaugaPacient = new JButton("Adauga");

        adaugaPacientPanel.add(numeLabel );
        adaugaPacientPanel.add(numeTF,"span");
        adaugaPacientPanel.add(prenumeLabel);
        adaugaPacientPanel.add(prenumeTF, "span");
        adaugaPacientPanel.add(cnpLabel);
        adaugaPacientPanel.add(cnpTF, "span");
        adaugaPacientPanel.add(nrTelefonLabel);
        adaugaPacientPanel.add(nrTelefonTF, "span");
        adaugaPacientPanel.add(emailLabel);
        adaugaPacientPanel.add(emailTF, "span");
        adaugaPacientPanel.add(adaugaPacient);

        adaugaPacientFrame.setContentPane(adaugaPacientPanel);
        adaugaPacientFrame.pack();
        adaugaPacientFrame.setLocationRelativeTo(null);
        adaugaPacientFrame.setResizable(false);

        //// FINAL FEREASTRA ADAUGA PACIENT
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

    public DefaultTableModel getProgramariTableModel() {
        return programariTableModel;
    }

    public JXDatePicker getDataDP() {
        return dataDP;
    }

    public JTextField getOraTF() {
        return oraTF;
    }

    public JButton getAdaugaProgramare() {
        return adaugaProgramare;
    }

    public JComboBox<String> getIdMedicCB() {
        return idMedicCB;
    }

    public JComboBox<String> getIdPacientCB() {
        return idPacientCB;
    }

    public JComboBox<String> getSelectNumeCB() {
        return selectNumeCB;
    }



    public void replaceSelectNumeCB(ArrayList<String> strings)
    {
        selectNumeCB.removeAllItems();
        for(String s: strings)
        {
            selectNumeCB.addItem(s);
        }
    }
    public void replaceSelectIdMedicCB(ArrayList<String> strings)
    {
        idMedicCB.removeAllItems();
        for(String s: strings)
        {
            idMedicCB.addItem(s);
        }
    }

    public void replaceSelectIdPacientCB(ArrayList<String> strings)
    {
        idPacientCB.removeAllItems();
        for(String s: strings)
        {
            idPacientCB.addItem(s);
        }
    }



    public JTextField getNumeTF() {
        return numeTF;
    }

    public JTextField getPrenumeTF() {
        return prenumeTF;
    }

    public JTextField getNrTelefonTF() {
        return nrTelefonTF;
    }

    public JTextField getCnpTF() {
        return cnpTF;
    }

    public JTextField getEmailTF() {
        return emailTF;
    }

    public Object[][] getProgramariRowData() { return programariRowData; }
    public void setProgramariRowData(Object[][] programariRowData) {
        this.programariRowData = programariRowData;
    }

    public JFrame getAdaugaProgramareFrame() {
        return adaugaProgramareFrame;
    }

    public JFrame getAdaugaPacientFrame() {
        return adaugaPacientFrame;
    }

    public JButton getButtonAdaugaPacient() { return buttonAdaugaPacient;}

    public JButton getButtonAdaugaProgramare() { return buttonAdaugaProgramare;}

    public JTable getProgramariTable() { return programariTable;}


    public JButton getAdaugaPacient() {
        return adaugaPacient;
    }


}
