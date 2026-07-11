package org.example.model;

import java.util.Objects;

public class Marks {

    private int marksId;

    private int studentId;

    private int courseId;

    private int semester;

    private double internalMarks;

    private double externalMarks;

    private double totalMarks;

    private String grade;

    public Marks() {
    }

    public Marks(int studentId,
                 int courseId,
                 int semester,
                 double internalMarks,
                 double externalMarks,
                 double totalMarks,
                 String grade) {

        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;
        this.totalMarks = totalMarks;
        this.grade = grade;

    }

    public Marks(int marksId,
                 int studentId,
                 int courseId,
                 int semester,
                 double internalMarks,
                 double externalMarks,
                 double totalMarks,
                 String grade) {

        this.marksId = marksId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;
        this.totalMarks = totalMarks;
        this.grade = grade;

    }

    public int getMarksId() {
        return marksId;
    }

    public void setMarksId(int marksId) {
        this.marksId = marksId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getInternalMarks() {
        return internalMarks;
    }

    public void setInternalMarks(double internalMarks) {
        this.internalMarks = internalMarks;
    }

    public double getExternalMarks() {
        return externalMarks;
    }

    public void setExternalMarks(double externalMarks) {
        this.externalMarks = externalMarks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Marks))
            return false;

        Marks marks = (Marks) obj;

        return marksId == marks.marksId;

    }

    @Override
    public int hashCode() {

        return Objects.hash(marksId);

    }

}