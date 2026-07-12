package org.example.model;

import java.util.Objects;

/**
 * Model class representing Faculty.
 */
public class Faculty {

    private int facultyId;
    private String facultyName;
    private String email;
    private String phone;
    private int departmentId;

    public Faculty() {
    }

    public Faculty(String facultyName,
                   String email,
                   String phone,
                   int departmentId) {

        this.facultyName = facultyName;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    public Faculty(int facultyId,
                   String facultyName,
                   String email,
                   String phone,
                   int departmentId) {

        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return facultyName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Faculty)) return false;
        Faculty faculty = (Faculty) obj;
        return facultyId == faculty.facultyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId);
    }
}