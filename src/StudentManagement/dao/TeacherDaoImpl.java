/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.dao;

import StudentManagement.config.DBHelper;
import StudentManagement.model.Teacher;
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
        String query = "INSERT INTO teacher (teacher_id, first_name, last_name,dob, gender,department, degree,email, address,phone) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(query);
        ps.setString(1, t.getId());
        ps.setString(2, t.getFirst_name());
        ps.setString(3, t.getLast_name());
        ps.setDate(4, (Date) t.getDob());
        ps.setString(6, t.getDepartment());
        ps.setString(7, t.getDegree());
        ps.setString(8, t.getEmail());
        ps.setString(9, t.getAddress());
        ps.setString(10, t.getPhonenumber());
        ps.executeUpdate();
        ps.close();
    }

    public ResultSet searchTeacherById(String id) {
        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM teacher WHERE teacher_id = ?";
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
            String query = "DELETE FROM teacher WHERE teacher_id = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
