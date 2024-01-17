package View;

import Model.CurrentAngajat;
import jdk.jshell.execution.JdiDefaultExecutionControl;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminView extends BasicView{

    private JPanel adminM1Panel;
    private JButton adaugaAngajatButton;
    private JButton stergeAngajatButton;
    private JButton modificaAngajatButton;
    private DefaultTableModel angajatiTableModel;
    private JScrollPane angajatiSP;
    Object[][] angajatiRowData = null;
    private JTable angajatiTable;
///////////////////////////////////////////////////////

    private JFrame adaugaAngajatJFrame;
    private JPanel aajf_mainPanel;
    private JLabel[] aajf_l;
    private JComponent[] aajf_tfOrCb;
    private JComboBox<String> selectDeptCB2;
    private JTextField selectCentruTF;
    private JButton aajf_SubmitButton;
    private JComboBox<String> selectFunctiiCB;
    private JTextField aajf_procentTF;
    private JTextField aajf_codParafaTF;
    private JTextField aajf_titluStiintificTF;
    private JTextField aajf_postDidacticTF;

///////////////////////////////////////////////////////
    public AdminView(CurrentAngajat currentAngajat) {
        super("Admin View", currentAngajat);

        createJThings();
        reAddToBV_RP_m1Panel();
    }
    public void createJThings(){
        adminM1Panel = new JPanel(new FlowLayout());
        modificaAngajatButton = new JButton("Modifica Angajat");
        adaugaAngajatButton = new JButton("Adauga Angajat");
        stergeAngajatButton = new JButton("Sterge Angajat");
        updateAngajatiTable();

        createAdaugaAngajatFrame();
    }


    @Override
    public void reAddToBV_RP_m1Panel() {
        super.reAddToBV_RP_m1Panel();
        adminM1Panel = new JPanel(new FlowLayout());
        adminM1Panel.add(adaugaAngajatButton);
        adminM1Panel.add(modificaAngajatButton);
        adminM1Panel.add(stergeAngajatButton, "span");
        updateAngajatiTable();
        adminM1Panel.add(angajatiSP);


        BV_RP_m1Panel.removeAll();
        BV_RP_m1Panel.add(adminM1Panel);
    }

    public void updateAngajatiTable() {
        String[] angajati = new String[]{"id Angajat", "Nume", "Prenume", "Functie", "Numar Contract", "Nr telefon"};

        angajatiTableModel = new DefaultTableModel(angajatiRowData, angajati);
        angajatiTable = new JTable(angajatiTableModel);

        angajatiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        angajatiTable.setDefaultEditor(Object.class, null);
        angajatiTable.setAutoCreateRowSorter(true);
        angajatiTable.getTableHeader().setReorderingAllowed(false);
        angajatiTable.setColumnSelectionAllowed(false);

        angajatiSP = new JScrollPane(angajatiTable);
        angajatiSP.setAutoscrolls(true);
        angajatiSP.setPreferredSize(new Dimension(700, 520));
    }
    public void createAdaugaAngajatFrame()
    {
        adaugaAngajatJFrame = new JFrame();
        aajf_mainPanel = new JPanel(new MigLayout("gapx 10, gapy 10, insets 20"));
        aajf_mainPanel.setPreferredSize(new Dimension(400, 600));
        aajf_l = new JLabel[13];
        for(int i=0;i<13;i++)
            aajf_l[i] = new JLabel();
        aajf_tfOrCb = new JComponent[13];
        for(int i=0;i<13;i++)
        {
            aajf_tfOrCb[i] = new JTextField();
            aajf_tfOrCb[i].setPreferredSize(new Dimension(250,30));
        }
        aajf_tfOrCb[8] = new JPanel(new MigLayout("gapy 10"));
        selectDeptCB2 = new JComboBox<String>();
        selectCentruTF = new JTextField();
        aajf_tfOrCb[12] = selectCentruTF;

        aajf_SubmitButton = new JButton("Submit");
        aajf_SubmitButton.setFocusPainted(false); // Remove focus border
        aajf_SubmitButton.setBackground(new Color(155, 48, 80));
        aajf_SubmitButton.setForeground(Color.WHITE);
        aajf_SubmitButton.setPreferredSize(new Dimension(100, 30));
        aajf_SubmitButton.setFont(aajf_SubmitButton.getFont().deriveFont(Font.BOLD));
        selectFunctiiCB = new JComboBox<String>();
        aajf_tfOrCb[8].add(selectDeptCB2);
        selectDeptCB2.setPreferredSize(new Dimension(125,30));
        aajf_tfOrCb[8].add(selectFunctiiCB,"gapx 10,span");
        selectFunctiiCB.setPreferredSize(new Dimension(125,30));
        aajf_tfOrCb[8].setPreferredSize(new Dimension(250,30));


        aajf_l[0].setText("Nume");
        aajf_l[1].setText("Prenume");
        aajf_l[2].setText("CNP");
        aajf_l[3].setText("Adresa");
        aajf_l[4].setText("Nr. Telefon");
        aajf_l[5].setText("E-Mail");
        aajf_l[6].setText("IBAN");
        aajf_l[7].setText("Nr. Contract");
        aajf_l[8].setText("Functie");
        aajf_l[9].setText("Salariu_lunar");
        aajf_l[10].setText("username");
        aajf_l[11].setText("parola");
        aajf_l[12].setText("Centru");

        for(int i=0;i<13;i++)
        {
            aajf_mainPanel.add(aajf_l[i]);
            aajf_mainPanel.add(aajf_tfOrCb[i],"span");
        }


//////////
        aajf_procentTF = new JTextField();
        PromptSupport.setPrompt("Procent", aajf_procentTF);

        aajf_codParafaTF = new JTextField();
        PromptSupport.setPrompt("Cod parafa", aajf_codParafaTF);

        aajf_titluStiintificTF = new JTextField();
        PromptSupport.setPrompt("Titlu Stiintific", aajf_titluStiintificTF);

        aajf_postDidacticTF = new JTextField();
        PromptSupport.setPrompt("Post didactic", aajf_postDidacticTF);

        aajf_mainPanel.add(aajf_postDidacticTF);
        aajf_mainPanel.add(aajf_codParafaTF, "gapx 10");
        aajf_mainPanel.add(aajf_titluStiintificTF, "gapx 10");
        aajf_mainPanel.add(aajf_procentTF, "gapx 10, span");
/////////
        aajf_mainPanel.add(aajf_SubmitButton,"gapy 20, center,span");

        adaugaAngajatJFrame.setTitle("Adauga angajat");
        adaugaAngajatJFrame.setContentPane(aajf_mainPanel);
        adaugaAngajatJFrame.setResizable(false);

        adaugaAngajatJFrame.pack();
        adaugaAngajatJFrame.setLocationRelativeTo(null);
        adaugaAngajatJFrame.setVisible(false);
    }
    public void replaceComboBoxItems_DEPT2(ArrayList<String> strings)
    {
        selectDeptCB2.removeAllItems();
        for(String s: strings)
        {
            selectDeptCB2.addItem(s);
        }

    }

    public Object[][] getAngajatiRowData() {
        return angajatiRowData;
    }

    public void replaceComboBoxItems_FCT(ArrayList<String> strings)
    {
        selectFunctiiCB.removeAllItems();
        for(String s: strings)
        {
            selectFunctiiCB.addItem(s);
        }
    }

    public JButton getModificaAngajatButton() {
        return modificaAngajatButton;
    }

    public JTextField getSelectCentruTF() {
        return selectCentruTF;
    }

    public void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public JComboBox<String> getSelectDeptCB2() {
        return selectDeptCB2;
    }

    public JTable getAngajatiTable() {
        return angajatiTable;
    }

    public void setAngajatiRowData(Object[][] angajatiRowData) {
        this.angajatiRowData = angajatiRowData;
    }

    public JComboBox<String> getSelectFunctiiCB() {
        return selectFunctiiCB;
    }

    public JPanel getAdminM1Panel() {
        return adminM1Panel;
    }

    public JTextField getAajf_procentTF() {
        return aajf_procentTF;
    }

    public JTextField getAajf_codParafaTF() {
        return aajf_codParafaTF;
    }

    public JTextField getAajf_titluStiintificTF() {
        return aajf_titluStiintificTF;
    }

    public JTextField getAajf_postDidacticTF() {
        return aajf_postDidacticTF;
    }

    public JFrame getAdaugaAngajatJFrame() {
        return adaugaAngajatJFrame;
    }

    public JButton getAajf_SubmitButton() {
        return aajf_SubmitButton;
    }

    public JButton getAdaugaAngajatButton() {
        return adaugaAngajatButton;
    }

    public JButton getStergeAngajatButton() {
        return stergeAngajatButton;
    }

    public JComponent[] getAajf_tfOrCb() {
        return aajf_tfOrCb;
    }
}

