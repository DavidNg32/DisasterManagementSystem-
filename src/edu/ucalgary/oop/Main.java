package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class represents the main entry point of the application.
 * It handles the creation and management of the GUI and database connection.
 */

public class Main implements MainInterface{
    private final Inquirer inquirer;
    private final DisasterVictim disasterVictim;

    private DatabaseManager dbManager;

    /**
     * Constructs a new Main with the specified inquirer and disaster victim.
     * It also launches the database login GUI.
     *
     * @param inquirer the inquirer associated with this instance of the application
     * @param disasterVictim the disaster victim associated with this instance of the application
     */
    public Main(Inquirer inquirer, DisasterVictim disasterVictim) {
        this.inquirer = inquirer;
        this.disasterVictim = disasterVictim;
        launchDatabaseLoginGUI();
    }

    public void launchDatabaseLoginGUI() {
        JFrame loginFrame = new JFrame("Database Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(800, 600);
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel portLabel = new JLabel("Port:");
        JTextField portField = new JTextField();

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String port = portField.getText();
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                dbManager = new DatabaseManager("jdbc:postgresql://localhost:" + port + "/ensf380project", username, password);
                dbManager.initializeConnection();
                
                loginFrame.dispose();
                createGUI();
                
                    
                // Close the login frame and open the log GUI
                
                
            }
        });

        panel.add(portLabel);
        panel.add(portField);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        loginFrame.getContentPane().add(panel, BorderLayout.CENTER);
        loginFrame.setLocationRelativeTo(null); // This will center the frame
        loginFrame.setVisible(true);
    }

    private void createGUI() {
        JFrame frame = new JFrame("Inquirer Interface");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dbManager.close();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel logPanel = new JPanel();
        logPanel.setLayout(new GridLayout(12, 2));

        JLabel idLabel = new JLabel("id:");
        JTextField idField = new JTextField();
        JLabel inquirerIDLabel = new JLabel("inquirerID:");
        JTextField inquirerIDField = new JTextField();
        JLabel callDateLabel = new JLabel("callDate:");
        JTextField callDateField = new JTextField();
        JLabel detailsLabel = new JLabel("details:");
        JTextField detailsField = new JTextField();

        JButton submitButton = new JButton("Submit Query");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                int inquirerID = Integer.parseInt(inquirerIDField.getText());
                Date callDate = Date.valueOf(callDateField.getText());
                String details = detailsField.getText();

                logInquirerQuery(id, inquirerID, callDate, details);
            }
        });

        logPanel.add(idLabel);
        logPanel.add(idField);
        logPanel.add(inquirerIDLabel);
        logPanel.add(inquirerIDField);
        logPanel.add(callDateLabel);
        logPanel.add(callDateField);
        logPanel.add(detailsLabel);
        logPanel.add(detailsField);
        logPanel.add(submitButton);

        tabbedPane.addTab("Log Inquiry", logPanel);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 2));

        JLabel searchLabel = new JLabel("Search (Enter first name):");
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String namePart = searchField.getText();
                searchInquirer(namePart);
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        tabbedPane.addTab("Search", searchPanel);

        JLabel modeLabel = new JLabel("Mode:");
        JComboBox<String> modeComboBox = new JComboBox<>(new String[] {"Central", "Location-based"});
        modeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mode = (String) modeComboBox.getSelectedItem();
                if (mode.equals("Central")) {
                    tabbedPane.setEnabledAt(0, true);
                    tabbedPane.setEnabledAt(1, true);
                    tabbedPane.setEnabledAt(2, false);
                    tabbedPane.setSelectedIndex(0);
                } else {
                    tabbedPane.setEnabledAt(0, false);
                    tabbedPane.setEnabledAt(1, true);
                    tabbedPane.setEnabledAt(2, true);
                    tabbedPane.setSelectedIndex(1);
                }
            }
        });

        JPanel modePanel = new JPanel();
        modePanel.add(modeLabel);
        modePanel.add(modeComboBox);

        mainPanel.add(modePanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel disasterVictimPanel = new JPanel();
        disasterVictimPanel.setLayout(new GridLayout(10, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel entryDateLabel = new JLabel("Entry Date:");
        JTextField entryDateField = new JTextField();
        JLabel relationshipLabel = new JLabel("Relationship:");
        JTextField relationshipField = new JTextField();
        JLabel relativeFirstNameLabel = new JLabel("Relative First Name:");
        JTextField relativeFirstNameField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        JComboBox<String> locationComboBox = new JComboBox<>();
        ArrayList<String> locations = dbManager.getLocationsFromDatabase();
        for (String location : locations) {
            locationComboBox.addItem(location);
        }
        
        JLabel treatmentDetailsLabel = new JLabel("Treatment Details:");
        JTextField treatmentDetailsField = new JTextField();
        JLabel dateOfTreatmentLabel = new JLabel("Date of Treatment:");
        JTextField dateOfTreatmentField = new JTextField();
        // Create a JComboBox for gender selection
        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField();

        JLabel dietaryRestrictionsLabel = new JLabel("Dietary Restrictions:");
        JPanel dietaryRestrictionsPanel = new JPanel();
        dietaryRestrictionsPanel.setLayout(new BoxLayout(dietaryRestrictionsPanel, BoxLayout.Y_AXIS));
        dietaryRestrictionsPanel.add(dietaryRestrictionsLabel);


        for (DietaryRestrictions restriction : DietaryRestrictions.values()) {
            JCheckBox checkBox = new JCheckBox(restriction.name());
            dietaryRestrictionsPanel.add(checkBox);
        }
        
        JScrollPane dietaryRestrictionsScrollPane = new JScrollPane(dietaryRestrictionsPanel);
        dietaryRestrictionsScrollPane.setPreferredSize(new Dimension(500, 800)); 




        JButton enterDisasterVictimButton = new JButton("Submit");
        enterDisasterVictimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String ENTRY_DATE = entryDateField.getText();
                enterDisasterVictimInfo(firstName, ENTRY_DATE);
                String relationship = relationshipField.getText();
                String relativeFirstName = relativeFirstNameField.getText();
                enterDisasterVictimRelationship(firstName, ENTRY_DATE, relationship, relativeFirstName);
                String treatmentDetails = treatmentDetailsField.getText();
                String dateOfTreatment = dateOfTreatmentField.getText();
                String location = (String) locationComboBox.getSelectedItem();
                String[] locationParts = location.split(" - ");
                String locationName = locationParts[0];
                String locationAddress = locationParts[1];
                enterDisasterVictimMedicalRecord(firstName, ENTRY_DATE, new MedicalRecord(new Location(locationName,  
                        locationAddress), treatmentDetails, dateOfTreatment), locationName, locationAddress, 
                        treatmentDetails, dateOfTreatment);

                JOptionPane.showMessageDialog(frame, "Disaster Victim logging was successful.");
            }
        });

        disasterVictimPanel.add(firstNameLabel);
        disasterVictimPanel.add(firstNameField);
        disasterVictimPanel.add(entryDateLabel);
        disasterVictimPanel.add(entryDateField);
        disasterVictimPanel.add(relationshipLabel);
        disasterVictimPanel.add(relationshipField);
        disasterVictimPanel.add(relativeFirstNameLabel);
        disasterVictimPanel.add(relativeFirstNameField);
        disasterVictimPanel.add(locationLabel);
        disasterVictimPanel.add(locationComboBox);
        disasterVictimPanel.add(treatmentDetailsLabel);
        disasterVictimPanel.add(treatmentDetailsField);
        disasterVictimPanel.add(dateOfTreatmentLabel);
        disasterVictimPanel.add(dateOfTreatmentField);
        disasterVictimPanel.add(genderLabel);
        disasterVictimPanel.add(genderField);
        disasterVictimPanel.add(dietaryRestrictionsLabel);
        disasterVictimPanel.add(dietaryRestrictionsScrollPane);
        disasterVictimPanel.add(enterDisasterVictimButton);
        
        tabbedPane.addTab("Log DisasterVictim", disasterVictimPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
        
        
    }


    /**
     * Logs a new inquirer query to the database.
     *
     * @param id the id of the inquirer
     * @param inquirer the inquirer's id
     * @param callDate the date of the call
     * @param details the details of the call
     */
    @Override
    public void logInquirerQuery(int id, int inquirer, Date callDate, String details) {
        dbManager.logNewInquirer(id, inquirer, callDate, details);
    }

    @Override
    public void searchInquirer(String namePart){
        dbManager.searchNameFromTable(namePart, "inquirer");
    }

    /**
     * Enters a new disaster victim's information into the database.
     *
     * @param firstName the first name of the disaster victim
     * @param ENTRY_DATE the entry date of the disaster victim
     */
    @Override
    public void enterDisasterVictimInfo(String firstName, String ENTRY_DATE ) {
        DisasterVictim newVictim = new DisasterVictim(firstName, ENTRY_DATE);
       
    }
    /**
     * Enters a new disaster victim's relationship into the database.
     *
     * @param firstName the first name of the disaster victim
     * @param ENTRY_DATE the entry date of the disaster victim
     * @param relationship the relationship of the disaster victim
     * @param relativeFirstName the first name of the relative
     */
    @Override
    public void enterDisasterVictimRelationship(String firstName, String ENTRY_DATE, String relationship,
                                                String relativeFirstName) {
        DisasterVictim victim = new DisasterVictim(firstName, ENTRY_DATE);
        DisasterVictim relative = new DisasterVictim(relativeFirstName, ENTRY_DATE);
        FamilyRelation relation = new FamilyRelation(victim, relationship, relative);
        victim.addFamilyConnection(relation);
    }
    /**
     * Enters a new disaster victim's medical record into the database.
     *
     * @param firstName the first name of the disaster victim
     * @param ENTRY_DATE the entry date of the disaster victim
     * @param medicalRecord the medical record of the disaster victim
     * @param locationName the name of the location
     * @param locationAddress the address of the location
     * @param treatmentDetails the details of the treatment
     * @param dateOfTreatment the date of the treatment
     */
    @Override
    public void enterDisasterVictimMedicalRecord(String firstName, String ENTRY_DATE, MedicalRecord medicalRecord, 
                                                 String locationName, String locationAddress, String treatmentDetails, 
                                                 String dateOfTreatment) {
        DisasterVictim victim = new DisasterVictim(firstName, ENTRY_DATE);
        Location location = new Location(locationName, locationAddress);
        MedicalRecord record = new MedicalRecord(location, treatmentDetails, dateOfTreatment);
        victim.addMedicalRecord(record);
        
    }
    /**
     * The main method of the application.
     *
     * @param args the command-line arguments
     */

    public static void main(String[] args) {
        Main main = new Main(new Inquirer("John", "Doe", "123-456-7890", "Inquirer for disaster victims"),
                new DisasterVictim("Jane", "2024-01-31"));
        
    }
}
