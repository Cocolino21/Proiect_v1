package View;

import Controller.BasicController;
import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InspectorView extends BasicView {

    private JPanel inspectorM1Panel;
    private JTable angajatiTable;
    private DefaultTableModel angajatiTableModel;
    private JScrollPane angajatiSP;
    private JComboBox<String> selectAngajatCB = new JComboBox<>();
    Object[][] angajatiRowData = null;

    private JButton veziCerereConcediuButton;
    private JButton veziOrarButton;

    /////////////// ACCEPTA SAU NU CERERI CONCEDIU
    private JFrame cerereConcediuFrame;
    private JList<String> concediiList;
    private JButton acceptConcediuButton;
    private JButton refuzConcediuButton;
    //////////////// FINAL ACCEPTA SAU NU CERERI CONCEDIU

    //////// VEZI ORAR
    private JFrame orarFrame = new JFrame();
    private JPanel orarPanel = new JPanel();
    private JTextField[] zileSaptamaniiTF;
    private JLabel[] zileSaptamaniiLabel;
    /////// FINAL VEZI ORAR

    public InspectorView(CurrentAngajat currentAngajat) {
        super("Spital_V1_Inspector", currentAngajat);
        inspectorM1Panel = new JPanel(new FlowLayout());

        veziOrarButton = new JButton("Orar");
        veziCerereConcediuButton = new JButton("Concediu");
        updateAngajatiPanel();
        inspectorM1Panel.add(veziOrarButton);
        inspectorM1Panel.add(veziCerereConcediuButton, "span");
        inspectorM1Panel.add(angajatiSP);
        reAddToBV_RP_m1Panel();
        createOrarFrame();
    }

    public void createOrarFrame(){
        zileSaptamaniiLabel = new JLabel[7];
        zileSaptamaniiTF = new JTextField[7];
        for(int i=0; i<7; i++)
        {
            zileSaptamaniiTF[i] = new JTextField();
            zileSaptamaniiTF[i].setPreferredSize(new Dimension(350, 70));
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

        for(int i=0 ; i<7; i++){
            orarPanel.add(zileSaptamaniiLabel[i]);
            orarPanel.add(zileSaptamaniiTF[i], "span");
        }
        orarFrame.setContentPane(orarPanel);
        orarFrame.pack();
        orarFrame.setLocationRelativeTo(null);
        orarFrame.setResizable(false);
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

    public void reAddToInspectorM1Panel()
    {
        inspectorM1Panel.removeAll();
        updateAngajatiPanel();
        inspectorM1Panel.add(veziOrarButton);
        inspectorM1Panel.add(veziCerereConcediuButton, "span");
        inspectorM1Panel.add(angajatiSP);

    }

    public void replaceTabelaAngajati(Object[][] tabelAngajati) {

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

    public void setAngajatiRowData(Object[][] angajatiRowData) { this.angajatiRowData = angajatiRowData; }

    public Object[][] getAngajatiRowData() {
        return angajatiRowData;
    }

    public JButton getVeziOrarButton() {
        return veziOrarButton;
    }

    public JFrame getOrarFrame() {
        return orarFrame;
    }

    public JButton getVeziCerereConcediuButton() { return veziCerereConcediuButton; }

    public JButton getAcceptConcediuButton() { return acceptConcediuButton; }

    public JTable getAngajatiTable() { return angajatiTable; }

}
