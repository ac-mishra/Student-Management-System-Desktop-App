package org.example.dao;

import org.example.model.Marks;

import java.util.List;

public interface MarksDAO {

    boolean addMarks(Marks marks);

    boolean updateMarks(Marks marks);

    boolean deleteMarks(int marksId);

    Marks getMarksById(int marksId);

    List<Marks> getAllMarks();

    List<Marks> getMarksByStudent(int studentId);

    List<Marks> getMarksByCourse(int courseId);

}