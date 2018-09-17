package economysimulation.classes.managers.animation;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class StockGraph {
    
    protected Thread GThread;
    
    private JPanel panel;
    
    /**
    * Boolean to control loop on thread.
    */
    private volatile boolean
            build = true;
    
    /**
     * Locations of the graph vectors.
     */
    private int[][]
            vectors = new int[10][2];
    
    /**
     * Dimensions of the vectors and caps.
     */
    private final int
            VECTOR_WIDTH = 6,
            CAP_WIDTH = 60,
            CAP_HEIGHT = 4,
            LWIDTH = 1300,
            LHEIGHT = 1000;
    
    /**
     * List of JLabels for holding graph vector data.
     */
    private JLabel[]
            lines,
            capBottoms,
            capTops;
    
    
    public StockGraph(JPanel addPanel) {
        this.panel = addPanel;
        
        lines = new JLabel[vectors.length];
        capTops = new JLabel[vectors.length];
        capBottoms = new JLabel[vectors.length];
        
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new JLabel();
            lines[i].setSize(VECTOR_WIDTH, VECTOR_WIDTH);
            lines[i].setOpaque(true);
            lines[i].setBackground(Color.green);
            
            capBottoms[i] = new JLabel();
            capBottoms[i].setSize(CAP_WIDTH, CAP_HEIGHT);
            capBottoms[i].setOpaque(true);
            capBottoms[i].setBackground(Color.gray);
            
            capTops[i] = new JLabel();
            capTops[i].setSize(CAP_WIDTH, CAP_HEIGHT);
            capTops[i].setOpaque(true);
            capTops[i].setBackground(Color.gray);
        }

        initThread();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Defines line vectors,."> 
    /**
     * Sets the vectors of the lines.
     */
    private void setGraphCoords() {
        int step = LWIDTH / vectors.length;
        for (int i = 0; i < vectors.length; i++) {
            vectors[i][0] = (int) (step * i) + step/2;
            vectors[i][1] = (int) Math.floor(Math.sin(vectors[i][0])*400) + LHEIGHT/2;
        }
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Draws a cap that surrounds a line."> 
    /**
     * Draws a cap that surrounds a line.
     * 
     * @param i  Index of the cap list to use.
     * @param x  X coordinate of the line.
     * @param y1 First Y coordinate of the line.
     * @param y2 Second Y coordinate of the line.
     */
    private void insertCap(int i, int x, int y1, int y2) {
        panel.add(capTops[i]);
        panel.add(capBottoms[i]);
        capTops[i].setLocation(x - CAP_WIDTH/2 + VECTOR_WIDTH/2, y1 - CAP_HEIGHT);
        capBottoms[i].setLocation(x - CAP_WIDTH/2 + VECTOR_WIDTH/2, y2);
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Gets the next index in the list."> 
    /**
     * Gets the previous index of a list, and returns the last index if {@code i} arg is {@code 0}.
     * 
     * @param i Current index of the list.
     * @return Previous index in the list.
     */
    private int g(int i) {
        return (i == 0 ? vectors.length : i)-1;
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructs the graph and formats the lines and caps."> 
    /**
     * Constructs the graph and formats the lines and caps.
     * 
     * @param i Index of the list of lines.
     */
    private void buildGraph(int i) {
        panel.add(lines[i]);
        lines[i].setLocation(vectors[i][0], vectors[i][1]);

        int potY = vectors[g(i)][1] - vectors[i][1];
        if (potY < 0) {
            lines[g(i)].setBackground(Color.red);
            lines[g(i)].setSize(VECTOR_WIDTH, vectors[i][1] - vectors[g(i)][1]);
            insertCap(g(i), vectors[g(i)][0], vectors[g(i)][1], vectors[i][1]);
        } else {
            lines[g(i)].setBackground(Color.green);
            lines[g(i)].setLocation(vectors[g(i)][0], vectors[g(i)][1] - potY);
            lines[g(i)].setSize(VECTOR_WIDTH, potY);
            insertCap(g(i), vectors[g(i)][0], vectors[g(i)][1] - potY, vectors[g(i)][1]);
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Moves all vector positions along."> 
    /**
     * Shifts all vectors, lines and caps by {@code 1} pixel, or by the {@code width} of the panel if it touches the edge.
     */
    private synchronized void shiftGraph() {
        int i1 = -1;
        for (int i = 0; i < vectors.length; i++) {
            if (capBottoms[i].getX() == -CAP_WIDTH/2) i1 = LWIDTH+CAP_WIDTH/2;
            capBottoms[i].setLocation(capBottoms[i].getX() + i1, capBottoms[i].getY());
            capTops[i].setLocation(capTops[i].getX() + i1, capTops[i].getY());   
            lines[i].setLocation(lines[i].getX() + i1, lines[i].getY());
            i1 = -1;
        }
        if (build) {
            try {
                System.gc();
                GThread.sleep(20);
                shiftGraph();
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }//</editor-fold>
    
    public void stop() {
        this.build = false;
        System.gc();
    }

    /**
     * Initiates thread that shifts the x axis along.
     */
    private synchronized void initThread() {
        setGraphCoords();
        for (int i = 0; i < vectors.length; i++) {
            buildGraph(i);
        }
        GThread = new Thread(new GraphRunnable() {
        });
        GThread.start();
    }
    
    private class GraphRunnable implements Runnable {

        @Override
        public void run() {
            shiftGraph();
        }
        
    }
    
}
