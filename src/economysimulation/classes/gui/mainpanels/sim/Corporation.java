package economysimulation.classes.gui.mainpanels.sim;

import economysimulation.classes.economy.structure.Component;
import static economysimulation.classes.global.Methods.GameDisplay;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.pulse.GamePulse;

/**
 *
 * @author Max Carter
 */
public class Corporation extends javax.swing.JPanel implements GamePulse {

    private final CorporationComponents components;
    
    private class CorporationComponents extends Component {
        
        protected void calculateCorporationComponents() {
            
            double CorporationProfits = (Consumption - CostOfProduction);

            DailyCorporationTax = CorporationProfits * (CorporationProfits > 0 && CorporationTax > 0 && !GameDisplay.TaxBreak[0] ? (CorporationTax/100) : 0);
            CorporationProfits -= DailyCorporationTax;

            Taxation += DailyCorporationTax;
            
            CorporationConfidence = 1 * (100-CorporationTax)/100;

            double investment = CorporationProfits > 0 ? CorporationProfits * CorporationConfidence * 0.75 : 0;
            Investment+= investment;
            CorporationProfits -= investment;

            TotalCorporationTax += DailyCorporationTax;
            TotalCorporationProfits += CorporationProfits;

            if (CostOfProduction > TotalCorporationProfits && Unemployment < 100) {
                Unemployment++;
            } else if (Unemployment > 1) {
                Unemployment--;
            }
            
            if (TotalCorporationProfits <= 0) HintManager.createHint(Hints.CorporationBankrupt);
            
        }
        
    }
    
    public Corporation() {
        initComponents();
        this.components = new CorporationComponents();
    }

    @Override
    public void gamePulseEvent() {
        this.components.calculateCorporationComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
