package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class Room extends JFrame {
    JTable table;

    Room() {
        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        // Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/room.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(620, 100, 200, 200); 
        panel.add(label);

        // Table
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 40, 550, 400);
        panel.add(scrollPane);

        
        JButton back = new JButton("⬅ Back");
        back.setBounds(240, 460, 120, 40);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setBackground(new Color(30, 30, 30));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        panel.add(back);

        
        back.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                back.setBackground(new Color(50, 50, 50));
            }

            public void mouseExited(MouseEvent evt) {
                back.setBackground(new Color(30, 30, 30));
            }
        });

        // Back action
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false); // Or dispose();
            }
        });

        // Database
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM room";
            ResultSet rs = c.statement.executeQuery(q); // assumes executeQuery method exists
            table.setModel(buildTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame setup
        setUndecorated(true);
        setTitle("Hospital Room Viewer");
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 230);
        setVisible(true);
    }

    // Helper to build table model
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
        new Room();
    }
}
