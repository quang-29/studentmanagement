/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv.model;

/**
 *
 * @author My Laptop
 */
public class Score {
    private int subject_id;
    private String student_id;
    private double score;

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Score(int subject_id, String student_id, double score) {
        this.subject_id = subject_id;
        this.student_id = student_id;
        this.score = score;
    }

    

    public Score() {
    }

    @Override
    public String toString() {
        return "Score{" + "subject_id=" + subject_id + ", student_id=" + student_id + ", score=" + score + '}';
    }

    
    
    
    
}
