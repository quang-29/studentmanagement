/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

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
import qlsv.config.DBHelper;
import qlsv.dao.StudentDaoImpl;

/**
 *
 * @author My Laptop
 */
public class UpdateStudent extends JFrame implements ActionListener {

    JTextField tfaddress, tfphone, tfemail, tfsearch;
    JButton btnupdate, btncancel, btnseacrch, btnreset;
    String idstudent;
    JTextField tfid, tffirstname, tflastname, tfclassid;
    JDateChooser dcdob;
    java.sql.Date dob;
    JComboBox cbcourse;
    ButtonGroup group;
    private StudentDaoImpl studentDaoImpl;
    JRadioButton male, female;

    public UpdateStudent(String idstudent) {
        this.idstudent = idstudent;
        setSize(900, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Student");
        heading.setBounds(50, 20, 200, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 30));

        add(heading);

        tfsearch = new JTextField();
        tfsearch.setBounds(300, 40, 180, 30);
        tfsearch.setFont(new Font("serif", Font.BOLD, 20));
        add(tfsearch);

        btnseacrch = new JButton("Search");
        btnseacrch.setBounds(500, 40, 100, 30);
        btnseacrch.addActionListener(this);
        add(btnseacrch);

        btnreset = new JButton("Reset");
        btnreset.setBounds(620, 40, 100, 30);
        btnreset.addActionListener(this);
        add(btnreset);

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
        male.setBounds(200, 300, 80, 30);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(300, 300, 80, 30);
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

        btnupdate = new JButton("Update");
        btnupdate.setBounds(300, 600, 120, 30);
        btnupdate.setBackground(Color.BLACK);
        btnupdate.setForeground(Color.WHITE);
        btnupdate.addActionListener(this);
        btnupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(btnupdate);

        btncancel = new JButton("Cancel");
        btncancel.setBounds(500, 600, 120, 30);
        btncancel.setBackground(Color.BLACK);
        btncancel.setForeground(Color.WHITE);
        btncancel.addActionListener(this);
        btncancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(btncancel);

        if (!idstudent.equals("")) {
            fillFormWithStudentData(idstudent);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateStudent("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnseacrch) {
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
        cbcourse.setSelectedIndex(0);
        tfaddress.setText("");
        tfphone.setText("");
        dcdob.setDate(null);
        tfsearch.setText("");
    }

    private void updateStudentData() {
        String id = tfid.getText();
        String firstname = tffirstname.getText();
        String lastname = tflastname.getText();
        String gender = group.getSelection().getActionCommand();
        Date dob = new Date(dcdob.getDate().getTime());
        String classid = tfclassid.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String address = tfaddress.getText();
        String major = (String) cbcourse.getSelectedItem();

        try (Connection c = DBHelper.getConnection()) {
            String query = "UPDATE students SET first_name = ?, last_name = ?, gender = ?, date_of_birth = ?, class_id = ?, phone = ?, email = ?, address = ?, major = ? WHERE id = ?";
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
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, studentid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tfid.setText(rs.getString("id"));
                tffirstname.setText(rs.getString("first_name"));
                tflastname.setText(rs.getString("last_name"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfclassid.setText(rs.getString("class_id"));
                tfaddress.setText(rs.getString("address"));
                cbcourse.setSelectedItem(rs.getString("major"));
                dcdob.setDate(rs.getDate("date_of_birth"));
                group.setSelected(rs.getString("gender").equalsIgnoreCase("male") ? male.getModel() : female.getModel(), true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
