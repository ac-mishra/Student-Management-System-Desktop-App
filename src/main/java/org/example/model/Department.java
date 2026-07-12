package org.example.model;

import java.util.Objects;

/**
 * Model class representing a Department.
 */
public class Department {

    private int departmentId;
    private String departmentName;
    private String departmentCode;
    private String description;

    // Default Constructor
    public Department() {
    }

    // Parameterized Constructor (without ID)
    public Department(String departmentName,
                      String departmentCode,
                      String description) {

        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.description = description;
    }

    // Parameterized Constructor (with ID)
    public Department(int departmentId,
                      String departmentName,
                      String departmentCode,
                      String description) {

        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.description = description;
    }

    // Getters & Setters

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Department))
            return false;

        Department department = (Department) obj;

        return departmentId == department.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId);
    }
}