package org.example.ui;

import org.example.ui.login.LoginFrame;
import org.example.util.ColorPalette;
import org.example.util.UIConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JPanel sidebarPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel cardPanel;

    private JLabel titleLabel;

    private JButton dashboardButton;
    private JButton studentButton;
    private JButton departmentButton;
    private JButton courseButton;
    private JButton facultyButton;
    private JButton attendanceButton;
    private JButton marksButton;
    private JButton reportsButton;
    private JButton backupButton;
    private JButton logoutButton;

    private JPanel studentCard;
    private JPanel departmentCard;
    private JPanel courseCard;
    private JPanel facultyCard;

    public DashboardFrame() {

        initializeFrame();

        initializeComponents();

        initializeLayout();

        registerEvents();

    }

    private void initializeFrame() {

        setTitle("Student Management System");

        setSize(
                UIConstants.DASHBOARD_WIDTH,
                UIConstants.DASHBOARD_HEIGHT
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

    }

    private void initializeComponents() {

        sidebarPanel = new JPanel();

        headerPanel = new JPanel();

        contentPanel = new JPanel();

        cardPanel = new JPanel();

        titleLabel = new JLabel("Dashboard");

        dashboardButton = new JButton("Dashboard");

        studentButton = new JButton("Students");

        departmentButton = new JButton("Departments");

        courseButton = new JButton("Courses");

        facultyButton = new JButton("Faculty");

        attendanceButton = new JButton("Attendance");

        marksButton = new JButton("Marks");

        reportsButton = new JButton("Reports");

        backupButton = new JButton("Backup");

        logoutButton = new JButton("Logout");

        sidebarPanel.setBackground(ColorPalette.SECONDARY);

        headerPanel.setBackground(Color.WHITE);

        contentPanel.setBackground(ColorPalette.BACKGROUND);

        cardPanel.setBackground(ColorPalette.BACKGROUND);

    }

    private void initializeLayout() {

        setLayout(new BorderLayout());

        sidebarPanel.setPreferredSize(new Dimension(230,0));

        sidebarPanel.setLayout(new GridLayout(10,1,5,5));

        sidebarPanel.setBorder(new EmptyBorder(20,10,20,10));

        sidebarPanel.add(dashboardButton);

        sidebarPanel.add(studentButton);

        sidebarPanel.add(departmentButton);

        sidebarPanel.add(courseButton);

        sidebarPanel.add(facultyButton);

        sidebarPanel.add(attendanceButton);

        sidebarPanel.add(marksButton);

        sidebarPanel.add(reportsButton);

        sidebarPanel.add(backupButton);

        sidebarPanel.add(logoutButton);

        headerPanel.setPreferredSize(new Dimension(0,70));

        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        titleLabel.setFont(new Font("Segoe UI",Font.BOLD,26));

        headerPanel.add(titleLabel);

        cardPanel.setLayout(new GridLayout(2,2,20,20));

        cardPanel.setBorder(new EmptyBorder(30,30,30,30));

        studentCard=createCard("Students");

        departmentCard=createCard("Departments");

        courseCard=createCard("Courses");

        facultyCard=createCard("Faculty");

        cardPanel.add(studentCard);

        cardPanel.add(departmentCard);

        cardPanel.add(courseCard);

        cardPanel.add(facultyCard);

        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(cardPanel);

        add(sidebarPanel,BorderLayout.WEST);

        add(headerPanel,BorderLayout.NORTH);

        add(contentPanel,BorderLayout.CENTER);

    }

    private JPanel createCard(String title){

        JPanel panel=new JPanel();

        panel.setLayout(new BorderLayout());

        panel.setBackground(Color.WHITE);

        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel label=new JLabel(title,SwingConstants.CENTER);

        label.setFont(new Font("Segoe UI",Font.BOLD,24));

        panel.add(label,BorderLayout.CENTER);

        return panel;

    }

    private void registerEvents(){

        logoutButton.addActionListener(e->{

            dispose();

            new LoginFrame();

        });

    }

}