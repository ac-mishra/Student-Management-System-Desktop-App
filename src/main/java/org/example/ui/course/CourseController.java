package org.example.ui.course;

import org.example.model.Course;
import org.example.service.CourseService;
import org.example.service.impl.CourseServiceImpl;

import java.util.List;

public class CourseController {

    private final CourseService courseService;

    public CourseController() {

        courseService = new CourseServiceImpl();

    }

    public List<Course> loadCourses() {

        return courseService.getAllCourses();

    }

    public boolean saveCourse(Course course) {

        return courseService.addCourse(course);

    }

    public boolean updateCourse(Course course) {

        return courseService.updateCourse(course);

    }

    public boolean deleteCourse(int id) {

        return courseService.deleteCourse(id);

    }

}