package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public abstract class ComponentType {
    
    /**
     * The {@code value} of the component.
     */
    public double value;

    /**
     * Sets the value of the component.
     * @param value Sets the value of the component.
     */
    public abstract void setValue(double value);
    
    /**
     * Returns the {@code double value} of the component.
     * @return The {@code double value} of the component.
     */
    public abstract double getValue();
    
    /**
     * Adds the {@code value} to the components value.
     * @param value The {@code value} to be added to the components value.
     */
    public abstract void addValue(double value);
    
}
