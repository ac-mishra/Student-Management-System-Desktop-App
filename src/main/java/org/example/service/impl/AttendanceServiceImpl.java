package org.example.service.impl;

import org.example.dao.AttendanceDAO;
import org.example.dao.impl.AttendanceDAOImpl;
import org.example.model.Attendance;
import org.example.service.AttendanceService;

import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    @Override
    public boolean addAttendance(Attendance attendance) {
        return attendanceDAO.addAttendance(attendance);
    }

    @Override
    public boolean updateAttendance(Attendance attendance) {
        return attendanceDAO.updateAttendance(attendance);
    }

    @Override
    public boolean deleteAttendance(int attendanceId) {
        return attendanceDAO.deleteAttendance(attendanceId);
    }

    @Override
    public Attendance getAttendanceById(int attendanceId) {
        return attendanceDAO.getAttendanceById(attendanceId);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceDAO.getAllAttendance();
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceDAO.getAttendanceByStudent(studentId);
    }

    @Override
    public List<Attendance> getAttendanceByCourse(int courseId) {
        return attendanceDAO.getAttendanceByCourse(courseId);
    }
}