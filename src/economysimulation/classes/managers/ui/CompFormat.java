package economysimulation.classes.managers.ui;

import economysimulation.classes.managers.themes.Theme;
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
                backPanel.setBackground(Theme.primaryHover);
                colorPanel.setBackground(Theme.secondaryHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanel.setBackground(Theme.primaryBack);
                colorPanel.setBackground(Theme.primaryBack);
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adds light grey text with a prompt until user selects text box."> 
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(field.getText().replace(ghostText, ""));
                field.setForeground(Theme.primaryText);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(field.getText())) {
                    field.setText(ghostText);
                    field.setForeground(Theme.ghostText);
                }
                
            }

        });
    }//</editor-fold>
    
}
