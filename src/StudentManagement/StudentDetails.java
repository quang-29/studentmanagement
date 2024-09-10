/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import com.mysql.cj.Query;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import StudentManagement.config.DBHelper;
import StudentManagement.dao.StudentDaoImpl;

/**
 *
 * @author My Laptop
 */
public class StudentDetails extends JFrame implements ActionListener {

    Choice studentnumber;
    JTextField tfstudentnumber;
    JTable table;
    JButton search, print, update, add, cancel, delete, showall,btnshowall;
    String studentid;
    private StudentDaoImpl studentDaoImpl;

    StudentDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        setSize(1220, 800);
        setLocationRelativeTo(null);

        JLabel searchnumber = new JLabel("Search by Student Number");
        searchnumber.setBounds(40, 50, 200, 30);
        add(searchnumber);

        search = new JButton("Search");
        search.setBounds(20, 100, 80, 20);
        search.addActionListener(this);
        add(search);

        update = new JButton("Update");
        update.setBounds(120, 100, 80, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(640, 100, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        print = new JButton("Print");
        print.setBounds(320, 100, 80, 20);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(220, 100, 80, 20);
        add.addActionListener(this);
        add(add);
        
        btnshowall = new JButton("Show all");
        btnshowall.setBounds(520, 100, 100, 20);
        btnshowall.addActionListener(this);

        add(btnshowall);

        delete = new JButton("Delete");
        delete.setBounds(420, 100, 80, 20);
        delete.addActionListener(this);
        add(delete);

        tfstudentnumber = new JTextField();
        tfstudentnumber.setBounds(300, 50, 200, 30);
        add(tfstudentnumber);
        

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 1200, 500);
        add(jsp);
        
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                int row = table.getSelectedRow();
                studentid = table.getModel().getValueAt(row, 0).toString(); 
                tfstudentnumber.setText(studentid);
            }
        });
        

        loadData();

        setVisible(true);

    }

    public static void main(String[] args) {
        new StudentDetails();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String studentid = tfstudentnumber.getText();
        if (e.getSource() == search) {
            studentDaoImpl = StudentDaoImpl.getInstance();
            try(ResultSet rs = studentDaoImpl.searchStudentById(studentid)){
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
  
        } else if (e.getSource() == update) {
            new UpdateStudent(studentid);

        } else if (e.getSource() == add) {
            new AddStudent();

        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == delete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String studentnumberselected = (String) table.getValueAt(selectedRow, 0);

                int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure to delete this record", "Confirm deletion", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    
                    studentDaoImpl = new StudentDaoImpl();
                    studentDaoImpl.deleteStudentById(studentnumberselected);
                    loadData();
                    JOptionPane.showMessageDialog(this, "Delete successfully");
                }

            } else {
                JOptionPane.showMessageDialog(this, "You have not selected any records");

            }
        } else if (e.getSource() == cancel) {
            setVisible(false);

        } else if (e.getSource() == btnshowall){
            loadData();
            tfstudentnumber.setText("");
        }
    }

    private void loadData() {
        Connection c = DBHelper.getConnection();
        String query = "SELECT * FROM student";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
