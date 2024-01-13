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
public class ContabilView extends BasicView{

    private JFrame profitCentruFrame;
    private JPanel profitCentruPanel;
    private DefaultTableModel profitCentruTableModel;
    private JScrollPane profitCentruSP;
    private JTable profitCentruTable;
    Object[][] profitCentruRowData = null;
//////////////////////////////////////
    private JFrame profitMedicFrame;
    private JPanel profitMedicPanel;
    private DefaultTableModel profitMedicTableModel;
    private JScrollPane profitMedicSP;
    private JTable profitMedicTable;
    Object[][] profitMedicRowData = null;
    /////////////////////////////////////
    private JFrame profitSpecialitateFrame;
    private JPanel profitSpecialitatePanel;
    private DefaultTableModel profitSpecialitateTableModel;
    private JScrollPane profitSpecialitateSP;
    private JTable profitSpecialitateTable;
    Object[][] profitSpecialitateRowData = null;
    ///////////////////////////////////////////////
    private JPanel contabilM2Panel;
    private JComboBox<String> medicCB;
    private JButton profitMedicButton;
    private JButton profitCentruButton;
    private JComboBox<String> angajatCB;
    private JButton salarAngajatButton;
    private JTextField salarAngajatTF;
    private JComboBox<String> specialitateMedicalaCB;
    private JButton profitSpecialitateButton;

    private JLabel medicLabel;
    private JLabel angajatLabel;
    private JLabel specialitateLabel;

