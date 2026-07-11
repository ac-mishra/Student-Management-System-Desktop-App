package org.example.service.impl;

import org.example.dao.MarksDAO;
import org.example.dao.impl.MarksDAOImpl;
import org.example.model.Marks;
import org.example.service.MarksService;

import java.util.List;

public class MarksServiceImpl implements MarksService {

    private final MarksDAO marksDAO = new MarksDAOImpl();

    @Override
    public boolean addMarks(Marks marks) {

        return marksDAO.addMarks(marks);

    }

    @Override
    public boolean updateMarks(Marks marks) {

        return marksDAO.updateMarks(marks);

    }

    @Override
    public boolean deleteMarks(int marksId) {

        return marksDAO.deleteMarks(marksId);

    }

    @Override
    public Marks getMarksById(int marksId) {

        return marksDAO.getMarksById(marksId);

    }

    @Override
    public List<Marks> getAllMarks() {

        return marksDAO.getAllMarks();

    }

    @Override
    public List<Marks> getMarksByStudent(int studentId) {

        return marksDAO.getMarksByStudent(studentId);

    }

    @Override
    public List<Marks> getMarksByCourse(int courseId) {

        return marksDAO.getMarksByCourse(courseId);

    }

}