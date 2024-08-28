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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VictimTest {
    private final boolean rescueStatus = false;
    private final String injuryDetails = "Broken arm";
    private final Set<DisasterVictim> familyMembers = new HashSet<>();
    
    @Before
    public void setUp() {
        familyMembers.add(new DisasterVictim("John Smith", "2024-01-01"));
        familyMembers.add(new DisasterVictim("Jane Smith", "2024-01-01"));
    }
    
   @Test
    public void testObjectCreation() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        assertNotNull("Constructor should create a non-null Victim object", victim);
        assertEquals("Constructor should set the first name correctly", "John", victim.getFirstName());
        assertEquals("Constructor should set the entry date correctly", "2004-11-11", victim.getEntryDate());
        assertEquals("Constructor should set the rescue status correctly", rescueStatus, victim.getRescueStatus());
        assertEquals("Constructor should set the injury details correctly", injuryDetails, victim.getInjuryDetails());
        
    }
    @Test
    public void testInheritanceFromDisasterVictim() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        assertNotNull("Constructor should create a non-null Victim object", victim);
        assertTrue("Victim should be an instance of DisasterVictim", victim instanceof DisasterVictim);
    }
    
    @Test
    public void testSetRescueStatus() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        boolean newRescueStatus = true;
        victim.setRescueStatus(newRescueStatus);
        assertEquals("setRescueStatus should update the rescue status of the victim", newRescueStatus, victim.getRescueStatus());
    }
    
   @Test
    public void testSetInjuryDetails() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        String newInjuryDetails = "Broken leg";
        victim.setInjuryDetails(newInjuryDetails);
        assertEquals("setInjuryDetails should update the injury details of the victim", newInjuryDetails, victim.getInjuryDetails());
    }
    
    @Test
    public void testSetFamilyMembers() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        Set<DisasterVictim> newFamilyMembers = new HashSet<>();
        newFamilyMembers.add(new DisasterVictim("John Smith", "2024-01-01"));
        newFamilyMembers.add(new DisasterVictim("Jane Smith", "2024-01-01"));
        victim.setFamilyMembers(newFamilyMembers);
        assertEquals("setFamilyMembers should update the family members of the victim", newFamilyMembers, victim.getFamilyMembers());
    }
    
    
    @Test
    public void testRemoveFamilyMember() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        DisasterVictim familyMemberToRemove = new DisasterVictim("John Smith", "2024-01-01");
        victim.removeFamilyMember(familyMemberToRemove);
        assertFalse("removeFamilyMember should remove a family member", victim.getFamilyMembers().contains(familyMemberToRemove));
    }
    
    @Test
    public void testGetRescueStatus() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        assertEquals("getRescueStatus should return the correct rescue status", rescueStatus, victim.getRescueStatus());
    }
    
    @Test
    public void testGetInjuryDetails() {
        Victim victim = new Victim("John", "2004-11-11", rescueStatus, injuryDetails, familyMembers);
        assertEquals("getInjuryDetails should return the correct injury details", injuryDetails, victim.getInjuryDetails());
    }

    @Test
    public void testGetFamilyMembers() {
        Victim victim = new Victim("John", "2004-11-11", false, "Broken arm", new HashSet<>());
        DisasterVictim familyMemberOne = new DisasterVictim("John Smith", "2024-01-01");
        DisasterVictim familyMemberTwo = new DisasterVictim("Jane Smith", "2024-01-01");
        DisasterVictim familyMemberThree = new DisasterVictim("Bob Smith", "2024-01-01");
        victim.addFamilyMember(familyMemberOne);
        victim.addFamilyMember(familyMemberTwo);
        victim.addFamilyMember(familyMemberThree);
        Set<DisasterVictim> expectedFamilyMembers = new HashSet<>(Arrays.asList(familyMemberOne, familyMemberTwo, familyMemberThree));
        assertEquals("getFamilyMembers should return the correct family members", expectedFamilyMembers, victim.getFamilyMembers());
    }
    
    
    
    
    
}
