package org.example.ui.student;
import org.example.model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentPanel extends JPanel {

    private StudentFormPanel formPanel;

    private StudentTablePanel tablePanel;

    private final StudentController controller;

    public StudentPanel() {

        controller = new StudentController();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadStudents();

    }

    private void initializeComponents() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        formPanel = new StudentFormPanel();

        tablePanel = new StudentTablePanel();

    }

    private void initializeLayout() {

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT
        );

        splitPane.setResizeWeight(0.42);

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
                        "Student Records"
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
    private void loadStudents() {

        List<Student> students = controller.loadStudents();

        tablePanel.getTableModel().setStudents(students);

    }

    private void refreshTable() {

        loadStudents();

        formPanel.clearForm();

    }
    private void addStudent() {


        if (!validateForm()) {

            return;

        }

        Student student = new Student();
        student.setRollNo(formPanel.getRollNo());

        student.setFirstName(formPanel.getFirstName());

        student.setLastName(formPanel.getLastName());

        student.setGender(formPanel.getGender());

        student.setEmail(formPanel.getEmail());

        student.setPhone(formPanel.getPhone());

        student.setAddress(formPanel.getAddress());

        student.setStatus(formPanel.getStatus());

        student.setDob(
                formPanel
                        .getDob()
                        .toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate()
        );

        /*
         * Temporary value.
         * Next we'll map the selected department
         * to the real department_id from the database.
         */
        student.setDepartmentId(
                formPanel
                        .getDepartment()
                        .getDepartmentId()
        );

        if (controller.saveStudent(student)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Added Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to Add Student."
            );

        }

    }

    private void updateStudent() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first."
            );

            return;

        }

        Student student =
                tablePanel.getTableModel().getStudent(row);

        student.setRollNo(formPanel.getRollNo());

        student.setFirstName(formPanel.getFirstName());

        student.setLastName(formPanel.getLastName());

        student.setGender(formPanel.getGender());

        student.setEmail(formPanel.getEmail());

        student.setPhone(formPanel.getPhone());

        student.setAddress(formPanel.getAddress());

        student.setStatus(formPanel.getStatus());

        student.setAdmissionDate(
                formPanel
                        .getAdmissionDate()
                        .toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate()
        );

        student.setDepartmentId(1);

        if (controller.updateStudent(student)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Updated Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Update Failed."
            );

        }

    }

    private void deleteStudent() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a student first."
            );

            return;

        }

        Student student =
                tablePanel.getTableModel().getStudent(row);

        int option =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete selected student?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

        if (option != JOptionPane.YES_OPTION) {

            return;

        }

        if (controller.deleteStudent(student.getStudentId())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Deleted Successfully."
            );

            refreshTable();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Delete Failed."
            );

        }

    }
    private void loadSelectedStudent() {

        int row = tablePanel.getTable().getSelectedRow();

        if (row == -1) {

            return;

        }

        Student student =
                tablePanel.getTableModel().getStudent(row);

        if (student == null) {

            return;

        }

        formPanel.setStudent(student);

    }

    private boolean validateForm() {

        if (formPanel.getRollNo().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Roll Number is required."
            );

            formPanel.getTxtRollNo().requestFocus();

            return false;

        }

        if (formPanel.getFirstName().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "First Name is required."
            );

            formPanel.getTxtFirstName().requestFocus();

            return false;

        }

        if (formPanel.getLastName().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Last Name is required."
            );

            formPanel.getTxtLastName().requestFocus();

            return false;

        }

        if (formPanel.getGender().isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select Gender."
            );

            return false;

        }

        if(formPanel.getDepartment()==null){

            JOptionPane.showMessageDialog(
                    this,
                    "Select Department."
            );

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

        return true;

    }
    private void registerEvents() {

        formPanel.getBtnAdd().addActionListener(e -> addStudent());

        formPanel.getBtnUpdate().addActionListener(e -> updateStudent());

        formPanel.getBtnDelete().addActionListener(e -> deleteStudent());

        formPanel.getBtnClear().addActionListener(e -> formPanel.clearForm());

        formPanel.getBtnRefresh().addActionListener(e -> refreshTable());

        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                loadSelectedStudent();

            }

        });

    }

}