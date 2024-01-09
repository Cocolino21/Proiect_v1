package View;

import Model.CurrentAngajat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.imageio.ImageIO;
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
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MedicView extends BasicView {
        private JPanel medicM3Panel;
        @Override
        public void reAddToBV_RP_m3Panel()
        {
            medicM3Panel.setPreferredSize(new Dimension(720,520));
            BV_rightPanel.removeAll();
            BV_rightPanel.add(medicM3Panel,"gapx 10");
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
        }
        public  MedicView(CurrentAngajat currentAngajat){
            super("Proiect_V1_Policlinici_Medic",currentAngajat);
            medicM3Panel = new JPanel(new MigLayout("insets 0"));
            medicM3Panel.add(new JLabel("Hei ! sunt medic"));
        }

}
