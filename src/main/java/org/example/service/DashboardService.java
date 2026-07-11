package org.example.service;

import org.example.config.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardService {

    public Map<String, Integer> getDashboardStatistics() {

        Map<String, Integer> map =
                new LinkedHashMap<>();

        map.put("Students", count("students"));

        map.put("Departments", count("departments"));

        map.put("Courses", count("courses"));

        map.put("Faculty", count("faculty"));

        map.put("Attendance", count("attendance"));

        map.put("Marks", count("marks"));

        return map;

    }

    private int count(String table) {

        String sql =
                "SELECT COUNT(*) FROM " + table;

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

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

    public double getAverageMarks() {

        String sql =
                "SELECT AVG(total_marks) FROM marks";

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

            if (rs.next()) {

                return rs.getDouble(1);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    public double getAttendancePercentage() {

        String sql = """

                SELECT

                SUM(CASE

                    WHEN status='Present'

                    THEN 1

                    ELSE 0

                END)*100.0/

                COUNT(*)

                FROM attendance

                """;

        try (

                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()

        ) {

            if (rs.next()) {

                return rs.getDouble(1);

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

}