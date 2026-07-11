package org.example.ui.component;

import org.example.ui.theme.AppColors;
import org.example.ui.theme.AppFonts;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ModernTable extends JTable {

    public ModernTable() {

        initialize();

    }

    private void initialize() {

        setRowHeight(35);

        setFont(AppFonts.TABLE);

        setSelectionBackground(AppColors.PRIMARY);

        setSelectionForeground(Color.WHITE);

        setGridColor(AppColors.BORDER);

        setShowHorizontalLines(true);

        setShowVerticalLines(false);

        setAutoCreateRowSorter(true);

        setFillsViewportHeight(true);

        JTableHeader header = getTableHeader();

        header.setFont(AppFonts.BUTTON);

        header.setBackground(AppColors.PRIMARY);

        header.setForeground(Color.WHITE);

        header.setPreferredSize(new Dimension(0,40));

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        setDefaultRenderer(Object.class, centerRenderer);

    }

}