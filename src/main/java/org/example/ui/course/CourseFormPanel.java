package org.example.ui.course;

import net.miginfocom.swing.MigLayout;
import org.example.model.Course;
import org.example.model.Department;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CourseFormPanel extends JPanel {

    private FormLabel lblCourseCode;
    private FormLabel lblCourseName;
    private FormLabel lblCredits;
    private FormLabel lblDepartment;

    private RoundedTextField txtCourseCode;
    private RoundedTextField txtCourseName;

    private JSpinner spCredits;

    private JComboBox<Department> cmbDepartment;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;

    public CourseFormPanel() {

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadDepartments();

        clearForm();

    }
    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        lblCourseCode = new FormLabel("Course Code");

        lblCourseName = new FormLabel("Course Name");

        lblCredits = new FormLabel("Credits");

        lblDepartment = new FormLabel("Department");

        txtCourseCode = new RoundedTextField(20);

        txtCourseName = new RoundedTextField(20);

        spCredits = new JSpinner(
                new SpinnerNumberModel(
                        4,
                        1,
                        10,
                        1
                )
        );

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
                "Course Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblCourseCode);

        add(txtCourseCode, "growx,wrap");

        add(lblCourseName);

        add(txtCourseName, "growx,wrap");

        add(lblCredits);

        add(spCredits, "growx,wrap");

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

        txtCourseCode.setText("");

        txtCourseName.setText("");

        spCredits.setValue(4);

        if (cmbDepartment.getItemCount() > 0) {

            cmbDepartment.setSelectedIndex(0);

        }

        txtCourseCode.requestFocus();

    }
    public void setCourse(Course course) {

        if (course == null) {

            clearForm();

            return;

        }

        txtCourseCode.setText(
                course.getCourseCode()
        );

        txtCourseName.setText(
                course.getCourseName()
        );

        spCredits.setValue(
                course.getCredits()
        );

        for (int i = 0; i < cmbDepartment.getItemCount(); i++) {

            Department department = cmbDepartment.getItemAt(i);

            if (department.getDepartmentId() ==
                    course.getDepartmentId()) {

                cmbDepartment.setSelectedIndex(i);

                break;

            }

        }

    }
    public String getCourseCode() {

        return txtCourseCode.getText().trim();

    }

    public String getCourseName() {

        return txtCourseName.getText().trim();

    }

    public int getCredits() {

        return (Integer) spCredits.getValue();

    }

    public Department getDepartment() {

        return (Department) cmbDepartment.getSelectedItem();

    }

    public RoundedTextField getTxtCourseCode() {

        return txtCourseCode;

    }

    public RoundedTextField getTxtCourseName() {

        return txtCourseName;

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

    public JComboBox<Department> getCmbDepartment() {

        return cmbDepartment;

    }

}