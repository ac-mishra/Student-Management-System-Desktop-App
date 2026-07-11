package org.example.service;

import org.example.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    boolean enrollStudent(Enrollment enrollment);

    boolean updateEnrollment(Enrollment enrollment);

    boolean deleteEnrollment(int enrollmentId);

    Enrollment getEnrollmentById(int enrollmentId);

    List<Enrollment> getAllEnrollments();

    List<Enrollment> getEnrollmentsByStudent(int studentId);

    List<Enrollment> getEnrollmentsByCourse(int courseId);

    boolean isStudentEnrolled(int studentId, int courseId);

}