package economysimulation.classes.managers.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class Format {
    
    /**
     * Adds a particular format to the panels so they let off a
     * response when hovered on so signify their existence as buttons.
     * @param backPanel  Bottom of the panel which has a slight colour change.
     * @param colorPanel Sub panel on the top which has a drastic colour change.
     */
    public static void addButtonFormat(JPanel backPanel, JPanel colorPanel) {
        //validates that the panel is not null.
        if (backPanel == null) {
            throw new NullPointerException("The back panel cannot be null, unlike the color panel which can be.");
        }
        
        //adds listeners to the panel.
        backPanel.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseEntered(MouseEvent e) {
                //updates the colour when scrolled on.
                backPanel.setBackground(ThemeHandler.getTheme().getPrimaryHoverColor());
                if (colorPanel != null) colorPanel.setBackground(ThemeHandler.getTheme().getSecondaryHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                //resets the colour when the cursor leaves the panel.
                backPanel.setBackground(ThemeHandler.getTheme().getPrimaryColor());
                if (colorPanel != null) colorPanel.setBackground(ThemeHandler.getTheme().getPrimaryColor());
            }
        });
    }
    
    /**
     * Adds faint text to a text field.
     * @param field     Text box to add the text to.
     * @param ghostText The ghost text to add.
     */
    public static void addGhostText(JTextField field, String ghostText) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //remove the ghost text when foccus is gained.
                field.setText(field.getText().replace(ghostText, ""));
                field.setForeground(ThemeHandler.getTheme().getGhostTextColor());
            }

            @Override
            public void focusLost(FocusEvent e) {
                //adds ghost text when focus lost if the text box is empty.
                if ("".equals(field.getText())) {
                    field.setText(ghostText);
                    field.setForeground(ThemeHandler.getTheme().getGhostTextColor());
                }
            }
        });
    }
    
}
