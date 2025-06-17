package hospital;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {
    JComboBox<String> comboBox, maritalStatus, bloodGroup, admissionType;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposit, fatherName, dob, phone, email, address, emergencyContact, doctorAssigned;
    JRadioButton r1, r2;
    Choice c1; 
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 640);
        panel.setBackground(new Color(98, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(270, 10, 400, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelName);

        // All labels and their Y positions are set up correctly relative to each other
        String[] labels = {"ID:", "Number:", "Name:", "Father's Name:", "DOB (yyyy-mm-dd):", "Gender:",
                "Phone:", "Email:", "Address:", "Disease:", "Room:", "Doctor Assigned:", "Admission Type:",
                "Marital Status:", "Blood Group:", "Emergency Contact:", "Time:", "Deposit:"};
        int y = 50;

        JLabel[] jlabels = new JLabel[labels.length];
        for (int i = 0; i < labels.length; i++) {
            jlabels[i] = new JLabel(labels[i]);
            jlabels[i].setBounds(30, y, 180, 20);
            jlabels[i].setFont(new Font("Tahoma", Font.BOLD, 12));
            jlabels[i].setForeground(Color.WHITE);
            panel.add(jlabels[i]);
            y += 30;
        }

        comboBox = new JComboBox<>(new String[]{"National ID", "Passport ID", "Driving License ID"});
        comboBox.setBounds(200, 50, 150, 20);
        panel.add(comboBox);

        textFieldNumber = new JTextField(); textFieldNumber.setBounds(200, 80, 150, 20); panel.add(textFieldNumber);
        textName = new JTextField(); textName.setBounds(200, 110, 150, 20); panel.add(textName);
        fatherName = new JTextField(); fatherName.setBounds(200, 140, 150, 20); panel.add(fatherName);
        dob = new JTextField(); dob.setBounds(200, 170, 150, 20); panel.add(dob);

        r1 = new JRadioButton("Male"); r1.setBounds(200, 200, 70, 20);
        r2 = new JRadioButton("Female"); r2.setBounds(280, 200, 80, 20);
        ButtonGroup bg = new ButtonGroup(); bg.add(r1); bg.add(r2);
        panel.add(r1); panel.add(r2);

        phone = new JTextField(); phone.setBounds(200, 230, 150, 20); panel.add(phone);
        email = new JTextField(); email.setBounds(200, 260, 150, 20); panel.add(email);
        address = new JTextField(); address.setBounds(200, 290, 150, 20); panel.add(address);
        textFieldDisease = new JTextField(); textFieldDisease.setBounds(200, 320, 150, 20); panel.add(textFieldDisease);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select * from room where availability = 'Available'"); // Only show available rooms
            while (rs.next()) {
                c1.add(rs.getString("room_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching room numbers. Check database connection.");
        }
      
        c1.setBounds(200, 350, 150, 20); 
        panel.add(c1);

        doctorAssigned = new JTextField(); doctorAssigned.setBounds(200, 380, 150, 20); panel.add(doctorAssigned);

        admissionType = new JComboBox<>(new String[]{"Inpatient", "Outpatient"});
        admissionType.setBounds(200, 410, 150, 20); panel.add(admissionType);

        maritalStatus = new JComboBox<>(new String[]{"Single", "Married", "Divorced", "Widowed", "Prefer not to say"}); // Added an option
        maritalStatus.setBounds(200, 440, 150, 20); panel.add(maritalStatus);

        bloodGroup = new JComboBox<>(new String[]{"", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}); // Added empty string for optional
        bloodGroup.setBounds(200, 470, 150, 20); panel.add(bloodGroup);

        emergencyContact = new JTextField(); emergencyContact.setBounds(200, 500, 150, 20); panel.add(emergencyContact);

        Date date1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        date = new JLabel(formatter.format(date1)); 
        date.setBounds(200, 530, 250, 20);
        date.setForeground(Color.WHITE);
        panel.add(date);

        textFieldDeposit = new JTextField(); textFieldDeposit.setBounds(200, 560, 150, 20); panel.add(textFieldDeposit);

        b1 = new JButton("ADD"); b1.setBounds(100, 600, 120, 30); b1.setBackground(Color.BLACK); b1.setForeground(Color.WHITE); b1.addActionListener(this); panel.add(b1);
        b2 = new JButton("Back"); b2.setBounds(250, 600, 120, 30); b2.setBackground(Color.BLACK); b2.setForeground(Color.WHITE); b2.addActionListener(this); panel.add(b2);

        setUndecorated(true);
        setSize(850, 650);
        setLayout(null);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String idType = (String) comboBox.getSelectedItem();
            String idNumber = textFieldNumber.getText().trim();
            String name = textName.getText().trim();
            String fatherNameText = fatherName.getText().trim(); // Value for Father's Name
            String dobText = dob.getText().trim();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
            String phoneText = phone.getText().trim();
            String emailText = email.getText().trim();
            String addressText = address.getText().trim();
            String disease = textFieldDisease.getText().trim();
            String roomNumber = c1.getSelectedItem(); // Value from Choice
            String doctorAssignedText = doctorAssigned.getText().trim();
            String admissionTypeSelected = (String) admissionType.getSelectedItem();
            String maritalStatusSelected = (String) maritalStatus.getSelectedItem();
            String bloodGroupSelected = (String) bloodGroup.getSelectedItem(); 
            String emergencyContactText = emergencyContact.getText().trim();
            String admissionTime = date.getText(); 
            String depositText = textFieldDeposit.getText().trim();

        
            if (idNumber.isEmpty() || name.isEmpty() || fatherNameText.isEmpty() || dobText.isEmpty() ||
                gender == null || phoneText.isEmpty() || emailText.isEmpty() || addressText.isEmpty() ||
                disease.isEmpty() || roomNumber == null || roomNumber.isEmpty() || doctorAssignedText.isEmpty() ||
                admissionTypeSelected == null || maritalStatusSelected == null ||
                emergencyContactText.isEmpty() || depositText.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please fill all *required* fields (all fields except Blood Group).");
                return;
            }
            if (!idNumber.matches("\\d+") || !phoneText.matches("\\d+") || !emergencyContactText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Number, Phone, and Emergency Contact must be numeric.");
                return;
            }
            try {
                Double.parseDouble(depositText); // Deposit must be a number
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Deposit must be a numeric value.");
                return;
            }

            // DOB format validation (yyyy-mm-dd)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Strict parsing
            try {
                dateFormat.parse(dobText);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Date of Birth must be in yyyy-mm-dd format.");
                return;
            }

            if (!emailText.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
                return;
            }

            try {
                Conn c = new Conn();
                String q = "INSERT INTO patient_info (" +
                           "ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Deposit, Father_Name, DOB, Phone, Email, Address, Marital_Status, Emergency_Contact, Blood_Group, Doctor_Assigned, Admission_Type" +
                           ") VALUES ('" +
                           idType + "', '" +
                           idNumber + "', '" +
                           name + "', '" +
                           gender + "', '" +
                           disease + "', '" +
                           roomNumber + "', '" +
                           admissionTime + "', '" +
                           depositText + "', '" +
                           fatherNameText + "', '" + // Value for 'Father_Name' column
                           dobText + "', '" +
                           phoneText + "', '" +
                           emailText + "', '" +
                           addressText + "', '" +
                           maritalStatusSelected + "', '" +
                           emergencyContactText + "', '" +
                           (bloodGroupSelected.isEmpty() ? null : bloodGroupSelected) + "', '" + // Insert NULL if Blood Group is empty
                           doctorAssignedText + "', '" +
                           admissionTypeSelected + "');";

                String q1 = "UPDATE room SET availability = 'Occupied' WHERE room_no = '" + roomNumber + "';";

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Patient Added Successfully");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to add patient. Please check database connection and ensure column names are correct. Error: " + ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}