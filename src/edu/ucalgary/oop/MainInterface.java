package edu.ucalgary.oop;

import java.sql.Date;
import java.util.List;

public interface MainInterface {
    void logInquirerQuery(int id, int inquirer, Date callDate, String details);
    void searchInquirer(String namePart);
    void enterDisasterVictimInfo(String firstName, String ENTRY_DATE);
    void enterDisasterVictimRelationship(String firstName, String ENTRY_DATE, String relationship,
                                         String relativeFirstName);
    void enterDisasterVictimMedicalRecord(String firstName, String ENTRY_DATE, MedicalRecord medicalRecord,
                                                 String locationName, String locationAddress, String treatmentDetails,
                                                 String dateOfTreatment);
    
}
