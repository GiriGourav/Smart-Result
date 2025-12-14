package com.substring.smartresult.entities;

import jakarta.persistence.*;

@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String subject;

    private int marks;

    private int maxMarks;

    private String remark;

//    private String grade;
    private String answerSheetPath;


    @ManyToOne
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public String getGrade() {
//        return grade;
//    }

//    public void setGrade(String grade) {
//        this.grade = grade;
//    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAnswerSheetPath() {
        return answerSheetPath;
    }

    public void setAnswerSheetPath(String answerSheetPath) {
        this.answerSheetPath = answerSheetPath;
    }
}
