# DisasterManagementSystem-
A Java-based application designed to search and log a inquirers' informations and add victims information from a disaster into the database.

- Main:
o The main class is designed to run, upon running you will get an GUI interface where you can enter your: (port, username, password) to log in into the database.
o After you are logged in, you can select the mode (Central worker or Location-based worker) to gain access to logging inquiry and searching inquiries if you are central worker and you can search inquiry and log disaster victims if you are location-based.
o The logging inquiries will require an ID, inquirer ID, callDate which has to follow the format (yyyy-mm-dd) and details then the information will be logged into a database
o The searching inquiries works by entering an inquirer first name, you can enter part of it and you can have access to the inquiries log to potentially link to disaster victims
o The logging disaster victim you will need to enter the necessary information, pick a location from the drop-down box and select
the victim’s dietary restriction using a check
box (scroll down for more options).
o The database will be disconnected and closed after you exit the program

- PROJECT.SQL:
o The SQL has been altered to add Location table and the code necessary to fill the table up with location name, location address, location ID.
o The SQL ensf380 project has given the username: oop and password: ucalgary the owner access so you can access the database and table with that login info.
