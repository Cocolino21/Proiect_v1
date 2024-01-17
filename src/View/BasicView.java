package View;

import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class BasicView extends JFrame {



    protected String BV_nume_fereastra;
    protected CurrentAngajat BV_currentAngajat;


    protected JPanel BV_mainPanel;
    protected JPanel BV_leftPanel;
    protected JPanel BV_rightPanel;
    protected JPanel BV_RP_profilePanel;

    protected JButton BV_profileButton;
    protected ImageIcon BV_profileImage;
    protected JButton BV_profileImageButton;

    protected JButton BV_m1Button;
    protected JButton BV_m2Button;
    protected JButton BV_m3Button;
    protected JButton BV_logOutButton;

    protected JLabel[] BV_userLabels = new JLabel[12];

    protected JTextField[] BV_userInfoTextFields = new JTextField[12];
    protected Image pic;

    protected JPanel BV_RP_m1Panel;

    protected JPanel BV_RP_m2Panel;

    protected JPanel BV_RP_m3Panel;
    protected JLabel[] BV_orar;
    protected JLabel BV_orarPersonalLabel;
    protected JTextField[] BV_userOrarTextFields_begin = new JTextField[7];
    protected JTextField[] BV_userOrarTextFields_end = new JTextField[7];
    protected JTextField BV_concediuActivBegin;
    protected JTextField BV_concediuActivEnd;

    protected JLabel BV_concediuActivLabel;
    protected JLabel BV_cerereConcediuLabel;
    protected JLabel BV_0lb = new JLabel();

    protected JButton BV_creeazaCerereConcediuButton;

    protected JFrame BV_ccjf;
    protected JPanel BV_ccjf_mainPanel;
    protected JTextField BV_ccjf_motivTF;
    protected JXDatePicker BV_ccjf_beginDate;
    protected JXDatePicker BV_ccjf_endDate;
    protected JButton BV_ccjf_submitButton;
    protected JTextField BV_salariuConstantTF;
    protected Object[][] BV_salariiLuniRowData;
    protected DefaultTableModel BV_salariiLuniTableModel;
    protected JTable BV_salariiLuniTable;
    protected JScrollPane BV_salariiLuniSP;


    protected void BV_beautifyButton(JButton button, Color color, Dimension dimension)
    {
        button.setFocusPainted(false); // Remove focus border
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(dimension); // Increase button size
        button.setFont(button.getFont().deriveFont(Font.BOLD));

    }

    public void reAddToBV_RP_profilePanel()
    {

        BV_RP_profilePanel.setPreferredSize(new Dimension(720,520));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(BV_RP_profilePanel,"gapx 10");
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToBV_RP_m1Panel()
    {
        for(int i=0;i<7;i++)
        {
            if(BV_userOrarTextFields_begin[i].getText().equals("00:00")&&BV_userOrarTextFields_end[i].getText().equals("00:00")) {
                BV_RP_m1Panel.remove(BV_userOrarTextFields_end[i]);
                BV_RP_m1Panel.remove(BV_userOrarTextFields_begin[i]);
            }
        }
        BV_RP_m1Panel.setPreferredSize(new Dimension(720,520));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(BV_RP_m1Panel,"gapx 10");
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToBV_RP_m2Panel()
    {


        BV_RP_m2Panel.setPreferredSize(new Dimension(720,520));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(BV_RP_m2Panel,"gapx 10");
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToBV_RP_m3Panel()
    {
        BV_RP_m3Panel.setPreferredSize(new Dimension(720,520));
        BV_rightPanel.removeAll();
        BV_rightPanel.add(BV_RP_m3Panel,"gapx 10");
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }






    public BasicView(String nume_fereastra, CurrentAngajat currentAngajat)
    {

        this.BV_currentAngajat=currentAngajat;
        this.BV_nume_fereastra=nume_fereastra;
        BV_mainPanel = new JPanel(new MigLayout("insets 0"));
        BV_leftPanel = new JPanel(new MigLayout("center,gapx 10, insets 0"));
        BV_rightPanel = new JPanel(new MigLayout("insets 0"));
        BV_profileButton = new JButton(currentAngajat.getFunctie() + " " +  currentAngajat.getNume());
        BV_beautifyButton(BV_profileButton,new Color(96, 108, 124), new Dimension(120,30));
        BV_profileButton.setMaximumSize(new Dimension(120,30));
        BV_profileImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/medic_photo.png"))); ///DE FACUT SWITCH PT FUNCTIE ANGAJAT
        BV_orar = new JLabel[7];
        try {
            pic = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("images/bv_userInfo_bg.png")));
        }
        catch (IOException | NullPointerException e) {
            pic = new ImageIcon().getImage();
        }
        BV_RP_profilePanel  = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pic, 0, 0, null);
            }

        };
        BV_RP_profilePanel.setLayout(new MigLayout("insets 0"));
        BV_RP_m1Panel = new JPanel(new MigLayout("insets 0"));
        BV_RP_m2Panel = new JPanel(new MigLayout("insets 0"));
        BV_RP_m2Panel.add(new JLabel("Salariu constant curent: "),"gapy 30");
        BV_salariuConstantTF = new JTextField();
        BV_salariuConstantTF.setPreferredSize(new Dimension(70, 30));
        BV_salariuConstantTF.setEditable(false);
        BV_salariuConstantTF.setEnabled(false);
        BV_salariuConstantTF.setText(Integer.valueOf(currentAngajat.getSalariu()).toString());
        BV_RP_m2Panel.add(BV_salariuConstantTF,"span");
        BV_RP_m3Panel = new JPanel(new MigLayout("center, insets 0"));
        BV_RP_m3Panel.add(new JLabel("Nu aveti acces la zona operationala!"));

        BV_salariiLuniRowData = new Object[13][13];
        String[] salariiLuni = new String[]{"Luna","Salariu"};

        String[] luniileAnului = new String[]{"Ianuarie","Februarie","Martie","Aprilie","Mai","Iunie","Iulie","August","Septembrie","Octombrie","Noiembrie","Decembrie"};
        for(int i = 0 ;i<12;i++) {
            BV_salariiLuniRowData[i][0] = luniileAnului[i];
            BV_salariiLuniRowData[i][1] = Integer.valueOf(currentAngajat.getSalariu()).toString();
        }

        BV_salariiLuniTableModel = new DefaultTableModel(BV_salariiLuniRowData, salariiLuni);
        BV_salariiLuniTable = new JTable(BV_salariiLuniTableModel);
        BV_salariiLuniTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        BV_salariiLuniTable.setDefaultEditor(Object.class, null);
        BV_salariiLuniTable.getTableHeader().setReorderingAllowed(false);
        BV_salariiLuniSP = new JScrollPane(BV_salariiLuniTable);
        BV_salariiLuniSP.setAutoscrolls(true);
        BV_salariiLuniSP.setPreferredSize(new Dimension(675,370));
        BV_RP_m2Panel.add(BV_salariiLuniSP,"gapy 20, span");



        BV_profileImageButton = new JButton();
        BV_profileImageButton.setIcon(BV_profileImage);
        BV_profileImageButton.setEnabled(false);
        BV_m1Button = new JButton("Res. Umane");
        BV_m2Button = new JButton("Finante");
        BV_m3Button = new JButton("Operational");
        BV_logOutButton = new JButton("Log-out");
        BV_orarPersonalLabel = new JLabel("Orar personal");
        BV_orarPersonalLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        BV_orarPersonalLabel.setPreferredSize(new Dimension(100, 30));
        BV_RP_m1Panel.add(BV_orarPersonalLabel,"span");
        for(int i=0;i<7;i++) {
            BV_userOrarTextFields_begin[i] = new JTextField();
            BV_userOrarTextFields_end[i] = new JTextField();
            BV_orar[i] = new JLabel();
            BV_orar[i].setFont(new Font(Font.SANS_SERIF,Font.BOLD,13));
            BV_orar[i].setPreferredSize(new Dimension(60, 30));
            BV_userOrarTextFields_begin[i].setPreferredSize(new Dimension(100, 30));
            BV_userOrarTextFields_begin[i].setEditable(false);
            BV_userOrarTextFields_begin[i].setEnabled(false);
            BV_userOrarTextFields_end[i].setPreferredSize(new Dimension(100, 30));
            BV_userOrarTextFields_end[i].setEditable(false);
            BV_userOrarTextFields_end[i].setEnabled(false);

            BV_RP_m1Panel.add(BV_orar[i]);
            BV_RP_m1Panel.add( BV_userOrarTextFields_begin[i]);
            BV_RP_m1Panel.add(new JLabel(" - "));
            BV_RP_m1Panel.add(BV_userOrarTextFields_end[i],"span");

        }
        BV_concediuActivBegin = new JTextField();
        BV_concediuActivEnd = new JTextField();

        for(int i=0;i<12;i++)
        {

            BV_userLabels[i] = new JLabel();
            BV_userInfoTextFields[i] = new JTextField();
            BV_userLabels[i].setFont(new Font(Font.SANS_SERIF,Font.BOLD,13));
            BV_userLabels[i].setPreferredSize(new Dimension(100, 30));
            BV_userInfoTextFields[i].setPreferredSize(new Dimension(150, 30));
            BV_userInfoTextFields[i].setEditable(false);
            BV_userInfoTextFields[i].setEnabled(false);
            BV_RP_profilePanel.add(BV_userLabels[i]);
            BV_RP_profilePanel.add(BV_userInfoTextFields[i],"span");


        }
        BV_userLabels[0].setText("Nume");
        BV_userLabels[1].setText("Prenume");
        BV_userLabels[2].setText("CNP");
        BV_userLabels[3].setText("Adresa");
        BV_userLabels[4].setText("Nr. Telefon");
        BV_userLabels[5].setText("E-Mail");
        BV_userLabels[6].setText("IBAN");
        BV_userLabels[7].setText("Nr. Contract");
        BV_userLabels[8].setText("Functie");
        BV_userLabels[9].setText("Salariu_lunar");
        BV_userLabels[10].setText("username");
        BV_userLabels[11].setText("Centru");
        BV_orar[0].setText("Luni");
        BV_orar[1].setText("Marti");
        BV_orar[2].setText("Miercuri");
        BV_orar[3].setText("Joi");
        BV_orar[4].setText("Vineri");
        BV_orar[5].setText("Sambata");
        BV_orar[6].setText("Duminica");

        BV_userInfoTextFields[0].setText(currentAngajat.getNume());
        BV_userInfoTextFields[1].setText(currentAngajat.getPrenume());
        BV_userInfoTextFields[2].setText(currentAngajat.getCnp());
        BV_userInfoTextFields[3].setText(currentAngajat.getAdresa());
        BV_userInfoTextFields[4].setText(currentAngajat.getNrTelefon());
        BV_userInfoTextFields[5].setText(currentAngajat.getEmail());
        BV_userInfoTextFields[6].setText(currentAngajat.getIban());
        BV_userInfoTextFields[7].setText(Integer.valueOf(currentAngajat.getNrContract()).toString());
        BV_userInfoTextFields[8].setText(currentAngajat.getFunctie());
        BV_userInfoTextFields[9].setText(Integer.valueOf(currentAngajat.getSalariu()).toString());
        BV_userInfoTextFields[10].setText(currentAngajat.getUsername());
        BV_concediuActivLabel = new JLabel("Concediu activ");
        BV_cerereConcediuLabel = new JLabel("Creeaza o noua cerere de concediu");
        BV_RP_m1Panel.add(BV_0lb,"span");
        BV_concediuActivBegin.setPreferredSize(new Dimension(100,20));
        BV_concediuActivEnd.setPreferredSize(new Dimension(100,20));
        BV_concediuActivBegin.setEditable(false);
        BV_concediuActivBegin.setEnabled(false);
        BV_concediuActivEnd.setEditable(false);
        BV_concediuActivEnd.setEnabled(false);
        BV_RP_m1Panel.add(BV_concediuActivLabel, "gapy 150");
        BV_RP_m1Panel.add(BV_concediuActivBegin);
        BV_RP_m1Panel.add(new JLabel(" - "));
        BV_creeazaCerereConcediuButton = new JButton("Creeaza");
        BV_beautifyButton(BV_creeazaCerereConcediuButton,new Color(50, 150, 255), new Dimension(50,20));
        BV_RP_m1Panel.add(BV_concediuActivEnd,"span");
        BV_RP_m1Panel.add(BV_cerereConcediuLabel,"gapy 15");
        BV_RP_m1Panel.add(BV_creeazaCerereConcediuButton,"span");
        BV_ccjf = new JFrame();
        BV_ccjf_mainPanel = new JPanel(new MigLayout("insets 10"));
        BV_ccjf_mainPanel.add(new JLabel("Motiv"),"center, span");
        BV_ccjf_motivTF = new JTextField();
        BV_ccjf_motivTF.setPreferredSize(new Dimension(150,50));
        int maxLength = 99;
        BV_ccjf_motivTF.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (BV_ccjf_motivTF.getText().length() > maxLength) {
                    BV_showErrorMessage("Ai depasit limita de caractere");
                    BV_ccjf_motivTF.setText(BV_ccjf_motivTF.getText().substring(0, maxLength));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // No action needed
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No action needed
            }
        });
        BV_ccjf_mainPanel.add(BV_ccjf_motivTF,"center, span");
        BV_ccjf_beginDate = new JXDatePicker();
        BV_ccjf_beginDate.setPreferredSize(new Dimension(50,20));

        BV_ccjf_endDate = new JXDatePicker();
        BV_ccjf_endDate.setPreferredSize(new Dimension(50,20));

        BV_ccjf_mainPanel.add(new JLabel("Perioada concediului"),"gapy 20, center, span");
        BV_ccjf_mainPanel.add(BV_ccjf_beginDate);
        BV_ccjf_mainPanel.add(BV_ccjf_endDate,"span");
        BV_ccjf_submitButton = new JButton("Submit");
        BV_beautifyButton(BV_ccjf_submitButton,new Color(155, 48, 80), new Dimension(100,30));
        BV_ccjf_mainPanel.add(BV_ccjf_submitButton,"gapy 20,center, span");
        BV_ccjf.setContentPane(BV_ccjf_mainPanel);
        BV_ccjf.pack();
        BV_ccjf.setTitle("Cerere concediu");
        BV_ccjf.setLocationRelativeTo(null);
        BV_ccjf.setResizable(false);
        BV_ccjf.setVisible(false);




        BV_beautifyButton(BV_m1Button, new Color(96, 108, 124), new Dimension(150,35));
        BV_beautifyButton(BV_m2Button, new Color(96, 108, 124), new Dimension(150,35));
        BV_beautifyButton(BV_m3Button, new Color(96, 108, 124), new Dimension(150,35));
        BV_beautifyButton(BV_logOutButton, new Color(155, 48, 80), new Dimension(100,30));





        BV_leftPanel.add(BV_profileButton,"gapy 40,wrap,center, span");
        BV_leftPanel.add(BV_profileImageButton,"span");
        BV_leftPanel.add(BV_m1Button,"gapy 20,center,span");
        BV_leftPanel.add(BV_m2Button,"center,span");
        BV_leftPanel.add(BV_m3Button,"center,span");
        BV_leftPanel.add(BV_logOutButton,"gapy 50,center,span");

        BV_leftPanel.setPreferredSize(new Dimension(200,520));

        BV_leftPanel.setBackground(new Color(50, 150, 255));
        BV_rightPanel.setPreferredSize(new Dimension(720,520));


        BV_mainPanel.add(BV_leftPanel);
        BV_mainPanel.add(BV_rightPanel);
        this.setContentPane(BV_mainPanel);
        this.setTitle(nume_fereastra);
        this.setSize(920,520);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void BV_showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void BV_showSuccesMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Succes", JOptionPane.PLAIN_MESSAGE);
    }

    public JButton getBV_ccjf_submitButton() {
        return BV_ccjf_submitButton;
    }



    public JTextField getBV_ccjf_motivTF() {
        return BV_ccjf_motivTF;
    }

    public JXDatePicker getBV_ccjf_beginDate() {
        return BV_ccjf_beginDate;
    }

    public JXDatePicker getBV_ccjf_endDate() {
        return BV_ccjf_endDate;
    }

    public JLabel[] getBV_orar() {
        return BV_orar;
    }

    public JFrame getBV_ccjf() {
        return BV_ccjf;
    }

    public JTextField[] getBV_userOrarTextFields_begin() {
        return BV_userOrarTextFields_begin;
    }

    public JTextField[] getBV_userOrarTextFields_end() {
        return BV_userOrarTextFields_end;
    }

    public JButton getBV_creeazaCerereConcediuButton() {
        return BV_creeazaCerereConcediuButton;
    }

    public JTextField[] getBV_userInfoTextFields() {
        return BV_userInfoTextFields;
    }

    public JButton getBV_profileButton() {
        return BV_profileButton;
    }

    public JButton getBV_m1Button() {
        return BV_m1Button;
    }

    public JButton getBV_m2Button() {
        return BV_m2Button;
    }

    public JButton getBV_m3Button() {
        return BV_m3Button;
    }

    public JButton getBV_logOutButton() {
        return BV_logOutButton;
    }
}
