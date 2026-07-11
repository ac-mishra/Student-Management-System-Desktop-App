package org.example.ui.attendance;

import org.example.model.Attendance;
import org.example.service.AttendanceService;
import org.example.service.impl.AttendanceServiceImpl;

import java.util.List;

public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController() {

        attendanceService = new AttendanceServiceImpl();

    }

    public List<Attendance> loadAttendances() {

        return attendanceService.getAllAttendance();

    }

    public boolean saveAttendance(Attendance attendance) {

        return attendanceService.addAttendance(attendance);

    }

    public boolean updateAttendance(Attendance attendance) {

        return attendanceService.updateAttendance(attendance);

    }

    public boolean deleteAttendance(int id) {

        return attendanceService.deleteAttendance(id);

    }

}