package org.example.ui.faculty;

import org.example.model.Faculty;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FacultyTableModel extends AbstractTableModel {

    private final String[] columns = {
            "ID",
            "Faculty Name",
            "Email",
            "Phone",
            "Department"
    };

    private List<Faculty> faculties = new ArrayList<>();

    public void setFaculties(List<Faculty> faculties) {

        this.faculties = faculties;

        fireTableDataChanged();

    }

    public Faculty getFaculty(int row) {

        if (row < 0 || row >= faculties.size()) {

            return null;

        }

        return faculties.get(row);

    }

    @Override
    public int getRowCount() {

        return faculties.size();

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

        Faculty faculty = faculties.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> faculty.getFacultyId();

            case 1 -> faculty.getFacultyName();

            case 2 -> faculty.getEmail();

            case 3 -> faculty.getPhone();

            case 4 -> faculty.getDepartmentId();

            default -> "";

        };

    }

}