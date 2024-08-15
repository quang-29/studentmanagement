/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import StudentManagement.config.DBHelper;

public class MarkResult extends JFrame implements ActionListener {

    String studentnum;
    JButton cancel;

    MarkResult(String studentnum) {
        this.studentnum = studentnum;

        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Technical Univeristy");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination ");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel studentnumber = new JLabel("Student number " + studentnum);
        studentnumber.setBounds(60, 100, 500, 20);
        studentnumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(studentnumber);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        JLabel sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);

        JLabel sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);

        JLabel sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);

        JLabel sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);

        try {
            
            Connection c = DBHelper.getConnection();
            String query1 = "SELECT * FROM subject WHERE studentnumber = ?";
            PreparedStatement ps1 = c.prepareStatement(query1);
            ps1.setString(1, studentnum);
            String query2 = "SELECT * FROM grade WHERE studentnumber = ?";
            PreparedStatement ps2 = c.prepareStatement(query2);
            ps2.setString(1, studentnum);
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();

            while (rs1.next()) {
                sub1.setText(rs1.getString("subject1"));
                sub2.setText(rs1.getString("subject2"));
                sub3.setText(rs1.getString("subject3"));
                sub4.setText(rs1.getString("subject4"));
                
            }

            while (rs2.next()) {
                sub1.setText(sub1.getText() + "------------" + rs2.getString("mark1"));
                sub2.setText(sub2.getText() + "------------" + rs2.getString("mark2"));
                sub3.setText(sub3.getText() + "------------" + rs2.getString("mark3"));
                sub4.setText(sub4.getText() + "------------" + rs2.getString("mark4"));
                
                lblsemester.setText("Semester " + rs2.getString("semester"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new MarkResult("");
    }
}
