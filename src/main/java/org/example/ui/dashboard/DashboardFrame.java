package org.example.ui.dashboard;

import net.miginfocom.swing.MigLayout;
import org.example.ui.attendance.AttendancePanel;
import org.example.ui.component.SidebarButton;
import org.example.ui.component.StatusBar;
import org.example.ui.course.CoursePanel;
import org.example.ui.department.DepartmentPanel;
import org.example.ui.faculty.FacultyPanel;
import org.example.ui.marks.MarksPanel;
import org.example.ui.reports.ReportsPanel;
import org.example.ui.student.StudentPanel;
import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    /*--------------------------------------------------
                    Layout Components
     --------------------------------------------------*/

    private JPanel sidebarPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;

    private StatusBar statusBar;

    private CardLayout cardLayout;

    /*--------------------------------------------------
                     Services
     --------------------------------------------------*/


    /*--------------------------------------------------
                     Dashboard
     --------------------------------------------------*/

    private DashboardHomePanel dashboardHomePanel;

    /*--------------------------------------------------
                     Modules
     --------------------------------------------------*/

    private StudentPanel studentPanel;

    private DepartmentPanel departmentPanel;

    private CoursePanel coursePanel;

    private FacultyPanel facultyPanel;

    private AttendancePanel attendancePanel;

    private MarksPanel marksPanel;

    private ReportsPanel reportsPanel;

    /*--------------------------------------------------
                     Constructor
     --------------------------------------------------*/

    public DashboardFrame() {


        initializeFrame();

        initializeComponents();

        initializeLayout();

        registerEvents();

        refreshDashboard();
        setVisible(true);

    }
    /*--------------------------------------------------
                Frame Initialization
 --------------------------------------------------*/

    private void initializeFrame() {

        setTitle("Student Management System");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setMinimumSize(new Dimension(1280, 720));

        setLocationRelativeTo(null);

    }

