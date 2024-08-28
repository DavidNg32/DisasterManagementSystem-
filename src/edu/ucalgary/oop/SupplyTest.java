package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {
    private String expectedType = "Blanket";
    private int expectedQuantity = 5;
    private Supply supply;

    @Before
    public void setUp() {
        supply = new Supply(expectedType, expectedQuantity);
    }

    @Test
    public void testGetType() {
        assertEquals("getType should return the correct type", expectedType, supply.getType());
    }

    @Test
    public void testGetQuantity() {
        assertEquals("getQuantity should return the correct quantity", expectedQuantity, supply.getQuantity());
    }

    @Test
    public void testSetType() {
        String newType = "Food";
        supply.setType(newType);
        assertEquals("setType should update the type", newType, supply.getType());
    }

    @Test
    public void testSetQuantity() {
        int newQuantity = 10;
        supply.setQuantity(newQuantity);
        assertEquals("setQuantity should update the quantity", newQuantity, supply.getQuantity());
    }
    
}
