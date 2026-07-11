package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Model class representing a Student.
 */
public class Student {

    private int studentId;
    private String rollNo;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;
    private String email;
    private String phone;
    private String address;
    private int departmentId;
    private LocalDate admissionDate;
    private String status;

    // Default Constructor
    public Student() {
    }

    // Constructor without ID
    public Student(String rollNo, String firstName, String lastName,
                   String gender, LocalDate dob, String email,
                   String phone, String address, int departmentId,
                   LocalDate admissionDate, String status) {

        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.departmentId = departmentId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    // Constructor with ID
    public Student(int studentId, String rollNo, String firstName,
                   String lastName, String gender, LocalDate dob,
                   String email, String phone, String address,
                   int departmentId, LocalDate admissionDate,
                   String status) {

        this.studentId = studentId;
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.departmentId = departmentId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    // Getters and Setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", rollNo='" + rollNo + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", departmentId=" + departmentId +
                ", admissionDate=" + admissionDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Student))
            return false;

        Student student = (Student) obj;

        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}