package org.example.service;

import org.example.model.Faculty;

import java.util.List;

public interface FacultyService {

    boolean addFaculty(Faculty faculty);

    boolean updateFaculty(Faculty faculty);

    boolean deleteFaculty(int facultyId);

    Faculty getFacultyById(int facultyId);

    Faculty getFacultyByEmail(String email);

    List<Faculty> getAllFaculty();

    List<Faculty> searchFaculty(String keyword);

    boolean facultyExists(String email);

}