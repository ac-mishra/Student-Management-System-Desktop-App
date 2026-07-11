package org.example.ui.marks;

import org.example.ui.component.ModernTable;

import javax.swing.*;
import java.awt.*;

public class MarksTablePanel extends JPanel {

    private final ModernTable table;

    private final MarksTableModel tableModel;

    public MarksTablePanel() {

        setLayout(new BorderLayout());

        tableModel = new MarksTableModel();

        table = new ModernTable();

        table.setModel(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

    }

    public ModernTable getTable() {

        return table;

    }

    public MarksTableModel getTableModel() {

        return tableModel;

    }

}