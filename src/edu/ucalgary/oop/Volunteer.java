package edu.ucalgary.oop;

public class Volunteer extends DisasterVictim {
    private int ASSIGNED_SOCIAL_ID;
    static int generateSocialID() {
        int socialID = (int) (Math.random() * 10000); // generate a random number between 0 and 9999
        return socialID;
    }
    /**
     * Constructs a new Volunteer with the specified assigned social ID, first name, and entry date.
     *
     * @param ASSIGNED_SOCIAL_ID the assigned social ID of the volunteer
     * @param firstName the first name of the volunteer
     * @param ENTRY_DATE the entry date of the volunteer
     */
    public Volunteer(int ASSIGNED_SOCIAL_ID, String firstName, String ENTRY_DATE) {
        super(firstName, ENTRY_DATE);
        this.ASSIGNED_SOCIAL_ID = ASSIGNED_SOCIAL_ID;
    }
    /**
     * Returns the assigned social ID of the volunteer.
     *
     * @return the assigned social ID of the volunteer
     */
    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    /**
     * Sets the assigned social ID of the volunteer.
     *
     * @param ASSIGNED_SOCIAL_ID the assigned social ID of the volunteer
     */
    
    public void setAssignedSocialID(int ASSIGNED_SOCIAL_ID) {
        this.ASSIGNED_SOCIAL_ID = ASSIGNED_SOCIAL_ID;
    }
    
}
