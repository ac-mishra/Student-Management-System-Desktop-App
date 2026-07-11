package org.example.ui.faculty;

import org.example.model.Faculty;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FacultyPanel extends JPanel {

    private final FacultyFormPanel formPanel;

    private final FacultyTablePanel tablePanel;

    private final FacultyController controller;

    public FacultyPanel() {

        controller = new FacultyController();

        formPanel = new FacultyFormPanel();

        tablePanel = new FacultyTablePanel();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadFaculties();

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

        JPanel topPanel = new JPanel(
                new BorderLayout()
        );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Faculty Records"
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

        formPanel.getBtnAdd().addActionListener(e -> addFaculty());

        formPanel.getBtnUpdate().addActionListener(e -> updateFaculty());

        formPanel.getBtnDelete().addActionListener(e -> deleteFaculty());

        formPanel.getBtnClear().addActionListener(e -> formPanel.clearForm());

        formPanel.getBtnRefresh().addActionListener(e -> refreshTable());

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                loadSelectedFaculty();

            }

        });

    }

    private void loadFaculties() {

        List<Faculty> faculties =
                controller.loadFaculties();

        tablePanel.getTableModel()
                .setFaculties(faculties);

    }

    private void refreshTable() {

        loadFaculties();

        formPanel.clearForm();

    }

    private void addFaculty() {

        if (!validateForm()) {

            return;

        }

        Faculty faculty = new Faculty();

        faculty.setFacultyName(
                formPanel.getFacultyName()
        );

        faculty.setEmail(
                formPanel.getEmail()
        );

        faculty.setPhone(
                formPanel.getPhone()
        );

        faculty.setDepartmentId(
                formPanel
                        .getDepartment()
                        .getDepartmentId()
        );

        if (controller.saveFaculty(faculty)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Faculty."
            );

        }

    }

    private void updateFaculty() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a faculty."
            );

            return;

        }

        if (!validateForm()) {

            return;

        }

        Faculty faculty =
                tablePanel.getTableModel().getFaculty(row);

        faculty.setFacultyName(
                formPanel.getFacultyName()
        );

        faculty.setEmail(
                formPanel.getEmail()
        );

        faculty.setPhone(
                formPanel.getPhone()
        );

        faculty.setDepartmentId(
                formPanel
                        .getDepartment()
                        .getDepartmentId()
        );

        if (controller.updateFaculty(faculty)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Update Faculty."
            );

        }

    }



    private void deleteFaculty() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a faculty."
            );

            return;

        }

        Faculty faculty =
                tablePanel.getTableModel().getFaculty(row);

        int option = JOptionPane.showConfirmDialog(
                this,
                "Delete selected faculty?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteFaculty(
                faculty.getFacultyId()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Delete Faculty."
            );

        }

    }

    private boolean validateForm() {

        if (formPanel.getFacultyName().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Faculty Name is required."
            );

            formPanel.getTxtFacultyName().requestFocus();

            return false;

        }

        if (formPanel.getEmail().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Email is required."
            );

            formPanel.getTxtEmail().requestFocus();

            return false;

        }

        if (!formPanel.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid email."
            );

            return false;

        }

        if (formPanel.getPhone().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone Number is required."
            );

            return false;

        }

        if (!formPanel.getPhone().matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone Number must contain exactly 10 digits."
            );

            return false;

        }

        if (formPanel.getDepartment() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select Department."
            );

            return false;

        }

        return true;

    }

    private void loadSelectedFaculty() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            return;

        }

        Faculty faculty =
                tablePanel.getTableModel().getFaculty(row);

        if (faculty == null) {

            return;

        }

        formPanel.setFaculty(faculty);

    }

}