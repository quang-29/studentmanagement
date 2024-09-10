package StudentManagement;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import StudentManagement.config.DBHelper;
import StudentManagement.dao.TeacherDaoImpl;

public class UpdateTeacher extends JFrame implements ActionListener {

    JTextField tfid, tffirstname, tflastname, tfemail, tfaddress, tfphonenumber,tfsearch;
    JButton btnupdate, btncancel, btnsearch, btnreset;
    JDateChooser dcdob;
    JComboBox<String> cbbdepartment, cbbdegree;
    JRadioButton male, female;
    ButtonGroup group;
    private String idTeacher;

    public UpdateTeacher(String idTeacher) {
        this.idTeacher = idTeacher;

        // Frame setup
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Teacher");
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
        cbbdepartment = new JComboBox<>(groupdepartment);
        cbbdepartment.setBounds(500, 150, 180, 30);
        add(cbbdepartment);

        JLabel lbldegree = new JLabel("Degree");
        lbldegree.setBounds(400, 200, 180, 30);
        add(lbldegree);
        String[] degree = {"Bachelor", "Master", "Doctor"};
        cbbdegree = new JComboBox<>(degree);
        cbbdegree.setBounds(500, 200, 180, 30);
        add(cbbdegree);

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

        if (!idTeacher.isEmpty()) {
            fillFormWithTeacherData(idTeacher);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateTeacher("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String searchById = tfsearch.getText();
        if (e.getSource() == btnupdate) {
            updateTeacherData();
            setNull();
        } else if (e.getSource() == btncancel) {
            setVisible(false);
        } else if (e.getSource() == btnsearch) {
            if (searchById.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in id to search teacher");
            } else {
                fillFormWithTeacherData(searchById);
            }
        } else if (e.getSource() == btnreset){
            setNull();
        }
    }

    public void fillFormWithTeacherData(String idTeacher) {
        try (Connection c = DBHelper.getConnection()) {
            String query = "SELECT * FROM teacher WHERE teacher_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, idTeacher);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfid.setText(rs.getString("teacher_id"));
                tffirstname.setText(rs.getString("first_name"));
                tflastname.setText(rs.getString("last_name"));
                dcdob.setDate(rs.getDate("dob"));
                group.setSelected(rs.getString("gender").equalsIgnoreCase("male") ? male.getModel() : female.getModel(), true);
                cbbdepartment.setSelectedItem(rs.getString("department"));
                cbbdegree.setSelectedItem(rs.getString("degree"));
                tfemail.setText(rs.getString("email"));
                tfaddress.setText(rs.getString("address"));
                tfphonenumber.setText(rs.getString("phone"));
                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateTeacherData() {
        String id = tfid.getText();
        String firstname = tffirstname.getText();
        String lastname = tflastname.getText();
        String gender = male.isSelected()? "Male": "Female";
        Date dob = new Date(dcdob.getDate().getTime());
        String department = (String) cbbdepartment.getSelectedItem();
        String degree = (String) cbbdegree.getSelectedItem();
        String phone = tfphonenumber.getText();
        String email = tfemail.getText();
        String address = tfaddress.getText();
        

        try (Connection c = DBHelper.getConnection()) {
            String query = "UPDATE teacher SET first_name = ?, last_name = ?, gender = ?, dob = ?, department = ?, degree = ?, phone = ?, email = ?, address = ? WHERE teacher_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, gender);
            ps.setDate(4, dob);
            ps.setString(5, department);
            ps.setString(6, degree);
            ps.setString(7, phone);
            ps.setString(8, email);
            ps.setString(9, address);   
            ps.setString(10, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update failed!");
            ex.printStackTrace();
        }
    }

    public void setNull() {
        tfid.setText("");
        tffirstname.setText("");
        tflastname.setText("");
        group.clearSelection(); // Clears the selected radio button for gender
        dcdob.setDate(null); // Resets the date chooser
        cbbdepartment.setSelectedIndex(0); // Resets the department combo box to the first option
        cbbdegree.setSelectedIndex(0); // Resets the degree combo box to the first option
        tfaddress.setText("");
        tfphonenumber.setText("");
        tfemail.setText("");
        tfsearch.setText("");
    }

}
