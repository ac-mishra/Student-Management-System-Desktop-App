package org.example.service.impl;

import org.example.dao.DepartmentDAO;
import org.example.dao.impl.DepartmentDAOImpl;
import org.example.model.Department;
import org.example.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    @Override
    public boolean addDepartment(Department department) {
        return departmentDAO.addDepartment(department);
    }

    @Override
    public boolean updateDepartment(Department department) {
        return departmentDAO.updateDepartment(department);
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        return departmentDAO.deleteDepartment(departmentId);
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        return departmentDAO.getDepartmentById(departmentId);
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {
        return departmentDAO.getDepartmentByCode(departmentCode);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public List<Department> searchDepartments(String keyword) {
        return departmentDAO.searchDepartments(keyword);
    }

    @Override
    public boolean departmentExists(String departmentCode) {
        return departmentDAO.departmentExists(departmentCode);
    }

}