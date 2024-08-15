/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;
import StudentManagement.config.DBHelper;

/**
 *
 * @author My Laptop
 */
public class Login extends JFrame implements ActionListener {

    JButton login, cancel;
    JTextField tfusername, tfpassword;

    public Login() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblusername = new JLabel("Username:");
        add(lblusername);
        lblusername.setBounds(40, 20, 100, 20);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(150, 70, 150, 20);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setVisible(true);
        setSize(600, 300);
        setLocation(500, 250);
        setResizable(false);

    }

    public static void main(String[] args) {
        new Login();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = tfusername.getText();
            String password = tfpassword.getText();

            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            
            
            Connection c = DBHelper.getConnection();
            try {
                
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    setVisible(false);
                    new Home(); // Ensure Home class is correctly implemented
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login information!");
                }

            } catch (SQLException ex) {
                 ex.printStackTrace();
                   JOptionPane.showMessageDialog(null, "Database connection error: " + ex.getMessage());
            }
        }

    else if (e.getSource () 
        == cancel) {
        setVisible(false);
    }
}

}
