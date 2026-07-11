package org.example.ui.reports;

import org.example.dao.impl.AttendanceDAOImpl;
import org.example.dao.impl.CourseDAOImpl;
import org.example.dao.impl.DepartmentDAOImpl;
import org.example.dao.impl.FacultyDAOImpl;
import org.example.dao.impl.MarksDAOImpl;
import org.example.dao.impl.StudentDAOImpl;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ReportsController {

    public DefaultTableModel getStudentReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{
                "ID",
                "Roll No",
                "First Name",
                "Last Name",
                "Department",
                "Email",
                "Phone"
        });

        StudentDAOImpl dao = new StudentDAOImpl();

        dao.getAllStudents().forEach(student -> {

            model.addRow(new Object[]{

                    student.getStudentId(),
                    student.getRollNo(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getDepartmentId(),
                    student.getEmail(),
                    student.getPhone()

            });

        });

        return model;

    }

    public DefaultTableModel getDepartmentReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Department Code",
                "Department Name"

        });

        DepartmentDAOImpl dao = new DepartmentDAOImpl();

        dao.getAllDepartments().forEach(department -> {

            model.addRow(new Object[]{

                    department.getDepartmentId(),
                    department.getDepartmentCode(),
                    department.getDepartmentName()

            });

        });

        return model;

    }

    public DefaultTableModel getCourseReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Code",
                "Course",
                "Credits",
                "Department"

        });

        CourseDAOImpl dao = new CourseDAOImpl();

        dao.getAllCourses().forEach(course -> {

            model.addRow(new Object[]{

                    course.getCourseId(),
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getCredits(),
                    course.getDepartmentId()

            });

        });

        return model;

    }

    public DefaultTableModel getFacultyReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Faculty",
                "Email",
                "Phone",
                "Department"

        });

        FacultyDAOImpl dao = new FacultyDAOImpl();

        dao.getAllFaculty().forEach(faculty -> {

            model.addRow(new Object[]{

                    faculty.getFacultyId(),
                    faculty.getFacultyName(),
                    faculty.getEmail(),
                    faculty.getPhone(),
                    faculty.getDepartmentId()

            });

        });

        return model;

    }

    public DefaultTableModel getAttendanceReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Student",
                "Course",
                "Date",
                "Status"

        });

        AttendanceDAOImpl dao = new AttendanceDAOImpl();

        dao.getAllAttendance().forEach(attendance -> {

            model.addRow(new Object[]{

                    attendance.getAttendanceId(),
                    attendance.getStudentId(),
                    attendance.getCourseId(),
                    attendance.getAttendanceDate(),
                    attendance.getStatus()

            });

        });

        return model;

    }

    public DefaultTableModel getMarksReport() {

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new Object[]{

                "ID",
                "Student",
                "Course",
                "Semester",
                "Internal",
                "External",
                "Total",
                "Grade"

        });

        MarksDAOImpl dao = new MarksDAOImpl();

        dao.getAllMarks().forEach(marks -> {

            model.addRow(new Object[]{

                    marks.getMarksId(),
                    marks.getStudentId(),
                    marks.getCourseId(),
                    marks.getSemester(),
                    marks.getInternalMarks(),
                    marks.getExternalMarks(),
                    marks.getTotalMarks(),
                    marks.getGrade()

            });

        });

        return model;

    }

}