/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.model;

/**
 *
 * @author My Laptop
 */
public class Subject {
    private String subject_id;
    private String subject_name;
    private int credits;
    private String semester;
    private String academic_year;
    private String teacher_id;

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    

    
   

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public Subject(String subject_id, String subject_name, int credits, String semester, String academic_year, String teacher_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.credits = credits;
        this.semester = semester;
        this.academic_year = academic_year;
        this.teacher_id = teacher_id;
    }

    

    

    public Subject() {
    }

    @Override
    public String toString() {
        return "Subject{" + "subject_id=" + subject_id + ", subject_name=" + subject_name + ", credits=" + credits + ", semester=" + semester + ", academic_year=" + academic_year + ", teacher_id=" + teacher_id + '}';
    }

   

    
    
}
