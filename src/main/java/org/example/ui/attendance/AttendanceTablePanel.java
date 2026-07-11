package org.example.ui.attendance;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class AttendanceTablePanel extends JPanel {

    private final ModernTable table;

    private final AttendanceTableModel tableModel;

    public AttendanceTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new AttendanceTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public AttendanceTableModel getTableModel() {

        return tableModel;

    }

}