package Library;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class adminsupport {

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cutmlibrary", "root", "bubu@098");
            return con;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String UID) {
        adminsupport as = new adminsupport();
        JFrame admin_frame = new JFrame("Centurion University Library");
        JButton add_issue_book_btn = new JButton("Issue a Book");
        add_issue_book_btn.setBackground(Color.blue);
        add_issue_book_btn.setForeground(Color.white);
        add_issue_book_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.returnABook();
            }
        });
        JButton add_return_book_btn = new JButton("Return a Book");
        add_return_book_btn.setBackground(Color.blue);
        add_return_book_btn.setForeground(Color.white);
        JButton addnew_book_btn = new JButton("Add New Books");
        addnew_book_btn.setBackground(Color.blue);
        addnew_book_btn.setForeground(Color.white);
        addnew_book_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.addNewBook();
            }
        });

        JButton view_users_btn = new JButton("View Users");
        view_users_btn.setBackground(Color.blue);
        view_users_btn.setForeground(Color.white);
        view_users_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.viewUser();
            }
        });

        JButton view_books_btn = new JButton("View Books");
        view_books_btn.setBackground(Color.blue);
        view_books_btn.setForeground(Color.white);
        view_books_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.viewBook();
            }
        });

        JButton view_issued_books_btn = new JButton("View Issued Books");
        view_issued_books_btn.setBackground(Color.blue);
        view_issued_books_btn.setForeground(Color.white);
        view_issued_books_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.showIssuedBook();
            }
        });

        JButton view_returned_books_btn = new JButton("View Returned Books");
        view_returned_books_btn.setBackground(Color.blue);
        view_returned_books_btn.setForeground(Color.white);

        JButton add_user_btn = new JButton("Add Admin/Student");
        add_user_btn.setBackground(Color.blue);
        add_user_btn.setForeground(Color.white);
        add_user_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                as.addUser();
            }
        });

        JButton LOGOUT = new JButton("LOGOUT");
        LOGOUT.setBackground(Color.red);
        LOGOUT.setForeground(Color.black);
        LOGOUT.addActionListener(new ActionListener() {
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

        admin_frame.add(view_issued_books_btn);
        admin_frame.add(view_returned_books_btn);
        admin_frame.add(view_users_btn);
        admin_frame.add(LOGOUT);

        admin_frame.setLayout(new GridLayout(2, 4));
        admin_frame.setBounds(650, 300, 800, 240);
        admin_frame.setVisible(true);
    }

    public void addUser() {
        JFrame add_user_frame = new JFrame("Enter User Details");
        JLabel l1 = new JLabel("Username", SwingConstants.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.yellow);
        l1.setForeground(Color.black);
        JLabel l2 = new JLabel("Password", SwingConstants.CENTER);
        l2.setOpaque(true);
        l2.setBackground(Color.yellow);
        l2.setForeground(Color.black);
        JTextField add_username_tf = new JTextField();
        add_username_tf.setBackground(Color.white);
        add_username_tf.setForeground(Color.blue);
        JPasswordField add_password_tf = new JPasswordField();
        add_password_tf.setBackground(Color.white);
        add_password_tf.setForeground(Color.blue);
        JRadioButton admin_radio1 = new JRadioButton("Admin");
        admin_radio1.setHorizontalAlignment(SwingConstants.CENTER);
        admin_radio1.setBackground(Color.cyan);
        admin_radio1.setForeground(Color.black);
        JRadioButton student_radio2 = new JRadioButton("Student");
        student_radio2.setHorizontalAlignment(SwingConstants.CENTER);
        student_radio2.setBackground(Color.CYAN);
        student_radio2.setForeground(Color.black);
        ButtonGroup btn_grp = new ButtonGroup();
        btn_grp.add(admin_radio1);
        btn_grp.add(student_radio2);
        JButton create_btn = new JButton("Submit");
        create_btn.setBackground(Color.blue);
        create_btn.setForeground(Color.white);
        create_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = add_username_tf.getText();
                String password = add_password_tf.getText();
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    if (admin_radio1.isSelected()) {
                        stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,USER_TYPE) VALUES ('" + username + "','"
                                + password + "','" + "1" + "')");
                        JOptionPane.showMessageDialog(null, "Admin added!");
                        add_user_frame.dispose();
                    } else {
                        stmt.executeUpdate("INSERT INTO USERS(USERNAME,PASSWORD,USER_TYPE) VALUES ('" + username + "','"
                                + password + "','" + "0" + "')");
                        JOptionPane.showMessageDialog(null, "Student added!");
                        add_user_frame.dispose();
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        JButton cancel_btn = new JButton("Cancel");
        cancel_btn.setBackground(Color.red);
        cancel_btn.setForeground(Color.white);

        add_user_frame.add(l1);
        add_user_frame.add(add_username_tf);
        add_user_frame.add(l2);
        add_user_frame.add(add_password_tf);
        add_user_frame.add(admin_radio1);
        add_user_frame.add(student_radio2);
        add_user_frame.add(cancel_btn);
        add_user_frame.add(create_btn);
        add_user_frame.setBounds(300, 200, 600, 200);
        add_user_frame.setLayout(new GridLayout(4, 2));
        add_user_frame.setVisible(true);
        add_user_frame.setResizable(true);

    }

    public void viewUser() {
        JFrame viewUsersFrame = new JFrame("Users List");
        Connection connection = connect();
        String sql = "select * from users";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            JTable users_list = new JTable();
            String[] userColumnNames = { "User ID", "User Name", "User Type" };
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
                    userModel.addRow(new Object[] { uid, user_name, "ADMIN" });
                } else {
                    userModel.addRow(new Object[] { uid, user_name, "STUDENT" });
                }
            }
            viewUsersFrame.add(scrollUser);
            viewUsersFrame.setBounds(600, 200, 500, 300);
            viewUsersFrame.setVisible(true);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public void viewBook() {
        JFrame viewBooksFrame = new JFrame("Books Available");
        Connection connection = connect();
        String sql = "select * from BOOKS";
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            JTable book_list = new JTable();
            String[] bookColumnNames = { "Book ID", "Book ISBN", "Book Name", "Book Publisher", "Book Edition",
                    "Book Genre", "Book price", "Book Pages", "No of Books" };
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
                int book_count = rs.getInt(9);
                bookModel.addRow(new Object[] { book_id, book_isbn, book_name, book_publisher, book_edition, book_genre,
                        book_price, book_pages, book_count });
            }
            viewBooksFrame.add(scrollBook);
            viewBooksFrame.setBounds(600, 300, 800, 300);
            viewBooksFrame.setVisible(true);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }

    }

    public void addNewBook() {
        JFrame book_frame = new JFrame("Enter Book Details");
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        l1 = new JLabel("ISBN", SwingConstants.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.yellow);
        l1.setForeground(Color.black);
        l2 = new JLabel("Name", SwingConstants.CENTER);
        l2.setOpaque(true);
        l2.setBackground(Color.yellow);
        l2.setForeground(Color.black);
        l3 = new JLabel("Publisher", SwingConstants.CENTER);
        l3.setOpaque(true);
        l3.setBackground(Color.yellow);
        l3.setForeground(Color.black);
        l4 = new JLabel("Edition", SwingConstants.CENTER);
        l4.setOpaque(true);
        l4.setBackground(Color.yellow);
        l4.setForeground(Color.black);
        l5 = new JLabel("Genre", SwingConstants.CENTER);
        l5.setOpaque(true);
        l5.setBackground(Color.yellow);
        l5.setForeground(Color.black);
        l6 = new JLabel("Price", SwingConstants.CENTER);
        l6.setOpaque(true);
        l6.setBackground(Color.yellow);
        l6.setForeground(Color.black);
        l7 = new JLabel("Pages", SwingConstants.CENTER);
        l7.setOpaque(true);
        l7.setBackground(Color.yellow);
        l7.setForeground(Color.black);
        l8 = new JLabel("Number of Books", SwingConstants.CENTER);
        l8.setOpaque(true);
        l8.setBackground(Color.yellow);
        l8.setForeground(Color.black);
        JTextField book_isbn_tf = new JTextField();
        book_isbn_tf.setBackground(Color.white);
        book_isbn_tf.setForeground(Color.blue);
        JTextField book_name_tf = new JTextField();
        book_name_tf.setBackground(Color.white);
        book_name_tf.setForeground(Color.blue);
        JTextField book_publisher_tf = new JTextField();
        book_publisher_tf.setBackground(Color.white);
        book_publisher_tf.setForeground(Color.blue);
        JTextField book_edition_tf = new JTextField();
        book_edition_tf.setBackground(Color.white);
        book_edition_tf.setForeground(Color.blue);
        JTextField book_genre_tf = new JTextField();
        book_genre_tf.setBackground(Color.white);
        book_genre_tf.setForeground(Color.blue);
        JTextField book_price_tf = new JTextField();
        book_price_tf.setBackground(Color.white);
        book_price_tf.setForeground(Color.blue);
        JTextField book_pages_tf = new JTextField();
        book_pages_tf.setBackground(Color.white);
        book_pages_tf.setForeground(Color.blue);
        JTextField booksnumber_tf = new JTextField();
        book_pages_tf.setBackground(Color.white);
        book_pages_tf.setForeground(Color.blue);
        JButton submit_btn = new JButton("Submit");
        submit_btn.setBackground(Color.blue);
        submit_btn.setForeground(Color.white);
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String book_isbn = book_isbn_tf.getText();
                String book_name = book_name_tf.getText();
                String book_publisher = book_publisher_tf.getText();
                String book_edition = book_edition_tf.getText();
                String book_genre = book_genre_tf.getText();
                int book_price = Integer.parseInt(book_price_tf.getText());
                int book_pages = Integer.parseInt(book_pages_tf.getText());
                int book_count = Integer.parseInt(booksnumber_tf.getText());
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    stmt.executeUpdate(
                            "INSERT INTO BOOKS(book_isbn,book_name,book_publisher,book_edition,book_genre,book_price,book_pages,book_count) VALUES ('"
                                    + book_isbn + "','" + book_name + "','" + book_publisher + "','" + book_edition
                                    + "','" + book_genre + "','" + book_price + "','" + book_pages + "','" + book_count
                                    + ")");
                    JOptionPane.showMessageDialog(null, "Book added!");
                    book_frame.dispose();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
        JButton add_book_cancel_btn = new JButton("Cancel");
        add_book_cancel_btn.setBackground(Color.red);
        add_book_cancel_btn.setForeground(Color.white);
        add_book_cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book_frame.dispose();
            }
        });
        book_frame.add(l1);
        book_frame.add(book_isbn_tf);
        book_frame.add(l2);
        book_frame.add(book_name_tf);
        book_frame.add(l3);
        book_frame.add(book_publisher_tf);
        book_frame.add(l4);
        book_frame.add(book_edition_tf);
        book_frame.add(l5);
        book_frame.add(book_genre_tf);
        book_frame.add(l6);
        book_frame.add(book_price_tf);
        book_frame.add(l7);
        book_frame.add(book_pages_tf);
        book_frame.add(l8);
        book_frame.add(booksnumber_tf);
        book_frame.add(submit_btn);
        book_frame.add(add_book_cancel_btn);
        book_frame.setBounds(300, 200, 600, 300);
        book_frame.setLayout(new GridLayout(9, 2));
        book_frame.setVisible(true);
        book_frame.setResizable(true);

    }

    public void issueABook() {

    }

    public void showIssuedBook() {
        JFrame issuedBooksFrame = new JFrame("Issued Books List");
        Connection connection = connect();
        String sql = "select * from issued_books;";
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql);
            JTable issue_book_list = new JTable();
            String[] issueBookColumnNames = { "Issue ID", "Admin/Student ID", "Book ID", "Issue Date", "Period" };
            DefaultTableModel issuedBookModel = new DefaultTableModel();
            issuedBookModel.setColumnIdentifiers(issueBookColumnNames);
            issue_book_list.setModel(issuedBookModel);
            issue_book_list.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            issue_book_list.setFillsViewportHeight(true);
            issue_book_list.setFocusable(true);
            issue_book_list.setBackground(Color.white);
            issue_book_list.setForeground(Color.blue);
            JScrollPane scrollIssuedBook = new JScrollPane(issue_book_list);
            scrollIssuedBook.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollIssuedBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            while (rs.next()) {
                int issued_id = rs.getInt(1);
                int admin_student_id = rs.getInt(2);
                int book_id = rs.getInt(3);
                String issued_date = rs.getString(4);
                int period = rs.getInt(5);
                issuedBookModel.addRow(new Object[] { issued_id, admin_student_id, book_id, issued_date, period });

            }
            issuedBooksFrame.add(scrollIssuedBook);
            issuedBooksFrame.setBounds(700, 300, 800, 300);
            issuedBooksFrame.setVisible(true);
        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "An error occure in database , contact developer.");
        }

    }

    public void returnABook() {

        JFrame returnBookFrame = new JFrame("Enter Details");
        JLabel l1 = new JLabel("Book ID", SwingConstants.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.yellow);
        l1.setForeground(Color.black);
        JLabel l2 = new JLabel("Student/Admin ID", SwingConstants.CENTER);
        l2.setOpaque(true);
        l2.setBackground(Color.yellow);
        l2.setForeground(Color.black);
        JLabel l3 = new JLabel("Return Date(DD-MM-YYYY)", SwingConstants.CENTER);
        l3.setOpaque(true);
        l3.setBackground(Color.yellow);
        l3.setForeground(Color.black);
        JLabel l4 = new JLabel("Fine", SwingConstants.CENTER);
        l4.setOpaque(true);
        l4.setBackground(Color.yellow);
        l4.setForeground(Color.black);
        JTextField bid_tf = new JTextField();
        bid_tf.setBackground(Color.white);
        bid_tf.setForeground(Color.blue);
        JTextField uid_tf = new JTextField();
        uid_tf.setBackground(Color.white);
        uid_tf.setForeground(Color.blue);
        JTextField returnDate = new JTextField();
        returnDate.setBackground(Color.white);
        returnDate.setForeground(Color.blue);
        JTextField fine_tf = new JTextField();
        fine_tf.setBackground(Color.white);
        fine_tf.setForeground(Color.blue);
        JButton return_book_btn = new JButton("Return");
        return_book_btn.setBackground(Color.blue);
        return_book_btn.setForeground(Color.white);
        return_book_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent rb) {
                int bid = Integer.parseInt(bid_tf.getText());
                int uid = Integer.parseInt(uid_tf.getText());
                String date = returnDate.getText();
                int fine = Integer.parseInt(fine_tf.getText());
                Connection connection = connect();
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("INSERT INTO `returned_books`(`bid`,`uid`,`return_date`,`fine`) VALUES('" + bid
                            + "','" + uid + "','" + date + "','" + fine + "')");
                    JOptionPane.showMessageDialog(null, "Book returned Succesfully.");
                    returnBookFrame.dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "error in the database");
                }

            }
        });
        JButton cancel_btn = new JButton("Cancel");
        cancel_btn.setBackground(Color.red);
        cancel_btn.setForeground(Color.white);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent rbc) {
                returnBookFrame.dispose();
            }
        });
        returnBookFrame.add(l1);
        returnBookFrame.add(bid_tf);
        returnBookFrame.add(l2);
        returnBookFrame.add(uid_tf);
        returnBookFrame.add(l3);
        returnBookFrame.add(returnDate);
        returnBookFrame.add(l4);
        returnBookFrame.add(fine_tf);
        returnBookFrame.add(cancel_btn);
        returnBookFrame.add(return_book_btn);
        returnBookFrame.setBounds(700, 300, 600, 300);
        returnBookFrame.setLayout(new GridLayout(5, 2));
        returnBookFrame.setVisible(true);
        returnBookFrame.setResizable(false);
    }

    public void showReturnedBook() {

    }
}
