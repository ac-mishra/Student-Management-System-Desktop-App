package org.example.ui.department;

import org.example.model.Department;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DepartmentTableModel extends AbstractTableModel {

    private final String[] columns = {

            "ID",

            "Department Name",

            "Department Code",

            "Description"

    };

    private List<Department> departments = new ArrayList<>();

    public void setDepartments(List<Department> departments) {

        this.departments = departments;

        fireTableDataChanged();

    }

    public Department getDepartment(int row) {

        return departments.get(row);

    }

    @Override
    public int getRowCount() {

        return departments.size();

    }

    @Override
    public int getColumnCount() {

        return columns.length;

    }

    @Override
    public String getColumnName(int column) {

        return columns[column];

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Department department = departments.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return department.getDepartmentId();

            case 1:
                return department.getDepartmentName();

            case 2:
                return department.getDepartmentCode();

            case 3:
                return department.getDescription();

            default:
                return null;

        }

    }

}