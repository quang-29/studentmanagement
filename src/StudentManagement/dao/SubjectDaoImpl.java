/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import StudentManagement.config.DBHelper;
import StudentManagement.model.Subject;

/**
 *
 * @author My Laptop
 */
public class SubjectDaoImpl {
    public static SubjectDaoImpl getInstance(){
        return new SubjectDaoImpl();
    }
    
    
    public void insertSubjectById(Subject sub){
        Connection c = DBHelper.getConnection();
        try {
            String query = "INSERT INTO subjects (subject_id, subject_name,credits, semester,academic_year) VALUES (?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, sub.getSubject_id());
            ps.setString(2, sub.getSubject_name());
            ps.setInt(3, sub.getCredits());
            ps.setString(4, sub.getSemester());
            ps.setString(5, sub.getAcademic_year());
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet fillSubjectWithData() throws SQLException{
        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM subjects";
        PreparedStatement ps = c.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    public void deleteSubjectById(String id){
        Connection c = DBHelper.getConnection();
        String query = "DELETE FROM subjects WHERE subject_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
    }
    
    public void addSubject(Subject su){
        Connection c = DBHelper.getConnection();
        String query = "INSERT INTO subjects (subject_id,subject_name, credits, semester, academic_year) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, su.getSubject_id());
            ps.setString(2, su.getSubject_name());
            ps.setInt(3, su.getCredits());
            ps.setString(4, su.getSemester());
            ps.setString(5, su.getAcademic_year());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
        }
    }
}
