package economysimulation.classes.gui.mainpanels.sim.consumer;

import economysimulation.classes.economy.structure.Component;
import static economysimulation.classes.global.Methods.ThemeManager;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ConsumerMiddle extends JPanel {

    protected int sector = 6;
    
    public ConsumerMiddle() {
        super.setSize(450, 450);
        
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        for (int i = 0; i < 6; i++) {
            g.setColor(sector == i ? ThemeManager.Theme.getPrimaryColor() : ThemeManager.Theme.getPrimaryTextColor());
            g.fillArc(0, 0, 450, 450, (60*i)+62, 52);
        }
        
        g.setColor(ThemeManager.Theme.getPrimaryColor());
        g.fillOval(75, 75, 300, 300);
        
        g.setColor(ThemeManager.Theme.getPrimaryTextColor());
        g.setFont(new Font("Agency FB", Font.PLAIN, 60));
        g.drawString("CONSUMERS", 110, 220);
        
        g.setFont(new Font("Agency FB", Font.PLAIN, 40));
        g.drawString("Support: " + new DecimalFormat("0").format(Component.ConsumerConfidence * 100) + "%", 130, 270);
        
    }
    
}
