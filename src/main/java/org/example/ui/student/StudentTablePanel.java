package org.example.ui.student;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class StudentTablePanel extends JPanel {

    private final ModernTable table;

    private final StudentTableModel tableModel;

    public StudentTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new StudentTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public StudentTableModel getTableModel() {

        return tableModel;

    }

}