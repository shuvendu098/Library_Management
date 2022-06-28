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
    adminsupport as=new adminsupport();
    JFrame admin_frame=new JFrame("Centurion University Library");
    JButton add_issue_book_btn=new JButton("Issue a Book");
    add_issue_book_btn.setBackground(Color.blue);
    add_issue_book_btn.setForeground(Color.black);

    JButton add_return_book_btn=new JButton("Return a Book");
    add_return_book_btn.setBackground(Color.blue);
    add_return_book_btn.setForeground(Color.black);

    JButton addnew_book_btn=new JButton("Add New Books");
    addnew_book_btn.setBackground(Color.blue);
    addnew_book_btn.setForeground(Color.black);

    JButton add_book_btn=new JButton("Add Existing Books");
    add_book_btn.setBackground(Color.blue);
    add_book_btn.setForeground(Color.black);

    JButton view_users_btn=new JButton("View Users");
    view_users_btn.setBackground(Color.blue);
    view_users_btn.setForeground(Color.black);
    view_users_btn.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            as.viewUser();
            }
    });


    JButton view_books_btn=new JButton("View Books");
    view_books_btn.setBackground(Color.blue);
    view_books_btn.setForeground(Color.black);
    view_books_btn.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            as.viewBook();  
        }
    });
  

    JButton view_issued_books_btn=new JButton("View Issued Books");
    view_issued_books_btn.setBackground(Color.blue);
    view_issued_books_btn.setForeground(Color.black);

    JButton view_returned_books_btn=new JButton("View Returned Books");
    view_returned_books_btn.setBackground(Color.blue);
    view_returned_books_btn.setForeground(Color.black);

    JButton add_user_btn=new JButton("Add Admin/Student");
    add_user_btn.setBackground(Color.blue);
    add_user_btn.setForeground(Color.black);

    JButton LOGOUT= new JButton("LOGOUT");
    LOGOUT.setBackground(Color.red);
    LOGOUT.setForeground(Color.black);
    LOGOUT.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            admin_frame.dispose();
        }
    });

    admin_frame.add(addnew_book_btn);
    admin_frame.add(add_issue_book_btn);
    admin_frame.add(add_return_book_btn);
    admin_frame.add(view_books_btn);
    admin_frame.add(add_user_btn);

    admin_frame.add(add_book_btn);
    admin_frame.add(view_issued_books_btn);
    admin_frame.add(view_returned_books_btn);
    admin_frame.add(view_users_btn);
    admin_frame.add(LOGOUT);
    
    admin_frame.setLayout(new GridLayout(2, 4));
    admin_frame.setBounds(650, 300, 800, 240);
    admin_frame.setVisible(true);
  }






  
  public void addUser(){

}




public void deletUser(){

}


public void viewUser(){
  JFrame viewUsersFrame = new JFrame("Users List");
  Connection connection = connect();
  String sql = "select * from users";
  try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      JTable users_list = new JTable();
      String[] userColumnNames = {"User ID", "User Name", "User Type"};
      DefaultTableModel userModel = new DefaultTableModel();
      userModel.setColumnIdentifiers(userColumnNames);
      users_list.setModel(userModel);
      users_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      users_list.setFillsViewportHeight(true);
      users_list.setBackground(Color.white);
      users_list.setForeground(Color.blue);
      JScrollPane scrollUser = new JScrollPane(users_list);
      scrollUser.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      while (rs.next()) {
          int uid = rs.getInt(1);
          String user_name = rs.getString(2);
          int user_type = rs.getInt(4);
          if (user_type == 1) {
              userModel.addRow(new Object[]{uid, user_name, "ADMIN"});
          } else {
              userModel.addRow(new Object[]{uid, user_name, "STUDENT"});
          }
      }
      viewUsersFrame.add(scrollUser);
      viewUsersFrame.setBounds(600,200,500,300);
      viewUsersFrame.setVisible(true);
  } catch (Exception e1) {
      JOptionPane.showMessageDialog(null, e1);
  }
}




public void viewBook(){
  JFrame viewBooksFrame = new JFrame("Books Available");
  Connection connection = connect();
  String sql = "select * from BOOKS";
  try {
      Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs = stmt.executeQuery(sql);
      JTable book_list = new JTable();
      String[] bookColumnNames = {"Book ID", "Book ISBN", "Book Name", "Book Publisher", "Book Edition", "Book Genre", "Book price", "Book Pages","No of Books"};
      DefaultTableModel bookModel = new DefaultTableModel();
      bookModel.setColumnIdentifiers(bookColumnNames);
      book_list.setModel(bookModel);
      book_list.setBackground(Color.white);
      book_list.setForeground(Color.blue);
      book_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
      book_list.setFillsViewportHeight(true);
      book_list.setFocusable(false);
      JScrollPane scrollBook = new JScrollPane(book_list);
      scrollBook.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      while (rs.next()) {
          int book_id = rs.getInt(1);
          String book_isbn = rs.getString(2);
          String book_name = rs.getString(3);
          String book_publisher = rs.getString(4);
          String book_edition = rs.getString(5);
          String book_genre = rs.getString(6);
          int book_price = rs.getInt(7);
          int book_pages = rs.getInt(8);
          int book_count=rs.getInt(9);
          bookModel.addRow(new Object[]{book_id, book_isbn, book_name, book_publisher, book_edition, book_genre, book_price, book_pages,book_count});
      }
      viewBooksFrame.add(scrollBook);
      viewBooksFrame.setBounds(600,300,800,300);
      viewBooksFrame.setVisible(true);
  } catch (Exception e1) {
      JOptionPane.showMessageDialog(null, e1);
  }

}
public void addBook(){

}

public void addNewBook(){

}
public void issueABook(){

}
public void showIssuedBook(){

}
public void returnABook(){

}
public void showReturnedBook(){

}
}
  

