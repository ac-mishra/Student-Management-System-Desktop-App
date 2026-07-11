package org.example.ui.course;

import org.example.model.Course;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CourseTableModel extends AbstractTableModel {

    private final String[] columns = {
            "ID",
            "Course Code",
            "Course Name",
            "Credits",
            "Department"
    };

    private List<Course> courses = new ArrayList<>();

    public void setCourses(List<Course> courses) {

        this.courses = courses;

        fireTableDataChanged();

    }

    public Course getCourse(int row) {

        if (row < 0 || row >= courses.size()) {

            return null;

        }

        return courses.get(row);

    }

    @Override
    public int getRowCount() {

        return courses.size();

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

        Course course = courses.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> course.getCourseId();

            case 1 -> course.getCourseCode();

            case 2 -> course.getCourseName();

            case 3 -> course.getCredits();

            case 4 -> course.getDepartmentId();

            default -> "";

        };

    }

}