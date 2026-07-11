package org.example.service.impl;

import org.example.config.ConnectionPool;
import org.example.service.DashboardService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardServiceImpl implements DashboardService {

    @Override
    public int getTotalStudents() {

        return getCount("SELECT COUNT(*) FROM students");

    }

    @Override
    public int getTotalDepartments() {

        return getCount("SELECT COUNT(*) FROM departments");

    }

    @Override
    public int getTotalCourses() {

        return getCount("SELECT COUNT(*) FROM courses");

    }

    @Override
    public int getTotalFaculty() {

        return getCount("SELECT COUNT(*) FROM faculty");

    }

    private int getCount(String sql) {

        try (

                Connection connection = ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()

        ) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

}