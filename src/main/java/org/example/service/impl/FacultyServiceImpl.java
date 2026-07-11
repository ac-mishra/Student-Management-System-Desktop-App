package org.example.service.impl;

import org.example.dao.FacultyDAO;
import org.example.dao.impl.FacultyDAOImpl;
import org.example.model.Faculty;
import org.example.service.FacultyService;

import java.util.List;

public class FacultyServiceImpl implements FacultyService {

    private final FacultyDAO facultyDAO = new FacultyDAOImpl();

    @Override
    public boolean addFaculty(Faculty faculty) {
        return facultyDAO.addFaculty(faculty);
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        return facultyDAO.updateFaculty(faculty);
    }

    @Override
    public boolean deleteFaculty(int facultyId) {
        return facultyDAO.deleteFaculty(facultyId);
    }

    @Override
    public Faculty getFacultyById(int facultyId) {
        return facultyDAO.getFacultyById(facultyId);
    }

    @Override
    public Faculty getFacultyByEmail(String email) {
        return facultyDAO.getFacultyByEmail(email);
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyDAO.getAllFaculty();
    }

    @Override
    public List<Faculty> searchFaculty(String keyword) {
        return facultyDAO.searchFaculty(keyword);
    }

    @Override
    public boolean facultyExists(String email) {
        return facultyDAO.facultyExists(email);
    }
}