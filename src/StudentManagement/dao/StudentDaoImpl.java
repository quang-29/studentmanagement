/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.dao;

import java.util.List;
import StudentManagement.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import StudentManagement.config.DBHelper;

/**
 *
 * @author My Laptop
 */
public class StudentDaoImpl {

    public static StudentDaoImpl getInstance() {
        return new StudentDaoImpl();
    }

    public void insertStudent(Student st) throws SQLException {
        Connection c = DBHelper.getConnection();
        String query = "INSERT INTO student (student_id, first_name, last_name, dob, gender, major, class_id, email, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, st.getId());
        ps.setString(2, st.getFirst_name());
        ps.setString(3, st.getLast_name());
        ps.setDate(4, st.getDob());
        ps.setString(5, st.getGender());
        ps.setString(6, st.getMajor());
        ps.setString(7, st.getClass_id());  // Corrected index
        ps.setString(8, st.getEmail());
        ps.setString(9, st.getAddress());
        ps.setString(10, st.getPhone());
        ps.executeUpdate();
        ps.close();
    }

    public List<Student> getStudentById(String id) {
        List<Student> lists = null;
        Connection connection = DBHelper.getConnection();
        String query = "SELECT * FROM student WHERE student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                Student st = new Student();
//                st.setStudentNumber(rs.getString("studentnumber"));
//                st.setName(rs.getString("name"));
//                st.setPhoneNumber(rs.getString("phone"));
//                st.setMajor(rs.getString("major"));
//                st.setEmail(rs.getString("email"));
//                st.setDob(rs.getString("dob"));
//                st.setClassName(rs.getString("class"));
//                st.setAddress(rs.getString("address"));
//
//                lists.add(st);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lists;
    }

    public ResultSet searchStudentById(String id) {

        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM student WHERE student_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void deleteStudentById(String id) {
        Connection c = DBHelper.getConnection();
        String query = "DELETE FROM student WHERE student_id = ?";
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
