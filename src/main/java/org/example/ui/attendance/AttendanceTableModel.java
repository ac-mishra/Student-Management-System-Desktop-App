package org.example.ui.attendance;

import org.example.model.Attendance;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AttendanceTableModel extends AbstractTableModel {

    private final String[] columns = {
            "ID",
            "Student ID",
            "Course ID",
            "Attendance Date",
            "Status"
    };

    private List<Attendance> attendances = new ArrayList<>();

    public void setAttendances(List<Attendance> attendances) {

        this.attendances = attendances;

        fireTableDataChanged();

    }

    public Attendance getAttendance(int row) {

        if (row < 0 || row >= attendances.size()) {

            return null;

        }

        return attendances.get(row);

    }

    @Override
    public int getRowCount() {

        return attendances.size();

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

        Attendance attendance = attendances.get(rowIndex);

        return switch (columnIndex) {

            case 0 -> attendance.getAttendanceId();

            case 1 -> attendance.getStudentId();

            case 2 -> attendance.getCourseId();

            case 3 -> attendance.getAttendanceDate();

            case 4 -> attendance.getStatus();

            default -> "";

        };

    }

}