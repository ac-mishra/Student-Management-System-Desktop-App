package org.example.ui.department;

import org.example.dao.impl.DepartmentDAOImpl;
import org.example.model.Department;
import org.example.service.DepartmentService;
import org.example.service.impl.DepartmentServiceImpl;

import java.util.List;

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController() {

        departmentService = new DepartmentServiceImpl(

        );

    }

    public List<Department> loadDepartments() {

        return departmentService.getAllDepartments();

    }

    public boolean saveDepartment(Department department) {

        return departmentService.addDepartment(department);

    }

    public boolean updateDepartment(Department department) {

        return departmentService.updateDepartment(department);

    }

    public boolean deleteDepartment(int id) {

        return departmentService.deleteDepartment(id);

    }

}