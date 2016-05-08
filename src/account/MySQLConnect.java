package account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

class MySQLConnect {

    static Connection ConnectDB(){

        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "");
            JOptionPane.showMessageDialog(null,"Successfully Connected to the Database !");
            return con;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Database Connection Error!!");
            return null;
        }
    }

}
