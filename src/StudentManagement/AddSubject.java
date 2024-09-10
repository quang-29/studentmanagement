/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import StudentManagement.dao.SubjectDaoImpl;
import StudentManagement.model.Subject;

/**
 *
 * @author My Laptop
 */
public class AddSubject extends JFrame implements ActionListener{

    
    JTextField tfsubject_name, tfsubject_id,tfteacher_id;
    JTextField tfacademicyear,tfcredits;
    JComboBox cbbsemester;
    JButton btnsubmit, btncancel;
    private SubjectDaoImpl subjectDaoImpl;

    public AddSubject() {
        
        
         setSize(900, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon8.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 50, 400, 400);
        add(image);

        JLabel heading = new JLabel("Add Subject");
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
        lblacademicyear.setBounds(50, 450, 200, 30);
        add(lblacademicyear);
        tfacademicyear = new JTextField();
        tfacademicyear.setBounds(50, 500, 300, 30);
        add(tfacademicyear);

        JLabel lblsubject = new JLabel("Subject Id");
        lblsubject.setBounds(50, 100, 200, 30);
        add(lblsubject);

        tfsubject_id = new JTextField();
        tfsubject_id.setBounds(50, 150, 300, 30);
        add(tfsubject_id);

        JLabel lblsubject_id = new JLabel("Subject Name");
        lblsubject_id.setBounds(50, 200, 200, 30);
        add(lblsubject_id);

        tfsubject_name = new JTextField();
        tfsubject_name.setBounds(50, 250, 300, 30);
        add(tfsubject_name);
        
        
         JLabel lblcredits = new JLabel("Credits");
        lblcredits.setBounds(50, 300, 200, 30);
        add(lblcredits);

        tfcredits = new JTextField();
        tfcredits.setBounds(50, 350, 300, 30);
        add(tfcredits);

       JLabel lblteacher_id = new JLabel("Teacher Id");
        lblteacher_id.setBounds(50, 550, 100, 30);
        add(lblteacher_id);

        tfteacher_id = new JTextField();
        tfteacher_id.setBounds(200, 550, 150, 30);
        add(tfteacher_id);
        

        btnsubmit = new JButton("Submit");
        btnsubmit.setBounds(50, 600, 100, 30);
        btnsubmit.addActionListener(this);
        add(btnsubmit);

        btncancel = new JButton("Cancel");
        btncancel.setBounds(200, 600, 100, 30);
        btncancel.addActionListener(this);
        add(btncancel);

        setVisible(true);
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == btnsubmit){
           String subjectid = tfsubject_id.getText();
           String subjectname = tfsubject_name.getText();
           String credits = tfcredits.getText();
           String semester = (String) cbbsemester.getSelectedItem();
           String academicyear = tfacademicyear.getText();
           String teacherid = tfteacher_id.getText();
           
           if (subjectid.equals("") || subjectname.equals("") || credits.equals("") ||
                   academicyear.equals("")){
               JOptionPane.showMessageDialog(this, "Please fill in all information!");
           } else {
               Subject sub = new Subject();
               sub.setSubject_name(subjectname);
               sub.setSubject_id(subjectid);
               sub.setSemester(semester);
               sub.setCredits(Integer.parseInt(credits));
               sub.setAcademic_year(academicyear);
               sub.setTeacher_id(teacherid);
               subjectDaoImpl = SubjectDaoImpl.getInstance();
               subjectDaoImpl.insertSubject(sub);
               JOptionPane.showMessageDialog(this, "Add subject successfully");
           }
       } else if (e.getSource() == btncancel){
           setVisible(false);
       }
    }
    public static void main(String[] args) {
        new AddSubject();
    }
}
