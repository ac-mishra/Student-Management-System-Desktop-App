package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Model class representing Student Enrollment.
 */
public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, LocalDate enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
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

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Enrollment)) return false;
        Enrollment enrollment = (Enrollment) obj;
        return enrollmentId == enrollment.enrollmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentId);
    }
}