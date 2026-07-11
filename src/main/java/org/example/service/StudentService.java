package org.example.service;

import org.example.model.Student;

import java.util.List;

public interface StudentService {

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(int studentId);

    Student getStudentById(int studentId);

    Student getStudentByRollNo(String rollNo);

    List<Student> getAllStudents();

    List<Student> searchStudents(String keyword);

    boolean studentExists(String rollNo);

}