package org.example.ui.course;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class CourseTablePanel extends JPanel {

    private final ModernTable table;

    private final CourseTableModel tableModel;

    public CourseTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new CourseTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public CourseTableModel getTableModel() {

        return tableModel;

    }

}