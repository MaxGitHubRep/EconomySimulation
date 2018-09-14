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
public class Consumer extends javax.swing.JPanel implements GamePulse {

    private final ConsumerComponents components;
    
    private class ConsumerComponents extends Component {
    
        protected void calculateConsumerComponents() {

            double Wages = (0.000000064 * (Population * ((100 - Unemployment)/100)) * WageMultiplier);
            double DisposableIncome = Wages;
            CostOfProduction+= Wages;

            ConsumerConfidence = StandardOfLiving * (100-IncomeTax)/100;

            PropensityToConsume = ((100 - InterestRate)/100) * ConsumerConfidence;
            if (PropensityToConsume == 0) PropensityToConsume+=0.01;

            if (DisposableIncome == 0 && TotalSavings >= 0.1) {
                TotalSavings-=0.1;
                DisposableIncome+=0.1;
            } else if (TotalSavings < 0.1 && DisposableIncome == 0) {
                HintManager.createHint(Hints.ConsumersBankrupt);
            }

            DailyIncomeTax = Wages * (Wages > 0 && IncomeTax > 0 && !GameDisplay.TaxBreak[1] ? (IncomeTax/100) : 0);
            DisposableIncome -= DailyIncomeTax;

            Consumption = PropensityToConsume * ( DisposableIncome + 0.4 * (!GameDisplay.TaxBreak[1] ? 1-(IncomeTax/100) : 1));
            double Savings = (1 - PropensityToConsume) * DisposableIncome;
            
            TotalConsumption += Consumption;
            TotalIncomeTax += DailyIncomeTax;
            TotalSavings += Savings;
            Taxation += DailyIncomeTax;
            
        }

    }
    
    public Consumer() {
        initComponents();
        this.components = new ConsumerComponents();
 
    }

    @Override
    public void gamePulseEvent() {
        this.components.calculateConsumerComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 769, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
