package View;

import Model.AuthCheck;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AuthView extends JFrame {


       private JPanel authViewMainPanel;
       private JPanel upGapPanel;
       private JLabel lb1;

       private JComboBox<String> selectCentruCB;
       private JTextField usernameField;
       private JPasswordField passwordField;
       private JLabel lb2;
       private JLabel lb3;
       private JButton loginButton;
       private Image pic;



       public AuthView() {


           ///initComponents
           try {
               pic = ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream("images/auth_bg.png")));
           }
           catch (IOException | NullPointerException e) {
               pic = new ImageIcon().getImage();
           }
           lb2 = new JLabel("Introduceti username-ul:");
           lb1 = new JLabel("Selecteaza centru:");
           lb3 = new JLabel("Introduceti parola:");
           loginButton = new JButton("Log-in");
           authViewMainPanel = new JPanel() {
               @Override
               public void paintComponent(Graphics g) {
                   super.paintComponent(g);
                   g.drawImage(pic, 0, 0, null);
               }

           };
           upGapPanel = new JPanel();
           selectCentruCB = new JComboBox<String>();
           usernameField = new JTextField();
           passwordField = new JPasswordField();



           upGapPanel.setPreferredSize(new Dimension(920,100));
           //upGapPanel.setBackground(Color.CYAN);
           authViewMainPanel.setLayout(new MigLayout("ax center,gapx 10, gapy 10"));
           authViewMainPanel.add(upGapPanel,"span");
           lb1.setFont( new Font(Font.SANS_SERIF,Font.PLAIN,  20));
           authViewMainPanel.add(lb1,"wrap,align 50% 50%,center,span");
           selectCentruCB.setPreferredSize(new Dimension(220,20));
           selectCentruCB.setEditable(true);
           AutoCompleteDecorator.decorate(selectCentruCB);
           authViewMainPanel.add(selectCentruCB,"wrap,align 50% 50%,center,span");
           lb2.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
           authViewMainPanel.add(lb2,"align 50% 50%,center,span");
           usernameField.setPreferredSize(new Dimension(180,20));
           authViewMainPanel.add(usernameField,"align 50% 50%,center,span");
           lb3.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
           authViewMainPanel.add(lb3,"align 50% 50%,center,span");
           passwordField.setPreferredSize(new Dimension(180,20));
           authViewMainPanel.add(passwordField,"align 50% 50%,center,span");
           loginButton.setFocusPainted(false); // Remove focus border
           loginButton.setBackground(new Color(50, 150, 255));
           loginButton.setForeground(Color.WHITE);
           loginButton.setPreferredSize(new Dimension(100, 40)); // Increase button size
           loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD));
           authViewMainPanel.add(loginButton,"align 50% 50%,center,span");




           this.setTitle("Proiect_V1_Policlinici");


           this.setContentPane(authViewMainPanel);

           this.setSize(920,520);
           this.setResizable(false);
           this.setVisible(true);
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       }

       public void replaceComboBoxItems(ArrayList<String> strings)
       {
           selectCentruCB.removeAllItems();
           for(String s: strings)
           {
               selectCentruCB.addItem(s);
           }

       }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<String> getSelectCentruCB() {
        return selectCentruCB;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}
