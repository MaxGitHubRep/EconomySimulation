package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public class CompHold extends ComponentType {

    public CompHold(){
        this.setValue(0);
    }
    
    public CompHold(double value) {
        this.setValue(value);
    }
    
    @Override
    public void setValue(double value) {
        super.value = value;
    }

    @Override
    public double getValue() {
        return super.value;
    }

    @Override
    public void addValue(double value) {
        super.value += value;
    }

}
