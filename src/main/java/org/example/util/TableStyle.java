package org.example.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableStyle {

    private TableStyle() {
    }

    public static void apply(
            JTable table
    ) {

        table.setRowHeight(30);

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();

        renderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < table.getColumnCount();
             i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(renderer);

        }

    }

}