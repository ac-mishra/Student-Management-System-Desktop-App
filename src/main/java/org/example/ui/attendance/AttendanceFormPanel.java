package org.example.ui.attendance;

import net.miginfocom.swing.MigLayout;
import org.example.model.Attendance;
import org.example.model.Course;
import org.example.model.Student;
import org.example.ui.component.*;
import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.ZoneId;
import java.util.Date;

public class AttendanceFormPanel extends JPanel {

    private FormLabel lblStudent;
    private FormLabel lblCourse;
    private FormLabel lblAttendanceDate;
    private FormLabel lblStatus;

    private JComboBox<Student> cmbStudent;
    private JComboBox<Course> cmbCourse;
    private JComboBox<String> cmbStatus;

    private JSpinner spAttendanceDate;

    private SuccessButton btnAdd;
    private WarningButton btnUpdate;
    private DangerButton btnDelete;
    private SecondaryButton btnClear;
    private PrimaryButton btnRefresh;

    public AttendanceFormPanel() {

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

        lblAttendanceDate = new FormLabel("Attendance Date");

        lblStatus = new FormLabel("Status");

        cmbStudent = new JComboBox<>();

        cmbCourse = new JComboBox<>();

        cmbStatus = new JComboBox<>();

        cmbStatus.addItem("Present");
        cmbStatus.addItem("Absent");
        cmbStatus.addItem("Late");

        spAttendanceDate = new JSpinner(
                new SpinnerDateModel()
        );

        JSpinner.DateEditor editor =
                new JSpinner.DateEditor(
                        spAttendanceDate,
                        "dd-MM-yyyy"
                );

        spAttendanceDate.setEditor(editor);

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
                "Attendance Information",
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));

        add(lblStudent);
        add(cmbStudent, "growx,wrap");

        add(lblCourse);
        add(cmbCourse, "growx,wrap");

        add(lblAttendanceDate);
        add(spAttendanceDate, "growx,wrap");

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

        add(buttonPanel, "span 2,center");

    }
    private void registerEvents() {

        btnClear.addActionListener(e -> clearForm());

        btnRefresh.addActionListener(e -> {

            loadStudents();

            loadCourses();

        });

    }

    private void loadStudents() {

        cmbStudent.removeAllItems();

        /*
         * Temporary Data
         * Later replace with StudentService.
         */

        Student s1 = new Student();
        s1.setStudentId(1);
        s1.setRollNo("CS24001");
        s1.setFirstName("Amrit");
        s1.setLastName("Mishra");

        Student s2 = new Student();
        s2.setStudentId(2);
        s2.setRollNo("CS24002");
        s2.setFirstName("Rahul");
        s2.setLastName("Kumar");

        cmbStudent.addItem(s1);
        cmbStudent.addItem(s2);

    }

    private void loadCourses() {

        cmbCourse.removeAllItems();

        /*
         * Temporary Data
         * Later replace with CourseService.
         */

        Course c1 = new Course();
        c1.setCourseId(1);
        c1.setCourseCode("CS101");
        c1.setCourseName("Programming in Java");

        Course c2 = new Course();
        c2.setCourseId(2);
        c2.setCourseCode("CS102");
        c2.setCourseName("Database Management");

        cmbCourse.addItem(c1);
        cmbCourse.addItem(c2);

    }

    public void clearForm() {

        if (cmbStudent.getItemCount() > 0) {

            cmbStudent.setSelectedIndex(0);

        }

        if (cmbCourse.getItemCount() > 0) {

            cmbCourse.setSelectedIndex(0);

        }

        cmbStatus.setSelectedIndex(0);

        spAttendanceDate.setValue(new Date());

        cmbStudent.requestFocus();

    }
    public void setAttendance(Attendance attendance) {

        if (attendance == null) {

            clearForm();

            return;

        }

        for (int i = 0; i < cmbStudent.getItemCount(); i++) {

            Student student = cmbStudent.getItemAt(i);

            if (student.getStudentId() ==
                    attendance.getStudentId()) {

                cmbStudent.setSelectedIndex(i);

                break;

            }

        }

        for (int i = 0; i < cmbCourse.getItemCount(); i++) {

            Course course = cmbCourse.getItemAt(i);

            if (course.getCourseId() ==
                    attendance.getCourseId()) {

                cmbCourse.setSelectedIndex(i);

                break;

            }

        }

        cmbStatus.setSelectedItem(
                attendance.getStatus()
        );

        spAttendanceDate.setValue(
                java.sql.Date.valueOf(
                        attendance.getAttendanceDate()
                )
        );

    }

    public Student getStudent() {

        return (Student) cmbStudent.getSelectedItem();

    }

    public Course getCourse() {

        return (Course) cmbCourse.getSelectedItem();

    }

    public String getStatus() {

        return (String) cmbStatus.getSelectedItem();

    }

    public Date getAttendanceDate() {

        return (Date) spAttendanceDate.getValue();

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