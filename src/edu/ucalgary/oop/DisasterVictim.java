package edu.ucalgary.oop;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;



public class DisasterVictim {
    private static int counter = 0;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int approximateAge;
    private Set<FamilyRelation> familyConnections = new HashSet<>();
    private ArrayList<MedicalRecord> medicalRecords = new ArrayList<>();
    private ArrayList<Supply> personalBelongings;
    private final String ENTRY_DATE;
    private ArrayList<String> gender;
    private String comments;
    private ArrayList<DietaryRestrictions> dietaryRestrictions;

    /**
     * Constructs a new DisasterVictim with the specified first name and entry date.
     *
     * @param firstName the first name of the disaster victim
     * @param ENTRY_DATE the entry date of the disaster victim
     */

    public DisasterVictim(String firstName, String ENTRY_DATE) {
        this.firstName = firstName;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.gender = new ArrayList<>(); 
        this.dietaryRestrictions = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        
    }

    /**
     * Checks if the given date is in a valid format.
     *
     * @param date the date to check
     * @return true if the date is in a valid format, false otherwise
     */
    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }


    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
        this.approximateAge = -1; // Reset approximateAge
    }


    public Set<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public ArrayList<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    // The add and remove methods remain correct.

    // Correct the setters to accept Lists instead of arrays
    public void setFamilyConnections(FamilyRelation[] connections) {
        this.familyConnections.clear();
        for (FamilyRelation newRecord : connections) {
            addFamilyConnection(newRecord);
        }
    }

    public void setMedicalRecords(ArrayList<MedicalRecord> records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    public void setPersonalBelongings(ArrayList<Supply> belongings) {
        this.personalBelongings.clear();
        for (Supply newSupply : belongings) {
            addPersonalBelonging(newSupply);
        }
    }

    // Add a Supply to personalBelonging
    public void addPersonalBelonging(Supply supply) {
        personalBelongings.add(supply);
    }

    // Remove a Supply from personalBelongings, we assume it only appears once
    public void removePersonalBelonging(Supply unwantedSupply) {
        personalBelongings.remove(unwantedSupply);
    }

    public void removeFamilyConnection(FamilyRelation familyRelation){
        // Remove the family relation from the family connections array
        Set<FamilyRelation> familyConnection = this.getFamilyConnections();

        for (FamilyRelation relation : familyConnection){
            if (relation.getPersonTwo().equals(familyRelation.getPersonTwo())){ 
                familyConnection.remove(relation);
                break;
            }
        }

        // Remove the reverse relation from the other person's family connections array
        Set<FamilyRelation> familyConnectionTwo = familyRelation.getPersonTwo().getFamilyConnections();
        for (FamilyRelation relation : familyConnectionTwo){
            if (relation.getPersonTwo().equals(familyRelation.getPersonOne())){ 
                familyConnectionTwo.remove(relation);
                break;
            }
        }
    }

    /**
     * Adds a new family connection to the disaster victim.
     *
     * @param familyRelation the new family connection to add
     */
    public void addFamilyConnection(FamilyRelation familyRelation) {
        // Check if the family relation already exists
        for (FamilyRelation relation : familyConnections) {
            if (relation.equals(familyRelation)) {
                return; // If the relation already exists, return
            }
        }

        // Add the family relation to the family connections set
        familyConnections.add(familyRelation);

        // Add the reciprocal relation to the other person's family connections set
        FamilyRelation reciprocalRelation = new FamilyRelation(familyRelation.getPersonTwo(), familyRelation.getRelationshipTo(), familyRelation.getPersonOne());
        familyRelation.getPersonTwo().getFamilyConnections().add(reciprocalRelation);

        // Add relationships between all siblings
        for (FamilyRelation relation : familyConnections) {
            if (!relation.getPersonTwo().equals(familyRelation.getPersonTwo()) && relation.getRelationshipTo().equals(familyRelation.getRelationshipTo())) {
                FamilyRelation siblingRelation = new FamilyRelation(familyRelation.getPersonTwo(), relation.getRelationshipTo(), relation.getPersonTwo());
                familyRelation.getPersonTwo().getFamilyConnections().add(siblingRelation);
                relation.getPersonTwo().getFamilyConnections().add(new FamilyRelation(relation.getPersonTwo(), relation.getRelationshipTo(), familyRelation.getPersonTwo()));
            }
        }
    }


    /**
     * Adds a new medical record to the disaster victim.
     *
     * @param record the new medical record to add
     */
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addGender(String gender) throws IOException {
        ArrayList<String> validGenders = readGenderOptions();
        if (validGenders.contains(gender)) {
            this.gender.add(gender);
        } else {
            throw new IllegalArgumentException("Invalid gender. Please choose a gender from the GenderOptions.txt file.");
        }
    }

    public void setGender(ArrayList<String> genders) throws IOException {
        ArrayList<String> validGenders = readGenderOptions();
        for (String gender : genders) {
            if (!validGenders.contains(gender)) {
                throw new IllegalArgumentException("Invalid gender: " + gender + ". Please choose a gender from the GenderOptions.txt file.");
            }
        }
        this.gender = new ArrayList<>(genders);
    }

    public ArrayList<String> getGender() {
        return this.gender;
    }
    
    
    public void setDietaryRestrictions(ArrayList<DietaryRestrictions> restrictions) {
        this.dietaryRestrictions.clear();
        for (DietaryRestrictions newRestriction : restrictions) {
            addDietaryRestriction(newRestriction);
        }
    }
    
    public void addDietaryRestriction(DietaryRestrictions restriction) {
        dietaryRestrictions.add(restriction);
    }
    
    public void removeDietaryRestriction(DietaryRestrictions unwantedRestriction) {
        dietaryRestrictions.remove(unwantedRestriction);
    }

    public ArrayList<DietaryRestrictions> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }
    
    public int getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(int approximateAge) {
        this.approximateAge = approximateAge;
        this.dateOfBirth = null; // Reset dateOfBirth
    }

    ArrayList<String> readGenderOptions() throws IOException {
        Path path = Paths.get("./data/GenderOptions.txt" );
        return (ArrayList<String>) Files.readAllLines(path);
    }

    public void allocateSupply(Supply supply, Location location) {
        if (location.getSupplies().contains(supply)) {
            addPersonalBelonging(supply);
            location.removeSupply(supply);
        } else {
            System.out.println("The supply is not available at the location.");
        }
    }

    public void removeMedicalRecord(MedicalRecord testRecord) {
        medicalRecords.remove(testRecord);
    }
}





