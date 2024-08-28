package edu.ucalgary.oop;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class manages the database connection and operations.
 */

public class DatabaseManager {

    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private Connection dbConnect;
    private ResultSet results;

    /**
     * Constructs a new DatabaseManager with the specified URL, username, and password.
     *
     * @param url the database URL
     * @param user the username for the database
     * @param pw the password for the database
     */
    public DatabaseManager(String url, String user, String pw){

        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }


    //Must create a connection to the database, no arguments, no return value    
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String getDburl(){
        return this.DBURL;
    }

    String getUsername(){
        return this.USERNAME;
    }

    String getPassword(){
        return this.PASSWORD;
    }


    /**
     * Logs a new inquirer into the database.
     *
     * @param id the ID of the inquirer
     * @param inquirer the inquirer
     * @param callDate the date of the call
     * @param details the details of the call
     */

    public void logNewInquirer(int id, int inquirer, Date callDate, String details){
        
        if(!validateInquirer(inquirer)){
            System.out.println("Invalid inquirer ID");
            return;
        }
        
        try {
            String query = "INSERT INTO inquiry_log (id, inquirer, callDate, details) VALUES (?, ?, ?, ?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setInt(1, id);
            myStmt.setInt(2, inquirer);
            myStmt.setDate(3, callDate);
            myStmt.setString(4, details);

            int rowsAffected = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            String message = "Inquirer query logged successfully";
            JOptionPane.showMessageDialog(null, message);

            myStmt.close();
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Validates the inquirer ID.
     *
     * @param inquirerID the ID of the inquirer
     * @return true if the inquirer ID is valid, false otherwise
     */
    
    
    private boolean validateInquirer(int inquirerID){

        boolean validInquirer = false;

        try {
            Statement myStmt = dbConnect.createStatement();

            // Execute SQL query
            results = myStmt.executeQuery("SELECT * FROM inquirer");

            // Process the results set
            while (results.next()){
                if(results.getInt("id") == inquirerID)
                    validInquirer = true;
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return validInquirer;

    }

    /**
     * Searches for a name in the specified table.
     *
     * @param namePart the part of the name to search for
     * @param tableName the name of the table to search in
     */
    

    public void searchNameFromTable(String namePart, String tableName) {
        String lowerCaseNamePart = namePart.toLowerCase();
        StringBuilder messageBuilder = new StringBuilder();
        try {
            // Prepare a SQL query
            String query = "SELECT inquirer.*, inquiry_log.callDate, inquiry_log.details FROM " + tableName +
                    " JOIN inquiry_log ON inquirer.id = inquiry_log.inquirer WHERE LOWER(firstName) LIKE ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, "%" + lowerCaseNamePart + "%");

            // Execute the SQL query and store the results
            ResultSet rs = myStmt.executeQuery();

            // Iterate over the results
            while (rs.next()) {
                // Append the results to the message builder
                messageBuilder.append("ID: ").append(rs.getInt("id"))
                        .append("\nFirst Name: ").append(rs.getString("firstName"))
                        .append("\nLast Name: ").append(rs.getString("lastName"))
                        .append("\nPhone Number: ").append(rs.getString("phoneNumber"))
                        .append("\nCall Date: ").append(rs.getDate("callDate"))
                        .append("\nDetails: ").append(rs.getString("details"))
                        .append("\n------------------------\n");
            }

            // Close the statement and result set
            myStmt.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Create a JTextArea to display the message with a larger font
        JTextArea textArea = new JTextArea(messageBuilder.toString());
        textArea.setFont(new Font("monospaced", Font.PLAIN, 18));
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, textArea, "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Retrieves a list of locations from the database.
     *
     * @return a list of locations
     */

    public ArrayList<String> getLocationsFromDatabase() {
        ArrayList<String> locations = new ArrayList<>();
        try {
            String query = "SELECT LocationName, LocationAddress FROM LOCATION";
            PreparedStatement statement = dbConnect.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String locationName = resultSet.getString("LocationName");
                String locationAddress = resultSet.getString("LocationAddress");
                locations.add(locationName + " - " + locationAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
    
    
    public void close() {
        try {
            if (results != null) {
                results.close();
                System.out.println("Results closed");
            }
            if (dbConnect != null) {
                dbConnect.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}


