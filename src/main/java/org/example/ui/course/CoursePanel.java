package org.example.ui.course;

import org.example.model.Course;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CoursePanel extends JPanel {

    private final CourseFormPanel formPanel;

    private final CourseTablePanel tablePanel;

    private final CourseController controller;

    public CoursePanel() {

        controller = new CourseController();

        formPanel = new CourseFormPanel();

        tablePanel = new CourseTablePanel();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadCourses();

    }

    private void initializeComponents() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

    }

    private void initializeLayout() {

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT
        );

        splitPane.setResizeWeight(0.40);

        splitPane.setDividerSize(8);

        splitPane.setBorder(null);

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Course Records"
                )
        );

        bottomPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        splitPane.setTopComponent(topPanel);

        splitPane.setBottomComponent(bottomPanel);

        add(splitPane, BorderLayout.CENTER);

    }

    private void registerEvents() {

        formPanel.getBtnAdd().addActionListener(e -> addCourse());

        formPanel.getBtnUpdate().addActionListener(e -> updateCourse());

        formPanel.getBtnDelete().addActionListener(e -> deleteCourse());

        formPanel.getBtnClear().addActionListener(e -> formPanel.clearForm());

        formPanel.getBtnRefresh().addActionListener(e -> refreshTable());

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                loadSelectedCourse();

            }

        });

    }
    private void loadCourses() {

        List<Course> courses = controller.loadCourses();

        tablePanel.getTableModel().setCourses(courses);

    }

    private void refreshTable() {

        loadCourses();

        formPanel.clearForm();

    }
    private void addCourse() {

        if (!validateForm()) {

            return;

        }

        Course course = new Course();

        course.setCourseCode(
                formPanel.getCourseCode()
        );

        course.setCourseName(
                formPanel.getCourseName()
        );

        course.setCredits(
                formPanel.getCredits()
        );

        course.setDepartmentId(
                formPanel
                        .getDepartment()
                        .getDepartmentId()
        );

        if (controller.saveCourse(course)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Course."
            );

        }

    }

    private void updateCourse() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a course."
            );

            return;

        }

        if (!validateForm()) {

            return;

        }

        Course course =
                tablePanel.getTableModel().getCourse(row);

        course.setCourseCode(
                formPanel.getCourseCode()
        );

        course.setCourseName(
                formPanel.getCourseName()
        );

        course.setCredits(
                formPanel.getCredits()
        );

        course.setDepartmentId(
                formPanel
                        .getDepartment()
                        .getDepartmentId()
        );

        if (controller.updateCourse(course)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Update Course."
            );

        }

    }

    private void deleteCourse() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a course."
            );

            return;

        }

        Course course =
                tablePanel.getTableModel().getCourse(row);

        int option = JOptionPane.showConfirmDialog(
                this,
                "Delete selected course?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteCourse(
                course.getCourseId()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Delete Course."
            );

        }

    }
    private void loadSelectedCourse() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            return;

        }

        Course course = tablePanel
                .getTableModel()
                .getCourse(row);

        if (course == null) {

            return;

        }

        formPanel.setCourse(course);

    }

    private boolean validateForm() {

        if (formPanel.getCourseCode().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course Code is required."
            );

            formPanel.getTxtCourseCode().requestFocus();

            return false;

        }

        if (formPanel.getCourseName().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Course Name is required."
            );

            formPanel.getTxtCourseName().requestFocus();

            return false;

        }

        if (formPanel.getCredits() <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Credits must be greater than zero."
            );

            return false;

        }

        if (formPanel.getDepartment() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a department."
            );

            return false;

        }

        return true;

    }

}