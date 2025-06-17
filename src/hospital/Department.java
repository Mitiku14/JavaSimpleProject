package hospital;

import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class Department extends JFrame {

    Department() {
        
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 498);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        
        JTable table = new JTable();
        table.setFont(new Font("Tahoma", Font.BOLD, 14)); 
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15)); 
        table.getTableHeader().setBackground(new Color(70, 130, 140));
        table.getTableHeader().setForeground(Color.WHITE);

        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 40, 630, 350);
        panel.add(scrollPane);
        
        JButton b1 = new JButton("BACK");
        b1.setBounds(400, 410, 130, 20);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
            }
        });
    


        try {
            Conn c = new Conn();
            String query = "SELECT Department, Phone_no FROM department";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(buildTableModel(rs));

           
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(300);
            columnModel.getColumn(1).setPreferredWidth(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        setUndecorated(true);
        setTitle("Department List");
        setSize(700, 500);
        setLayout(null);
        setLocation(350, 250);
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
        new Department();
    }
}
