package org.example.service;

import org.example.config.ConnectionPool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseStatisticsService {

    public Map<String, Object> getStatistics() {

        Map<String, Object> map =
                new HashMap<>();

        try (

                Connection connection =
                        ConnectionPool.getConnection()

        ) {

            DatabaseMetaData meta =
                    connection.getMetaData();

            map.put(
                    "Database",
                    meta.getDatabaseProductName()
            );

            map.put(
                    "Version",
                    meta.getDatabaseProductVersion()
            );

            map.put(
                    "Driver",
                    meta.getDriverName()
            );

            Statement statement =
                    connection.createStatement();

            map.put(
                    "Students",
                    count(statement, "students")
            );

            map.put(
                    "Departments",
                    count(statement, "departments")
            );

            map.put(
                    "Courses",
                    count(statement, "courses")
            );

            map.put(
                    "Faculty",
                    count(statement, "faculty")
            );

            map.put(
                    "Attendance",
                    count(statement, "attendance")
            );

            map.put(
                    "Marks",
                    count(statement, "marks")
            );

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return map;

    }

    private int count(
            Statement statement,
            String table
    ) throws Exception {

        ResultSet rs =
                statement.executeQuery(
                        "SELECT COUNT(*) FROM " + table
                );

        rs.next();

        return rs.getInt(1);

    }

}