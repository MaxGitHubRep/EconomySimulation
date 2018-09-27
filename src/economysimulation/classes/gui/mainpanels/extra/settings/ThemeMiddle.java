package economysimulation.classes.gui.mainpanels.extra.settings;

import static economysimulation.classes.global.Methods.ThemeManager;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ThemeMiddle extends JPanel {
    
    public int sector = 4;
    
    public ThemeMiddle() {
        super.setSize(360, 180);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < 4; i++) {
            g.setColor(sector == i ? ThemeManager.Theme.getPrimaryColor() : ThemeManager.Theme.getPrimaryTextColor());
            g.fillArc(0, 0, 360, 360, (47*i), 40);
        }
        
        g.setColor(ThemeManager.Theme.getPrimaryColor());
        g.fillArc(50, 50, 260, 260, 0, 180);
        
        g.setColor(ThemeManager.Theme.getPrimaryTextColor());
        g.setFont(new Font("Agency FB", Font.PLAIN, 70));
        g.drawString("THEME", 115, 130);
        
        g.setFont(new Font("Agency FB", Font.PLAIN, 40));
        g.drawString("SELECTOR", 125, 170);
        
    }
    
}
