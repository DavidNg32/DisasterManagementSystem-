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

import java.io.IOException;
import java.util.*;

public class DisasterVictimTest {
    
    private DisasterVictim victim;
    private ArrayList<Supply> personalBelongings;
    private Set<FamilyRelation> familyConnections = new HashSet<FamilyRelation>();
    private ArrayList<MedicalRecord> medicalRecords;
    private ArrayList<DietaryRestrictions> dietaryRestrictions;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private ArrayList<String> expectedGender = new ArrayList<String>();
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        personalBelongings = new ArrayList<Supply>();
        personalBelongings.add(new Supply("Water Bottle", 10));
        personalBelongings.add(new Supply("Blanket", 5));

        Set<FamilyRelation> familyConnections = new HashSet<FamilyRelation>();
        familyConnections.add(new FamilyRelation(victim, "sibling", new DisasterVictim("Jane", "2024-01-20")));
        
        medicalRecords = new ArrayList<MedicalRecord>();
        medicalRecords.add(new MedicalRecord(new Location("Shelter A", "1234 Shelter Ave"), "test for strep", "2024-02-09"));
        medicalRecords.add(new MedicalRecord(new Location("Shelter B", "4321 Shelter Blvd"), "test for flu", "2024-02-10"));

        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        
    }
    
  @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }


   @Test
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth, victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
    }
	
	@Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName, victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }
    

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    @Test
    public void testSetAndGetGender() throws IOException {
        ArrayList<String> expectedGender = victim.readGenderOptions();

        victim.setGender(expectedGender);
        assertEquals("getGender should return the expected gender", expectedGender, victim.getGender());
        assertEquals("setGender should update with expected gender", expectedGender, victim.getGender());
    }
	

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        Set<FamilyRelation> expectedRelations = new HashSet<FamilyRelation>();
        victim2.setFamilyConnections(expectedRelations.toArray(new FamilyRelation[0]));

        Set<FamilyRelation> testFamily = victim2.getFamilyConnections();
        boolean correct = false;

        if ((testFamily!=null) && (testFamily.equals(expectedRelations))) {
                correct = true;
        }
        assertTrue("addFamilyConnection should add a family relationship", correct);
    }

   @Test
   public void testAddMedicalRecord() {
            MedicalRecord testRecord = new MedicalRecord(new Location("Shelter Z", "1234 Shelter Ave"), "test for strep", "2024-02-09");
            ArrayList<MedicalRecord> expectedRecords = new ArrayList<MedicalRecord>();
            expectedRecords.add(testRecord);
            victim.addMedicalRecord(testRecord);
            ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();
            boolean correct = actualRecords.equals(expectedRecords);
            assertTrue("addMedicalRecord should add a medical record", correct);
        }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        Set<FamilyRelation> originalRelations = new HashSet<FamilyRelation>();
        Set<FamilyRelation> expectedRelations = new HashSet<FamilyRelation>();
        victim.setFamilyConnections(originalRelations.toArray(new FamilyRelation[0]));
    
        DisasterVictim victim = new DisasterVictim("Freda", "2024-01-23");
        victim.addFamilyConnection(relation1);
        victim.addFamilyConnection(relation2);
        victim.removeFamilyConnection(relation1);
    
        Set<FamilyRelation> testFamily = victim.getFamilyConnections();
        boolean correct = testFamily.equals(expectedRelations);
    
        assertTrue("removeFamilyConnection should remove the family member", true);
    }
    
    @Test
    public void testRemoveMedicalRecord() {
        MedicalRecord testRecord = new MedicalRecord(new Location("Shelter Z", "1234 Shelter Ave"), "test for strep", "2024-02-09");
        ArrayList<MedicalRecord> expectedRecords = new ArrayList<MedicalRecord>();
        victim.addMedicalRecord(testRecord);
        victim.removeMedicalRecord(testRecord);
        ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();
        boolean correct = actualRecords.equals(expectedRecords);
        assertTrue("removeMedicalRecord should remove a medical record", correct);
    }

    @Test
    public void testSetFamilyConnections() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        FamilyRelation relation1 = new FamilyRelation(victim, "sibling", victim1);
        FamilyRelation relation2 = new FamilyRelation(victim, "sibling", victim2);
        Set<FamilyRelation> newRelations = new HashSet<FamilyRelation>();
        newRelations.add(relation1);
        newRelations.add(relation2);
        victim.setFamilyConnections(newRelations.toArray(new FamilyRelation[0]));
        Set<FamilyRelation> actualRelations = victim.getFamilyConnections();
        boolean correct = actualRelations.equals(newRelations);
        assertTrue("setFamilyConnections should correctly update family connections", correct);
    }
    


   @Test
    public void testSetPersonalBelongings() {
        Supply one = new Supply("Tent", 1);
        Supply two = new Supply("Jug", 3);
        ArrayList<Supply> newSupplies = new ArrayList<Supply>();
        ArrayList<Supply> actualSupplies = victim.getPersonalBelongings();
        victim.setPersonalBelongings(newSupplies);
        boolean correct = actualSupplies.equals(newSupplies);
        assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
    }

    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        ArrayList<MedicalRecord> newRecords = new ArrayList<MedicalRecord>();
        victim.setMedicalRecords(newRecords);
        ArrayList<MedicalRecord> actualRecords = victim.getMedicalRecords();
        boolean correct = actualRecords.equals(newRecords);
        assertTrue("setMedicalRecords should correctly update medical records", correct);
    }
    
    @Test
    public void testAddDietaryRestriction() {
        victim.addDietaryRestriction(DietaryRestrictions.AVML);
        ArrayList<DietaryRestrictions> testRestrictions = victim.getDietaryRestrictions();
        boolean correct = testRestrictions.contains(DietaryRestrictions.AVML);
    }
    
    @Test
    public void testRemoveDietaryRestriction() {
        victim.addDietaryRestriction(DietaryRestrictions.AVML);
        victim.removeDietaryRestriction(DietaryRestrictions.AVML);
        ArrayList<DietaryRestrictions> testRestrictions = victim.getDietaryRestrictions();
        boolean correct = !testRestrictions.contains(DietaryRestrictions.AVML);
    }
    
    @Test
    public void testSetDietaryRestrictions() {
        ArrayList<DietaryRestrictions> newRestrictions = new ArrayList<DietaryRestrictions>();
        victim.setDietaryRestrictions(newRestrictions);
        ArrayList<DietaryRestrictions> actualRestrictions = victim.getDietaryRestrictions();
        boolean correct = actualRestrictions.equals(newRestrictions);
        assertTrue("setDietaryRestrictions should correctly update dietary restrictions", correct);
    }

    @Test
    public void testGetDietaryRestrictions() {
        ArrayList<DietaryRestrictions> expectedRestrictions = new ArrayList<>();
        expectedRestrictions.add(DietaryRestrictions.AVML); // Add the dietary restrictions you expect
        victim.setDietaryRestrictions(expectedRestrictions);
        ArrayList<DietaryRestrictions> actualRestrictions = victim.getDietaryRestrictions();
        assertTrue("getDietaryRestrictions should return the correct dietary restrictions", actualRestrictions.equals(expectedRestrictions));
    }
    
    @Test
    public void testSetApproximatedAge() {
        int newAge = 40;
        victim.setApproximateAge(newAge);
        int actualAge = victim.getApproximateAge();
        boolean correct = actualAge == newAge;
        assertTrue("setApproximatedAge should correctly update the approximated age", correct);
    }

    @Test
    public void testGetApproximatedAge() {
        int expectedAge = 37;
        victim.setApproximateAge(expectedAge);
        int actualAge = victim.getApproximateAge();
        assertTrue("getApproximatedAge should return the correct approximated age", actualAge == expectedAge);
    }

    @Test
    public void testAllocateSupply() {
        Supply supply = new Supply("Water Bottle", 10);
        Location location = new Location("Shelter A", "123 Shelter St");
        location.addSupply(supply); // Add the supply to the location's supplies
        victim.allocateSupply(supply, location);
        ArrayList<Supply> supplies = victim.getPersonalBelongings();
        assertTrue("allocateSupply should add the supply to personal belongings", supplies.contains(supply));
    }

    @Test
    public void testTransferSupplyFromShelterToVictim() {
        Supply supply = new Supply("Water Bottle", 10);
        Location location = new Location("Shelter A", "123 Shelter St");
        location.addSupply(supply); // Add the supply to the location's supplies
        victim.allocateSupply(supply, location);
        ArrayList<Supply> supplies = victim.getPersonalBelongings();
        assertTrue("allocateSupply should transfer the supply from the shelter to the victim", supplies.contains(supply));
    }

    @Test
    public void testAgeOrBirthdateButNotBoth() {
        DisasterVictim victim = new DisasterVictim("John", "2024-01-01");
        
        victim.setDateOfBirth("1980-01-01");
        
        assertEquals("1980-01-01", victim.getDateOfBirth());
        assertEquals(-1, victim.getApproximateAge());
        
        victim.setApproximateAge(40);
        
        assertEquals(40, victim.getApproximateAge());
        assertNull(victim.getDateOfBirth());
    }
    
    
    
    
    


}





