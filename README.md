# Hospital Management System

## Project Description
This is a simple desktop-based Hospital Management System developed using Java Swing and JDBC for database connectivity. The application aims to streamline various operations within a hospital, including patient registration, viewing patient and ambulance information, and managing rooms.

## Features
* **Reception Dashboard:** A main interface for navigating to different functionalities of the system.
* **New Patient Registration:** Allows staff to register new patients with detailed information such as ID type, number, name, gender, contact details, medical history, room assignment, and deposit.
* **View All Patient Information:** Displays a comprehensive list of all registered patients in a scrollable table, with clear headers and organized data.
* **Ambulance Information:** Provides a view of available ambulances, including driver details, contact numbers, car information, plate numbers, availability, location, status, last service date, capacity, and type.
* **Room Management:** Displays room numbers, their availability, price, and bed type.
* **User-Friendly Interface:** Built with Java Swing for an interactive graphical user interface.
* **Database Integration:** Uses JDBC to connect with a relational database (e.g., MySQL, PostgreSQL, or Access, depending on your `Conn` class implementation) to store and retrieve patient, room, and ambulance data.

## Technologies Used
* **Java (JDK 8 or higher)**
* **Java Swing** (for GUI)
* **JDBC** (Java Database Connectivity)
* **SQL Database** (e.g., MySQL, PostgreSQL, SQL Server, or SQLite - *Assumes you have a `Conn.java` class handling this connection*)

## Setup and Installation

### Prerequisites
* Java Development Kit (JDK) 8 or higher installed.
* A compatible SQL database (e.g., MySQL) installed and running.
* A JDBC driver for your chosen database (e.g., `mysql-connector-java.jar` for MySQL).

### Database Setup
1.  **Create Database:** Create a database in your SQL server (e.g., `hospital_db`).
2.  **Create Tables:** Execute SQL scripts to create the necessary tables: `Patient_Info`, `Ambulance`, `Room`, etc. The schema is based on the application's data requirements.
    * **Example `Patient_Info` Table Structure (simplified):**
        ```sql
        CREATE TABLE Patient_Info (
            ID VARCHAR(50),
            Number VARCHAR(50) PRIMARY KEY,
            Name VARCHAR(100),
            "Father's Name" VARCHAR(100),
            DOB DATE,
            Gender VARCHAR(10),
            Phone VARCHAR(20),
            Email VARCHAR(100),
            Address VARCHAR(255),
            Patient_Disease VARCHAR(100),
            Room_Number VARCHAR(10),
            Doctor_Assigned VARCHAR(100),
            Admission_Type VARCHAR(50),
            Marital_Status VARCHAR(50),
            Emergency_Contact VARCHAR(20),
            Blood_Group VARCHAR(5),
            Time DATETIME,
            Deposit DOUBLE
        );
        ```
    * **Example `Ambulance` Table Structure (simplified):**
        ```sql
        CREATE TABLE Ambulance (
            ID INT PRIMARY KEY AUTO_INCREMENT,
            Driver_Name VARCHAR(100),
            Gender VARCHAR(10),
            Phone_Number VARCHAR(20),
            Car_Name VARCHAR(100),
            Plate_Number VARCHAR(50),
            Available VARCHAR(10),
            Location VARCHAR(100),
            Status VARCHAR(50),
            Last_Service_Date DATE,
            Capacity INT,
            Ambulance_Type VARCHAR(50)
        );
        ```
    * **Example `Room` Table Structure (simplified):**
        ```sql
        CREATE TABLE Room (
            room_no VARCHAR(10) PRIMARY KEY,
            availability VARCHAR(20),
            price DOUBLE,
            Room_Type VARCHAR(50)
        );
        -- Insert some initial data
        INSERT INTO Room (room_no, availability, price, Room_Type) VALUES ('101', 'Available', 500.00, 'ICU');
        INSERT INTO Room (room_no, availability, price, Room_Type) VALUES ('201', 'Available', 1500.00, 'General Ward');
        ```
3.  **Configure Database Connection:** Ensure your `hospital.Conn` class is properly configured with your database URL, username, and password.

### Running the Application
1.  **Clone/Download:** Get the project files.
2.  **IDE Setup:** Open the project in your favorite Java IDE (IntelliJ IDEA, Eclipse, VS Code with Java extensions).
3.  **Add JDBC Driver:** Add your JDBC driver JAR file to the project's build path.
4.  **Compile and Run:** Compile the Java source files.
5.  **Execute `Reception.java` (if it's your main entry point) or specific UI classes like `ALL_Patient_Info.java` or `Ambulance.java` directly for testing.**

## Usage
* Navigate through the system using the buttons on the main `Reception` dashboard.
* Click "Add New Patient" to open the patient registration form and fill in the details.
* Click "Patient Info" to view all registered patient records in a scrollable table.
* Click "Hospital Ambulance" to see the list of ambulances and their current status.
* Click "Room" to view room availability and pricing.
* Use the "BACK" button in each information display window to return to the previous screen.

## Screenshots

| Feature | Screenshot |
|---|---|
| Reception Dashboard | ![Reception Dashboard](image_062e74.png) |
| Patient Information Display (Before Fix) | ![Patient Info Display Before Fix](image_0641d4.png) |
| Patient Information Display (After Fix) | ![Patient Info Display After Fix](image_063214.png) |
| Ambulance Information Display | ![Ambulance Info Display](image_078f73.png) |
| Room Management Display | ![Room Management Display](image_087431.png) |
| Update Screen (Partial) | ![Update Screen Partial](image_169b55.png) |



