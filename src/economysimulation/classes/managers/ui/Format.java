package economysimulation.classes.managers.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 *
 * @author Max Carter
 */
public class Format {
    
    //<editor-fold defaultstate="collapsed" desc="Formats the labels."> 
    /**
     * Adds a particular format to the panels so they let off a
     * response when hovered on so signify their existence as buttons.
     * 
     * @param backPanel  Bottom of the panel which has a slight colour change.
     * @param colorPanel Sub panel on the top which has a drastic colour change.
     */
    public static void addButtonFormat(JPanel backPanel, JPanel colorPanel) {
        if (backPanel == null) {
            throw new NullPointerException("The back panel cannot be null, unlike the color panel which can be.");
        }
        
        backPanel.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseEntered(MouseEvent e) {
                backPanel.setBackground(ThemeHandler.getTheme().getPrimaryHoverColor());
                if (colorPanel != null) colorPanel.setBackground(ThemeHandler.getTheme().getSecondaryHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backPanel.setBackground(ThemeHandler.getTheme().getPrimaryColor());
                if (colorPanel != null) colorPanel.setBackground(ThemeHandler.getTheme().getPrimaryColor());
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Adds light grey text with a prompt until user selects text box."> 
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setText(field.getText().replace(ghostText, ""));
                field.setForeground(ThemeHandler.getTheme().getGhostTextColor());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(field.getText())) {
                    field.setText(ghostText);
                    field.setForeground(ThemeHandler.getTheme().getGhostTextColor());
                }
                
            }

        });
    }//</editor-fold>
    
}
