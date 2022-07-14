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
    studentInfo si=new studentInfo();
    JFrame student_frame=new JFrame("Centurion University,BBSR");
    JButton view_book, myissued_book, myreturned_book,cancel;
    view_book=new JButton("View Books");
    view_book.setOpaque(true);
    view_book.setBackground(Color.CYAN);
    view_book.setForeground(Color.black);
    view_book.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            si.viewBook();
        }
    });
    myissued_book=new JButton("My Issued Book");
    myissued_book.setOpaque(true);
    myissued_book.setBackground(Color.CYAN);
    myissued_book.setForeground(Color.black);
    myissued_book.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            si.myissuedbook(uID);
        }
    });
    myreturned_book=new JButton("My Returned Book");
    myreturned_book.setOpaque(true);
    myreturned_book.setBackground(Color.CYAN);
    myreturned_book.setForeground(Color.black);
    myreturned_book.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            si.myreturnedbook(uID);
        }
    });
    cancel = new JButton("LOGOUT");
    cancel.setOpaque(true);
    cancel.setBackground(Color.red);
    cancel.setForeground(Color.white);
    cancel.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            student_frame.dispose();
        }
    });

    student_frame.add(view_book);
    student_frame.add(myissued_book);
    student_frame.add(myreturned_book);
    student_frame.add(cancel);
    student_frame.setVisible(true);
    student_frame.setBounds(800,200,400,500);
    student_frame.setLayout(new GridLayout(4, 1));

}
public void viewBook(){
    JFrame viewBooksFrame = new JFrame("Books Available");
    Connection connection = connect();
    String sql = "select * from BOOKS";
    try {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        JTable book_list = new JTable();
        String[] bookColumnNames = {"Book ID", "Book ISBN", "Book Name", "Book Publisher", "Book Edition", "Book Genre", "Book price", "Book Pages","No of Books"};
        DefaultTableModel bookModel = new DefaultTableModel();
        bookModel.setColumnIdentifiers(bookColumnNames);
        book_list.setModel(bookModel);
        book_list.setBackground(Color.white);
        book_list.setForeground(Color.black);
        book_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        book_list.setFillsViewportHeight(true);
        book_list.setFocusable(true);
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
            int book_page = rs.getInt(8);
            int book_count=rs.getInt(9);
            bookModel.addRow(new Object[]{book_id, book_isbn, book_name, book_publisher, book_edition, book_genre, book_price, book_page,book_count});
        }
        viewBooksFrame.add(scrollBook);
        viewBooksFrame.setBounds(700,300,800,300);
        viewBooksFrame.setVisible(true);
    } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, "An error occure in database , try contact to the developer.");
    }

}
public void myissuedbook(String UID){
    JFrame IssuedBooksFrame = new JFrame("My Issued Books");
    Connection connection = connect();
    try {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from issued_books where uid="+UID);
        JTable book_list = new JTable();
        String[] IssuedBookColumnNames = {"issued ID", "User ID", "Book ID", "Issued Date", "Period"};
        DefaultTableModel bookModel = new DefaultTableModel();
        bookModel.setColumnIdentifiers(IssuedBookColumnNames);
        book_list.setModel(bookModel);
        book_list.setBackground(Color.white);
        book_list.setForeground(Color.black);
        book_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        book_list.setFillsViewportHeight(true);
        book_list.setFocusable(false);
        JScrollPane scrollIssuedBook = new JScrollPane(book_list);
        scrollIssuedBook.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollIssuedBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        while(rs.next()){
            int iid=rs.getInt(1);
            int uid=rs.getInt(2);
            int bid=rs.getInt(3);
            String iDate=rs.getString(4);
            int Period=rs.getInt(5);
            bookModel.addRow(new Object[]{iid,uid,bid,iDate,Period});
        }
        IssuedBooksFrame.add(scrollIssuedBook);
        IssuedBooksFrame.setBounds(600,300,600,300);
        IssuedBooksFrame.setVisible(true);
        IssuedBooksFrame.setResizable(false);
    } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, "Can't find any thing in the database");
    }
   


}
public void myreturnedbook(String UID){
    JFrame ReturnedBooksFrame = new JFrame("My Returned Books");
    Connection connection = connect();
    try {
        JTable book_list = new JTable();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from returned_books where uid="+UID);
        String[] returnedBookColumnNames = {"Return ID", "Book ID", "User ID", "Returned Date", "Fine"};
        DefaultTableModel bookModel = new DefaultTableModel();
        bookModel.setColumnIdentifiers(returnedBookColumnNames);
        book_list.setModel(bookModel);
        book_list.setBackground(Color.white);
        book_list.setForeground(Color.black);
        book_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        book_list.setFillsViewportHeight(true);
        book_list.setFocusable(false);
        JScrollPane scrollIssuedBook = new JScrollPane(book_list);
        scrollIssuedBook.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollIssuedBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        while(rs.next()){
            int rid=rs.getInt(1);
            int bid=rs.getInt(2);
            int uid=rs.getInt(3);
            String rDate=rs.getString(4);
            int fine=rs.getInt(5);
            bookModel.addRow(new Object[]{rid,uid,bid,rDate,fine});
        }
        ReturnedBooksFrame.add(scrollIssuedBook);
        ReturnedBooksFrame.setBounds(600,300,600,300);
        ReturnedBooksFrame.setVisible(true);
        ReturnedBooksFrame.setResizable(false);
    } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, "An Error Occured!");
    }

}
}
