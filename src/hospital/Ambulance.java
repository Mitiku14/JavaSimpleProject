package hospital;

import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.sql.ResultSetMetaData;

public class Ambulance extends JFrame {
    Ambulance() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 1000, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBackground(new Color(90, 156, 163));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(table);
       
        scrollPane.setBounds(10, 80, 980, 420); 
        panel.add(scrollPane);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Ambulance";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching ambulance data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton button = new JButton("BACK");
        button.setBounds(440, 520, 120, 30); 
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
            }
        });

        
        setUndecorated(true);
        setSize(1000, 600); 
        setLayout(null);
        setLocation(300, 200); 
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
        new Ambulance();
    }
}