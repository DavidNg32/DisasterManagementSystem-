package edu.ucalgary.oop;

import java.util.ArrayList;

public class InquirerInteractionLog {
    private Inquirer inquirer;
    private final ArrayList<ReliefService> interactions = new ArrayList<>();

    /**
     * Constructs a new InquirerInteractionLog with the specified inquirer.
     *
     * @param inquirer the inquirer associated with this interaction log
     */
    public InquirerInteractionLog(Inquirer inquirer) {
        this.inquirer = inquirer;
    }

    /**
     * Adds a new interaction to this interaction log.
     *
     * @param service the new interaction to add
     */
    public void addInteraction(ReliefService service) {
        interactions.add(service);
    }

    /**
     * Returns the list of interactions associated with this inquirer.
     *
     * @return the list of interactions associated with this inquirer
     */
    public ArrayList<ReliefService> getInteractions() {
        return interactions;
    }

    /**
     * Returns the inquirer associated with this interaction log.
     *
     * @return the inquirer associated with this interaction log
     */
    
    public Inquirer getInquirer() {
        return inquirer;
    }


    /**
     * Sets the inquirer associated with this interaction log.
     *
     * @param inquirer the inquirer to set
     */
    
    public void setInquirer(Inquirer inquirer) {
        this.inquirer = inquirer;
    }
}
