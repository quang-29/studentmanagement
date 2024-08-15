/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;
import qlsv.config.DBHelper;

/**
 *
 * @author My Laptop
 */
public class EnterMark extends JFrame implements ActionListener {

    JTextField tfid, tfscore, tfsubject, tfsubject_id;
    JTextField tfacademicyear;
    JComboBox cbbsemester;
    JButton btnsubmit, btncancel;

    public EnterMark() {

        setSize(900, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 400, 500);
        add(image);

        JLabel heading = new JLabel("Student Mark");
        heading.setBounds(50, 20, 200, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 20));
        add(heading);

        JLabel studentnumber = new JLabel("Student Number");
        studentnumber.setBounds(50, 100, 200, 30);
        add(studentnumber);

        tfid = new JTextField();
        tfid.setBounds(50, 150, 300, 30);
        add(tfid);

        JLabel lblsubject_id = new JLabel("Enter Subject Id");
        lblsubject_id.setBounds(50, 200, 200, 30);
        add(lblsubject_id);

        tfsubject_id = new JTextField();
        tfsubject_id.setBounds(50, 250, 300, 30);
        add(tfsubject_id);

        JLabel lblscore = new JLabel("Enter Score");
        lblscore.setBounds(50, 300, 200, 30);
        add(lblscore);

        tfscore = new JTextField();
        tfscore.setBounds(50, 350, 300, 30);
        add(tfscore);

        btnsubmit = new JButton("Submit");
        btnsubmit.setBounds(50, 450, 100, 30);
        btnsubmit.addActionListener(this);
        add(btnsubmit);

        btncancel = new JButton("Cancel");
        btncancel.setBounds(250, 450, 100, 30);
        btncancel.addActionListener(this);
        add(btncancel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new EnterMark();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnsubmit) {
            String id = tfid.getText();
            String semester = (String) cbbsemester.getSelectedItem();
            String academicyear = tfacademicyear.getText();
            String subjectid = tfsubject_id.getText();
            String subject = tfsubject.getText();
            String score = tfscore.getText();

            if (id.equals("") || academicyear.equals("")
                    || subject.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in all information!");
            } else {

                Connection c = DBHelper.getConnection();
                try {

                    String query2 = "INSERT INTO scores (student_id,subject_id,score) VALUES (?,?,?)";

                    PreparedStatement ps2 = c.prepareStatement(query2);


                    ps2.setString(1, id);
                    ps2.setString(2, subjectid);
                    ps2.setDouble(3, Double.parseDouble(score));
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Add successfully");
                    setNull();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        } else if (e.getSource() == btncancel) {
            setVisible(false);
        }

    }

    public void setNull() {
        tfid.setText("");
        cbbsemester.setSelectedIndex(0);
        tfacademicyear.setText("");
        tfscore.setText("");
        tfsubject.setText("");
        tfsubject_id.setText("");

    }

}
