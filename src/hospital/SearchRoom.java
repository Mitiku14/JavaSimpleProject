package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.ResultSetMetaData;

public class SearchRoom extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton searchButton, backButton;

    SearchRoom() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 440);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search For Room");
        For.setBounds(250, 11, 186, 31);
        For.setForeground(Color.WHITE);
        For.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(For);

        JLabel status = new JLabel("Status: ");
        status.setBounds(50, 70, 80, 20);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        status.setForeground(Color.WHITE);
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170, 70, 120, 20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBackground(Color.WHITE); 
        table.setForeground(Color.BLACK); 

        table.getTableHeader().setBackground(new Color(90, 156, 163)); 
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));


        
        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.getViewport().setBackground(new Color(90, 156, 163));
        scrollPane.setBounds(0, 100, 690, 270); 
                                                 
        panel.add(scrollPane);

        try {
            Conn c = new Conn();
            String query = "select * from room";
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(buildTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        
        searchButton = new JButton("Search");
        searchButton.setBounds(200, 390, 120, 30);
        searchButton.setBackground(new Color(30, 30, 25));
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        panel.add(searchButton);

        backButton = new JButton("Back");
        backButton.setBounds(380, 390, 120, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);
        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(450, 250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                String selectedStatus = choice.getSelectedItem();
                Conn c = new Conn();
                String query = "select * from room where availability = '" + selectedStatus + "'";
                ResultSet rs = c.statement.executeQuery(query);
                table.setModel(buildTableModel(rs));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws Exception {
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

    public static void main(String[] args) {
        new SearchRoom();
    }
}