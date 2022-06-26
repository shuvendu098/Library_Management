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
public class adminsupport {
  public static Connection connect() {
    //Making Database Connection once & using multiple times whenever required.
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/cutmlibrary", "root", "yourpassword");
        return con;
    } 
    catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}
public static void main(String[] args) {
   JButton view_users_btn = new JButton("View Users");
    //Setting Background color of the button.
    view_users_btn.setBackground(new Color(51, 35, 85));
    //Setting Foreground color of the button.
    view_users_btn.setForeground(Color.white);
    //Performing actions on the button.
    view_users_btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Creating frame.
            JFrame viewUsersFrame = new JFrame("Users List");
            //Connection to database
            Connection connection = connect();
            //Query for retrieving data from database
            String sql = "select * from users";
            try {
                //Creating Statement
                Statement stmt = connection.createStatement();
                //Executing query
                ResultSet rs = stmt.executeQuery(sql);
                //Creating Table for to data will be in table format
                JTable users_list = new JTable();
                String[] userColumnNames = {"User ID", "User Name", "User Type"};
                //Creating model for the table
                DefaultTableModel userModel = new DefaultTableModel();
                //Setting up the columns names of the model
                userModel.setColumnIdentifiers(userColumnNames);
                //Adding model to the table component
                users_list.setModel(userModel);
                //Setting up table auto-resizable
                users_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                users_list.setFillsViewportHeight(true);
                //Setting background colour of the table.
                users_list.setBackground(new Color(51, 35, 85));
                //Setting foreground colour of the table.
                users_list.setForeground(Color.white);
                //Creating scrollbars for table
                JScrollPane scrollUser = new JScrollPane(users_list);
                scrollUser.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                while (rs.next()) {
                    //Fetching the data from mysql database
                    int uid = rs.getInt(1);
                    String user_name = rs.getString(2);
                    int user_type = rs.getInt(4);
                    if (user_type == 1) {
                        //Checking if it is 1 then it is admin
                        userModel.addRow(new Object[]{uid, user_name, "ADMIN"});
                    } else {
                        //Else it will be user
                        userModel.addRow(new Object[]{uid, user_name, "USER"});
                    }
                }
                //Adding scrollbars in the frame
                viewUsersFrame.add(scrollUser);
                //Setting up the size of the frame (width,height)
                viewUsersFrame.setSize(800, 400);
                //Setting up frame visible for user
                viewUsersFrame.setVisible(true);
            } catch (Exception e1) {
                //Creating Dialog box to show any error if occured!
                JOptionPane.showMessageDialog(null, e1);
            }
        }
    });
    //Creating button
}
    
}
