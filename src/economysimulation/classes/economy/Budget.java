package economysimulation.classes.economy;

/**
 *
 * @author Max Carter
 */
public class Budget extends Component {
    
    /**
     * Spends £{@code value} billion into {@code sector}.
     * 
     * @param sector
     * @param money 
     */
    public static void spendMoney(int sector, int money) {
        ANNUAL_BUDGET -= money;
        Spending[sector]+= money;
        SpendingInfluence[sector] += money;
    }
    
}
