package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Victim extends DisasterVictim {
    private boolean rescueStatus;
    private String injuryDetails;
    private Set<DisasterVictim> familyMembers;

    /**
     * Constructs a new Victim with the specified first name, entry date, rescue status, injury details, and family members.
     *
     * @param firstName the first name of the victim
     * @param ENTRY_DATE the entry date of the victim
     * @param rescueStatus the rescue status of the victim
     * @param injuryDetails the details of the victim's injuries
     * @param familyMembers the family members of the victim
     */
    
    public Victim(String firstName, String ENTRY_DATE, boolean rescueStatus, String injuryDetails, Set<DisasterVictim> familyMembers) {
        super(firstName, ENTRY_DATE);
        this.rescueStatus = rescueStatus;
        this.injuryDetails = injuryDetails;
        this.familyMembers = new HashSet<>();
    }
    
    public boolean getRescueStatus() {
        return rescueStatus;
    }
    
    public void setRescueStatus(boolean rescueStatus) {
        this.rescueStatus = rescueStatus;
    }
    
    public String getInjuryDetails() {
        return injuryDetails;
    }
    
    public void setInjuryDetails(String injuryDetails) {
        this.injuryDetails = injuryDetails;
    }

    public Set<DisasterVictim> getFamilyMembers() {
        return this.familyMembers;
    }
    
    public void setFamilyMembers(Set<DisasterVictim> familyMembers) {
        this.familyMembers.clear();
        for (DisasterVictim newfamilyMembers : familyMembers) {
            addFamilyMember(newfamilyMembers);
        }
    }
    
    public void addFamilyMember(DisasterVictim familyMember) {
        familyMembers.add(familyMember);
    }
    
    public void removeFamilyMember(DisasterVictim familyMember) {
        familyMembers.remove(familyMember);
    }
}
