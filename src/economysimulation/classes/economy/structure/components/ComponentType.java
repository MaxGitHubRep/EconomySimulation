package economysimulation.classes.economy.structure.components;

/**
 *
 * @author Max Carter
 */
public abstract class ComponentType {
    
    public double value;
    public String name, description;
    
    /**
     * Sets the name of the component.
     * @param name What the component will be called.
     */
    public abstract void setName(String name);
    
    /**
     * Returns the name of the component.
     * @return The name of the component.
     */
    public abstract String getName();
    
    /**
     * Sets the description of the component.
     * @param description What the description of the component will be.
     */
    public abstract void setDescription(String description);
    
    /**
     * Returns the description of the component.
     * @return The description of the component.
     */
    public abstract String getDescription();
    
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
    
}
