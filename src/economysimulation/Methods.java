package economysimulation;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Max Carter
 */
public class Methods {

    public static String username;
    
    public static int TICKS;
    
    // Gov variables
    public static double INTEREST_RATE, EXCHANGE_RATE, CORP_TAX, CONS_TAX, REGULATIONS, SUBSIDIES, GOV_SPENDING, PENSIONS;
    
    public static ArrayList<Double> INTEREST_RATES = new ArrayList<Double>();
    
    // Budget variables
    public static double ANNUAL_BUDGET, NATIONAL_DEBT, PUBLIC_SECTOR_BUDGET, POLITICAL_INFLUENCE, INFLATION, PRICE_LEVEL, REAL_GDP;
    
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
                label.setForeground(new Color(255, 55, 0));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    GameHold.addToFrontPanel(panel, true);
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
    
}
