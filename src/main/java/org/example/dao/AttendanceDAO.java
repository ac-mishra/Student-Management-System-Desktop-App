package org.example.dao;

import org.example.model.Attendance;

import java.util.List;

public interface AttendanceDAO {

    boolean addAttendance(Attendance attendance);

    boolean updateAttendance(Attendance attendance);

    boolean deleteAttendance(int attendanceId);

    Attendance getAttendanceById(int attendanceId);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByStudent(int studentId);

    List<Attendance> getAttendanceByCourse(int courseId);

}