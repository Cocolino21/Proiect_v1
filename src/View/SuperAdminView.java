package View;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SuperAdminView extends JFrame {

    private DefaultTableModel centreTableModel;

    private DefaultTableModel angajatiTableModel;
    private JPanel adminWrapper;

    private JPanel centreWrapper;

    private JPanel admsWrapper;
    private JPanel angajatiWrapper;
    private JPanel functiiWrapper;
    private JPanel leftWrapper;
    private JPanel gapYpanel;
    private JButton centreButton;
    private JButton adminButton;
    private JButton angajatiButton;
    private JButton functiiButton;

    private JButton backButton;

    private JButton logOutButton;
    private Image pic;

    private JLabel lb1;
    private JTable centreTable;

    private JTable angajatiTable;
    private JScrollPane centreSP;
    private JScrollPane angajatiSP;
    private JPanel centreSearchByLabel;

    private JPanel angajatiSearchBylabel;
    private JLabel lb_a1;

    private JLabel lb_a2 = new JLabel("Sel. Centru");
    private JLabel lb_a3 = new JLabel("Sel. Dept.");


    private JButton addAngajatButton;
    private JButton removeAngajatButton;
    private JButton searchAngajatButton;
    private JButton editAngajatButton;
    private JButton clearSearchAngajatButton;


    Object[][] centreRowData = null;

    Object[][] angajatiRowData = null;

    private JLabel lb_c1;

    private JButton addCentruButton;

    private ImageIcon addCentruButtonIcon;

    private JButton removeCentruButton;
    private ImageIcon removeCentruButtonIcon;
    private  ImageIcon clearSearchCentruButtonIcon;
    private JButton searchCentruButton;

    private JButton clearSearchCentruButton;
    private ImageIcon searchCentruButtonIcon;

    private JComboBox<String> selectCentruCB;

    private JFrame addCentruJFrame;
    private JPanel acjf_mainPanel;
    private JLabel acjf_l1 = new JLabel("Nume Centru");
    private JTextField acjf_numeTF = new JTextField();
    private JLabel acjf_l2 = new JLabel("Adresa Centru");
    private JTextField acjf_adresaTF = new JTextField();
    private JLabel acjf_l3 = new JLabel("Program");
    private JTextField acjf_programTF = new JTextField();
    private JButton acjf_SubmitButton;
    private JLabel acjf_l4 = new JLabel("Nu s-a putut adauga centrul");

    private JFrame addAngajatJFrame;
    private JPanel aajf_mainPanel;
    private JLabel[] aajf_l;
    private JComponent[] aajf_tfOrCb;


    private JComboBox<String> selectCentruCB2;
    private JComboBox<String> selectDeptCB;
    private JComboBox<String> selectDeptCB2;
    private JComboBox<String> selectFunctiiCB;

    private JComboBox<String> selectCentruCB3;

    private JButton aajf_SubmitButton;

    private ImageIcon editButtonIcon;

/////////////
    private JTextField aajf_procentTF;
    private JTextField aajf_codParafaTF;
    private JTextField aajf_titluStiintificTF;
    private JTextField aajf_postDidacticTF;
/////////////
    private void beautifyButton(JButton button)
    {
        button.setFocusPainted(false); // Remove focus border
        button.setBackground(new Color(50, 150, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(110, 100)); // Increase button size
        button.setFont(button.getFont().deriveFont(Font.BOLD));

    }
    public void replaceComboBoxItems(ArrayList<String> strings)
    {
        selectCentruCB.removeAllItems();
        for(String s: strings)
        {
            selectCentruCB.addItem(s);
        }

    }

    public void replaceComboBoxItems3(ArrayList<String> strings)
    {
        selectCentruCB3.removeAllItems();
        for(String s: strings)
        {
            selectCentruCB3.addItem(s);
        }

    }

    public void replaceComboBoxItems2(ArrayList<String> strings)
    {
        selectCentruCB2.removeAllItems();
        for(String s: strings)
        {
            selectCentruCB2.addItem(s);
        }

    }

    public void replaceComboBoxItems_DEPT(ArrayList<String> strings)
    {
        selectDeptCB.removeAllItems();
        for(String s: strings)
        {
            selectDeptCB.addItem(s);
        }

    }

    public void replaceComboBoxItems_DEPT2(ArrayList<String> strings)
    {
        selectDeptCB2.removeAllItems();
        for(String s: strings)
        {
            selectDeptCB2.addItem(s);
        }

    }

    public void replaceComboBoxItems_FCT(ArrayList<String> strings)
    {
        selectFunctiiCB.removeAllItems();
        for(String s: strings)
        {
            selectFunctiiCB.addItem(s);
        }


    }




    public void reAddToAdminView()
    {
        this.getContentPane().removeAll();
        setContentPane(adminWrapper);

        adminWrapper.add(gapYpanel,"wrap,align left, span");
        adminWrapper.add(lb1,"span, gapx 10");
        adminWrapper.add(centreButton, "gapx 10");
        adminWrapper.add(angajatiButton);
        backButton.setVisible(false);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();

    }


    public void reAddToCentreView()
    {
        this.getContentPane().removeAll();
        setContentPane(centreWrapper);

        centreWrapper.add(gapYpanel,"wrap,align left, span");
        centreWrapper.add(centreSearchByLabel,"wrap,span");
        updateCentreTable();
        centreWrapper.add(centreSP);
        backButton.setVisible(true);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }


    public void reAddToAngajatiView()
    {
        this.getContentPane().removeAll();
        setContentPane(angajatiWrapper);

        angajatiWrapper.add(gapYpanel,"wrap,align left, span");
        angajatiWrapper.add(angajatiSearchBylabel,"wrap,span");
        updateAngajatiTable();
        angajatiWrapper.add(angajatiSP);
        backButton.setVisible(true);



        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void reAddToFunctiiView()
    {
        this.getContentPane().removeAll();
        setContentPane(functiiWrapper);

        functiiWrapper.add(gapYpanel,"wrap,align left, span");
        backButton.setVisible(true);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }


    public void updateCentreTable()
    {
        String[] centre = new String[]{"id. centru","nume de centru","adresa","program centru"};


        centreTableModel = new DefaultTableModel(centreRowData,centre);
        centreTable = new JTable(centreTableModel);
        centreTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        centreTable.setDefaultEditor(Object.class, null);
        centreTable.getTableHeader().setReorderingAllowed(false);
        centreSP = new JScrollPane(centreTable);
        centreSP.setAutoscrolls(true);
        centreSP.setPreferredSize(new Dimension(920,370));

    }

    public void updateAngajatiTable()
    {
        String[] angajati = new String[]{"id. angajat","nume","prenume","functie","dept.","username","nr. contract","nr. telefon","id_centru"};
        angajatiTableModel = new DefaultTableModel(angajatiRowData, angajati);
        angajatiTable = new JTable(angajatiTableModel);
        angajatiTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        angajatiTable.setDefaultEditor(Object.class, null);
        angajatiTable.getTableHeader().setReorderingAllowed(false);
        angajatiSP = new JScrollPane(angajatiTable);
        angajatiSP.setAutoscrolls(true);
        angajatiSP.setPreferredSize(new Dimension(920,370));

    }





    public SuperAdminView()
    {
        ///initComponents
        try {
            pic = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("images/super_admin_bg.png")));
        }
        catch (IOException | NullPointerException e) {
            pic = new ImageIcon().getImage();
        }
        ///ADMIN WRAPER
        adminWrapper  = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pic, 0, 0, null);
            }

        };



        adminWrapper.setLayout(new MigLayout("gapx 20, gapy 20, insets 0"));
        centreButton = new JButton("Centre");
        adminButton = new JButton("Admini");
        angajatiButton = new JButton("Angajati");
        functiiButton = new JButton("Functii");
        logOutButton = new JButton("Log-out");
        backButton = new JButton("Back");

        leftWrapper = new JPanel();
        gapYpanel = new JPanel();
        lb1 = new JLabel("Ce doriti sa editati? : ");




        backButton.setFocusPainted(false); // Remove focus border
        backButton.setBackground(new Color(155, 48, 80));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 30)); // Increase button size
        backButton.setFont(logOutButton.getFont().deriveFont(Font.BOLD));
        backButton.setVisible(false);
        logOutButton.setFocusPainted(false); // Remove focus border
        logOutButton.setBackground(new Color(155, 48, 80));
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setPreferredSize(new Dimension(100, 30)); // Increase button size
        logOutButton.setFont(logOutButton.getFont().deriveFont(Font.BOLD));
        gapYpanel.setBackground(new Color(50, 150, 255));
        gapYpanel.setLayout(new MigLayout());
        gapYpanel.setPreferredSize(new Dimension(920, 50));

        gapYpanel.add(logOutButton);
        gapYpanel.add(backButton);
        leftWrapper.setPreferredSize(new Dimension(120, 520));
        leftWrapper.setBackground(new Color(50, 150, 255));
        this.add(leftWrapper);
        adminWrapper.add(gapYpanel,"wrap,align left, span");
        lb1.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,  20));
        adminWrapper.add(lb1,"span, gapx 10");

        beautifyButton(centreButton);
        adminWrapper.add(centreButton,"gapx 10");


        beautifyButton(angajatiButton);
        adminWrapper.add(angajatiButton);




        //CENTRE WRAPPER

        addCentruButton = new JButton();
        addCentruButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/add_button_icon.png")));
        addCentruButton.setIcon(addCentruButtonIcon);
        addCentruButton.setFocusPainted(false);
        removeCentruButton = new JButton();
        removeCentruButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/remove_button_icon.png")));
        removeCentruButton.setIcon(removeCentruButtonIcon);
        removeCentruButton.setFocusPainted(false);
        searchCentruButton = new JButton();
        searchCentruButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/search_button_icon.png")));
        searchCentruButton.setIcon(searchCentruButtonIcon);
        searchCentruButton.setFocusPainted(false);
        clearSearchCentruButton = new JButton();
        clearSearchCentruButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/clear_search_button_icon.png")));
        clearSearchCentruButton.setIcon(clearSearchCentruButtonIcon);
        clearSearchCentruButton.setFocusPainted(false);
        centreWrapper = new JPanel();
        selectCentruCB = new JComboBox<String>();
        selectCentruCB.setPreferredSize(new Dimension(250,20));

        lb_c1 = new JLabel("Cauta centre: ");
        lb_c1.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,  15));
        centreSearchByLabel = new JPanel(new MigLayout("insets 0"));
        centreSearchByLabel.setPreferredSize(new Dimension(920,50));
        centreSearchByLabel.add(lb_c1,"gapx 10,gapy 10");


        selectCentruCB.setEditable(true);
        AutoCompleteDecorator.decorate(selectCentruCB);
        centreSearchByLabel.add(selectCentruCB);
        centreSearchByLabel.add(searchCentruButton,"gapy 10");
        centreSearchByLabel.add(clearSearchCentruButton,"gapy 10");
        centreSearchByLabel.add(addCentruButton,"gapy 10,  gapx 480");
        centreSearchByLabel.add(removeCentruButton,"gapy 10");
        centreWrapper.setLayout(new MigLayout("gapx 0, gapy 0, insets 0"));
        String[] centre = new String[]{"id. centru","nume de centru","adresa","program centru"};
        centreTableModel = new DefaultTableModel(centreRowData,centre);
        centreTable = new JTable(centreTableModel);
        centreTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        centreTable.setDefaultEditor(Object.class, null);
        centreTable.getTableHeader().setReorderingAllowed(false);
        centreTable.setColumnSelectionAllowed(false);
        centreSP = new JScrollPane(centreTable);
        centreSP.setPreferredSize(new Dimension(920,500));
        addCentruJFrame = new JFrame();
        acjf_mainPanel = new JPanel(new MigLayout("gapx 10, gapy 10"));

        acjf_numeTF.setPreferredSize(new Dimension(100,30));
        acjf_adresaTF.setPreferredSize(new Dimension(100,30));
        acjf_programTF.setPreferredSize(new Dimension(100,30));
        acjf_SubmitButton = new JButton("Submit");
        acjf_SubmitButton.setFocusPainted(false); // Remove focus border
        acjf_SubmitButton.setBackground(new Color(155, 48, 80));
        acjf_SubmitButton.setForeground(Color.WHITE);
        acjf_SubmitButton.setPreferredSize(new Dimension(100, 30));
        acjf_SubmitButton.setFont(acjf_SubmitButton.getFont().deriveFont(Font.BOLD));
        acjf_mainPanel.add(acjf_l1);
        acjf_mainPanel.add(acjf_numeTF);
        acjf_mainPanel.add(acjf_l2);
        acjf_mainPanel.add(acjf_adresaTF);
        acjf_mainPanel.add(acjf_l3);
        acjf_mainPanel.add(acjf_programTF);
        acjf_mainPanel.add(acjf_SubmitButton,"span");
        acjf_l4.setVisible(false);
        acjf_mainPanel.add(acjf_l4);
        addCentruJFrame.setTitle("Adauga centru");
        addCentruJFrame.setContentPane(acjf_mainPanel);
        addCentruJFrame.setResizable(false);

        addCentruJFrame.pack();
        addCentruJFrame.setLocationRelativeTo(null);
        addCentruJFrame.setVisible(false);


        //ADMS WRAPPER
        admsWrapper = new JPanel();
        admsWrapper.setLayout(new MigLayout("gapx 20, gapy 20, insets 0"));

        //ANGAJATI WRAPPER
        angajatiWrapper = new JPanel();
        angajatiWrapper.setLayout(new MigLayout("gapx 0, gapy 0, insets 0"));
        angajatiSearchBylabel = new JPanel(new MigLayout("insets 0"));
        angajatiSearchBylabel.setPreferredSize(new Dimension(920,50));
        lb_a1=new JLabel("Cauta angajat: ");
        lb_a1.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,  15));
        angajatiSearchBylabel.add(lb_a1,"gapx 10,gapy 10");

        selectCentruCB2 = new JComboBox<String>();
        selectCentruCB2.setPreferredSize(new Dimension(250,20));
        selectCentruCB2.setEditable(true);
        AutoCompleteDecorator.decorate(selectCentruCB2);
        angajatiSearchBylabel.add(lb_a2);
        angajatiSearchBylabel.add(selectCentruCB2);
        angajatiSearchBylabel.add(lb_a3,"gapx 10");
        selectDeptCB = new JComboBox<String>();
        selectDeptCB.setEditable(true);
        AutoCompleteDecorator.decorate(selectDeptCB);
        selectDeptCB.setPreferredSize(new Dimension(200,20));
        angajatiSearchBylabel.add(selectDeptCB);
        searchAngajatButton = new JButton();
        searchAngajatButton.setIcon(searchCentruButtonIcon);
        searchCentruButton.setFocusPainted(false);
        angajatiSearchBylabel.add(searchAngajatButton,"gapy 10");
        clearSearchAngajatButton = new JButton();
        clearSearchAngajatButton.setIcon(clearSearchCentruButtonIcon);
        clearSearchAngajatButton.setFocusPainted(false);
        angajatiSearchBylabel.add(clearSearchAngajatButton,"gapy 10");
        addAngajatButton = new JButton();
        addAngajatButton.setIcon(addCentruButtonIcon);
        addAngajatButton.setFocusPainted(false);
        angajatiSearchBylabel.add(addAngajatButton,"gapy 10, gapx 200");
        removeAngajatButton = new JButton();
        removeAngajatButton.setIcon(removeCentruButtonIcon);
        removeAngajatButton.setFocusPainted(false);
        angajatiSearchBylabel.add(removeAngajatButton,"gapy 10");
        editAngajatButton = new JButton();
        editButtonIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("images/edit_button_icon.png")));
        editAngajatButton.setIcon(editButtonIcon);
        editAngajatButton.setFocusPainted(false);
        angajatiSearchBylabel.add(editAngajatButton,"gapy 10");
        addAngajatJFrame = new JFrame();
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
        selectCentruCB3 = new JComboBox<String>();
        aajf_tfOrCb[12] = selectCentruCB3;

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



        addAngajatJFrame.setTitle("Modif. angajat");
        addAngajatJFrame.setContentPane(aajf_mainPanel);
        addAngajatJFrame.setResizable(false);

        addAngajatJFrame.pack();
        addAngajatJFrame.setLocationRelativeTo(null);
        addAngajatJFrame.setVisible(false);






        //FUNCTII WRAPPER
        functiiWrapper = new JPanel();
        functiiWrapper.setLayout(new MigLayout("gapx 20, gapy 20, insets 0"));



        this.setContentPane(adminWrapper);
        this.setTitle("Proiect_V1_Policlinici_SUPERADMIN");
        this.setSize(920,520);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void showErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public Object[][] getCentreRowData() {
        return centreRowData;
    }

    public void setCentreRowData(Object[][] centreRowData) {
        this.centreRowData = centreRowData;
    }

    public void setAngajatiRowData(Object[][] angajatiRowData) {
        this.angajatiRowData = angajatiRowData;
    }

    public JButton getClearSearchCentruButton() {
        return clearSearchCentruButton;
    }

    public JComponent[] getAajf_tfOrCb() {
        return aajf_tfOrCb;
    }

    public void setClearSearchCentruButton(JButton clearSearchCentruButton) {
        this.clearSearchCentruButton = clearSearchCentruButton;
    }

    public JComboBox<String> getSelectCentruCB3() {
        return selectCentruCB3;
    }

    public JButton getCentreButton() {
        return centreButton;
    }

    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getAngajatiButton() {
        return angajatiButton;
    }

    public JButton getFunctiiButton() {
        return functiiButton;
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public JButton getBackButton() {
        return backButton;
    }


    public JButton getAajf_SubmitButton() {
        return aajf_SubmitButton;
    }

    public DefaultTableModel getCentreTableModel() {
        return centreTableModel;
    }

    public JTable getCentreTable() {
        return centreTable;
    }

    public JButton getAddAngajatButton() {
        return addAngajatButton;
    }

    public JButton getRemoveAngajatButton() {
        return removeAngajatButton;
    }

    public JComboBox<String> getSelectCentruCB2() {
        return selectCentruCB2;
    }

    public JComboBox<String> getSelectDeptCB() {
        return selectDeptCB;
    }

    public DefaultTableModel getAngajatiTableModel() {
        return angajatiTableModel;
    }

    public JTable getAngajatiTable() {
        return angajatiTable;
    }

    public JButton getSearchAngajatButton() {
        return searchAngajatButton;
    }

    public JButton getEditAngajatButton() {
        return editAngajatButton;
    }

    public JButton getClearSearchAngajatButton() {
        return clearSearchAngajatButton;
    }

    public JPanel getAdminWrapper() {
        return adminWrapper;
    }

    public JFrame getAddAngajatJFrame() {
        return addAngajatJFrame;
    }

    public JComboBox<String> getSelectDeptCB2() {
        return selectDeptCB2;
    }

    public JComboBox<String> getSelectFunctiiCB() {
        return selectFunctiiCB;
    }

    public JButton getAddCentruButton() {
        return addCentruButton;
    }

    public JButton getRemoveCentruButton() {
        return removeCentruButton;
    }

    public JButton getSearchCentruButton() {
        return searchCentruButton;
    }

    public JComboBox<String> getSelectCentruCB() {
        return selectCentruCB;
    }

    public JPanel getGapYpanel() {
        return gapYpanel;
    }

    public JPanel getCentreWrapper() {
        return centreWrapper;
    }

    public JFrame getAddCentruJFrame() {
        return addCentruJFrame;
    }

    public JLabel getAcjf_l4() {
        return acjf_l4;
    }

    public JTextField getAcjf_numeTF() {
        return acjf_numeTF;
    }

    public JTextField getAcjf_adresaTF() {
        return acjf_adresaTF;
    }

    public JTextField getAcjf_programTF() {
        return acjf_programTF;
    }

    public JButton getAcjf_SubmitButton() {
        return acjf_SubmitButton;
    }
}