/*--------------------------------------------------
              Component Initialization
 --------------------------------------------------*/

    private void initializeComponents() {

        sidebarPanel = new JPanel();

        headerPanel = new JPanel();

        contentPanel = new JPanel();

        statusBar = new StatusBar();

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);

        dashboardHomePanel = new DashboardHomePanel();

        studentPanel = new StudentPanel();

        departmentPanel = new DepartmentPanel();

        coursePanel = new CoursePanel();

        facultyPanel = new FacultyPanel();

        attendancePanel = new AttendancePanel();

        marksPanel = new MarksPanel();

        reportsPanel = new ReportsPanel();

    }
    /*--------------------------------------------------
                Layout Initialization
 --------------------------------------------------*/

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

        sidebarPanel.setPreferredSize(new Dimension(240, 0));

        sidebarPanel.setBackground(AppColors.SIDEBAR);

        sidebarPanel.setLayout(new MigLayout(
                "fillx,insets 10",
                "[grow]"
        ));

        JLabel logo = new JLabel("Student Management System");

        logo.setForeground(Color.WHITE);

        logo.setFont(AppFonts.TITLE);

        sidebarPanel.add(logo, "center,wrap 20");

        SidebarButton btnDashboard =
                new SidebarButton("Dashboard");

        SidebarButton btnStudents =
                new SidebarButton("Students");

        SidebarButton btnDepartments =
                new SidebarButton("Departments");

        SidebarButton btnCourses =
                new SidebarButton("Courses");

        SidebarButton btnFaculty =
                new SidebarButton("Faculty");

        SidebarButton btnAttendance =
                new SidebarButton("Attendance");

        SidebarButton btnMarks =
                new SidebarButton("Marks");

        SidebarButton btnReports =
                new SidebarButton("Reports");

        SidebarButton btnBackup =
                new SidebarButton("Backup");

        SidebarButton btnLogout =
                new SidebarButton("Logout");

        sidebarPanel.add(btnDashboard, "growx,wrap");

        sidebarPanel.add(btnStudents, "growx,wrap");

        sidebarPanel.add(btnDepartments, "growx,wrap");

        sidebarPanel.add(btnCourses, "growx,wrap");

        sidebarPanel.add(btnFaculty, "growx,wrap");

        sidebarPanel.add(btnAttendance, "growx,wrap");

        sidebarPanel.add(btnMarks, "growx,wrap");

        sidebarPanel.add(btnReports, "growx,wrap");

        sidebarPanel.add(btnBackup, "growx,wrap");

        sidebarPanel.add(new JLabel(), "push,growy,wrap");

        sidebarPanel.add(btnLogout, "growx");

    /*-----------------------------------------
                Navigation
     -----------------------------------------*/

        btnDashboard.addActionListener(e -> {

            refreshDashboard();

            showModule("DASHBOARD");

        });

        btnStudents.addActionListener(e ->
                showModule("STUDENT"));

        btnDepartments.addActionListener(e ->
                showModule("DEPARTMENT"));

        btnCourses.addActionListener(e ->
                showModule("COURSE"));

        btnFaculty.addActionListener(e ->
                showModule("FACULTY"));

        btnAttendance.addActionListener(e ->
                showModule("ATTENDANCE"));

        btnMarks.addActionListener(e ->
                showModule("MARKS"));

        btnReports.addActionListener(e ->
                showModule("REPORTS"));

        btnBackup.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Backup Module Coming Soon"
            );

        });

        btnLogout.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                dispose();

            }

        });

    }

    private void buildHeader() {

        headerPanel.setPreferredSize(new Dimension(0, 70));

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Student Management System");

        title.setFont(AppFonts.TITLE);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        0,
                        0
                )
        );

        JLabel user = new JLabel("Welcome, Admin");

        user.setFont(AppFonts.NORMAL);

        user.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        0,
                        20
                )
        );

        headerPanel.add(title, BorderLayout.WEST);

        headerPanel.add(user, BorderLayout.EAST);

    }

    private void buildContent() {

        contentPanel.setBackground(
                AppColors.BACKGROUND
        );

        contentPanel.add(
                new JScrollPane(dashboardHomePanel),
                "DASHBOARD"
        );

        contentPanel.add(
                new JScrollPane(studentPanel),
                "STUDENT"
        );

        contentPanel.add(
                new JScrollPane(departmentPanel),
                "DEPARTMENT"
        );

        contentPanel.add(
                new JScrollPane(coursePanel),
                "COURSE"
        );

        contentPanel.add(
                new JScrollPane(facultyPanel),
                "FACULTY"
        );

        contentPanel.add(
                new JScrollPane(attendancePanel),
                "ATTENDANCE"
        );

        contentPanel.add(
                new JScrollPane(marksPanel),
                "MARKS"
        );

        contentPanel.add(
                new JScrollPane(reportsPanel),
                "REPORTS"
        );

        cardLayout.show(
                contentPanel,
                "DASHBOARD"
        );

    }
    /*--------------------------------------------------
                Event Registration
 --------------------------------------------------*/

    private void registerEvents() {

        statusBar.setStatus("System Ready");

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {

                dashboardHomePanel.refreshDashboard();

                statusBar.setStatus(
                        "Dashboard Loaded Successfully"
                );

            }

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                int option =
                        JOptionPane.showConfirmDialog(

                                DashboardFrame.this,

                                "Are you sure you want to exit?",

                                "Exit",

                                JOptionPane.YES_NO_OPTION,

                                JOptionPane.QUESTION_MESSAGE

                        );

                if (option == JOptionPane.YES_OPTION) {

                    dispose();

                } else {

                    setDefaultCloseOperation(
                            JFrame.DO_NOTHING_ON_CLOSE
                    );

                }

            }

        });

    }
    /*--------------------------------------------------
              Refresh Dashboard
 --------------------------------------------------*/

    private void refreshDashboard() {

        try {

            dashboardHomePanel.refreshDashboard();

            statusBar.setStatus("Dashboard Refreshed");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Dashboard Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }
    /*--------------------------------------------------
                Change Module
 --------------------------------------------------*/

    private void showModule(String module) {

        cardLayout.show(
                contentPanel,
                module
        );

        statusBar.setStatus(
                module + " Loaded"
        );

    }
    /*--------------------------------------------------
                Public Methods
 --------------------------------------------------*/

    public void openDashboard() {

        refreshDashboard();

        showModule("DASHBOARD");

    }

    public void openStudents() {

        showModule("STUDENT");


    }

    public void openDepartments() {

        showModule("DEPARTMENT");

    }

    public void openCourses() {

        showModule("COURSE");

    }

    public void openFaculty() {

        showModule("FACULTY");

    }

    public void openAttendance() {

        showModule("ATTENDANCE");

    }

    public void openMarks() {

        showModule("MARKS");

    }

    public void openReports() {

        showModule("REPORTS");

    }

/*--------------------------------------------------
                Dispose Resources
 --------------------------------------------------*/

    @Override
    public void dispose() {

        try {

            if (dashboardHomePanel != null) {

                dashboardHomePanel.refreshDashboard();

            }

        } catch (Exception ignored) {

        }

        super.dispose();

    }
}