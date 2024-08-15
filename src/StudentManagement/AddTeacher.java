/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import StudentManagement.dao.TeacherDaoImpl;
import StudentManagement.model.Teacher;

/**
 *
 * @author My Laptop
 */
public class AddTeacher extends JFrame implements ActionListener {

    JTextField tfid, tffirstname,tflastname, tfaddress, tfphonenumber, tfemail, tfsubject;
    JDateChooser dcdob;
    JComboBox cbbdepartment, cbbdegree;
    JButton btnsubmit, btncancel;
    JRadioButton male, female;
    ButtonGroup group;
    java.util.Date utilDate;
    private TeacherDaoImpl teacherDaoImpl;

    AddTeacher() {
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Teacher");
        heading.setBounds(50, 50, 150, 30);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading);

        JLabel lblid = new JLabel("Teacher id");
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
        String groupdepartment[] = {"Information Technology", "Information Security", "Electronics and Telecommunication"};
        cbbdepartment = new JComboBox(groupdepartment);
        cbbdepartment.setBounds(500, 150, 180, 30);
        add(cbbdepartment);

        JLabel lbldegree = new JLabel("Degree");
        lbldegree.setBounds(400, 200, 180, 30);
        add(lbldegree);
        String degree[] = {"Bachelor", "Master", "Doctor"};
        cbbdegree = new JComboBox(degree);
        cbbdegree.setBounds(500, 200, 180, 30);
        add(cbbdegree);


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
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(400, 250, 180, 30);
        add(lbladdress);
        tfaddress = new JTextField();
        tfaddress.setBounds(500, 250, 180, 30);
        add(tfaddress);
        

        JLabel phonenumber = new JLabel("Phone Number");
        phonenumber.setBounds(50, 300, 180, 30);
        add(phonenumber);
        tfphonenumber = new JTextField();
        tfphonenumber.setBounds(150, 300, 180, 30);
        add(tfphonenumber);
        
        
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(400, 300, 180, 30);
        add(lblemail);
        tfemail = new JTextField();
        tfemail.setBounds(500, 300, 180, 30);
        add(tfemail);
        
        
        
        JLabel lblsubject = new JLabel("Subject");
        lblsubject.setBounds(50, 350, 180, 30);
        add(lblsubject);
        tfsubject = new JTextField();
        tfsubject.setBounds(150, 350, 180, 30);
        add(tfsubject);
        

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

    public static void main(String[] args) {
        new AddTeacher();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnsubmit) {
            String id = tfid.getText();
            String firstname = tffirstname.getText();
            String lastname = tflastname.getText();
            String gender = male.isSelected() ? "Male" : "Female";
            String phonenumber = tfphonenumber.getText();
            utilDate = dcdob.getDate() ;
            String department = (String) cbbdepartment.getSelectedItem();
            String degree = (String) cbbdegree.getSelectedItem();
            String address = tfaddress.getText();
            String email = tfemail.getText();
            String subject = tfsubject.getText();
            if (id.equals("") || firstname.equals("")
                    || lastname.equals("") || phonenumber.equals("")
                    || address.equals("") || group.getSelection() == null 
                    || utilDate == null || email.equals("") || subject.equals("") ) {
                JOptionPane.showMessageDialog(this, "Please fill in all information");
            } else {
                
                Teacher t = new Teacher();
                t.setId(id);
                t.setFirst_name(firstname);
                t.setLast_name(lastname);
                t.setPhonenumber(phonenumber);
                t.setDob(new java.sql.Date(utilDate.getTime()));
                t.setAddress(address);
                t.setDepartment(department);
                t.setDegree(degree);
                t.setGender(gender);
                t.setEmail(email);
                t.setSubject(subject);
                teacherDaoImpl = new TeacherDaoImpl();
                try {
                    teacherDaoImpl.insertTeacher(t);
                    JOptionPane.showMessageDialog(this, "Add teacher successfully");
                    setNull();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }     
            }
        } else if (e.getSource() == btncancel) {
            setVisible(false);
        }
    }

    private void setNull() {
        
        tfid.setText("");
        tffirstname.setText("");
        tflastname.setText("");
        tfaddress.setText("");
        tfphonenumber.setText("");
        dcdob.setDate(null);
        group.clearSelection();
        cbbdepartment.setSelectedIndex(0);
        cbbdegree.setSelectedIndex(0);
        tfemail.setText("");
        tfsubject.setText("");
    }

}

