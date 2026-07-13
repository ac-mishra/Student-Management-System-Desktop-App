package org.example.ui.panel;

import net.miginfocom.swing.MigLayout;
import org.example.service.DashboardService;
import org.example.ui.component.DashboardCard;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashboardPanel extends JPanel {

    private final DashboardService dashboardService;

    private DashboardCard studentCard;
    private DashboardCard departmentCard;
    private DashboardCard courseCard;
    private DashboardCard facultyCard;

    public DashboardPanel() {

        dashboardService = new DashboardService();
        initializeComponents();

        initializeLayout();

        loadDashboardData();

    }

    private void initializeComponents() {

        setLayout(new MigLayout(
                "fill,insets 20,gap 20",
                "[grow][grow]",
                "[][grow]"
        ));

        setBackground(AppColors.BACKGROUND);

        setBorder(new EmptyBorder(20,20,20,20));

        studentCard = new DashboardCard("Students", "0");

        departmentCard = new DashboardCard("Departments", "0");

        courseCard = new DashboardCard("Courses", "0");

        facultyCard = new DashboardCard("Faculty", "0");

    }

    private void initializeLayout() {

        add(studentCard, "grow");

        add(departmentCard, "grow,wrap");

        add(courseCard, "grow");

        add(facultyCard, "grow");

    }

    public void loadDashboardData() {

        var statistics = dashboardService.getDashboardStatistics();

        studentCard.setValue(
                String.valueOf(
                        statistics.getOrDefault("Students", 0)
                )
        );

        departmentCard.setValue(
                String.valueOf(
                        statistics.getOrDefault("Departments", 0)
                )
        );

        courseCard.setValue(
                String.valueOf(
                        statistics.getOrDefault("Courses", 0)
                )
        );

        facultyCard.setValue(
                String.valueOf(
                        statistics.getOrDefault("Faculty", 0)
                )
        );

    }

}