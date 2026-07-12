package org.example.ui.dashboard;

import net.miginfocom.swing.MigLayout;
import org.example.service.DashboardService;
import org.example.ui.component.DashboardCard;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import java.util.Map;

public class DashboardHomePanel extends JPanel {

    private final DashboardService dashboardService;

    private DashboardCard studentCard;
    private DashboardCard departmentCard;
    private DashboardCard courseCard;
    private DashboardCard facultyCard;
    private DashboardCard attendanceCard;
    private DashboardCard marksCard;
    private DashboardCard averageMarksCard;
    private DashboardCard attendancePercentageCard;

    public DashboardHomePanel() {

        dashboardService = new DashboardService();

        initializeComponents();

        initializeLayout();

        loadDashboardStatistics();

    }

    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        studentCard = new DashboardCard("Students");

        departmentCard = new DashboardCard("Departments");

        courseCard = new DashboardCard("Courses");

        facultyCard = new DashboardCard("Faculty");

        attendanceCard = new DashboardCard("Attendance");

        marksCard = new DashboardCard("Marks");

        averageMarksCard = new DashboardCard("Average Marks");

        attendancePercentageCard = new DashboardCard("Attendance %");

    }

    private void initializeLayout() {

        setLayout(new MigLayout(
                "fill,insets 20",
                "[grow][grow][grow][grow]",
                "[grow][grow]"
        ));

        add(studentCard, "grow");

        add(departmentCard, "grow");

        add(courseCard, "grow");

        add(facultyCard, "grow,wrap");

        add(attendanceCard, "grow");

        add(marksCard, "grow");

        add(averageMarksCard, "grow");

        add(attendancePercentageCard, "grow");

    }

    public void loadDashboardStatistics() {

        try {

            Map<String, Integer> statistics =
                    dashboardService.getDashboardStatistics();

            studentCard.setValue(
                    statistics.getOrDefault("Students", 0)
            );

            departmentCard.setValue(
                    statistics.getOrDefault("Departments", 0)
            );

            courseCard.setValue(
                    statistics.getOrDefault("Courses", 0)
            );

            facultyCard.setValue(
                    statistics.getOrDefault("Faculty", 0)
            );

            attendanceCard.setValue(
                    statistics.getOrDefault("Attendance", 0)
            );

            marksCard.setValue(
                    statistics.getOrDefault("Marks", 0)
            );

            averageMarksCard.setValue(
                    String.format(
                            "%.2f",
                            dashboardService.getAverageMarks()
                    )
            );

            attendancePercentageCard.setValue(
                    String.format(
                            "%.2f %%",

                            dashboardService.getAttendancePercentage()
                    )
            );

        }

        catch (Exception ex) {

            JOptionPane.showMessageDialog(

                    this,

                    ex.getMessage(),

                    "Dashboard Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    public void refreshDashboard() {

        loadDashboardStatistics();

    }

}