package economysimulation.classes.managers.animation;

import economysimulation.classes.managers.exception.InvalidTimeException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Max Carter
 */
public class NumberIncrementer {

    private Timer timer;
    
    private JLabel label;
    private String text;
    private int start, end, tickDelay;
    private boolean increase;
    
    public NumberIncrementer(JLabel label, String text, int start, int end, int tickDelay) throws InvalidTimeException {
        if (tickDelay < 1) {
            throw new InvalidTimeException(tickDelay);
        }
        this.label = label;
        this.text = text;
        this.start = start;
        this.end = end;
        this.increase = start < end;
        this.tickDelay = tickDelay;
    }
    
    public void startIncrementer() { 
        timer = new Timer(tickDelay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (increase) {
                    if (start < end) {
                        start++;
                        label.setText(String.format(text, start));
                        startIncrementer();
                    } else {
                        timer.stop();
                    } 
                } else {
                    if (start > end) {
                        start--;
                        label.setText(String.format(text, start));
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
