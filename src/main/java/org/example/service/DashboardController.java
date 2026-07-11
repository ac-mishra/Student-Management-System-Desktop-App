package org.example.ui.dashboard;

import org.example.service.DashboardService;

import java.util.Map;

public class DashboardController {

    private final DashboardService service =
            new DashboardService();

    public Map<String, Integer> getStatistics() {

        return service.getDashboardStatistics();

    }

    public double getAverageMarks() {

        return service.getAverageMarks();

    }

    public double getAttendancePercentage() {

        return service.getAttendancePercentage();

    }

}