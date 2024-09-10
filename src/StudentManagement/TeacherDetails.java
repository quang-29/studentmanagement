/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import com.mysql.cj.Query;
import java.awt.*;
import java.sql.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import StudentManagement.config.DBHelper;
import StudentManagement.dao.TeacherDaoImpl;
import StudentManagement.model.Teacher;

/**
 *
 * @author My Laptop
 */
public class TeacherDetails extends JFrame implements ActionListener {

    JTextField tfsearch;
    JButton btnadd, btnupdate, btnprint, btnsearch, btncancel, btndelete, btnshowall;
    JTable table;
    private TeacherDaoImpl teacherDaoImpl;

    public TeacherDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(1220, 800);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Search teacher by id");
        heading.setBounds(50, 50, 150, 30);
        add(heading);

        tfsearch = new JTextField();
        tfsearch.setBounds(200, 50, 150, 30);
        add(tfsearch);

        btnsearch = new JButton("Search");
        btnsearch.setBounds(50, 100, 100, 30);

        btnsearch.addActionListener(this);
        add(btnsearch);

        btnadd = new JButton("Add");
        btnadd.setBounds(200, 100, 100, 30);

        btnadd.addActionListener(this);

        add(btnadd);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(350, 100, 100, 30);

        btnupdate.addActionListener(this);

        add(btnupdate);

        btndelete = new JButton("Delete");
        btndelete.setBounds(500, 100, 100, 30);

        btndelete.addActionListener(this);

        add(btndelete);

        btnprint = new JButton("Print");
        btnprint.setBounds(650, 100, 100, 30);
        
        btnprint.addActionListener(this);

        add(btnprint);

        btnshowall = new JButton("Show all");
        btnshowall.setBounds(800, 100, 100, 30);
        btnshowall.addActionListener(this);

        add(btnshowall);

        btncancel = new JButton("Cancel");
        btncancel.setBounds(950, 100, 100, 30);

        btncancel.addActionListener(this);

        add(btncancel);

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 1200, 700);
        add(jsp);

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                tfsearch.setText(table.getModel().getValueAt(row, 0).toString());

            }

        });

        loadData();

        setVisible(true);
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String teacherid = tfsearch.getText();
        if (e.getSource() == btnsearch) {

            if (teacherid.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in id!");
            } else {
                teacherDaoImpl = TeacherDaoImpl.getInstance();
                try (ResultSet rs = teacherDaoImpl.searchTeacherById(teacherid)) {
                    table.setModel(DbUtils.resultSetToTableModel(rs)); // Assuming this does not consume the ResultSet fully

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (e.getSource() == btnupdate) {
            new UpdateTeacher(teacherid);

        } else if (e.getSource() == btnadd) {
            new AddTeacher();
        } else if (e.getSource() == btnprint) {
            try {
                table.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == btndelete) {
            deleteSelectedRow();

        } else if (e.getSource() == btnshowall) {
            loadData();
            tfsearch.setText("");

        } else if (e.getSource() == btncancel) {
            setVisible(false);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String selectedid = (String) table.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {

                teacherDaoImpl = TeacherDaoImpl.getInstance();
                teacherDaoImpl.deleteTeacherById(selectedid);
                loadData();
                tfsearch.setText("");
                JOptionPane.showMessageDialog(this, "Delete successfully");

            }
        } else {
            JOptionPane.showMessageDialog(this, "You have not selected any records");
        }
    }

    private void loadData() {
        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM teacher";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
