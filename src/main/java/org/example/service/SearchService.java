package org.example.service;

import org.example.dao.SearchDAO;

import java.util.List;
import java.util.Map;

public class SearchService {

    private final SearchDAO dao =
            new SearchDAO();

    public List<Map<String, Object>> searchStudents(
            String keyword
    ) {

        return dao.search(

                "students",

                new String[]{

                        "roll_no",

                        "first_name",

                        "last_name",

                        "email",

                        "phone"

                },

                keyword

        );

    }

    public List<Map<String, Object>> searchDepartments(
            String keyword
    ) {

        return dao.search(

                "departments",

                new String[]{

                        "department_code",

                        "department_name"

                },

                keyword

        );

    }

    public List<Map<String, Object>> searchCourses(
            String keyword
    ) {

        return dao.search(

                "courses",

                new String[]{

                        "course_code",

                        "course_name"

                },

                keyword

        );

    }

    public List<Map<String, Object>> searchFaculty(
            String keyword
    ) {

        return dao.search(

                "faculty",

                new String[]{

                        "faculty_name",

                        "email",

                        "phone"

                },

                keyword

        );

    }

    public List<Map<String, Object>> searchAttendance(
            String keyword
    ) {

        return dao.search(

                "attendance",

                new String[]{

                        "status"

                },

                keyword

        );

    }

    public List<Map<String, Object>> searchMarks(
            String keyword
    ) {

        return dao.search(

                "marks",

                new String[]{

                        "grade"

                },

                keyword

        );

    }

}