package hospital;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    Connection connection;
    Statement statement;
    public Conn(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospital_management_system", "root", "Kit@1M234_");
            statement = connection.createStatement();


        }catch(Exception e){
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }

    }
    public Connection getConnection() {
        return connection;
    }
}
