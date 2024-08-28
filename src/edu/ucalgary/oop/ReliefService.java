package edu.ucalgary.oop;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReliefService  {
    private final InquirerInteractionLog interactionLog;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;
    
    public static final List<DisasterVictim> disasterVictims = new ArrayList<>();

    /**
     * Constructs a new ReliefService with the specified interaction log, missing person, date of inquiry, information provided, and last known location.
     *
     * @param interactionLog the interaction log associated with this relief service
     * @param missingPerson the missing person associated with this relief service
     * @param dateOfInquiry the date of the inquiry
     * @param infoProvided the information provided during the inquiry
     * @param lastKnownLocation the last known location of the missing person
     */
    public ReliefService(InquirerInteractionLog interactionLog, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided, Location lastKnownLocation) {
        this.interactionLog = new InquirerInteractionLog(interactionLog.getInquirer());
        this.missingPerson = missingPerson;
        setDateOfInquiry(dateOfInquiry);
        this.infoProvided = infoProvided;
        this.lastKnownLocation = lastKnownLocation;
    }

    // Getter and setter for inquirer
    public Inquirer getInquirer() {
        return interactionLog.getInquirer();
    }

    public void setInquirer(Inquirer inquirer) {
        interactionLog.setInquirer(inquirer);
    }

    // Getter and setter for missingPerson
    public DisasterVictim getMissingPerson() {
        return missingPerson;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        this.missingPerson = missingPerson;
    }

    // Getter and setter for dateOfInquiry
    public String getDateOfInquiry() {
        return dateOfInquiry;
    }

    public void setDateOfInquiry(String dateOfInquiry) {
        // Check if the dateOfInquiry string matches the expected date format
        if (!isValidDateFormat(dateOfInquiry)) {
            throw new IllegalArgumentException("Invalid date format for date of inquiry. Expected format: YYYY-MM-DD");
        }
        this.dateOfInquiry = dateOfInquiry;
    }

    // Getter and setter for infoProvided
    public String getInfoProvided() {
        return infoProvided;
    }

    public void setInfoProvided(String infoProvided) {
        this.infoProvided = infoProvided;
    }

    // Getter and setter for lastKnownLocation
    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }

    // Helper method to check if a string matches the YYYY-MM-DD date format
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getLogDetails() {
       return "Inquirer: " + interactionLog.getInquirer().getFirstName() + 
           ", Missing Person: " + missingPerson.getFirstName() + 
           ", Date of Inquiry: " + dateOfInquiry + 
           ", Info Provided: " + infoProvided + 
           ", Last Known Location: " + lastKnownLocation.getName();
    }

    public InquirerInteractionLog getInteractionLog() {
        return interactionLog;
    }

    
}
