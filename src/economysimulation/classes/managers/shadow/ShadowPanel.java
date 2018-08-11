package economysimulation.classes.managers.shadow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class ShadowPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public int pixels;

    public ShadowPanel(int pix, int width, int height, JPanel panel) {
        this.pixels = pix;
        setSize(width, height);
        
        add(panel);
        panel.setSize(width - (pixels * 2), height - (pixels * 2));
        panel.setLocation(pixels, pixels);
        
        setBorder(BorderFactory.createCompoundBorder(this.getBorder(), BorderFactory.createEmptyBorder(pixels, pixels, pixels, pixels)));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        int shade = 0;
        int topOpacity = 80;
        for (int i = 0; i < pixels; i++) {
            g.setColor(new Color(shade, shade, shade, ((topOpacity / pixels) * i)));
            g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
        }
    }
}