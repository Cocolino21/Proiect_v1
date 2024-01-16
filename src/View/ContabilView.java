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
    private JTextField profitCentruAnTF;
    private JComboBox<String> profitCentruLunaCB;
    private JButton veziProfitCentruButton;
    private JTextField profitCentruTF;
//////////////////////////////////////
    private JFrame profitMedicFrame;
    private JPanel profitMedicPanel;
    private JTextField profitMedicAnTF;
    private JComboBox<String> profitMedicLunaCB;
    private JButton veziProfitMedicButton;
    private JTextField profitMedicTF;
    /////////////////////////////////////
    private JFrame profitSpecialitateFrame;
    private JPanel profitSpecialitatePanel;
    private JTextField profitSpecialitateAnTF;
    private JComboBox<String> profitSpecialitateLunaCB;
    private JButton veziProfitSpecialitateButton;
    private JTextField profitSpecialitateTF;
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
        salarAngajatTF.setEnabled(false);
        salarAngajatTF.setEditable(false);

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
        createProfitMedic();

        profitMedicFrame.setContentPane(profitMedicPanel);
        profitMedicFrame.pack();
        profitMedicFrame.setLocationRelativeTo(null);
        profitMedicFrame.setResizable(false);
        /////// SFARSIT FEREASTRA PROFIT MEDIC /



        ///// INCEPUT FEREASTRA PROFIT SPECIALITATE
        profitSpecialitateFrame = new JFrame("Profit specialitate");
        profitSpecialitatePanel = new JPanel(new MigLayout());
        createProfitSpecialitateTable();

        profitSpecialitateFrame.setContentPane(profitSpecialitatePanel);
        profitSpecialitateFrame.pack();
        profitSpecialitateFrame.setLocationRelativeTo(null);
        profitSpecialitateFrame.setResizable(false);
        ///// SFARSIT FEREASTRA PROFIT SPECIALITATE



        ///// INCEPUT FEREASTRA PROFIT CENTRU
        profitCentruFrame = new JFrame("Profit specialitate");
        profitCentruPanel = new JPanel(new MigLayout());
        createProfitCentruTable();

        profitCentruFrame.setContentPane(profitCentruPanel);
        profitCentruFrame.pack();
        profitCentruFrame.setLocationRelativeTo(null);
        profitCentruFrame.setResizable(false);
        ///// SFARSIT FEREASTRA PROFIT CENTRU

    }
    public void createProfitCentruTable(){
        profitCentruAnTF = new JTextField();
        profitCentruLunaCB = new JComboBox<>();
        veziProfitCentruButton = new JButton("Profit");
        profitCentruTF = new JTextField();
        profitCentruTF.setEditable(false);
        profitCentruTF.setEnabled(false);

        profitCentruPanel.add(profitCentruAnTF);
        profitCentruPanel.add(profitCentruLunaCB);
        profitCentruPanel.add(veziProfitCentruButton, "span");
        profitCentruPanel.add(profitCentruTF);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    public void createProfitSpecialitateTable(){
        profitSpecialitateAnTF = new JTextField();
        profitSpecialitateLunaCB = new JComboBox<>();
        veziProfitSpecialitateButton = new JButton("Profit");
        profitSpecialitateTF = new JTextField();
        profitSpecialitateTF.setEditable(false);
        profitSpecialitateTF.setEnabled(false);


        profitSpecialitatePanel.add(profitSpecialitateAnTF);
        profitSpecialitatePanel.add(profitSpecialitateLunaCB);
        profitSpecialitatePanel.add(veziProfitSpecialitateButton, "span");
        profitSpecialitatePanel.add(profitSpecialitateTF);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    public void createProfitMedic(){
        profitMedicAnTF = new JTextField();
        profitMedicLunaCB = new JComboBox<>();
        veziProfitMedicButton = new JButton("Profit");
        profitMedicTF = new JTextField();
        profitMedicTF.setEditable(false);
        profitMedicTF.setEnabled(false);


        profitMedicPanel.add(profitMedicAnTF);
        profitMedicPanel.add(profitMedicLunaCB);
        profitMedicPanel.add(veziProfitMedicButton, "span");
        profitMedicPanel.add(profitMedicTF);
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


    public JButton getProfitMedicButton() {
        return profitMedicButton;
    }
    public JButton getProfitCentruButton() {
        return profitCentruButton;
    }
    public JButton getSalarAngajatButton() {
        return salarAngajatButton;
    }
    public JButton getProfitSpecialitateButton() {
        return profitSpecialitateButton;
    }
    public JFrame getProfitCentruFrame() {
        return profitCentruFrame;
    }
    public JFrame getProfitMedicFrame() {
        return profitMedicFrame;
    }
    public JFrame getProfitSpecialitateFrame() {
        return profitSpecialitateFrame;
    }
}
