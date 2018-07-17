package economysimulation.classes.managers.animation;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class NumberIncrementer {
    
    // EXPERIMENTAL CLASS - DOESN'T ALL WORK YET
    
    private Timer timer;
    
    private JLabel label;
    private String text;
    private int start, end, tickDelay, index;
    private boolean increase;
    
    public NumberIncrementer(JLabel label, String text, int start, int end, int msTime) {
        this.label = label;
        this.text = text;
        this.start = start;
        this.end = end;
        this.increase = start < end;
        this.tickDelay = msTime / (Math.abs(end - start) == 0 ? 1 : Math.abs(end - start));
        this.index = increase ? start : end;
    }
    
    public void startIncrementer() { 
        timer = new Timer(tickDelay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (increase) {
                    if (start < end) {
                        index++;
                        label.setText(String.format(text, index));
                        startIncrementer();
                    } else {
                        timer.stop();
                    } 
                } else {
                    if (end > start) {
                        index--;
                        label.setText(String.format(text, index));
                        startIncrementer();
                    } else {
                        timer.stop();
                    }
                }
            }
        }); 
        timer.start();
    }
    
}
