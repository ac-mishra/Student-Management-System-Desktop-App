package org.example.ui.student;

import org.example.model.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {

    private final String[] columns = {
            "ID",
            "Roll No",
            "First Name",
            "Last Name",
            "Gender",
            "Department",
            "Email",
            "Phone",
            "Status"
    };

    private List<Student> students = new ArrayList<>();

    public void setStudents(List<Student> students) {

        this.students = students;

        fireTableDataChanged();

    }

    public Student getStudent(int row) {

        if (row < 0 || row >= students.size()) {

            return null;

        }

        return students.get(row);

    }

    @Override
    public int getRowCount() {

        return students.size();

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

        Student s = students.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> s.getStudentId();

            case 1 -> s.getRollNo();

            case 2 -> s.getFirstName();

            case 3 -> s.getLastName();

            case 4 -> s.getGender();

            case 5 -> s.getDepartmentId();

            case 6 -> s.getEmail();

            case 7 -> s.getPhone();

            case 8 -> s.getStatus();

            default -> "";

        };

    }

}