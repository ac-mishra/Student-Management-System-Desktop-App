package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Model class representing Attendance.
 */
public class Attendance {

    private int attendanceId;
    private int studentId;
    private int courseId;
    private LocalDate attendanceDate;
    private String status;

    public Attendance() {
    }

    public Attendance(int studentId, int courseId,
                      LocalDate attendanceDate, String status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public Attendance(int attendanceId, int studentId,
                      int courseId,
                      LocalDate attendanceDate,
                      String status) {

        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
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

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Attendance)) return false;
        Attendance attendance = (Attendance) obj;
        return attendanceId == attendance.attendanceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceId);
    }
}