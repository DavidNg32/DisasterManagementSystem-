/**
 * @author David Nguyen <a href="mailto:dangducduy.nguyen@ucalgary.ca">
 * dangducduy.nguyen@ucalgary.ca
 * @version 1.1
 * @since 1.0
 */

package edu.ucalgary.oop;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class InquirerInteractionLogTest {
    private Inquirer inquirer;
    private List<ReliefService> interactions;

    private InquirerInteractionLog inquirerInteractionLog;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for my family member");
        interactions = new ArrayList<ReliefService>();
        inquirerInteractionLog = new InquirerInteractionLog(inquirer);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null InquirerInteractionLog object", inquirerInteractionLog);
        assertEquals("Constructor should set the inquirer correctly", inquirer, inquirerInteractionLog.getInquirer());
        assertEquals("Constructor should set the interactions correctly", interactions, inquirerInteractionLog.getInteractions());
    }
    
    
    
    @Test
    public void testObjectCreation() {
        assertNotNull("InquirerInteractionLog object should not be null", inquirerInteractionLog);
    }
    
    @Test
    public void testGetInquirer() {
        assertEquals("Inquirer should match the one set in setup", inquirer, inquirerInteractionLog.getInquirer());
    }
    
    @Test
    public void testGetInteractions() {
        assertEquals("Interactions should match the one set in setup", interactions, inquirerInteractionLog.getInteractions());
    }
    
    @Test
    public void testAddInteraction() {
        Inquirer inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for my family member");

        // Step 2: Create a new InquirerInteractionLog object with the Inquirer object
        DisasterVictim missingPerson = new DisasterVictim("Jane Alex", "2024-01-25");
        Location lastKnownLocation = new Location("University of Calgary", "2500 University Dr NW");
        String validDate = "2024-02-10";
        String expectedInfoProvided = "Looking for family member";
        InquirerInteractionLog inquirerInteractionLog = new InquirerInteractionLog(inquirer);
        ReliefService reliefService = new ReliefService(inquirerInteractionLog, missingPerson, validDate, expectedInfoProvided, lastKnownLocation);
        inquirerInteractionLog.addInteraction(reliefService);
        assertTrue("addInteraction should add a ReliefService object to the interactions list", inquirerInteractionLog.getInteractions().contains(reliefService));
    }

    @Test
    public void testLogInteractions() {
        // Step 1: Create a new Inquirer object
        Inquirer inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for my family member");

        // Step 2: Create a new InquirerInteractionLog object with the Inquirer object
        DisasterVictim missingPerson = new DisasterVictim("Jane Alex", "2024-01-25");
        Location lastKnownLocation = new Location("University of Calgary", "2500 University Dr NW");
        String validDate = "2024-02-10";
        String expectedInfoProvided = "Looking for family member";
        InquirerInteractionLog inquirerInteractionLog = new InquirerInteractionLog(inquirer);
        ReliefService reliefService = new ReliefService(inquirerInteractionLog, missingPerson, validDate, expectedInfoProvided, lastKnownLocation);

        // Step 4: Add the ReliefService object to the InquirerInteractionLog object using the addInteraction method
        inquirerInteractionLog.addInteraction(reliefService);

        // Step 5: Assert that the ReliefService object is in the InquirerInteractionLog object's interactions
        assertTrue(inquirerInteractionLog.getInteractions().contains(reliefService));
    }
    
    
}
