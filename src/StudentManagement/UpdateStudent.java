/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import StudentManagement.config.DBHelper;
import StudentManagement.dao.StudentDaoImpl;

/**
 *
 * @author My Laptop
 */
public class UpdateStudent extends JFrame implements ActionListener {

    JTextField tfaddress, tfphone, tfemail, tfsearch;
    JButton btnupdate, btncancel, btnsearch, btnreset;
    String idstudent;
    JTextField tfid, tffirstname, tflastname, tfclassid;
    JDateChooser dcdob;
    java.sql.Date dob;
    JComboBox cbbmajor;
    ButtonGroup group;
    private StudentDaoImpl studentDaoImpl;
    JRadioButton male, female;

    public UpdateStudent(String idstudent) {
        this.idstudent = idstudent;

        // Frame setup
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Student");
        heading.setBounds(50, 20, 180, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 20));
        add(heading);

        tfsearch = new JTextField();
        tfsearch.setBounds(200, 50, 180, 30);
        add(tfsearch);
        btnsearch = new JButton("Search");
        btnsearch.setBounds(400, 50, 100, 30);
        btnsearch.addActionListener(this);
        add(btnsearch);

        btnreset = new JButton("Reset");
        btnreset.setBounds(520, 50, 100, 30);
        btnreset.addActionListener(this);
        add(btnreset);

        JLabel lblid = new JLabel("Teacher ID");
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

        JLabel department = new JLabel("Department");
        department.setBounds(400, 150, 180, 30);
        add(department);
        String[] groupdepartment = {"Information Technology", "Information Security", "Electronics and Telecommunication"};
        cbbmajor = new JComboBox<>(groupdepartment);
        cbbmajor.setBounds(500, 150, 180, 30);
        add(cbbmajor);

        JLabel lblclassid = new JLabel("Class Id");
        lblclassid.setBounds(400, 200, 180, 30);
        add(lblclassid);
        tfclassid = new JTextField();
        tfclassid.setBounds(500, 200, 180, 30);
        add(tfclassid);

        JLabel lblfirstname = new JLabel("First name");
        lblfirstname.setBounds(50, 150, 100, 30);
        add(lblfirstname);
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

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(400, 250, 180, 30);
        add(lbladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(500, 250, 180, 30);
        add(tfaddress);

        JLabel phonenumber = new JLabel("Phone");
        phonenumber.setBounds(50, 300, 180, 30);
        add(phonenumber);
        tfphone = new JTextField();
        tfphone.setBounds(150, 300, 180, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(400, 300, 180, 30);
        add(lblemail);
        tfemail = new JTextField();
        tfemail.setBounds(500, 300, 180, 30);
        add(tfemail);


        btnupdate = new JButton("Update");
        btnupdate.setBackground(Color.WHITE);
        btnupdate.setBounds(250, 400, 100, 30);
        btnupdate.addActionListener(this);
        add(btnupdate);

        btncancel = new JButton("Cancel");
        btncancel.setBackground(Color.WHITE);
        btncancel.setBounds(400, 400, 100, 30);
        btncancel.addActionListener(this);
        add(btncancel);

        if (!idstudent.isEmpty()) {
            fillFormWithStudentData(idstudent);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateStudent("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnsearch) {
            String studentid = tfsearch.getText();
            if (studentid.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter student id to search!");
            } else {
                fillFormWithStudentData(studentid);
            }

        } else if (e.getSource() == btnupdate) {
            if (tfid.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "No student to update!");
            } else {
                updateStudentData();
                setNull();
            }

        } else if (e.getSource() == btncancel) {
            setVisible(false);
        } else if (e.getSource() == btnreset) {
            setNull();
        }
    }

    private void setNull() {
        tfid.setText("");
        tffirstname.setText("");
        tflastname.setText("");
        group.clearSelection();
        tfclassid.setText("");
        tfemail.setText("");
        cbbmajor.setSelectedIndex(0);
        tfaddress.setText("");
        tfphone.setText("");
        dcdob.setDate(null);
        tfsearch.setText("");
    }

    private void updateStudentData() {
        String id = tfid.getText();
        String firstname = tffirstname.getText();
        String lastname = tflastname.getText();
        String gender = male.isSelected()?"Male" : "Female";
        Date dob = new Date(dcdob.getDate().getTime());
        String classid = tfclassid.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String address = tfaddress.getText();
        String major = (String) cbbmajor.getSelectedItem();

        try (Connection c = DBHelper.getConnection()) {
            String query = "UPDATE student SET first_name = ?, last_name = ?, gender = ?, dob = ?, class_id = ?, phone = ?, email = ?, address = ?, major = ? WHERE student_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, gender);
            ps.setDate(4, dob);
            ps.setString(5, classid);
            ps.setString(6, phone);
            ps.setString(7, email);
            ps.setString(8, address);
            ps.setString(9, major);
            ps.setString(10, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Update failed!");
        }
    }

    private void fillFormWithStudentData(String studentid) {
        // Retrieve student data from DB using studentid
        try (Connection c = DBHelper.getConnection()) {
            String query = "SELECT * FROM student WHERE student_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, studentid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tfid.setText(rs.getString("student_id"));
                tffirstname.setText(rs.getString("first_name"));
                tflastname.setText(rs.getString("last_name"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfclassid.setText(rs.getString("class_id"));
                tfaddress.setText(rs.getString("address"));
                cbbmajor.setSelectedItem(rs.getString("major"));
                dcdob.setDate(rs.getDate("dob"));
                group.setSelected(rs.getString("gender").equalsIgnoreCase("male") ? male.getModel() : female.getModel(), true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
