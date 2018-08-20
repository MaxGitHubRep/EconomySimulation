package economysimulation.classes.managers.customcomp;

import economysimulation.classes.managers.themes.Theme;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class CircleProgressBar extends JPanel {
    
    public static double percent = 0;
    
    @Override
    protected void paintComponent(Graphics g) {
        // Draws background of progress bar.
        g.setColor(Theme.secondaryBack);
        g.fillOval(1, 1, 238, 238);
        
        // Draws progress bar.
        g.setColor(Theme.primaryText);
        g.fillArc(0, 0, 240, 240, 90, -(int) (360 * percent));
        
        // Fills in middle of circle.
        g.setColor(Theme.secondaryBack);
        g.fillOval(20, 20, 200, 200);
        
        //Configures color and font for percentage display.
        g.setColor(Theme.primaryText);
        g.setFont(new Font("Agency FB", Font.PLAIN, 82));
        String text = new DecimalFormat("0").format(percent*100) + "%";
        
        // Adjusts x coordinate of text based on text length.
        int x = 0;
        switch (text.length()) {
            case 2:
                x = 70;
                break;
            case 3:
                x = 65;
                break;
            case 4:
                x = 55;
                break;
        }
        
        // Draws text percentage in middle of circle.
        g.drawString(text, x, 145);
    }
    
}
