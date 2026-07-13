package org.example.ui.department;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class DepartmentTablePanel extends JPanel {

    private final ModernTable table;

    private final DepartmentTableModel tableModel;

    public DepartmentTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new DepartmentTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public DepartmentTableModel getTableModel() {

        return tableModel;

    }

}