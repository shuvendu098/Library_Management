package Library;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class library_management
{
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
    public static void main(String[] args) {

        JFrame loginFrame = new JFrame("Login"); 
        JLabel l1 = new JLabel("Username", SwingConstants.CENTER);
        l1.setBackground(Color.pink);
        l1.setForeground(Color.black);
        JPanel cp = new JPanel();
        cp.setBorder(new EmptyBorder(5,5,5,5));
        cp.setLayout(null);
        JLabel l2 = new JLabel("Password", SwingConstants.CENTER);
        l2.setBackground(Color.pink);
        l2.setForeground(Color.black);
        JTextField usernameTF = new JTextField();
        usernameTF.setBackground(Color.white);
        usernameTF.setForeground(Color.blue);
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBackground(Color.white);
        passwordTF.setForeground(Color.black);
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(124, 85, 227));
        loginBtn.setForeground(Color.white);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(Color.red);
        cancelBtn.setForeground(Color.white);
        loginFrame.add(l1);
        loginFrame.add(usernameTF);
        loginFrame.add(l2);
        loginFrame.add(passwordTF);
        loginFrame.add(cancelBtn);
        loginFrame.add(loginBtn);
        loginFrame.setLayout(new GridLayout(3, 2));
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.setBounds(750, 300, 400, 240);






        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText();
                String password = passwordTF.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter username"); 
                } 
                else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter password"); 
                }
                else {
                    Connection connection = connect();
                    try {
                        Statement stmt = connection.createStatement();
                        String un= "select * from users where username="+username+"& password="+password;
                        ResultSet rs = stmt.executeQuery(un);
                        if (rs.next() == false) { 
                            JOptionPane.showMessageDialog(null, "Invalid Username!"); 
                        } 
                        else {
                            loginFrame.dispose();
                            rs.beforeFirst(); 
                            while (rs.next()) {
                                String user = rs.getString("user_type");
                                String USER_ID = rs.getString("UID"); 
                                if (user.equals("1")) { 
                                    System.out.println("admin");
                                } else {
                                    System.out.println("student");

                                   
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
            }
        });
        
        
    }
        
    }
