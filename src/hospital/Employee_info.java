package hospital;

import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class Employee_info extends JFrame {

    Employee_info() {
        // Main panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 980, 550); 
        panel.setBackground(new Color(189, 164, 170));
        panel.setLayout(null);
        add(panel);

        // Header Labels
        // JLabel label1 = new JLabel("Name");
        // label1.setBounds(41, 9, 70, 20);
        // label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label1);

        // JLabel label2 = new JLabel("Age");
        // label2.setBounds(180, 9, 70, 20);
        // label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label2);

        // JLabel label3 = new JLabel("Phone Number");
        // label3.setBounds(330, 9, 150, 20);
        // label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label3);

        // JLabel label4 = new JLabel("Salary");
        // label4.setBounds(520, 9, 70, 20);
        // label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label4);

        // JLabel label5 = new JLabel("Email");
        // label5.setBounds(670, 9, 70, 20);
        // label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label5);

        // JLabel label6 = new JLabel("Employee ID");
        // label6.setBounds(810, 9, 100, 20);
        // label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        // panel.add(label6);

        // Table inside JScrollPane
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 34, 960, 430);  
        panel.add(scrollPane);

        
        JButton b1 = new JButton("BACK");
        b1.setBounds(420, 480, 130, 30); 
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        // Fetch data
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM EMP_INFO";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUndecorated(true);
        setSize(1000, 600);
        setLocation(350, 230);
        setLayout(null);
        setVisible(true);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        Vector<String> columnNames = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
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

    public static void main(String[] args) {
        new Employee_info();
    }
}
