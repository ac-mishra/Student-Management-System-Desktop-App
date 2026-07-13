package org.example.ui.reports;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportsPanel extends JPanel {

    private final ReportsController controller;

    private JComboBox<String> cmbReport;

    private JTextField txtSearch;

    private JButton btnLoad;
    private JButton btnPrint;
    private JButton btnExportExcel;
    private JButton btnExportPDF;

    private JTable table;

    private DefaultTableModel tableModel;

    public ReportsPanel() {

        controller = new ReportsController();

        initializeComponents();

        initializeLayout();

        registerEvents();

        loadReport();

    }

    private void initializeComponents() {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        cmbReport = new JComboBox<>();

        cmbReport.addItem("Students");

        cmbReport.addItem("Departments");

        cmbReport.addItem("Courses");

        cmbReport.addItem("Faculty");

        cmbReport.addItem("Attendance");

        cmbReport.addItem("Marks");

        txtSearch = new JTextField();

        btnLoad = new JButton("Load");

        btnPrint = new JButton("Print");

        btnExportExcel = new JButton("Export Excel");

        btnExportPDF = new JButton("Export PDF");

        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);

        table.setRowHeight(28);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    }

    private void initializeLayout() {

        JPanel topPanel = new JPanel(

                new MigLayout(
                        "fillx,insets 10",
                        "[][grow,fill][][][]"
                )

        );

        topPanel.add(cmbReport, "w 180!");

        topPanel.add(txtSearch);

        topPanel.add(btnLoad);

        topPanel.add(btnPrint);

        topPanel.add(btnExportExcel);

        topPanel.add(btnExportPDF);

        add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void registerEvents() {

        btnLoad.addActionListener(e -> loadReport());

        cmbReport.addActionListener(e -> loadReport());

        btnPrint.addActionListener(e -> {

            try {

                table.print();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(

                        this,

                        ex.getMessage(),

                        "Print Error",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        });

        btnExportExcel.addActionListener(e -> {

            try {

                controller.exportExcel(table);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(

                        this,

                        ex.getMessage(),

                        "Excel Export Error",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        });

        btnExportPDF.addActionListener(e -> {

            try {

                controller.exportPDF(table);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(

                        this,

                        ex.getMessage(),

                        "PDF Export Error",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        });

    }

    private void loadReport() {

        String report =
                String.valueOf(
                        cmbReport.getSelectedItem()
                );
        switch (report) {

            case "Students" ->

                    table.setModel(
                            controller.getStudentReport()
                    );

            case "Departments" ->

                    table.setModel(
                            controller.getDepartmentReport()
                    );

            case "Courses" ->

                    table.setModel(
                            controller.getCourseReport()
                    );

            case "Faculty" ->

                    table.setModel(
                            controller.getFacultyReport()
                    );

            case "Attendance" ->

                    table.setModel(
                            controller.getAttendanceReport()
                    );

            case "Marks" ->

                    table.setModel(
                            controller.getMarksReport()
                    );

        }

    }

}