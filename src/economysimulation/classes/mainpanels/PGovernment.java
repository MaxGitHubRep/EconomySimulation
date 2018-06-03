package economysimulation.classes.mainpanels;

import economysimulation.classes.Methods;
import economysimulation.classes.algorithms.Component;
import economysimulation.classes.subpanels.QGovernment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
/**
 *
 * @author Max Carter
 */
public class PGovernment extends javax.swing.JPanel {

    private static final String[] TITLES = new String[]{ "Interest Rates", "Consumer Taxes", "Corporation Taxes", "Pensions" };
    private static final ArrayList<Double>[] HISTORY = new ArrayList[]{ Component.INTEREST_RATES, Component.CONSUMER_TAXES, Component.CORPORATION_TAXES, };
    
    public static JButton[] graphButtons;
    private static int graphCode = 0;
    
    //<editor-fold defaultstate="collapsed" desc="Receives clock pulse.">
    public static void globalClockPulseGov() {
        Component.INTEREST_RATES.add(Component.INTEREST_RATE);
        Component.CONSUMER_TAXES.add(Component.CONS_TAX);
        Component.CORPORATION_TAXES.add(Component.CORP_TAX);

        if (Component.INTEREST_RATES.size() == Methods.GRAPH_TICKS+1) {
            for (ArrayList<Double> list : HISTORY) {
                list.remove(0);
            }
        }
        
        Methods.createGraph(TITLES[graphCode], HISTORY[graphCode], graphPanel);
        
    }//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Button listener to change graph type."> 
    private void addButtonListenerGraph(JButton button, int id) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphButtons[graphCode].setEnabled(true);
                graphCode = id;
                graphButtons[id].setEnabled(false);
            }
        });
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public PGovernment() {
        initComponents();
        Methods.addToFrontPanel(backPanelGov, Methods.govClass, false);
        
        graphButtons = new JButton[]{ QGovernment.historyIR, QGovernment.historyCT, QGovernment.historyCT2, QGovernment.historyP };

        for (int i = 0; i < graphButtons.length; i++) {
            graphButtons[i].setEnabled(true);
            addButtonListenerGraph(graphButtons[i], i);
        }

        
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphPanel = new javax.swing.JPanel();
        backPanelGov = new javax.swing.JPanel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        graphPanel.setOpaque(false);

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1076, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        backPanelGov.setOpaque(false);

        javax.swing.GroupLayout backPanelGovLayout = new javax.swing.GroupLayout(backPanelGov);
        backPanelGov.setLayout(backPanelGovLayout);
        backPanelGovLayout.setHorizontalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        backPanelGovLayout.setVerticalGroup(
            backPanelGovLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(backPanelGov, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backPanelGov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanelGov;
    private static javax.swing.JPanel graphPanel;
    // End of variables declaration//GEN-END:variables
}
