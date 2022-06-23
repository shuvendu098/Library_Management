import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class studentInfo {
<<<<<<< Updated upstream
public static void main(String[] args) {
    
}
public static Connection connect() {
  //Making Database Connection once & using multiple times whenever required.
  try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/cutmlibrary", "root", "yourpassword");
      return con;
  } catch (Exception ex) {
      ex.printStackTrace();
  }
  return null;
}
=======

  public static void student(String uID) {
  }

>>>>>>> Stashed changes
}
