package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Inquirer {
    private final String FIRST_NAME;
    private final String LAST_NAME;
    private final String INFO;
    private final String SERVICES_PHONE;
    private final List<DisasterVictim> disasterVictims; // List of DisasterVictim instances

    /**
     * Constructs a new Inquirer with the specified first name, last name, phone number, and information.
     *
     * @param firstName the first name of the inquirer
     * @param lastName the last name of the inquirer
     * @param phone the phone number of the inquirer's services
     * @param info the information of the inquirer
     */
    public Inquirer(String firstName, String lastName, String phone, String info) {
        this.FIRST_NAME = firstName;
        this.LAST_NAME = lastName;
        this.SERVICES_PHONE = phone;
        this.INFO = info;
        this.disasterVictims = new ArrayList<>(); // Initialize the list
        

    }


    /**
     * Returns the first name of the inquirer.
     *
     * @return the first name of the inquirer
     */
    public String getFirstName() { return this.FIRST_NAME; }

    /**
     * Returns the last name of the inquirer.
     *
     * @return the last name of the inquirer
     */
    public String getLastName() { return this.LAST_NAME; }

    /**
     * Returns the phone number of the inquirer's services.
     *
     * @return the phone number of the inquirer's services
     */
    public String getServicesPhoneNum() { return this.SERVICES_PHONE; }

    /**
     * Returns the information of the inquirer.
     *
     * @return the information of the inquirer
     */
    public String getInfo() { return this.INFO; }
    
    
}
