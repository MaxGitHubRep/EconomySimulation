package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public abstract class ComponentType {
    
    /**
     * The {@code value} of the component.
     */
    public double value, defaultValue;

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
    
    /**
     * Sets the default value of the component.
     * @param value The default value of the component.
     */
    public abstract void setDefaultValue(double value);
    
    /**
     * Set the component's value to its default value.
     */
    public abstract void applyDefaultValue();
    
}
