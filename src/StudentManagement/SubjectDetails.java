/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import StudentManagement.dao.StudentDaoImpl;
import StudentManagement.dao.SubjectDaoImpl;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import StudentManagement.config.DBHelper;
import StudentManagement.model.Subject;

/**
 *
 * @author My Laptop
 */
public class SubjectDetails extends JFrame implements ActionListener {

    JTable table;
    JTextField tfsubject_name, tfsubject_id;
    JTextField tfacademicyear, tfcredits,tfteacher_id;
    JComboBox cbbsemester;
    JButton btnadd, btnupdate, btndelete, btnclear;
    private SubjectDaoImpl subjectDaoImpl;

    SubjectDetails() {

        setSize(1200, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Subject Details");
        heading.setBounds(50, 50, 200, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 20));
        add(heading);

        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(50, 400, 200, 30);
        add(lblsemester);

        String semester[] = {"Học kì 1", "Học kì 2"};
        cbbsemester = new JComboBox(semester);
        cbbsemester.setBounds(200, 400, 100, 30);
        add(cbbsemester);

        JLabel lblacademicyear = new JLabel("Academic year");
        lblacademicyear.setBounds(50, 450, 100, 30);
        add(lblacademicyear);
        tfacademicyear = new JTextField();
        tfacademicyear.setBounds(200, 450, 200, 30);
        add(tfacademicyear);
        
        JLabel lblteacher_id = new JLabel("Teacher Id");
        lblteacher_id.setBounds(50, 500, 100, 30);
        add(lblteacher_id);
        tfteacher_id = new JTextField();
        tfteacher_id.setBounds(200, 500, 200, 30);
        add(tfteacher_id);

        JLabel lblsubject = new JLabel("Subject Id");
        lblsubject.setBounds(50, 100, 200, 30);
        add(lblsubject);

        tfsubject_id = new JTextField();
        tfsubject_id.setBounds(50, 150, 350, 30);
        add(tfsubject_id);

        JLabel lblsubject_id = new JLabel("Subject Name");
        lblsubject_id.setBounds(50, 200, 200, 30);
        add(lblsubject_id);

        tfsubject_name = new JTextField();
        tfsubject_name.setBounds(50, 250, 350, 30);
        add(tfsubject_name);

        JLabel lblcredits = new JLabel("Credits");
        lblcredits.setBounds(50, 300, 200, 30);
        add(lblcredits);

        tfcredits = new JTextField();
        tfcredits.setBounds(50, 350, 350, 30);
        add(tfcredits);

        btnadd = new JButton("Add");
        btnadd.setBounds(50, 570, 80, 30);
        btnadd.addActionListener(this);
        add(btnadd);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(140, 570, 80, 30);
        btnupdate.addActionListener(this);
        add(btnupdate);

        btndelete = new JButton("Delete");
        btndelete.setBounds(230, 570, 80, 30);
        btndelete.addActionListener(this);
        add(btndelete);

        btnclear = new JButton("Clear");
        btnclear.setBounds(320, 570, 80, 30);
        btnclear.addActionListener(this);
        add(btnclear);

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(450, 100, 700, 500);
        add(jsp);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                String subjectid = table.getModel().getValueAt(row, 0).toString();
                String subjectname = table.getModel().getValueAt(row, 1).toString();
                String credits = table.getModel().getValueAt(row, 2).toString();
                String semester = table.getModel().getValueAt(row, 3).toString();
                String academicyear = table.getModel().getValueAt(row, 4).toString();
                String teacherid = table.getModel().getValueAt(row, 5).toString();
                tfsubject_id.setText(subjectid);
                tfsubject_name.setText(subjectname);
                tfcredits.setText(credits);
                tfacademicyear.setText(academicyear);
                cbbsemester.setSelectedItem(semester);
                tfteacher_id.setText(teacherid);
            }
        });

        loadData();

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String subjectid = tfsubject_id.getText();
        String subjectname = tfsubject_name.getText();
        String credits = tfcredits.getText();
        String academicyear = tfacademicyear.getText();
        String semester = (String) cbbsemester.getSelectedItem();
        String teacherid = tfteacher_id.getText();
        if (e.getSource() == btnadd) {
            if (subjectid.equals("") || subjectname.equals("")
                    || credits.equals("") || academicyear.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in all information");
            } else {
                Subject su = new Subject();
                su.setSubject_id(subjectid);
                su.setSubject_name(subjectname);
                su.setCredits(Integer.parseInt(credits));
                su.setSemester(semester);
                su.setAcademic_year(academicyear);
                su.setTeacher_id(teacherid);
                subjectDaoImpl = SubjectDaoImpl.getInstance();
                subjectDaoImpl.addSubject(su);
                JOptionPane.showMessageDialog(this, "Add subject successfully");
                setNull();
                loadData();
            }

        } else if (e.getSource() == btnupdate) {
            if (subjectid.equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose subject to update");
            } else {
                Connection c = DBHelper.getConnection();
                String query = "UPDATE subject SET subject_name = ?, credits = ?, semester = ?, academic_year = ?,teacher_id = ? WHERE subject_id = ?";
                try {
                    PreparedStatement ps = c.prepareStatement(query);
                    ps.setString(1, subjectname);
                    ps.setInt(2, Integer.parseInt(credits));
                    ps.setString(3, semester);
                    ps.setString(4, academicyear);
                    ps.setString(5, teacherid);
                    ps.setString(6, subjectid);
                    int result = ps.executeUpdate();
                    if (result != 0) {
                        JOptionPane.showMessageDialog(this, "Update subject successfully!");
                        setNull();
                        loadData();
                    }
                    ps.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        } else if (e.getSource() == btndelete) {
            subjectDaoImpl = SubjectDaoImpl.getInstance();
            subjectDaoImpl.deleteSubjectById(subjectid);
            JOptionPane.showMessageDialog(this, "Delete successfully!");
            loadData();

        } else if (e.getSource() == btnclear) {
            setNull();
        }

    }

    public static void main(String[] args) {
        new SubjectDetails();
    }

    public void loadData() {
        subjectDaoImpl = SubjectDaoImpl.getInstance();
        ResultSet rs;
        try {
            rs = subjectDaoImpl.fillSubjectWithData();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setNull() {
        tfsubject_id.setText("");
        tfsubject_name.setText("");
        tfcredits.setText("");
        tfacademicyear.setText("");
        cbbsemester.setSelectedIndex(0);
        tfteacher_id.setText("");
    }

}
