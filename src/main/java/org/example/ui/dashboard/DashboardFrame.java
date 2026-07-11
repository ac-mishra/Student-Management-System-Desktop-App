package org.example.ui.dashboard;

import net.miginfocom.swing.MigLayout;
import org.example.ui.attendance.AttendancePanel;
import org.example.ui.component.SidebarButton;
import org.example.ui.component.StatusBar;
import org.example.ui.course.CoursePanel;
import org.example.ui.department.DepartmentPanel;
import org.example.ui.faculty.FacultyPanel;
import org.example.ui.student.StudentPanel;
import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;
import org.example.service.DashboardService;
import org.example.service.impl.DashboardServiceImpl;
import org.example.ui.component.DashboardCard;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JPanel sidebarPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;

    private StatusBar statusBar;

    private DashboardService dashboardService;

    private DashboardCard studentCard;
    private DashboardCard departmentCard;
    private DashboardCard courseCard;
    private DashboardCard facultyCard;


    private CardLayout cardLayout;

    private StudentPanel studentPanel;

    private DepartmentPanel departmentPanel;

    private CoursePanel coursePanel;

    private FacultyPanel facultyPanel;

    private AttendancePanel attendancePanel;

    public DashboardFrame() {

        dashboardService = new DashboardServiceImpl();

        initializeFrame();

        initializeComponents();

        initializeLayout();

        registerEvents();

        setVisible(true);

    }

    private void initializeFrame() {

        setTitle("Student Management System");

        setSize(1400, 850);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void initializeComponents() {

        sidebarPanel = new JPanel();

        headerPanel = new JPanel();

        contentPanel = new JPanel();

        statusBar = new StatusBar();

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        studentPanel = new StudentPanel();

        departmentPanel = new DepartmentPanel();

        coursePanel = new CoursePanel();

        facultyPanel = new FacultyPanel();

        attendancePanel = new AttendancePanel();

    }

    private void initializeLayout() {

        setLayout(new BorderLayout());

        buildSidebar();

        buildHeader();

        buildContent();

        add(sidebarPanel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        add(contentPanel, BorderLayout.CENTER);

        add(statusBar, BorderLayout.SOUTH);

    }

    private void buildSidebar() {

        sidebarPanel.setPreferredSize(new Dimension(230, 0));

        sidebarPanel.setBackground(AppColors.SIDEBAR);

        sidebarPanel.setLayout(new MigLayout(
                "fillx,insets 10",
                "[grow]"
        ));

        JLabel logo = new JLabel("SMS");

        logo.setForeground(Color.WHITE);

        logo.setFont(AppFonts.TITLE);

        sidebarPanel.add(logo, "center,wrap 20");

        SidebarButton btnDashboard = new SidebarButton("Dashboard");

        SidebarButton btnStudents = new SidebarButton("Students");

        SidebarButton btnDepartments = new SidebarButton("Departments");

        SidebarButton btnCourses = new SidebarButton("Courses");

        SidebarButton btnFaculty = new SidebarButton("Faculty");

        SidebarButton btnAttendance = new SidebarButton("Attendance");

        SidebarButton btnMarks = new SidebarButton("Marks");

        SidebarButton btnReports = new SidebarButton("Reports");

        SidebarButton btnBackup = new SidebarButton("Backup");

        SidebarButton btnLogout = new SidebarButton("Logout");

        sidebarPanel.add(btnDashboard, "growx,wrap");

        sidebarPanel.add(btnStudents, "growx,wrap");

        sidebarPanel.add(btnDepartments, "growx,wrap");

        sidebarPanel.add(btnCourses, "growx,wrap");

        sidebarPanel.add(btnFaculty, "growx,wrap");

        sidebarPanel.add(btnAttendance, "growx,wrap");

        sidebarPanel.add(btnMarks, "growx,wrap");

        sidebarPanel.add(btnReports, "growx,wrap");

        sidebarPanel.add(btnBackup, "growx,wrap");

        sidebarPanel.add(btnLogout, "growx");

        btnStudents.addActionListener(e ->
                cardLayout.show(contentPanel, "STUDENT"));

        btnDepartments.addActionListener(e ->
                cardLayout.show(contentPanel, "DEPARTMENT"));

        btnCourses.addActionListener(e ->
                cardLayout.show(contentPanel, "COURSE"));

        btnFaculty.addActionListener(e ->
                cardLayout.show(contentPanel, "FACULTY"));

        btnAttendance.addActionListener(e ->
                cardLayout.show(contentPanel, "ATTENDANCE"));

    }
    private void buildHeader() {

        headerPanel.setPreferredSize(new Dimension(0, 70));

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Dashboard");

        title.setFont(AppFonts.TITLE);

        title.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

        JLabel user = new JLabel("Welcome, Admin");

        user.setFont(AppFonts.NORMAL);

        user.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        headerPanel.add(title, BorderLayout.WEST);

        headerPanel.add(user, BorderLayout.EAST);

    }

    private void buildContent() {

        contentPanel.setBackground(AppColors.BACKGROUND);

        contentPanel.add(new JScrollPane(studentPanel), "STUDENT");

        contentPanel.add(new JScrollPane(departmentPanel), "DEPARTMENT");

        contentPanel.add(new JScrollPane(coursePanel), "COURSE");

        contentPanel.add(new JScrollPane(facultyPanel), "FACULTY");

        contentPanel.add(new JScrollPane(attendancePanel), "ATTENDANCE");

        cardLayout.show(contentPanel, "STUDENT");

    }
    private void registerEvents() {

        statusBar.setStatus("Ready");

    }


}