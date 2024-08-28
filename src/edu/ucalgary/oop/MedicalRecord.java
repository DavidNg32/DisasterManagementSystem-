package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;
    /**
     * Constructs a new MedicalRecord with the specified location, treatment details, and date of treatment.
     *
     * @param location the location of the medical treatment
     * @param treatmentDetails the details of the medical treatment
     * @param dateOfTreatment the date of the medical treatment
     * @throws IllegalArgumentException if the date of treatment is not in the format YYYY-MM-DD
     */
    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) throws IllegalArgumentException {
        setLocation(location);
        this.treatmentDetails = treatmentDetails;

        // Check if the treatmentDetails string matches the expected date format
        if (!isValidDateFormat(dateOfTreatment)) {
            throw new IllegalArgumentException("Invalid date format for treatment details. Expected format: YYYY-MM-DD");
        }
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Returns the location of the medical treatment.
     *
     * @return the location of the medical treatment
     */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the details of the medical treatment.
     *
     * @return the details of the medical treatment
     */
    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    /**
     * Sets the details of the medical treatment.
     *
     * @param treatmentDetails the details of the medical treatment
     * @throws IllegalArgumentException if the treatment details are not valid
     */
    public void setTreatmentDetails(String treatmentDetails) throws IllegalArgumentException {
        this.treatmentDetails = treatmentDetails;
    }
    
    public String getDateOfTreatment() {
        return dateOfTreatment;
    }
    /**
     * Sets the date of the medical treatment.
     *
     * @param dateOfTreatment the date of the medical treatment
     * @throws IllegalArgumentException if the date of treatment is not in the format YYYY-MM-DD
     */
    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException {
        // Check if the date of treatment string matches the expected date format
        if (!isValidDateFormat(dateOfTreatment)) {
            throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
        }
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Checks if a string matches the YYYY-MM-DD date format.
     *
     * @param date the string to check
     * @return true if the string matches the YYYY-MM-DD date format, false otherwise
     */
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
