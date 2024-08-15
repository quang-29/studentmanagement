/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.dao;

import qlsv.config.DBHelper;
import qlsv.model.Teacher;
import java.sql.*;

/**
 *
 * @author My Laptop
 */
public class TeacherDaoImpl {

    public static TeacherDaoImpl getInstance() {
        return new TeacherDaoImpl();
    }

    public void insertTeacher(Teacher t) throws SQLException {
        Connection c = DBHelper.getConnection();
        String query = "INSERT INTO teachers (id, first_name, last_name, gender, phone_number, dob, department, degree, address, email, subject) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, t.getId());
        ps.setString(2, t.getFirst_name());
        ps.setString(3, t.getLast_name());
        ps.setString(4, t.getGender());
        ps.setString(5, t.getPhonenumber());
        ps.setDate(6, (Date) t.getDob());
        ps.setString(7, t.getDepartment());
        ps.setString(8, t.getDegree());
        ps.setString(9, t.getAddress());
        ps.setString(10, t.getEmail());
        ps.setString(11, t.getSubject());

        ps.executeUpdate();
        ps.close();
    }

    public ResultSet searchTeacherById(String id) {
        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM teachers WHERE id = ?";
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

    public void deleteTeacherById(String id) {
        Connection c = DBHelper.getConnection();
        try {
            String query = "DELETE FROM teachers WHERE id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
