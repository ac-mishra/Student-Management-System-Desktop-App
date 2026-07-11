package org.example.ui.department;

import org.example.model.Department;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DepartmentPanel extends JPanel {

    private final DepartmentFormPanel formPanel;

    private final DepartmentTablePanel tablePanel;

    private final DepartmentController controller;

    public DepartmentPanel() {

        controller = new DepartmentController();

        formPanel = new DepartmentFormPanel();

        tablePanel = new DepartmentTablePanel();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadDepartments();

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
                        "Department Records"
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

        formPanel.getBtnAdd().addActionListener(e -> addDepartment());

        formPanel.getBtnUpdate().addActionListener(e -> updateDepartment());

        formPanel.getBtnDelete().addActionListener(e -> deleteDepartment());

        formPanel.getBtnClear().addActionListener(e -> formPanel.clearForm());

        formPanel.getBtnRefresh().addActionListener(e -> refreshTable());

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                loadSelectedDepartment();

            }

        });

    }

    private void loadDepartments() {

        List<Department> departments =
                controller.loadDepartments();

        tablePanel.getTableModel()
                .setDepartments(departments);

    }

    private void refreshTable() {

        loadDepartments();

        formPanel.clearForm();

    }
    private void addDepartment() {

        if (!validateForm()) {

            return;

        }

        Department department = new Department();

        department.setDepartmentName(
                formPanel.getDepartmentName()
        );

        department.setDepartmentCode(
                formPanel.getDepartmentCode()
        );

        department.setDescription(
                formPanel.getDescription()
        );

        if (controller.saveDepartment(department)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Department."
            );

        }

    }

    private void updateDepartment() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a department."
            );

            return;

        }

        Department department =
                tablePanel.getTableModel().getDepartment(row);

        department.setDepartmentName(
                formPanel.getDepartmentName()
        );

        department.setDepartmentCode(
                formPanel.getDepartmentCode()
        );

        department.setDescription(
                formPanel.getDescription()
        );

        if (controller.updateDepartment(department)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Update Department."
            );

        }

    }

    private void deleteDepartment() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a department."
            );

            return;

        }

        Department department =
                tablePanel.getTableModel().getDepartment(row);

        int option = JOptionPane.showConfirmDialog(
                this,
                "Delete selected department?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteDepartment(
                department.getDepartmentId()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Delete Department."
            );

        }

    }
    private void loadSelectedDepartment() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            return;

        }

        Department department =
                tablePanel.getTableModel().getDepartment(row);

        if (department == null) {

            return;

        }

        formPanel.setDepartment(department);

    }

    private boolean validateForm() {

        if (formPanel.getDepartmentName().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department Name is required."
            );

            formPanel.getTxtDepartmentName().requestFocus();

            return false;

        }

        if (formPanel.getDepartmentCode().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Department Code is required."
            );

            formPanel.getTxtDepartmentCode().requestFocus();

            return false;

        }

        return true;

    }

}