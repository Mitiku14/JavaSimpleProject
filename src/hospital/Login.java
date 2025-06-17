package hospital;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
   JTextField textField;
   JPasswordField passwordField;
   JButton b1, b2;
    Login() {
      JLabel nameLabel = new JLabel("Username:");
         nameLabel.setBounds(40, 20, 100, 30);
         nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
         nameLabel.setForeground(Color.BLACK);
         add(nameLabel);


         JLabel password = new JLabel("Password:");
         password.setBounds(40, 70, 100, 30);
         password.setFont(new Font("Tahoma", Font.BOLD, 16));
         password.setForeground(Color.BLACK);
         add(password);

         textField = new JTextField();
         textField.setBounds(150, 20, 150, 30);
         textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
         textField.setBackground(new Color(255 , 179, 0));
         add(textField);

         passwordField = new JPasswordField();
         passwordField.setBounds(150, 70, 150, 30);
         passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
         passwordField.setBackground(new Color(255, 179, 0));
         add(passwordField);

      ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
      

      Image i1  = imageIcon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
      ImageIcon imageIcon1 = new ImageIcon(i1);
      JLabel label = new JLabel(imageIcon1);
        label.setBounds(320,-30, 400, 300);
         add(label);

         b1 = new JButton("Login");
         b1.setBounds(40, 140, 120, 30);
         b1.setFont(new Font("serif", Font.BOLD, 15));
         b1.setBackground(Color.BLACK);
         b1.setForeground(Color.WHITE);
         b1.addActionListener(this);
         add(b1);


         b2 = new JButton("Cancel");
         b2.setBounds(180, 140, 120, 30);
         b2.setFont(new Font("serif", Font.BOLD, 15));
         b2.setBackground(Color.BLACK);
         b2.setForeground(Color.WHITE);
         b2.addActionListener(this);
         add(b2);

         getContentPane().setBackground(new Color(109, 164, 170));
         setSize(750, 300);
         setLocation(400,270);
         setLayout(null);
         setVisible(true);

    }
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == b1){
         try{
            Conn c = new Conn();
            String user = textField.getText();
            String pass = passwordField.getText();
            String query = "select * from login where ID = '"+user+"' and password = '"+pass+"'";
            ResultSet rs = c.statement.executeQuery(query);
            if(rs.next()){
               new Reception();
               setVisible(false);


            }else{
               JOptionPane.showMessageDialog(null, "Invalid Username or Password");

            }


         }catch(Exception E){
            E.printStackTrace();

         }

      }else{
         System.exit(10);

      }

   }
   public static void main(String[] args) {
      new Login();
   }

}
