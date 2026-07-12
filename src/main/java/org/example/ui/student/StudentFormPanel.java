package org.example.ui.student;
import org.example.model.Department;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import org.example.service.DepartmentService;
import org.example.service.impl.DepartmentServiceImpl;

import net.miginfocom.swing.MigLayout;

import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import java.awt.Font;


public class StudentFormPanel extends JPanel {

    private FormLabel lblRollNo;
    private FormLabel lblFirstName;
    private FormLabel lblLastName;
    private FormLabel lblGender;
    private FormLabel lblDepartment;
    private FormLabel lblDob;
    private FormLabel lblEmail;
    private FormLabel lblPhone;
    private FormLabel lblAddress;
    private FormLabel lblAdmissionDate;
    private FormLabel lblStatus;

    private RoundedTextField txtRollNo;
    private RoundedTextField txtFirstName;
    private RoundedTextField txtLastName;
    private RoundedTextField txtEmail;
    private RoundedTextField txtPhone;

    private JTextArea txtAddress;

    private JComboBox<Department> cmbDepartment;    private JComboBox<String> cmbStatus;

    private JRadioButton rbMale;
    private JRadioButton rbFemale;
    private ButtonGroup genderGroup;

    private JSpinner spDob;
    private JSpinner spAdmissionDate;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;
    private final DepartmentService departmentService =
            new DepartmentServiceImpl();


    public StudentFormPanel() {

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadDepartments();

        clearForm();

    }
    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        lblRollNo = new FormLabel("Roll No");

        lblFirstName = new FormLabel("First Name");

        lblLastName = new FormLabel("Last Name");

        lblGender = new FormLabel("Gender");

        lblDepartment = new FormLabel("Department");

        lblDob = new FormLabel("Date of Birth");

        lblEmail = new FormLabel("Email");

        lblPhone = new FormLabel("Phone");

        lblAddress = new FormLabel("Address");

        lblAdmissionDate = new FormLabel("Admission Date");

        lblStatus = new FormLabel("Status");

        txtRollNo = new RoundedTextField(20);

        txtFirstName = new RoundedTextField(20);

        txtLastName = new RoundedTextField(20);

        txtEmail = new RoundedTextField(20);

        txtPhone = new RoundedTextField(20);

        txtAddress = new JTextArea(4,20);

        txtAddress.setLineWrap(true);

        txtAddress.setWrapStyleWord(true);

        txtAddress.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        cmbDepartment = new JComboBox<>();
        cmbStatus = new JComboBox<>();

        cmbStatus.addItem("ACTIVE");

        cmbStatus.addItem("INACTIVE");

        rbMale = new JRadioButton("Male");

        rbFemale = new JRadioButton("Female");

        rbMale.setBackground(AppColors.BACKGROUND);

        rbFemale.setBackground(AppColors.BACKGROUND);

        genderGroup = new ButtonGroup();

        genderGroup.add(rbMale);

        genderGroup.add(rbFemale);

        spDob = new JSpinner(new SpinnerDateModel());

        spDob.setEditor(
                new JSpinner.DateEditor(
                        spDob,
                        "dd-MM-yyyy"
                )
        );

        spAdmissionDate = new JSpinner(new SpinnerDateModel());

        spAdmissionDate.setEditor(
                new JSpinner.DateEditor(
                        spAdmissionDate,
                        "dd-MM-yyyy"
                )
        );

        btnAdd = new SuccessButton("Add");

        btnUpdate = new WarningButton("Update");

        btnDelete = new DangerButton("Delete");

        btnClear = new SecondaryButton("Clear");

