package org.example.ui.department;

import net.miginfocom.swing.MigLayout;
import org.example.model.Department;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DepartmentFormPanel extends JPanel {

    private FormLabel lblDepartmentName;
    private FormLabel lblDepartmentCode;
    private FormLabel lblDescription;

    private RoundedTextField txtDepartmentName;
    private RoundedTextField txtDepartmentCode;

    private JTextArea txtDescription;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;

    public DepartmentFormPanel() {

        initializeComponents();

        initializeLayout();

        registerEvents();

        clearForm();

    }
    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        lblDepartmentName = new FormLabel("Department Name");

        lblDepartmentCode = new FormLabel("Department Code");

        lblDescription = new FormLabel("Description");

        txtDepartmentName = new RoundedTextField(20);

        txtDepartmentCode = new RoundedTextField(20);

        txtDescription = new JTextArea(5,20);

        txtDescription.setLineWrap(true);

        txtDescription.setWrapStyleWord(true);

        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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
                "Department Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblDepartmentName);

        add(txtDepartmentName,"growx,wrap");

        add(lblDepartmentCode);

        add(txtDepartmentCode,"growx,wrap");

        add(lblDescription);

        JScrollPane scrollPane = new JScrollPane(txtDescription);

        add(scrollPane,"growx,h 120!,wrap 20");

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

        add(buttonPanel,"span 2,center");

    }
    private void registerEvents() {

        btnClear.addActionListener(e -> clearForm());

    }

    public void clearForm() {

        txtDepartmentName.setText("");

        txtDepartmentCode.setText("");

        txtDescription.setText("");

        txtDepartmentName.requestFocus();

    }

    public void setDepartment(Department department) {

        if (department == null) {

            clearForm();

            return;

        }

        txtDepartmentName.setText(
                department.getDepartmentName()
        );

        txtDepartmentCode.setText(
                department.getDepartmentCode()
        );

        txtDescription.setText(
                department.getDescription()
        );

    }

    public String getDepartmentName() {

        return txtDepartmentName.getText().trim();

    }

    public String getDepartmentCode() {

        return txtDepartmentCode.getText().trim();

    }

    public String getDescription() {

        return txtDescription.getText().trim();

    }

    public RoundedTextField getTxtDepartmentName() {

        return txtDepartmentName;

    }

    public RoundedTextField getTxtDepartmentCode() {

        return txtDepartmentCode;

    }

    public JTextArea getTxtDescription() {

        return txtDescription;

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