package org.example.util;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class TableSearchUtil {

    public static void filter(

            JTable table,

            String keyword

    ) {

        TableRowSorter sorter =

                new TableRowSorter(
                        table.getModel()
                );

        table.setRowSorter(sorter);

        if (keyword == null
                || keyword.isBlank()) {

            sorter.setRowFilter(null);

            return;

        }

        sorter.setRowFilter(

                RowFilter.regexFilter(

                        "(?i)" + keyword

                )

        );

    }

}