        btnRefresh = new PrimaryButton("Refresh");

    }
    private void initializeLayout() {

        setLayout(new MigLayout(
                "fillx,insets 20",
                "[right][grow,fill][right][grow,fill]",
                ""
        ));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AppColors.BORDER),
                "Student Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblRollNo);
        add(txtRollNo, "growx");

        add(lblFirstName);
        add(txtFirstName, "growx,wrap");

        add(lblLastName);
        add(txtLastName, "growx");

        add(lblGender);

        JPanel genderPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT,10,0)
        );

        genderPanel.setOpaque(false);

        genderPanel.add(rbMale);

        genderPanel.add(rbFemale);

        add(genderPanel, "growx,wrap");

        add(lblDepartment);
        add(cmbDepartment, "growx");

        add(lblDob);
        add(spDob, "growx,wrap");

        add(lblEmail);
        add(txtEmail, "growx");

        add(lblPhone);
        add(txtPhone, "growx,wrap");

        add(lblAddress);

        JScrollPane scrollPane = new JScrollPane(txtAddress);

        add(scrollPane,
                "growx,h 90!,wrap");

        add(lblAdmissionDate);
        add(spAdmissionDate, "growx");

        add(lblStatus);
        add(cmbStatus, "growx,wrap 20");

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

        add(buttonPanel,
                "span 4,center");
    }
    private void registerEvents() {

        btnClear.addActionListener(e -> clearForm());

        btnRefresh.addActionListener(e -> {

            loadDepartments();

            clearForm();

        });
    }

    public void clearForm() {

        txtRollNo.setText("");

        txtFirstName.setText("");

        txtLastName.setText("");

        txtEmail.setText("");

        txtPhone.setText("");

        txtAddress.setText("");

        genderGroup.clearSelection();

        if (cmbDepartment.getItemCount() > 0) {

            cmbDepartment.setSelectedIndex(0);

        }

        cmbStatus.setSelectedItem("ACTIVE");

        spDob.setValue(new java.util.Date());

        spAdmissionDate.setValue(new java.util.Date());

        txtRollNo.requestFocus();

    }


    public String getRollNo() {

        return txtRollNo.getText().trim();

    }

    private void loadDepartments() {

        cmbDepartment.removeAllItems();

        try {

            departmentService
                    .getAllDepartments()
                    .forEach(cmbDepartment::addItem);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load departments.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    public String getFirstName() {

        return txtFirstName.getText().trim();

    }

    public String getLastName() {

        return txtLastName.getText().trim();

    }

    public String getGender() {

        if (rbMale.isSelected()) {

            return "Male";

        }

        if (rbFemale.isSelected()) {

            return "Female";

        }

        return "";

    }
    public Department getDepartment() {

        return (Department) cmbDepartment.getSelectedItem();

    }

    public java.util.Date getDob() {

        return (java.util.Date) spDob.getValue();

    }

    public String getEmail() {

        return txtEmail.getText().trim();

    }

    public String getPhone() {

        return txtPhone.getText().trim();

    }

    public String getAddress() {

        return txtAddress.getText().trim();

    }

    public java.util.Date getAdmissionDate() {

        return (java.util.Date) spAdmissionDate.getValue();

    }

    public String getStatus() {

        Object selected = cmbStatus.getSelectedItem();

        return selected == null ? "" : selected.toString();

    }

    public void setStudent(org.example.model.Student student) {

        if (student == null) {

            clearForm();

            return;

        }

        txtRollNo.setText(student.getRollNo());

        txtFirstName.setText(student.getFirstName());

        txtLastName.setText(student.getLastName());

        txtEmail.setText(student.getEmail());

        txtPhone.setText(student.getPhone());

        txtAddress.setText(student.getAddress());

        if ("Male".equalsIgnoreCase(student.getGender())) {

            rbMale.setSelected(true);

        } else if ("Female".equalsIgnoreCase(student.getGender())) {

            rbFemale.setSelected(true);

        } else {

            genderGroup.clearSelection();

        }

        for (int i = 0; i < cmbDepartment.getItemCount(); i++) {

            Department department = cmbDepartment.getItemAt(i);

            if (department.getDepartmentId() == student.getDepartmentId()) {

                cmbDepartment.setSelectedIndex(i);

                break;

            }

        }

        if (student.getDob() != null) {

            spDob.setValue(
                    java.sql.Date.valueOf(student.getDob())
            );

        }

        if (student.getAdmissionDate() != null) {

            spAdmissionDate.setValue(
                    java.sql.Date.valueOf(student.getAdmissionDate())
            );

        }

        cmbStatus.setSelectedItem(student.getStatus());

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

    public RoundedTextField getTxtRollNo() {

        return txtRollNo;

    }

    public RoundedTextField getTxtFirstName() {

        return txtFirstName;

    }

    public RoundedTextField getTxtLastName() {

        return txtLastName;

    }

    public RoundedTextField getTxtEmail() {

        return txtEmail;

    }

    public RoundedTextField getTxtPhone() {

        return txtPhone;

    }

    public JTextArea getTxtAddress() {

        return txtAddress;

    }

    public JComboBox<Department> getCmbDepartment() {

        return cmbDepartment;

    }

    public JComboBox<String> getCmbStatus() {

        return cmbStatus;

    }

}