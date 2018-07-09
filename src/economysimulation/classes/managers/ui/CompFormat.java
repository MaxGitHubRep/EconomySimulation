package economysimulation.classes.managers.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Max Carter
 */
public class CompFormat {
    
    //<editor-fold defaultstate="collapsed" desc="Formats the labels."> 
    public static void addButtonFormat(JPanel backPanel, JPanel colorPanel) {
        backPanel.addMouseListener(new MouseAdapter() {

            @Override 
            public void mouseEntered(MouseEvent e) {
                backPanel.setBackground(new Color(240, 240, 240));
                colorPanel.setBackground(new Color(204, 0, 0));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanel.setBackground(Color.white);
                colorPanel.setBackground(Color.white);
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adds light grey text with a prompt until user selects text box."> 
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(field.getText().replace(ghostText, ""));
                field.setForeground(new Color(204, 0, 0));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(field.getText())) field.setText(ghostText);
                field.setForeground(new Color(153, 153, 153));
            }

        });
    }//</editor-fold>
    
}
