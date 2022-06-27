package Library;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class adminsupport {
  
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
public static void main(String UID) {
    JFrame admin_frame=new JFrame("Centurion University Library");

    JButton add_user_btn=new JButton("Add Admin/Student");
    add_user_btn.setBackground(Color.green);
    add_user_btn.setForeground(Color.black);

    JButton add_issue_book_btn=new JButton("Issue a Book");
    add_issue_book_btn.setBackground(Color.green);
    add_issue_book_btn.setForeground(Color.black);

    JButton add_return_book_btn=new JButton("Return a Book");
    add_return_book_btn.setBackground(Color.green);
    add_return_book_btn.setForeground(Color.black);

    JButton addnew_book_btn=new JButton("Add New Books");
    addnew_book_btn.setBackground(Color.green);
    addnew_book_btn.setForeground(Color.black);

    JButton add_book_btn=new JButton("Add Existing Books");
    add_book_btn.setBackground(Color.green);
    add_book_btn.setForeground(Color.black);

    JButton view_users_btn=new JButton("View Users");
    view_users_btn.setBackground(Color.green);
    view_users_btn.setForeground(Color.black);

    JButton view_books_btn=new JButton("View Books");
    view_books_btn.setBackground(Color.green);
    view_books_btn.setForeground(Color.black);

    JButton view_issued_books_btn=new JButton("View Issued Books");
    view_issued_books_btn.setBackground(Color.green);
    view_issued_books_btn.setForeground(Color.black);

    JButton view_returned_books_btn=new JButton("View Returned Books");
    view_returned_books_btn.setBackground(Color.green);
    view_returned_books_btn.setForeground(Color.black);

    JButton remove_user_btn= new JButton("Delete a Admin/Student");
    remove_user_btn.setBackground(Color.green);
    remove_user_btn.setForeground(Color.black);

    admin_frame.add(addnew_book_btn);
    admin_frame.add(add_issue_book_btn);
    admin_frame.add(add_return_book_btn);
    admin_frame.add(view_books_btn);
    admin_frame.add(add_user_btn);

    admin_frame.add(add_book_btn);
    admin_frame.add(view_issued_books_btn);
    admin_frame.add(view_returned_books_btn);
    admin_frame.add(view_users_btn);
    admin_frame.add(remove_user_btn);
    
    admin_frame.setLayout(new GridLayout(2, 4));
    admin_frame.setSize(800, 300);
    admin_frame.setVisible(true);
    admin_frame.setResizable(true);
  }
  
}


