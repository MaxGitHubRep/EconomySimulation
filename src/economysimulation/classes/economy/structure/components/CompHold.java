package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public class CompHold extends ComponentType {

    public CompHold(){
        this.setDefaultValue(0);
        this.applyDefaultValue();
    }
    
    public CompHold(double value) {
        this.setDefaultValue(value);
        this.applyDefaultValue();
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

    @Override
    public void setDefaultValue(double value) {
        super.defaultValue = value;
    }

    @Override
    public void applyDefaultValue() {
        super.value = super.defaultValue;
    }

}
