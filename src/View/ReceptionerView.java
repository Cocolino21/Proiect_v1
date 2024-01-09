package View;

import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReceptionerView extends BasicView{
    private JPanel receptionerM3Panel;
    private JFrame adaugaPacientFrame;
    private JFrame adaugaProgramareFrame;
    private JButton buttonAdaugaPacient= new JButton("Adauga pacient");
    private JButton buttonAdaugaProgramare= new JButton("Adauga programare");
    private DefaultTableModel programariTableModel;
    private JScrollPane programariSP;
    private JTable programariTable;
    Object[][] programariRowData = null;
    @Override
    public void reAddToBV_RP_m3Panel()
    {
        receptionerM3Panel.setPreferredSize(new Dimension(700, 500));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(receptionerM3Panel, "gapx 10");

        updateProgramariTable();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public ReceptionerView(CurrentAngajat currentAngajat) {
        super("Proiect_V1_Policlinici_Receptioner", currentAngajat);
        receptionerM3Panel = new JPanel(new MigLayout("insets 0"));

        updateProgramariTable();

        receptionerM3Panel.add(buttonAdaugaPacient, "span");
        receptionerM3Panel.add(buttonAdaugaProgramare, "span");
        receptionerM3Panel.add(programariSP);


        this.adaugaPacientFrame = new JFrame("Adauga pacient");

        this.adaugaProgramareFrame = new JFrame("Adauga programare");
        adaugaProgramareFrame.add(new JLabel("adaugi programari aici"));
    }

    public void updateProgramariTable() {
        String[] programari = new String[]{"id Pacient", "Nume", "Prenume", "Data", "Ora", "Serviciu"};

        programariTableModel = new DefaultTableModel(programariRowData, programari);
        programariTable = new JTable(programariTableModel);

        programariTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        programariTable.setDefaultEditor(Object.class, null);
        programariTable.getTableHeader().setReorderingAllowed(false);
        programariTable.setColumnSelectionAllowed(false);

        programariSP = new JScrollPane(programariTable);
        programariSP.setAutoscrolls(true);
        programariSP.setPreferredSize(new Dimension(920, 370));

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
}
