package org.example.ui.marks;

import org.example.model.Marks;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MarksTableModel extends AbstractTableModel {

    private final String[] columns = {

            "ID",
            "Student",
            "Course",
            "Semester",
            "Internal",
            "External",
            "Total",
            "Grade"

    };

    private List<Marks> marksList = new ArrayList<>();

    public void setMarks(List<Marks> marksList) {

        this.marksList = marksList;

        fireTableDataChanged();

    }

    public Marks getMarks(int row) {

        if (row < 0 || row >= marksList.size()) {

            return null;

        }

        return marksList.get(row);

    }

    @Override
    public int getRowCount() {

        return marksList.size();

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
    public Object getValueAt(int rowIndex,
                             int columnIndex) {

        Marks marks = marksList.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> marks.getMarksId();

            case 1 -> marks.getStudentId();

            case 2 -> marks.getCourseId();

            case 3 -> marks.getSemester();

            case 4 -> marks.getInternalMarks();

            case 5 -> marks.getExternalMarks();

            case 6 -> marks.getTotalMarks();

            case 7 -> marks.getGrade();

            default -> "";

        };

    }

}