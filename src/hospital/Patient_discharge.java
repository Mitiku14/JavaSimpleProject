package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_discharge extends JFrame{
    Patient_discharge(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.
        WHITE);
        panel.add(label);

        JLabel label1 = new JLabel("Patient ID");
        label1.setBounds(30, 80, 150, 20);  
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);

        try{
            Conn c = new Conn();
            ResultSet rs  = c.statement.executeQuery("SELECT * FROM Patient_Info");
            while(rs.next()){
                choice.add(rs.getString("Number"));
            }


        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label2 = new JLabel("Room Number");
        label2.setBounds(30, 130, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel RNo = new JLabel();
        RNo.setBounds(200, 130, 150, 20);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label3 = new JLabel("In Time");
        label3.setBounds(30, 180, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel InTime = new JLabel();
        InTime.setBounds(200, 180, 250, 20);
        InTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        InTime.setForeground(Color.WHITE);
        panel.add(InTime);

        JLabel label4 = new JLabel("Out Time");
        label4.setBounds(30, 230, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        Date date = new Date();

        JLabel OutTime = new JLabel(""+date);  
        OutTime.setBounds(200, 230, 250, 20);
        OutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        OutTime.setForeground(Color.WHITE);
        panel.add(OutTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30, 300, 120, 30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                Conn c = new Conn();
                try{
                    c.statement.executeUpdate("DELETE FROM Patient_Info WHERE Number = '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("update Room set Availability = 'Available' where room_no = '"+RNo.getText()+"'");
                    JOptionPane.showMessageDialog(null, "Patient Discharged Successfully");
                    setVisible(false);


                }catch(Exception E){
                    E.printStackTrace();
                }
                
            }
            
        });

        JButton check = new JButton("Check");
        check.setBounds(170, 300, 120, 30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn c = new Conn();
                try{
                    ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_Info WHERE Number = '"+choice.getSelectedItem()+"'");    
                    while(rs.next()){
                        RNo.setText(rs.getString("Room_Number"));
                        InTime.setText(rs.getString("Time"));
                    }


                }catch(Exception E) {
                    E.printStackTrace();
                }
            }
            
        });

        JButton Back = new JButton("Back");
        Back.setBounds(300, 300, 120, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                
            }
        });

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Patient_discharge();
    }
}
