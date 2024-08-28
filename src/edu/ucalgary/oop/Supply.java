package edu.ucalgary.oop;

public class Supply {
    private String type;
    private int quantity;


    /**
     * Constructs a new Supply with the specified type and quantity.
     *
     * @param type the type of the supply
     * @param quantity the quantity of the supply
     */
    
    public Supply(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Sets the type of the supply.
     *
     * @param type the type of the supply
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the quantity of the supply.
     *
     * @param quantity the quantity of the supply
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Returns the type of the supply.
     *
     * @return the type of the supply
     */
    public String getType() { return this.type; }

    /**
     * Returns the quantity of the supply.
     *
     * @return the quantity of the supply
     */
    public int getQuantity() { return this.quantity; }
    
    
}
