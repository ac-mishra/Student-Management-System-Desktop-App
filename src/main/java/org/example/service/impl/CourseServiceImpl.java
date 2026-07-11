package org.example.service.impl;

import org.example.dao.CourseDAO;
import org.example.dao.impl.CourseDAOImpl;
import org.example.model.Course;
import org.example.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl();
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        return courseDAO.getCourseByCode(courseCode);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        return courseDAO.searchCourses(keyword);
    }

    @Override
    public boolean courseExists(String courseCode) {
        return courseDAO.courseExists(courseCode);
    }
}