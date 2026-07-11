package org.example.ui.marks;

import org.example.model.Marks;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MarksPanel extends JPanel {

    private final MarksFormPanel formPanel;

    private final MarksTablePanel tablePanel;

    private final MarksController controller;

    public MarksPanel() {

        controller = new MarksController();

        formPanel = new MarksFormPanel();

        tablePanel = new MarksTablePanel();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadMarks();

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
                        "Marks Records"
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

        formPanel.getBtnAdd().addActionListener(
                e -> addMarks()
        );

        formPanel.getBtnUpdate().addActionListener(
                e -> updateMarks()
        );

        formPanel.getBtnDelete().addActionListener(
                e -> deleteMarks()
        );

        formPanel.getBtnClear().addActionListener(
                e -> formPanel.clearForm()
        );

        formPanel.getBtnRefresh().addActionListener(
                e -> refreshTable()
        );

        tablePanel.getTable()
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        loadSelectedMarks();

                    }

                });

    }

    private void loadMarks() {

        List<Marks> marksList =
                controller.loadMarks();

        tablePanel.getTableModel()
                .setMarks(marksList);

    }

    private void refreshTable() {

        loadMarks();

        formPanel.clearForm();

    }

    private void addMarks() {

        if (!validateForm()) {

            return;

        }

        Marks marks = new Marks();

        marks.setStudentId(
                formPanel
                        .getStudent()
                        .getStudentId()
        );

        marks.setCourseId(
                formPanel
                        .getCourse()
                        .getCourseId()
        );

        marks.setSemester(
                formPanel.getSemester()
        );

        marks.setInternalMarks(
                formPanel.getInternalMarks()
        );

        marks.setExternalMarks(
                formPanel.getExternalMarks()
        );

        marks.setTotalMarks(
                formPanel.getTotalMarks()
        );

        marks.setGrade(
                formPanel.getGrade()
        );

        if (controller.saveMarks(marks)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Marks Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Marks."
            );

        }

    }

    private void updateMarks() {

        int row = tablePanel
                .getTable()
                .getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record."
            );

            return;

        }

        if (!validateForm()) {

            return;

        }

        Marks marks =
                tablePanel
                        .getTableModel()
                        .getMarks(row);

        marks.setStudentId(
                formPanel
                        .getStudent()
                        .getStudentId()
        );

        marks.setCourseId(
                formPanel
                        .getCourse()
                        .getCourseId()
        );

        marks.setSemester(
                formPanel.getSemester()
        );

        marks.setInternalMarks(
                formPanel.getInternalMarks()
        );

        marks.setExternalMarks(
                formPanel.getExternalMarks()
        );

        marks.setTotalMarks(
                formPanel.getTotalMarks()
        );

        marks.setGrade(
                formPanel.getGrade()
        );

        if (controller.updateMarks(marks)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Marks Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Update Marks."
            );

        }

    }

    private void deleteMarks() {

        int row = tablePanel
                .getTable()
                .getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record."
            );

            return;

        }

        Marks marks =
                tablePanel
                        .getTableModel()
                        .getMarks(row);

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected marks record?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteMarks(
                marks.getMarksId()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Marks Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Delete Marks."
            );

        }

    }

    private void loadSelectedMarks() {

        int row = tablePanel
                .getTable()
                .getSelectedRow();

        if (row == -1) {

            return;

        }

        Marks marks =
                tablePanel
                        .getTableModel()
                        .getMarks(row);

        if (marks == null) {

            return;

        }

        formPanel.setMarks(marks);

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

        if (formPanel.getInternalMarks() < 0
                || formPanel.getInternalMarks() > 30) {

            JOptionPane.showMessageDialog(
                    this,
                    "Internal Marks must be between 0 and 30."
            );

            return false;

        }

        if (formPanel.getExternalMarks() < 0
                || formPanel.getExternalMarks() > 70) {

            JOptionPane.showMessageDialog(
                    this,
                    "External Marks must be between 0 and 70."
            );

            return false;

        }

        return true;

    }
}