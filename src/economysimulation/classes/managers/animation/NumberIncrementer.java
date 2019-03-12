package economysimulation.classes.managers.animation;

import economysimulation.classes.managers.exception.InvalidTimeException;
import javax.swing.JLabel;

/**
 * @author Max Carter
 */
public class NumberIncrementer {

    /** Animation thread. */
    private Thread NIThread;
    
    /** Difference between the start value and end value. */
    private volatile int difference;
    
    /** Label to change. */
    private JLabel label;
    
    /** String format of label. */
    private String text;
    
    //Starting and ending values and the duration.
    private int start, end, tickDelay;
    
    /** Whether or not the incrementer is going up or down. */
    private boolean increase;
    
    /**
     * Creates a new animated Number Incrementer.
     * @param label Label to change the text in.
     * @param text  String format.
     * @param start Starting value.
     * @param end   Ending value.
     * @param tickDelay Duration of effect.
     * @throws InvalidTimeException When {@code tickDelay} is below 1.
     */
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
    
    /** Starts the incrementer. */
    public synchronized void startIncrementer() { 
        difference = Math.abs(start - end);
        
        //new thread to make animation in.
        NIThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //loops the difference and adds 1 to start each iteration.
                    for (int i = 0; i < difference; i++) {
                        if (increase) {
                            start++;
                        } else {
                            start--;
                        }
                        //displays new text.
                        label.setText(String.format(text, start));
                        Thread.sleep(tickDelay/difference);
                    }
                    System.gc();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });  
        NIThread.start();
    }
    
}
