package Library;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class studentInfo {
    
        public static Connection connect() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/cutmlibrary", "root", "bubu@098");
                return con;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
public static void main(String uID) {
}
}
