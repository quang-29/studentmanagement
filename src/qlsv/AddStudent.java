/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import qlsv.dao.StudentDaoImpl;
import qlsv.model.Student;

/**
 *
 * @author My Laptop
 */
public class AddStudent extends JFrame implements ActionListener {

        JTextField tfaddress, tfphone, tfemail, tfid, tffirstname, tflastname, tfclassid;
        JDateChooser dcdob;
        java.sql.Date dob;
        JComboBox cbcourse;
        JButton submit, cancel;
        ButtonGroup group;
        private StudentDaoImpl studentDaoImpl;
        JRadioButton male, female;
    java.util.Date utilDate;

    AddStudent() {

        setSize(900, 700);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.ITALIC, 30));
        add(heading);

        JLabel lblid = new JLabel("Id");
        lblid.setBounds(50, 100, 100, 30);
        lblid.setFont(new Font("serif", Font.BOLD, 20));
        add(lblid);
        tfid = new JTextField();
        tfid.setBounds(200, 100, 180, 30);
        add(tfid);

        JLabel lblfirstname = new JLabel("First Name");
        lblfirstname.setBounds(400, 100, 200, 30);
        lblfirstname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfirstname);
        tffirstname = new JTextField();
        tffirstname.setBounds(600, 100, 180, 30);
        add(tffirstname);

        JLabel lbllastname = new JLabel("Last Name");
        lbllastname.setBounds(50, 200, 200, 30);
        lbllastname.setFont(new Font("serif", Font.BOLD, 20));
        add(lbllastname);
        tflastname = new JTextField();
        tflastname.setBounds(200, 200, 180, 30);
        add(tflastname);

        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400, 200, 250, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 180, 30);
        add(dcdob);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(50, 300, 200, 30);
        lblgender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgender);

        male = new JRadioButton("Male");
        male.setBounds(200, 300, 100, 30);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(300, 300, 100, 30);
        add(female);
        male.setActionCommand("Male");
        female.setActionCommand("Female");
        group = new ButtonGroup();
        group.add(male);
        group.add(female);

        JLabel lblclassid = new JLabel("Class id");
        lblclassid.setBounds(400, 300, 200, 30);
        lblclassid.setFont(new Font("serif", Font.BOLD, 20));
        add(lblclassid);
        tfclassid = new JTextField();
        tfclassid.setBounds(600, 300, 180, 30);
        add(tfclassid);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 400, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 400, 180, 30);
        add(tfemail);

        JLabel lblmajor = new JLabel("Major");
        lblmajor.setBounds(400, 400, 200, 30);
        lblmajor.setFont(new Font("serif", Font.BOLD, 20));
        add(lblmajor);

        String course[] = {"Information Technology", "Information Security", "Electronics and Telecommunication"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(600, 400, 180, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50, 500, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 500, 180, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(400, 500, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 500, 180, 30);
        add(tfphone);

        submit = new JButton("Submit");
        submit.setBounds(250, 580, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 580, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {

            String id = tfid.getText();
            String firstname = tffirstname.getText();
            String lastname = tflastname.getText();
            utilDate = dcdob.getDate();
            String gender = group.getSelection() != null ? group.getSelection().getActionCommand() : "";
            String major = (String) cbcourse.getSelectedItem();
            String classid = tfclassid.getText();
            String email = tfemail.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();

            if (id.equals("") || firstname.equals("")
                    || lastname.equals("") || address.equals("")
                    || phone.equals("") || classid.equals("") || email.equals("")
                    || address.equals("") || phone.equals("") || group.getSelection() == null
                    || utilDate == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all information!");
            } else {

                Student st = new Student(id, firstname, lastname, new java.sql.Date(utilDate.getTime()), gender, major, classid, email, address,phone);
                studentDaoImpl = new StudentDaoImpl();
                try {
                    studentDaoImpl.insertStudent(st);
                    JOptionPane.showMessageDialog(this, "Add Student Successfully");
                    setNull();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        } else {
            setVisible(false);
        }
    }

    public void setNull() {
        tfid.setText("");
        tffirstname.setText("");
        tflastname.setText("");
        dcdob.setDate(null); // Reset date chooser
        group.clearSelection(); // Deselect gender
        tfclassid.setText("");
        tfemail.setText("");
        tfaddress.setText("");
        tfphone.setText("");
        cbcourse.setSelectedIndex(0); // Reset combo box to first item
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
