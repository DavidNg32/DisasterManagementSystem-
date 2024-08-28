/**
 * @author David Nguyen <a href="mailto:dangducduy.nguyen@ucalgary.ca">
 * dangducduy.nguyen@ucalgary.ca
 * @version 1.1
 * @since 1.0
 */

package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VolunteerTest {
    private int ASSIGNED_SOCIAL_ID = 10;
    private Volunteer volunteer;

    @Before
    public void setUp() {
        // Initializing test objects before each test method
        volunteer = new Volunteer(ASSIGNED_SOCIAL_ID, "John", "2014-01-01");
    }
    
    @Test 
    public void testConstructor() {
        assertNotNull("Constructor should create a non-null Volunteer object", volunteer);
        assertEquals("Constructor should set the assigned social ID correctly", 10, volunteer.getAssignedSocialID());
        assertEquals("Constructor should set the entry date correctly", "2014-01-01", volunteer.getEntryDate());
    }
    
    @Test
    public void testInheritanceFromDisasterVictim() {
        assertNotNull("Constructor should create a non-null Volunteer object", volunteer);
        assertTrue("Volunteer should be an instance of DisasterVictim", volunteer instanceof DisasterVictim);
    }
    
    @Test
    public void testGenerateSocialID() {
        int socialID = Volunteer.generateSocialID();
        assertTrue("generateSocialID should return a random number between 0 and 9999", socialID >= 0 && socialID <= 9999);
    }

    @Test
    public void testGetAssignedSocialID() {
        assertEquals("getAssignedSocialID should return the assigned social ID of the volunteer", 10, volunteer.getAssignedSocialID());
    }
    
    @Test
    public void testSetAssignedSocialID() {
        int newID = volunteer.generateSocialID();
        volunteer.setAssignedSocialID(newID);
        assertEquals("setAssignedSocialID should update the assigned social ID of the volunteer", newID, volunteer.getAssignedSocialID());
    }
    
}
