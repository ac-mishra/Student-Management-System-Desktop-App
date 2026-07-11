package org.example.service.impl;

import org.example.dao.StudentDAO;
import org.example.dao.impl.StudentDAOImpl;
import org.example.model.Student;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    public StudentServiceImpl() {
        this.studentDAO = new StudentDAOImpl();
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    @Override
    public Student getStudentByRollNo(String rollNo) {
        return studentDAO.getStudentByRollNo(rollNo);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return studentDAO.searchStudents(keyword);
    }

    @Override
    public boolean studentExists(String rollNo) {
        return studentDAO.studentExists(rollNo);
    }

}