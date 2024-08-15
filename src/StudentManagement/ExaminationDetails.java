/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import com.mysql.cj.xdevapi.Table;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Action;
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

/**
 *
 * @author My Laptop
 */
public class ExaminationDetails extends JFrame implements ActionListener {

    JTable table;
    JButton btnresult, btnback, btnsearch,btnshowall;
    JTextField id;

    public ExaminationDetails() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Examination Details");
        heading.setBounds(50, 50, 400, 30);
        heading.setFont(new Font("serif", Font.ITALIC, 20));
        add(heading);

        id = new JTextField();
        id.setBounds(50, 100, 200, 30);
        add(id);
        

        btnresult = new JButton("Result");
        btnresult.setBounds(600, 100, 100, 30);
        btnresult.addActionListener(this);
        add(btnresult);

        btnsearch = new JButton("Search");
        btnsearch.setBounds(300, 100, 100, 30);
        btnsearch.addActionListener(this);
        add(btnsearch);
        
        btnshowall = new JButton("Show all");
        btnshowall.setBounds(450, 100, 100, 30);
        btnshowall.addActionListener(this);
        add(btnshowall);

        btnback = new JButton("Back");
        btnback.setBounds(750, 100, 100, 30);
        btnback.addActionListener(this);
        add(btnback);

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 150, 1000, 700);
        add(jsp);
        loadData();
        
        table.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent me) {
                int row = table.getSelectedRow();
                id.setText(table.getModel().getValueAt(row, 0).toString());
                
            }
            
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ExaminationDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String tfid = id.getText();
        if (e.getSource() == btnresult) {
            new MarkResult(tfid);

        } else if (e.getSource() == btnback) {
            setVisible(false);
        } else if (e.getSource() == btnsearch) {

            if (tfid.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in id to search!");
            } else {
                Connection c = DBHelper.getConnection();
                try {
                    String query = "SELECT * FROM students WHERE student_id = ?";
                    PreparedStatement ps = c.prepareStatement(query);
                    ps.setString(1, tfid);
                    ResultSet rs = ps.executeQuery();
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else if (e.getSource() == btnshowall){
            loadData();
            id.setText("");
        }

    }

    private void loadData() {
       
            Connection c = DBHelper.getConnection();
        try {
            
            String query = "SELECT s.student_id,s.first_name, s.last_name,sub.subject_id, sub.subject_name, sc.score\n" +
                                "FROM scores sc\n" +
                                "INNER JOIN students s ON sc.student_id = s.student_id\n" +
                                "INNER JOIN subjects sub ON sc.subject_id = sub.subject_id;";
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
