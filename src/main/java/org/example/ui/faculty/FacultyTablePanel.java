package org.example.ui.faculty;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class FacultyTablePanel extends JPanel {

    private final ModernTable table;

    private final FacultyTableModel tableModel;

    public FacultyTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new FacultyTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public FacultyTableModel getTableModel() {

        return tableModel;

    }

}