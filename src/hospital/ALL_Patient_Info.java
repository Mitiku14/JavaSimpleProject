package hospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ALL_Patient_Info extends JFrame implements ActionListener {

    JButton backButton;

    ALL_Patient_Info() {
    
        setSize(900, 600); 
        setLayout(null);
        setLocation(300, 200);
        JPanel panel = new JPanel();
        
        panel.setBounds(5, 5, 880, 550); 
        panel.setBackground(new Color(200, 220, 230)); 
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBackground(new Color(200, 220, 230)); 
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(160, 190, 200)); 
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBounds(10, 10, 860, 500); 
        scrollPane.setBackground(new Color(200, 220, 230)); 
        panel.add(scrollPane);

        try {
            Conn c = new Conn();
            String query = "SELECT ID, Number, Name, \"Father's Name\" AS Father_Name, DOB, Gender, Phone, Email, Address, Patient_Disease AS Disease, Room_Number, Doctor_Assigned, Admission_Type, Marital_Status, Emergency_Contact, Blood_Group, Time, Deposit FROM Patient_Info";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching patient data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        backButton = new JButton("BACK");
        
        backButton.setBounds(370, 520, 140, 35);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.addActionListener(this);
        panel.add(backButton);

        setUndecorated(true); 
        setVisible(true);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnLabel(i));
        }
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }
        return new DefaultTableModel(data, columnNames);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            
        }
    }

    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}