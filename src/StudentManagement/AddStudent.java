/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

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
import StudentManagement.dao.StudentDaoImpl;
import StudentManagement.model.Student;

/**
 *
 * @author My Laptop
 */
public class AddStudent extends JFrame implements ActionListener {

    JTextField tfaddress, tfphone, tfemail, tfid, tffirstname, tflastname, tfclassid;
    JDateChooser dcdob;
    java.sql.Date dob;
    JComboBox cbbmajor;
    JButton btnsubmit, btncancel;
    ButtonGroup group;
    private StudentDaoImpl studentDaoImpl;
    JRadioButton male, female;
    java.util.Date utilDate;

    AddStudent() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Student");
        heading.setBounds(50, 50, 150, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 20));
        add(heading);

        JLabel lblid = new JLabel("Student id");
        lblid.setBounds(50, 100, 100, 30);
        add(lblid);
        tfid = new JTextField();
        tfid.setBounds(150, 100, 180, 30);
        add(tfid);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(400, 100, 180, 30);
        add(dob);
        dcdob = new JDateChooser();
        dcdob.setBounds(500, 100, 180, 30);
        add(dcdob);

        JLabel department = new JLabel("Major");
        department.setBounds(400, 150, 180, 30);
        add(department);
        String groupdepartment[] = {"Information Technology", "Information Security", "Electronics and Telecommunication"};
        cbbmajor = new JComboBox(groupdepartment);
        cbbmajor.setBounds(500, 150, 180, 30);
        add(cbbmajor);
//
        JLabel lbldegree = new JLabel("Class Id");
        lbldegree.setBounds(400, 200, 180, 30);
        add(lbldegree);
        tfclassid = new JTextField();
        tfclassid.setBounds(500, 200, 180, 30);
        add(tfclassid);

        JLabel lblfirstname = new JLabel("First name");
        lblfirstname.setBounds(50, 150, 100, 30);
        add((lblfirstname));
        tffirstname = new JTextField();
        tffirstname.setBounds(150, 150, 180, 30);
        add(tffirstname);

        JLabel lbllastname = new JLabel("Last name");
        lbllastname.setBounds(50, 200, 180, 30);
        add(lbllastname);
        tflastname = new JTextField();
        tflastname.setBounds(150, 200, 180, 30);
        add(tflastname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(50, 250, 180, 30);
        add(lblgender);

        male = new JRadioButton("Male");
        male.setBounds(150, 250, 80, 30);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(250, 250, 80, 30);
        add(female);
        group = new ButtonGroup();
        group.add(male);
        group.add(female);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(50, 300, 180, 30);
        add(lblphone);
        tfphone = new JTextField();
        tfphone.setBounds(150, 300, 180, 30);
        add(tfphone);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(400, 250, 180, 30);
        add(lbladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(500, 250, 180, 30);
        add(tfaddress);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(400, 300, 180, 30);
        add(lblemail);
        tfemail = new JTextField();
        tfemail.setBounds(500, 300, 180, 30);
        add(tfemail);

        btnsubmit = new JButton("Submit");
        btnsubmit.setBackground(Color.WHITE);
        btnsubmit.setBounds(250, 400, 100, 30);
        btnsubmit.addActionListener(this);
        add(btnsubmit);

        btncancel = new JButton("Cancel");
        btncancel.setBackground(Color.WHITE);
        btncancel.setBounds(400, 400, 100, 30);
        btncancel.addActionListener(this);
        add(btncancel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnsubmit) {

            String id = tfid.getText();
            String firstname = tffirstname.getText();
            String lastname = tflastname.getText();
            utilDate = dcdob.getDate();
            String gender = male.isSelected() ? "Male" : "Female";
            String major = (String) cbbmajor.getSelectedItem();
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
                Student st = new Student(id, firstname, lastname, new java.sql.Date(utilDate.getTime()), gender, major, classid, email, address, phone);
                studentDaoImpl = new StudentDaoImpl();
                try {
                    studentDaoImpl.insertStudent(st);
                    JOptionPane.showMessageDialog(this, "Add Student Successfully");
                    setNull();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Add Student Failed");

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
        dcdob.setDate(null); 
        group.clearSelection(); 
        tfclassid.setText("");
        tfemail.setText("");
        tfaddress.setText("");
        tfphone.setText("");
        cbbmajor.setSelectedIndex(0); 
    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
