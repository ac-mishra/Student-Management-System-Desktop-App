package org.example.ui.student;

import org.example.model.Student;
import org.example.service.StudentService;
import org.example.service.impl.StudentServiceImpl;

import java.util.List;

public class StudentController {

    private final StudentService studentService;

    public StudentController() {

        studentService = new StudentServiceImpl();

    }

    public List<Student> loadStudents() {

        return studentService.getAllStudents();

    }

    public boolean saveStudent(Student student) {

        return studentService.addStudent(student);

    }

    public boolean updateStudent(Student student) {

        return studentService.updateStudent(student);

    }

    public boolean deleteStudent(int id) {

        return studentService.deleteStudent(id);

    }

}