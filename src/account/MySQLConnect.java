package account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class MySQLConnect {
    static boolean status;
    private static Connection con;

    /**
     * Creates a connection and returns for further use
     */
    static Connection ConnectDB(){

        try{
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "");
           // JOptionPane.showMessageDialog(null,"Successfully Connected to the Database !");
            status=true;
            return con;
        } catch(Exception e){
           // JOptionPane.showMessageDialog(null,"Database Connection Error!!");
            status=false;
            return null;
        }
    }

    /**
     * Returns the list of customers from the database
     * @return list of type ArrayList
     */
    static ArrayList getCustomers(){

        ArrayList<String> list = new ArrayList<>();

        // Get the accountIDs
        try {
            Statement statement = con.createStatement();
            String sql = "SELECT accountID FROM account";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                list.add(rs.getString("accountID"));
            }

            rs.close();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null,"Load Unsuccessful!");
        }

        return list;
    }

}
