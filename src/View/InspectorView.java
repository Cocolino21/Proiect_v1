package View;

import Controller.BasicController;
import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class InspectorView extends BasicView {

    private JPanel inspectorM1Panel;
    private JTable angajatiTable;
    private JTable cereriTable;
    private DefaultTableModel angajatiTableModel = new DefaultTableModel();

    private DefaultTableModel cereriTableModel;
    private JScrollPane angajatiSP;

    private JScrollPane cereriSP;
    private JComboBox<String> selectAngajatCB = new JComboBox<>();
    Object[][] angajatiRowData = null;

    Object[][] cereriRowData = null;

    private JButton veziCerereConcediuButton;

    private JButton veziConcediiButton;
    private JButton veziOrarButton;

    /////////////// ACCEPTA SAU NU CERERI CONCEDIU
    private JFrame cerereConcediuFrame;
    private JList<String> concediiList;
    private JButton acceptConcediuButton = new JButton("Accept Concediu");
    private JButton refuzConcediuButton = new JButton("Refuz Concediu");

    private JButton personalizeazaPretServiciiButton = new JButton("Personalizeaza Servicii");
    //////////////// FINAL ACCEPTA SAU NU CERERI CONCEDIU

    //////// VEZI ORAR
    private JFrame orarFrame = new JFrame();
    private JPanel orarPanel = new JPanel();
    private JPanel cererePanel= new JPanel();
    private JTextField[] zileSaptamaniiTF;
    private JLabel[] zileSaptamaniiLabel;

    private JFrame personalizeazaServiciiJF = new JFrame();
    /////// FINAL VEZI ORAR

    public InspectorView(CurrentAngajat currentAngajat) {
        super("Spital_V1_Inspector", currentAngajat);
        inspectorM1Panel = new JPanel(new FlowLayout());

        veziOrarButton = new JButton("Orar");
        veziCerereConcediuButton = new JButton("Concediu");
        veziConcediiButton = new JButton("Concedii");
        createCerereFrame();
        updateAngajatiPanel();
        updateCereriPanel();
        inspectorM1Panel.add(veziOrarButton);
        inspectorM1Panel.add(veziCerereConcediuButton,"span");
        inspectorM1Panel.add(veziConcediiButton,"span");
        inspectorM1Panel.add(angajatiSP);

        reAddToBV_RP_m1Panel();
        createOrarFrame();
        buidServiciiJFrame();
    }

    public void createOrarFrame(){

        orarFrame = new JFrame("Orar Angajat");
        zileSaptamaniiLabel = new JLabel[7];
        zileSaptamaniiTF = new JTextField[7];
        for(int i=0; i<7; i++)
        {
            zileSaptamaniiTF[i] = new JTextField();
            zileSaptamaniiTF[i].setPreferredSize(new Dimension(300, 50));
            zileSaptamaniiTF[i].setEnabled(false);
            zileSaptamaniiTF[i].setEditable(false);

            zileSaptamaniiLabel[i] = new JLabel();
        }
        zileSaptamaniiLabel[0].setText("Luni");
        zileSaptamaniiLabel[1].setText("Marti");
        zileSaptamaniiLabel[2].setText("Miercuri");
        zileSaptamaniiLabel[3].setText("Joi");
        zileSaptamaniiLabel[4].setText("Vineri");
        zileSaptamaniiLabel[5].setText("Sambata");
        zileSaptamaniiLabel[6].setText("Duminica");
        orarFrame = new JFrame();
        orarPanel = new JPanel(new MigLayout());
        for(int i=0 ; i<7; i++){
            orarPanel.add(zileSaptamaniiLabel[i]);
            orarPanel.add(zileSaptamaniiTF[i], "span");
        }
        orarFrame.setContentPane(orarPanel);
        orarFrame.setPreferredSize(new Dimension(200, 500));
        orarFrame.pack();
        orarFrame.setLocationRelativeTo(null);
        orarFrame.setResizable(false);
    }

    public void createCerereFrame(){

        cerereConcediuFrame = new JFrame("Concedii Angajat");
        cererePanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptConcediuButton);
        buttonPanel.add(refuzConcediuButton);

        String[] cereri = new String[]{"IdAngajat","Data inceput","Data Sfarsit","Movit"};
        inspectorM1Panel.add(veziCerereConcediuButton, "span");
        cereriTableModel = new DefaultTableModel(cereriRowData,cereri);
        cereriTable = new JTable(cereriTableModel);
        cereriTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cereriTable.setDefaultEditor(Object.class, null);
        cereriTable.getTableHeader().setReorderingAllowed(false);
        cereriTable.setColumnSelectionAllowed(false);
        cereriSP = new JScrollPane(cereriTable);
        cereriSP.setPreferredSize(new Dimension(920,500));

        cererePanel.add(new JScrollPane(cereriTable), BorderLayout.CENTER);
        cererePanel.add(buttonPanel, BorderLayout.SOUTH);

        cerereConcediuFrame.setContentPane(cererePanel);
        cerereConcediuFrame.setPreferredSize(new Dimension(700, 500));
        cerereConcediuFrame.pack();
        cerereConcediuFrame.setLocationRelativeTo(null);
        cerereConcediuFrame.setResizable(false);


        cererePanel.add(cereriSP);
    }

    public void updateCereriPanel() {
        String[] angajati = new String[]{"IdAngajat","Data inceput","Data Sfarsit","Motiv"};

        cereriTableModel = new DefaultTableModel(cereriRowData, angajati);
        cereriTable = new JTable(cereriTableModel);
        cereriTable.setAutoCreateRowSorter(true);
        cereriTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cereriTable.setDefaultEditor(Object.class, null);
        cereriTable.getTableHeader().setReorderingAllowed(false);
        cereriTable.setColumnSelectionAllowed(false);

        cereriSP = new JScrollPane(cereriTable);

        cereriSP.setAutoscrolls(true);
        cereriSP.setPreferredSize(new Dimension(600, 500));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }



    @Override
    public void reAddToBV_RP_m1Panel() {
        inspectorM1Panel.setPreferredSize(new Dimension(720,520));
        BV_rightPanel.setLayout(new MigLayout("insets 10"));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(inspectorM1Panel);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private JTextField cautaAngajatTF = new JTextField();
    private JButton searchAngajatButton = new JButton();
    private JButton refreshAngajatButton = new JButton();
    private ImageIcon searchAngajatButtonIcon;
    private  ImageIcon refreshAngajatButtonIcon;


    public void reAddToInspectorM1Panel()
    {
        inspectorM1Panel.removeAll();
        updateAngajatiPanel();
        cautaAngajatTF.setPreferredSize(new Dimension(100,20));
        PromptSupport.setPrompt("Nume angajat", cautaAngajatTF);
        searchAngajatButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/search_button_icon.png")));
        refreshAngajatButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/clear_search_button_icon.png")));
        searchAngajatButton.setIcon(searchAngajatButtonIcon);
        refreshAngajatButton.setIcon(refreshAngajatButtonIcon);
        inspectorM1Panel.add(cautaAngajatTF);
        inspectorM1Panel.add(searchAngajatButton);
        inspectorM1Panel.add(refreshAngajatButton);
        inspectorM1Panel.add(personalizeazaPretServiciiButton);
        inspectorM1Panel.add(veziOrarButton);
        inspectorM1Panel.add(veziCerereConcediuButton, "span");
        inspectorM1Panel.add(angajatiSP,"span");
        inspectorM1Panel.add(BV_cerereConcediuLabel);
        inspectorM1Panel.add(BV_creeazaCerereConcediuButton);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();

    }

    public void reAddToCereriPanel()
    {
        cererePanel.removeAll();
        updateCereriPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptConcediuButton);
        buttonPanel.add(refuzConcediuButton);


        cererePanel.add(cereriSP, BorderLayout.CENTER);
        cererePanel.add(buttonPanel, BorderLayout.SOUTH);

        cerereConcediuFrame.getContentPane().revalidate();
        cerereConcediuFrame.getContentPane().repaint();

    }

    private JPanel personalizeazaServiciiJP;
    private JComboBox<String> personalizeazaServiciiForMedicCB = new JComboBox<>();;
    private JTextField personalizeazaServiciiPretTF;
    private JTextField personalizeazaServiciiDurataTF;

    private JButton personalizeazaServiciiSubmitButton =  new JButton("Submit");;


    public void replaceServiciiForAngajatCB(ArrayList<String> strings)
    {
        personalizeazaServiciiForMedicCB.removeAllItems();
        for(String s: strings)
        {
            personalizeazaServiciiForMedicCB.addItem(s);
        }
    }

    public JLabel[] getZileSaptamaniiLabel() {
        return zileSaptamaniiLabel;
    }

    public void buidServiciiJFrame()
    {
        personalizeazaServiciiJP = new JPanel(new MigLayout("insets 10, center"));
        personalizeazaServiciiDurataTF = new JTextField();
        personalizeazaServiciiPretTF = new JTextField();


        personalizeazaServiciiJP.add(new JLabel("Selecteaza serviciul"));
        personalizeazaServiciiJP.add(personalizeazaServiciiForMedicCB,"span");
        personalizeazaServiciiJP.add(new JLabel("Pret"));
        personalizeazaServiciiJP.add(personalizeazaServiciiPretTF,"span");
        personalizeazaServiciiJP.add(new JLabel("Durata minima"));
        personalizeazaServiciiJP.add(personalizeazaServiciiDurataTF,"span");
        personalizeazaServiciiJP.add(personalizeazaServiciiSubmitButton);

        personalizeazaServiciiJF.setSize(250,175);
        personalizeazaServiciiJF.setLocationRelativeTo(null);
        personalizeazaServiciiJF.setContentPane(personalizeazaServiciiJP);
        personalizeazaServiciiJF.setVisible(false);
    }


    public void updateAngajatiPanel() {
        String[] angajati = new String[]{"id Angajat", "Nume", "Prenume", "Functie"};

        angajatiTableModel = new DefaultTableModel(angajatiRowData, angajati);
        angajatiTable = new JTable(angajatiTableModel);
        angajatiTable.setAutoCreateRowSorter(true);
        angajatiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        angajatiTable.setDefaultEditor(Object.class, null);
        angajatiTable.getTableHeader().setReorderingAllowed(false);
        angajatiTable.setColumnSelectionAllowed(false);

        angajatiSP = new JScrollPane(angajatiTable);

        angajatiSP.setAutoscrolls(true);
        angajatiSP.setPreferredSize(new Dimension(700, 370));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public JComboBox<String> getPersonalizeazaServiciiForMedicCB() {
        return personalizeazaServiciiForMedicCB;
    }

    public JFrame getPersonalizeazaServiciiJF() {
        return personalizeazaServiciiJF;
    }

    public JTextField getPersonalizeazaServiciiPretTF() {
        return personalizeazaServiciiPretTF;
    }

    public JTextField getPersonalizeazaServiciiDurataTF() {
        return personalizeazaServiciiDurataTF;
    }

    public JButton getPersonalizeazaPretServiciiButton() {
        return personalizeazaPretServiciiButton;
    }

    public JButton getPersonalizeazaServiciiSubmitButton() {
        return personalizeazaServiciiSubmitButton;
    }


    public JTextField getCautaAngajatTF() {
        return cautaAngajatTF;
    }

    public DefaultTableModel getAngajatiTableModel() {
        return angajatiTableModel;
    }

    public JButton getSearchAngajatButton() {
        return searchAngajatButton;
    }

    public JButton getRefreshAngajatButton() {
        return refreshAngajatButton;
    }

    public void setCereriRowData(Object[][] cereriRowData) {
        this.cereriRowData = cereriRowData;
    }

    public void setAngajatiRowData(Object[][] angajatiRowData) { this.angajatiRowData = angajatiRowData; }

    public Object[][] getAngajatiRowData() {
        return angajatiRowData;
    }

    public JButton getVeziOrarButton() {
        return veziOrarButton;
    }

    public JFrame getCerereConcediuFrame() {
        return cerereConcediuFrame;
    }

    public JButton getRefuzConcediuButton() {
        return refuzConcediuButton;
    }

    public JFrame getOrarFrame() {
        return orarFrame;
    }

    public Object[][] getCereriRowData() {
        return cereriRowData;
    }

    public JButton getVeziCerereConcediuButton() { return veziCerereConcediuButton; }

    public JButton getAcceptConcediuButton() { return acceptConcediuButton; }

    public JTable getAngajatiTable() { return angajatiTable; }

    public JTable getCereriTable() {
        return cereriTable;
    }

    public void showErrorMessageIn(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public JTextField[] getZileSaptamaniiTF() { return zileSaptamaniiTF; }
}
