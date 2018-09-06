package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public class CompHold extends ComponentType {

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
