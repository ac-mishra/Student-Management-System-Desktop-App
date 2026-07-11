package org.example.service.impl;

import org.example.dao.EnrollmentDAO;
import org.example.dao.impl.EnrollmentDAOImpl;
import org.example.model.Enrollment;
import org.example.service.EnrollmentService;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAOImpl();

    @Override
    public boolean enrollStudent(Enrollment enrollment) {
        return enrollmentDAO.enrollStudent(enrollment);
    }

    @Override
    public boolean updateEnrollment(Enrollment enrollment) {
        return enrollmentDAO.updateEnrollment(enrollment);
    }

    @Override
    public boolean deleteEnrollment(int enrollmentId) {
        return enrollmentDAO.deleteEnrollment(enrollmentId);
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        return enrollmentDAO.getEnrollmentById(enrollmentId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentDAO.getEnrollmentsByStudent(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentDAO.getEnrollmentsByCourse(courseId);
    }

    @Override
    public boolean isStudentEnrolled(int studentId, int courseId) {
        return enrollmentDAO.isStudentEnrolled(studentId, courseId);
    }

}