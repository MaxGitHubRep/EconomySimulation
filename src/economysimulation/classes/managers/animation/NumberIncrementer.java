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

    private Thread NIThread;
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
        
        int difference = Math.abs(start - end);
        
        NIThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < difference; i++) {
                        if (increase) {
                            start++;
                        } else {
                            start--;
                        }
                        label.setText(String.format(text, start));
                        Thread.sleep(tickDelay/difference);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });  
        NIThread.start();
    }
    
}
