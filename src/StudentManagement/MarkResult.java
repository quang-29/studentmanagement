
package StudentManagement;
import java.awt.*;

import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import StudentManagement.config.DBHelper;

public class MarkResult extends JFrame implements ActionListener {

    String studentnum;
    JButton cancel;
    JPanel subjectPanel;
    JScrollPane scrollPane;

    MarkResult(String studentnum) {
        this.studentnum = studentnum;

        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Technical University");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination ");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel studentnumber = new JLabel("Student number " + studentnum);
        studentnumber.setBounds(60, 100, 500, 20);
        studentnumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(studentnumber);

        // Tạo JPanel chứa các subject labels
        subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.Y_AXIS));

        // Thêm JScrollPane để cuộn nếu có nhiều môn học
        scrollPane = new JScrollPane(subjectPanel);
        scrollPane.setBounds(60, 150, 360, 300);
        add(scrollPane);

        loadSubjects();

        cancel = new JButton("Back");
        cancel.setBounds(250, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    private void loadSubjects() {
        try {
            Connection c = DBHelper.getConnection();
            String query = "SELECT s.student_id, s.first_name, s.last_name, s.class_id, sub.subject_name, sc.score "
                            + "FROM score sc "
                            + "INNER JOIN student s ON sc.student_id = s.student_id "
                            + "INNER JOIN subject sub ON sc.subject_id = sub.subject_id "
                            + "WHERE s.student_id = ?";
            PreparedStatement ps1 = c.prepareStatement(query);
            ps1.setString(1, studentnum);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                // Tạo JLabel mới cho mỗi môn học
                String subjectInfo = rs1.getString("subject_name") + " - Score: " + rs1.getString("score");
                JLabel subjectLabel = new JLabel(subjectInfo);
                subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
                
                // Thêm JLabel vào JPanel
                subjectPanel.add(subjectLabel);
            }

            // Cập nhật giao diện để hiển thị các labels mới
            subjectPanel.revalidate();
            subjectPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new MarkResult("CT050001");
    }
}
