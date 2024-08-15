/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author My Laptop
 */
public class Home extends JFrame implements ActionListener {
    
    JMenuItem studentInfo, facilityInfor, studentDetails, facilityDetails, updateFacilityInfor, updateStudentInfor;
    JMenu newInformation;
    JMenuItem enterMarks, examinationDetails, subjectDetails, addSubject;
    
    Home() {
        setSize(1540, 850);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/amazing.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        mb.setSize(1540, 300);
        
        newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.BLACK);
        mb.add(newInformation);
        
        facilityInfor = new JMenuItem("New Teacher Information");
        newInformation.add(facilityInfor);
        facilityInfor.addActionListener(this);
        
        studentInfo = new JMenuItem("New Student Information");
        newInformation.add(studentInfo);
        studentInfo.addActionListener(this);
        
        addSubject = new JMenuItem("New subject");
        addSubject.addActionListener(this);
        newInformation.add(addSubject);
        
        JMenu viewDetails = new JMenu("View Details");
        viewDetails.setForeground(Color.BLACK);
        mb.add(viewDetails);
        
        facilityDetails = new JMenuItem("View Teacher Details");
        viewDetails.add(facilityDetails);
        facilityDetails.addActionListener(this);
        
        studentDetails = new JMenuItem("View Student Details");
        viewDetails.add(studentDetails);
        studentDetails.addActionListener(this);
        
        subjectDetails = new JMenuItem("Subject Details");
        subjectDetails.addActionListener(this);
        viewDetails.add(subjectDetails);
        
        
        JMenu exam = new JMenu("Examination Details");
        exam.setForeground(Color.BLACK);
        mb.add(exam);
        
        examinationDetails = new JMenuItem("Examination Results");
        examinationDetails.addActionListener(this);
        exam.add(examinationDetails);
        
        enterMarks = new JMenuItem("Enter marks");
        enterMarks.addActionListener(this);
        exam.add(enterMarks);
        
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.BLACK);
        mb.add(updateInfo);
        
        updateFacilityInfor = new JMenuItem("Update Teacher Details");
        updateInfo.add(updateFacilityInfor);
        updateFacilityInfor.addActionListener(this);
        
        updateStudentInfor = new JMenuItem("Update Student Details");
        updateInfo.add(updateStudentInfor);
        updateStudentInfor.addActionListener(this);
        
        JMenu feeDetails = new JMenu("Fee Details");
        feeDetails.setForeground(Color.BLACK);
        mb.add(feeDetails);
        
        JMenuItem feeStructure = new JMenuItem("Fee structure");
        feeDetails.add(feeStructure);
        
        JMenuItem feeForm = new JMenuItem("Student Fee Form");
        feeDetails.add(feeForm);
        
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLACK);
        mb.add(utility);
        
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.addActionListener(this);
        utility.add(notepad);
        
        JMenuItem calc = new JMenuItem("Calculator");
        calc.addActionListener(this);
        utility.add(calc);
        
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.BLACK);
        mb.add(exit);
        
        JMenuItem ex = new JMenuItem("Exit");
        exit.add(ex);
        
        setJMenuBar(mb);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Home();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String msg = e.getActionCommand();
        
        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (IOException ex) {
                
            }
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (IOException ex) {
                
            }
        }
        if (e.getSource() == studentInfo) {
            new AddStudent();
        } else if (e.getSource() == studentDetails) {
            new StudentDetails();
        } else if (e.getSource() == facilityInfor) {
            new AddTeacher();
        } else if (e.getSource() == facilityDetails) {
            new TeacherDetails();
            
        } else if (e.getSource() == updateFacilityInfor) {
            new UpdateTeacher("");
        } else if (e.getSource() == updateStudentInfor) {
            new UpdateStudent("");
        } else if (e.getSource() == enterMarks) {
            new EnterMark();
        } else if (e.getSource() == examinationDetails){
            new ExaminationDetails();
        } else if (e.getSource() == addSubject){
            new AddSubject();
        } else if (e.getSource() == subjectDetails){
            new SubjectDetails();
        }
    }
}
