package org.example.service;

import org.example.model.Department;

import java.util.List;

public interface DepartmentService {

    boolean addDepartment(Department department);

    boolean updateDepartment(Department department);

    boolean deleteDepartment(int departmentId);

    Department getDepartmentById(int departmentId);

    Department getDepartmentByCode(String departmentCode);

    List<Department> getAllDepartments();

    List<Department> searchDepartments(String keyword);

    boolean departmentExists(String departmentCode);

}