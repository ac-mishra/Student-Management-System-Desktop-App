package org.example.ui.attendance;

import org.example.model.Attendance;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.util.List;

public class AttendancePanel extends JPanel {

    private final AttendanceFormPanel formPanel;

    private final AttendanceTablePanel tablePanel;

    private final AttendanceController controller;

    public AttendancePanel() {

        controller = new AttendanceController();

        formPanel = new AttendanceFormPanel();

        tablePanel = new AttendanceTablePanel();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadAttendances();

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
                        "Attendance Records"
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

        formPanel.getBtnAdd().addActionListener(e -> addAttendance());

        formPanel.getBtnUpdate().addActionListener(e -> updateAttendance());

        formPanel.getBtnDelete().addActionListener(e -> deleteAttendance());

        formPanel.getBtnClear().addActionListener(e -> formPanel.clearForm());

        formPanel.getBtnRefresh().addActionListener(e -> refreshTable());

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                loadSelectedAttendance();

            }

        });

    }

    private void loadAttendances() {

        List<Attendance> attendances =
                controller.loadAttendances();

        tablePanel.getTableModel()
                .setAttendances(attendances);

    }

    private void refreshTable() {

        loadAttendances();

        formPanel.clearForm();

    }

    private void addAttendance() {

        if (!validateForm()) {

            return;

        }

        Attendance attendance = new Attendance();

        attendance.setStudentId(
                formPanel
                        .getStudent()
                        .getStudentId()
        );

        attendance.setCourseId(
                formPanel
                        .getCourse()
                        .getCourseId()
        );

        attendance.setAttendanceDate(
                formPanel
                        .getAttendanceDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
        );

        attendance.setStatus(
                formPanel.getStatus()
        );

        if (controller.saveAttendance(attendance)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Attendance."
            );

        }

    }

    private void updateAttendance() {

        int row = tablePanel
                .getTable()
                .getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an attendance record."
            );

            return;

        }

        if (!validateForm()) {

            return;

        }

        Attendance attendance =
                tablePanel
                        .getTableModel()
                        .getAttendance(row);

        attendance.setStudentId(
                formPanel
                        .getStudent()
                        .getStudentId()
        );

        attendance.setCourseId(
                formPanel
                        .getCourse()
                        .getCourseId()
        );

        attendance.setAttendanceDate(
                formPanel
                        .getAttendanceDate()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
        );

        attendance.setStatus(
                formPanel.getStatus()
        );

        if (controller.updateAttendance(attendance)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Update Attendance."
            );

        }

    }

    private void deleteAttendance() {

        int row = tablePanel
                .getTable()
                .getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an attendance record."
            );

            return;

        }

        Attendance attendance =
                tablePanel
                        .getTableModel()
                        .getAttendance(row);

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected attendance record?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteAttendance(
                attendance.getAttendanceId()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Delete Attendance."
            );

        }

    }


    private void loadSelectedAttendance() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            return;

        }

        Attendance attendance =
                tablePanel
                        .getTableModel()
                        .getAttendance(row);

        if (attendance == null) {

            return;

        }

        formPanel.setAttendance(attendance);

    }

    private boolean validateForm() {

        if (formPanel.getStudent() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Student."
            );

            return false;

        }

        if (formPanel.getCourse() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a Course."
            );

            return false;

        }

        if (formPanel.getAttendanceDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select Attendance Date."
            );

            return false;

        }

        if (formPanel.getStatus() == null
                || formPanel.getStatus().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select Attendance Status."
            );

            return false;

        }

        return true;

    }
}