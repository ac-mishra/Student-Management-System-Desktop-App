package org.example.dao;

import org.example.model.Student;

import java.util.List;

public interface StudentDAO {

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(int studentId);

    Student getStudentById(int studentId);

    Student getStudentByRollNo(String rollNo);

    List<Student> getAllStudents();

    List<Student> searchStudents(String keyword);

    boolean studentExists(String rollNo);

}