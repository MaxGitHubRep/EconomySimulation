package economysimulation.classes.managers.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

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
    
}
