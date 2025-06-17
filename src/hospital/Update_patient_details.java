package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_patient_details extends JFrame{
    Update_patient_details(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 950, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500, 60, 300, 300);
        panel.add(label);


        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 260, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("Name:");
        label2.setBounds(25, 88, 100, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);
        
        Choice choice = new Choice();
        choice.setBounds(248, 85, 140, 25); 
        panel.add(choice);

        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_Info");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number:");
        label3.setBounds(25, 129, 100, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time:");
        label4.setBounds(25, 178, 100, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);
        JTextField textFieldINTime = new JTextField();
        textFieldINTime.setBounds(248, 174, 140, 20);
        panel.add(textFieldINTime);

        JLabel label5 = new JLabel("Amount Paid (ETB):");
        label5.setBounds(25, 216, 150, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);
        JTextField textFieldAmount= new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);

        JLabel label6 = new JLabel("Pending Amount(ETB):");
        label6.setBounds(25, 261, 150, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);
        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248, 261, 140, 20);
        panel.add(textFieldPending);
        
        JButton check = new JButton("CHECK");
        check.setBounds(281, 378, 89, 23);
        check.setFont(new Font("Tahoma", Font.BOLD, 14));
        check.setBackground(new Color(30, 30, 30));
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = choice.getSelectedItem();
        String query = "SELECT * FROM Patient_Info WHERE Name = '" + id + "'";
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(query);
            String roomNo = "", deposit = "";

            if (rs.next()) {
                roomNo = rs.getString("Room_Number");
                deposit = rs.getString("Deposit");
                textFieldR.setText(roomNo);
                textFieldINTime.setText(rs.getString("Time"));
                textFieldAmount.setText(deposit);
            }

            if (!roomNo.isEmpty() && !deposit.isEmpty()) {
                ResultSet resultSet1 = c.statement.executeQuery("SELECT Price FROM Room WHERE room_no = '" + roomNo + "'");
                if (resultSet1.next()) {
                    String price = resultSet1.getString("Price");
                    double pending = Double.parseDouble(price) - Double.parseDouble(deposit);
                    textFieldPending.setText(String.format("%.2f", pending));
                } else {
                    textFieldPending.setText("Room not found");
                }
            } else {
                textFieldPending.setText("Invalid deposit");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            textFieldPending.setText("Error");
        }
    }
});


        JButton update = new JButton("UPDATE");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.white);
        panel.add(update);
         
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    Conn c = new Conn();
                    String query = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTime.getText();
                    String amount = textFieldAmount.getText();
                    c.statement.executeUpdate("UPDATE Patient_Info SET Room_Number = '" + room + "', Time = '" + time + "', Deposit = '" + amount + "' WHERE Name = '" + query + "'");
                    JOptionPane.showMessageDialog(null, "Patient Details Updated Successfully");
                    setVisible(false);



                }catch(Exception E){
                    E.printStackTrace();
                }

                
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
       
    back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Update_patient_details();
    }

    
}
