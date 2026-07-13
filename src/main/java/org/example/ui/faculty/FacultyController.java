package org.example.ui.faculty;

import org.example.model.Faculty;
import org.example.service.FacultyService;
import org.example.service.impl.FacultyServiceImpl;

import java.util.List;

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController() {

        facultyService = new FacultyServiceImpl();

    }

    public List<Faculty> loadFaculties() {

        return facultyService.getAllFaculty();

    }

    public boolean saveFaculty(Faculty faculty) {

        return facultyService.addFaculty(faculty);

    }

    public boolean updateFaculty(Faculty faculty) {

        return facultyService.updateFaculty(faculty);

    }

    public boolean deleteFaculty(int id) {

        return facultyService.deleteFaculty(id);

    }

}