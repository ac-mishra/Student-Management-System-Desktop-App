package org.example.ui.faculty;

import net.miginfocom.swing.MigLayout;
import org.example.model.Department;
import org.example.model.Faculty;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FacultyFormPanel extends JPanel {

    private FormLabel lblFacultyName;
    private FormLabel lblEmail;
    private FormLabel lblPhone;
    private FormLabel lblDepartment;

    private RoundedTextField txtFacultyName;
    private RoundedTextField txtEmail;
    private RoundedTextField txtPhone;

    private JComboBox<Department> cmbDepartment;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;

    public FacultyFormPanel() {

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadDepartments();

        clearForm();

    }
    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        lblFacultyName = new FormLabel("Faculty Name");

        lblEmail = new FormLabel("Email");

        lblPhone = new FormLabel("Phone");

        lblDepartment = new FormLabel("Department");

        txtFacultyName = new RoundedTextField(20);

        txtEmail = new RoundedTextField(20);

        txtPhone = new RoundedTextField(20);

        cmbDepartment = new JComboBox<>();

        btnAdd = new SuccessButton("Add");

        btnUpdate = new WarningButton("Update");

        btnDelete = new DangerButton("Delete");

        btnClear = new SecondaryButton("Clear");

        btnRefresh = new PrimaryButton("Refresh");

    }
    private void initializeLayout() {

        setLayout(new MigLayout(
                "fillx,insets 20",
                "[right][grow,fill]",
                ""
        ));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AppColors.BORDER),
                "Faculty Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblFacultyName);

        add(txtFacultyName, "growx,wrap");

        add(lblEmail);

        add(txtEmail, "growx,wrap");

        add(lblPhone);

        add(txtPhone, "growx,wrap");

        add(lblDepartment);

        add(cmbDepartment, "growx,wrap 20");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        0
                )
        );

        buttonPanel.setOpaque(false);

        buttonPanel.add(btnAdd);

        buttonPanel.add(btnUpdate);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnClear);

        buttonPanel.add(btnRefresh);

        add(buttonPanel, "span 2,center");

    }
    private void registerEvents() {

        btnClear.addActionListener(e -> clearForm());

        btnRefresh.addActionListener(e -> loadDepartments());

    }

    private void loadDepartments() {

        cmbDepartment.removeAllItems();

        /*
         * Temporary Data
         * Later this will come from DepartmentService
         */

        Department d1 = new Department();
        d1.setDepartmentId(1);
        d1.setDepartmentName("Computer Science");

        Department d2 = new Department();
        d2.setDepartmentId(2);
        d2.setDepartmentName("Information Technology");

        Department d3 = new Department();
        d3.setDepartmentId(3);
        d3.setDepartmentName("Mechanical");

        cmbDepartment.addItem(d1);

        cmbDepartment.addItem(d2);

        cmbDepartment.addItem(d3);

    }

    public void clearForm() {

        txtFacultyName.setText("");

        txtEmail.setText("");

        txtPhone.setText("");

        if (cmbDepartment.getItemCount() > 0) {

            cmbDepartment.setSelectedIndex(0);

        }

        txtFacultyName.requestFocus();

    }
    public void setFaculty(Faculty faculty) {

        if (faculty == null) {

            clearForm();

            return;

        }

        txtFacultyName.setText(
                faculty.getFacultyName()
        );

        txtEmail.setText(
                faculty.getEmail()
        );

        txtPhone.setText(
                faculty.getPhone()
        );

        for (int i = 0; i < cmbDepartment.getItemCount(); i++) {

            Department department = cmbDepartment.getItemAt(i);

            if (department.getDepartmentId() ==
                    faculty.getDepartmentId()) {

                cmbDepartment.setSelectedIndex(i);

                break;

            }

        }

    }
    public String getFacultyName() {

        return txtFacultyName.getText().trim();

    }

    public String getEmail() {

        return txtEmail.getText().trim();

    }

    public String getPhone() {

        return txtPhone.getText().trim();

    }

    public Department getDepartment() {

        return (Department) cmbDepartment.getSelectedItem();

    }

    public RoundedTextField getTxtFacultyName() {

        return txtFacultyName;

    }

    public RoundedTextField getTxtEmail() {

        return txtEmail;

    }

    public RoundedTextField getTxtPhone() {

        return txtPhone;

    }

    public JComboBox<Department> getCmbDepartment() {

        return cmbDepartment;

    }

    public SuccessButton getBtnAdd() {

        return btnAdd;

    }

    public WarningButton getBtnUpdate() {

        return btnUpdate;

    }

    public DangerButton getBtnDelete() {

        return btnDelete;

    }

    public SecondaryButton getBtnClear() {

        return btnClear;

    }

    public PrimaryButton getBtnRefresh() {

        return btnRefresh;

    }

}