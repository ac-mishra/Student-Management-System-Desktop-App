package org.example.service;

import org.example.model.Course;

import java.util.List;

public interface CourseService {

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(int courseId);

    Course getCourseById(int courseId);

    Course getCourseByCode(String courseCode);

    List<Course> getAllCourses();

    List<Course> searchCourses(String keyword);

    boolean courseExists(String courseCode);

}