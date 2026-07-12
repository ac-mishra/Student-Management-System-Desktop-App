package org.example.ui.marks;

import net.miginfocom.swing.MigLayout;
import org.example.model.Course;
import org.example.model.Marks;
import org.example.model.Student;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import org.example.service.StudentService;
import org.example.service.CourseService;
import org.example.service.impl.StudentServiceImpl;
import org.example.service.impl.CourseServiceImpl;

public class MarksFormPanel extends JPanel {

    private FormLabel lblStudent;
    private FormLabel lblCourse;
    private FormLabel lblSemester;
    private FormLabel lblInternal;
    private FormLabel lblExternal;
    private FormLabel lblTotal;
    private FormLabel lblGrade;

    private JComboBox<Student> cmbStudent;
    private JComboBox<Course> cmbCourse;
    private JComboBox<Integer> cmbSemester;

    private JTextField txtInternal;
    private JTextField txtExternal;
    private JTextField txtTotal;
    private JTextField txtGrade;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;
    private final StudentService studentService =
            new StudentServiceImpl();

    private final CourseService courseService =
            new CourseServiceImpl();

    public MarksFormPanel() {

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadStudents();

        loadCourses();

        clearForm();

    }

    private void initializeComponents() {

        setBackground(AppColors.BACKGROUND);

        lblStudent = new FormLabel("Student");

        lblCourse = new FormLabel("Course");

        lblSemester = new FormLabel("Semester");

        lblInternal = new FormLabel("Internal Marks");

        lblExternal = new FormLabel("External Marks");

        lblTotal = new FormLabel("Total Marks");

        lblGrade = new FormLabel("Grade");

        cmbStudent = new JComboBox<>();

        cmbCourse = new JComboBox<>();

        cmbSemester = new JComboBox<>();

        for (int i = 1; i <= 8; i++) {

            cmbSemester.addItem(i);

        }

        txtInternal = new JTextField();

        txtExternal = new JTextField();

        txtTotal = new JTextField();

        txtGrade = new JTextField();

        txtTotal.setEditable(false);

        txtGrade.setEditable(false);

        btnAdd = new SuccessButton("Add");

        btnUpdate = new WarningButton("Update");

        btnDelete = new DangerButton("Delete");

        btnClear = new SecondaryButton("Clear");

        btnRefresh = new PrimaryButton("Refresh");

    }

    private void initializeLayout() {

        setLayout(new MigLayout(
                "fillx,insets 20",
                "[right][grow,fill]"
        ));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(AppColors.BORDER),
                "Marks Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblStudent);

        add(cmbStudent, "growx,wrap");

        add(lblCourse);

        add(cmbCourse, "growx,wrap");

        add(lblSemester);

        add(cmbSemester, "growx,wrap");

        add(lblInternal);

        add(txtInternal, "growx,wrap");

        add(lblExternal);

        add(txtExternal, "growx,wrap");

        add(lblTotal);

        add(txtTotal, "growx,wrap");

        add(lblGrade);

        add(txtGrade, "growx,wrap 20");

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

        btnRefresh.addActionListener(e -> {

            loadStudents();

            loadCourses();

            clearForm();

        });

        txtInternal.addActionListener(e -> calculateTotal());

        txtExternal.addActionListener(e -> calculateTotal());

        txtInternal.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                calculateTotal();

            }

        });

        txtExternal.addFocusListener(new java.awt.event.FocusAdapter() {

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {

                calculateTotal();

            }

        });

    }

    private void calculateTotal() {

        try {

            double internal = txtInternal.getText().isBlank()
                    ? 0
                    : Double.parseDouble(txtInternal.getText());

            double external = txtExternal.getText().isBlank()
                    ? 0
                    : Double.parseDouble(txtExternal.getText());

            double total = internal + external;

            txtTotal.setText(
                    String.format("%.2f", total)
            );

            txtGrade.setText(
                    calculateGrade(total)
            );

        } catch (NumberFormatException ex) {

            txtTotal.setText("");

            txtGrade.setText("");

        }

    }

    private String calculateGrade(double total) {

        if (total >= 90) {

            return "A+";

        }

        if (total >= 80) {

            return "A";

        }

        if (total >= 70) {

            return "B";

        }

        if (total >= 60) {

            return "C";

        }

        if (total >= 50) {

            return "D";

        }

        return "F";

    }

    private void loadStudents() {

        cmbStudent.removeAllItems();

        try {

            studentService
                    .getAllStudents()
                    .forEach(cmbStudent::addItem);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load students.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    private void loadCourses() {

        cmbCourse.removeAllItems();

        try {

            courseService
                    .getAllCourses()
                    .forEach(cmbCourse::addItem);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load courses.\n" + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

    public void clearForm() {

        if (cmbStudent.getItemCount() > 0) {

            cmbStudent.setSelectedIndex(0);

        }

        if (cmbCourse.getItemCount() > 0) {

            cmbCourse.setSelectedIndex(0);

        }

        cmbSemester.setSelectedIndex(0);

        txtInternal.setText("");

        txtExternal.setText("");

        txtTotal.setText("");

        txtGrade.setText("");

        cmbStudent.requestFocus();

    }

    public void setMarks(Marks marks) {

        if (marks == null) {

            clearForm();

            return;

        }

        for (int i = 0; i < cmbStudent.getItemCount(); i++) {

            Student student = cmbStudent.getItemAt(i);

            if (student.getStudentId() == marks.getStudentId()) {

                cmbStudent.setSelectedIndex(i);

                break;

            }

        }

        for (int i = 0; i < cmbCourse.getItemCount(); i++) {

            Course course = cmbCourse.getItemAt(i);

            if (course.getCourseId() == marks.getCourseId()) {

                cmbCourse.setSelectedIndex(i);

                break;

            }

        }

        cmbSemester.setSelectedItem(
                marks.getSemester()
        );

        txtInternal.setText(
                String.valueOf(
                        marks.getInternalMarks()
                )
        );

        txtExternal.setText(
                String.valueOf(
                        marks.getExternalMarks()
                )
        );

        txtTotal.setText(
                String.valueOf(
                        marks.getTotalMarks()
                )
        );

        txtGrade.setText(
                marks.getGrade()
        );

    }
    public Student getStudent() {

        Object selected = cmbStudent.getSelectedItem();

        return selected instanceof Student
                ? (Student) selected
                : null;

    }

    public Course getCourse() {

        Object selected = cmbCourse.getSelectedItem();

        return selected instanceof Course
                ? (Course) selected
                : null;

    }

    public int getSemester() {

        Integer semester = (Integer) cmbSemester.getSelectedItem();

        return semester == null ? 1 : semester;

    }

    public double getInternalMarks() {

        try {

            return txtInternal.getText().isBlank()
                    ? 0
                    : Double.parseDouble(txtInternal.getText());

        } catch (NumberFormatException ex) {

            return 0;

        }

    }

    public double getExternalMarks() {

        try {

            return txtExternal.getText().isBlank()
                    ? 0
                    : Double.parseDouble(txtExternal.getText());

        } catch (NumberFormatException ex) {

            return 0;

        }

    }

    public double getTotalMarks() {

        if (txtTotal.getText().isBlank()) {

            return 0;

        }

        return Double.parseDouble(
                txtTotal.getText()
        );

    }

    public String getGrade() {

        return txtGrade.getText();

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

    public JComboBox<Student> getCmbStudent() {

        return cmbStudent;

    }

    public JComboBox<Course> getCmbCourse() {

        return cmbCourse;

    }
}