/**
 * @author David Nguyen <a href="mailto:dangducduy.nguyen@ucalgary.ca">
 * dangducduy.nguyen@ucalgary.ca
 * @version 1.1
 * @since 1.0
 */
package edu.ucalgary.oop;


import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private final String relationshipTo = "sibling";
    private final FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);
    
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
	
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo should update the relationship", newRelationship, testFamilyRelationObject.getRelationshipTo());
    }

    @Test
    public void testtwoFamilyRelationsEqual() {
        FamilyRelation sameFamilyRelation = new FamilyRelation(personOne, relationshipTo, personTwo);
        assertEquals("Two FamilyRelation objects with the same attributes should be equal", testFamilyRelationObject, sameFamilyRelation);
    }

    @Test
    public void testNoDuplicateRelationships() {
        FamilyRelation duplicateRelation = new FamilyRelation(personOne, relationshipTo, personTwo);
        assertNotSame("No duplicate relationships should be allowed", testFamilyRelationObject, duplicateRelation);
    }

    @Test
    public void testEnforceRelationshipSeries() {
        DisasterVictim personThree = new DisasterVictim("Third Person", "2024-05-23");
        FamilyRelation siblingRelation = new FamilyRelation(personOne, "sibling", personThree);
        FamilyRelation indirectRelation = new FamilyRelation(personOne, "sibling", personThree);
        assertEquals("Relationship series should be enforced", indirectRelation, siblingRelation);
    }
}
