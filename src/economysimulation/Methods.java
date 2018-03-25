package economysimulation;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Max Carter
 */
public class Methods {

    public static int randomInt(int min, int max) {
        return new Random().nextInt((max-min)+1)+min;
    }
    
    public static void addButtonFormat(JLabel label, JPanel panel) {
        label.addMouseListener(new MouseAdapter() {

            @Override 
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.green);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(new Color(255, 51, 0));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    GameHold.addToFrontPanel(panel);
                } catch (Exception ex) {
                    
                }
            }
            
        });
    }
    
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(field.getText().replace(ghostText, ""));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(field.getText())) field.setText(ghostText);
            }

        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