    public ContabilView(CurrentAngajat currentAngajat) {

        super("Proiect_V1_Policlinici_Contabil", currentAngajat);
        try {
            pic = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("images/super_admin_bg.png")));
        }
        catch (IOException | NullPointerException e) {
            pic = new ImageIcon().getImage();
        }


        contabilM2Panel = new JPanel(new MigLayout()) {
            public void paintComponent(Graphics g) {
                super.paintComponents(g);
                g.drawImage(pic, 0, 0, null);
            }
        };

        profitMedicButton = new JButton("Profit medic");
        profitMedicButton.setPreferredSize(new Dimension(125, 50));
        profitCentruButton = new JButton("Profit centru");
        profitCentruButton.setPreferredSize(new Dimension(125, 50));
        profitSpecialitateButton = new JButton("Profit specialitate");
        profitSpecialitateButton.setPreferredSize(new Dimension(125, 50));
        salarAngajatButton = new JButton("Salar angajat");
        salarAngajatButton.setPreferredSize(new Dimension(125, 50));

        medicCB = new JComboBox<String>();
        medicCB.setPreferredSize(new Dimension(125,50));
        angajatCB = new JComboBox<String>();
        angajatCB.setPreferredSize(new Dimension(125,50));
        specialitateMedicalaCB = new JComboBox<String>();
        specialitateMedicalaCB.setPreferredSize(new Dimension(125,50));
        salarAngajatTF = new JTextField();
        salarAngajatTF.setPreferredSize(new Dimension(125,50));

        angajatLabel = new JLabel("Selectati angajatul");
        angajatLabel.setPreferredSize(new Dimension(100, 50));
        medicLabel = new JLabel("Selectati medicul");
        medicLabel.setPreferredSize(new Dimension(100, 50));
        specialitateLabel = new JLabel("Selectati specialitatea");
        specialitateLabel.setPreferredSize(new Dimension(100, 50));

        reAddToBV_RP_m2Panel();


        /////// INCEPUT FEREASTRA PROFIT MEDIC
        profitMedicFrame = new JFrame("Profit medic");
        profitMedicPanel = new JPanel(new MigLayout());
        updateProfitMedicTable();

        profitMedicPanel.add(profitMedicSP);
        profitMedicFrame.setContentPane(profitMedicPanel);
        profitMedicFrame.pack();
        profitMedicFrame.setLocationRelativeTo(null);
        profitMedicFrame.setResizable(false);
        /////// SFARSIT FEREASTRA PROFIT MEDIC



        ///// INCEPUT FEREASTRA PROFIT SPECIALITATE
        profitSpecialitateFrame = new JFrame("Profit specialitate");
        profitSpecialitatePanel = new JPanel(new MigLayout());
        updateProfitSpecialitateTable();

        profitSpecialitatePanel.add(profitSpecialitateSP);
        profitSpecialitateFrame.setContentPane(profitSpecialitatePanel);
        profitSpecialitateFrame.pack();
        profitSpecialitateFrame.setLocationRelativeTo(null);
        profitSpecialitateFrame.setResizable(false);
        ///// SFARSIT FEREASTRA PROFIT SPECIALITATE



        ///// INCEPUT FEREASTRA PROFIT CENTRU
        profitCentruFrame = new JFrame("Profit specialitate");
        profitCentruPanel = new JPanel(new MigLayout());
        updateProfitCentruTable();

        profitCentruPanel.add(profitCentruSP);
        profitCentruFrame.setContentPane(profitCentruPanel);
        profitCentruFrame.pack();
        profitCentruFrame.setLocationRelativeTo(null);
        profitCentruFrame.setResizable(false);
        ///// SFARSIT FEREASTRA PROFIT CENTRU

    }
    public void updateProfitCentruTable(){
        String[] profitCentru = new String[]{"Luna", "Cheltuieli", "Venituri"};

        profitCentruTableModel = new DefaultTableModel(profitCentruRowData, profitCentru);
        profitCentruTable = new JTable(profitCentruTableModel);
        profitCentruTable.setAutoCreateRowSorter(true);
        profitCentruTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profitCentruTable.setDefaultEditor(Object.class, null);
        profitCentruTable.getTableHeader().setReorderingAllowed(false);
        profitCentruTable.setColumnSelectionAllowed(false);

        profitCentruSP = new JScrollPane(profitCentruTable);

        profitCentruSP.setAutoscrolls(true);
        profitCentruSP.setPreferredSize(new Dimension(400, 300));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    public void updateProfitSpecialitateTable(){
        String[] profitSpecialitate = new String[]{"Luna", "Cheltuieli", "Venituri"};

        profitSpecialitateTableModel = new DefaultTableModel(profitSpecialitateRowData, profitSpecialitate);
        profitSpecialitateTable = new JTable(profitSpecialitateTableModel);
        profitSpecialitateTable.setAutoCreateRowSorter(true);
        profitSpecialitateTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profitSpecialitateTable.setDefaultEditor(Object.class, null);
        profitSpecialitateTable.getTableHeader().setReorderingAllowed(false);
        profitSpecialitateTable.setColumnSelectionAllowed(false);

        profitSpecialitateSP = new JScrollPane(profitSpecialitateTable);

        profitSpecialitateSP.setAutoscrolls(true);
        profitSpecialitateSP.setPreferredSize(new Dimension(400, 300));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    public void updateProfitMedicTable(){
        String[] profitMedic = new String[]{"Luna", "Cheltuieli", "Venituri"};

        profitMedicTableModel = new DefaultTableModel(profitMedicRowData, profitMedic);
        profitMedicTable = new JTable(profitMedicTableModel);
        profitMedicTable.setAutoCreateRowSorter(true);
        profitMedicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profitMedicTable.setDefaultEditor(Object.class, null);
        profitMedicTable.getTableHeader().setReorderingAllowed(false);
        profitMedicTable.setColumnSelectionAllowed(false);

        profitMedicSP = new JScrollPane(profitMedicTable);

        profitMedicSP.setAutoscrolls(true);
        profitMedicSP.setPreferredSize(new Dimension(400, 300));
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    @Override
    public void reAddToBV_RP_m2Panel()
    {
        reAddToContabilM2Panel();
        contabilM2Panel.setPreferredSize(new Dimension(675, 500));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(contabilM2Panel, "gapx 10");

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToContabilM2Panel()
    {
        contabilM2Panel.removeAll();
        contabilM2Panel.add(medicLabel);
        contabilM2Panel.add(medicCB);
        contabilM2Panel.add(profitMedicButton, "span");
        contabilM2Panel.add(angajatLabel);
        contabilM2Panel.add(angajatCB);
        contabilM2Panel.add(salarAngajatTF);
        contabilM2Panel.add(salarAngajatButton, "span");
        contabilM2Panel.add(specialitateLabel);
        contabilM2Panel.add(specialitateMedicalaCB);
        contabilM2Panel.add(profitSpecialitateButton, "span");
        contabilM2Panel.add(profitCentruButton);
    }

    public JComboBox<String> getMedicCB() {
        return medicCB;
    }

    public JButton getProfitMedicButton() {
        return profitMedicButton;
    }

    public JButton getProfitCentruButton() {
        return profitCentruButton;
    }

    public JComboBox<String> getAngajatCB() {
        return angajatCB;
    }

    public JButton getSalarAngajatButton() {
        return salarAngajatButton;
    }

    public JTextField getSalarAngajatTF() {
        return salarAngajatTF;
    }

    public JComboBox<String> getSpecialitateMedicalaCB() {
        return specialitateMedicalaCB;
    }

    public JButton getProfitSpecialitateButton() {
        return profitSpecialitateButton;
    }
}
