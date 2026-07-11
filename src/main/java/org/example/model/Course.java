package org.example.model;

import java.util.Objects;

/**
 * Model class representing a Course.
 */
public class Course {

    private int courseId;
    private String courseCode;
    private String courseName;
    private int credits;
    private int departmentId;

    // Default Constructor
    public Course() {
    }

    // Constructor without ID
    public Course(String courseCode,
                  String courseName,
                  int credits,
                  int departmentId) {

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentId = departmentId;
    }

    // Constructor with ID
    public Course(int courseId,
                  String courseCode,
                  String courseName,
                  int credits,
                  int departmentId) {

        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentId = departmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Course))
            return false;

        Course course = (Course) obj;

        return courseId == course.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}